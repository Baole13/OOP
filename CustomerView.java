import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CustomerView {
    private final Scanner scanner;
    private final List<MenuItem> menuItems;
    private final List<Table> tables;
    private final List<Customer> customers;
    private final List<Order> orders;
    private Customer currentCustomer;
    private Order currentOrder;
    private int nextOrderId = 1;
    private int nextCustomerId = 1;

    public CustomerView() {
        this.scanner = new Scanner(System.in);
        this.menuItems = new ArrayList<>();
        this.tables = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.orders = new ArrayList<>();
        initializeData();
    }

    public void start() {
        System.out.println("\n=== CHÀO MỪNG ĐẾN QUÁN CÀ PHÊ ===");
        
        while (true) {
            System.out.println("\n=== MENU KHÁCH HÀNG ===");
            System.out.println("1. Xem thực đơn");
            System.out.println("2. Đặt hàng");
            System.out.println("3. Xem giỏ hàng");
            System.out.println("4. Thanh toán");
            System.out.println("5. Đăng ký khách hàng");
            System.out.println("6. Đăng nhập");
            System.out.println("7. Xem lịch sử đơn hàng");
            System.out.println("8. Thoát");
            System.out.print("Chọn tùy chọn (1-8): ");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> viewMenu();
                case 2 -> placeOrder();
                case 3 -> viewCart();
                case 4 -> checkout();
                case 5 -> registerCustomer();
                case 6 -> loginCustomer();
                case 7 -> viewOrderHistory();
                case 8 -> {
                    System.out.println("Cảm ơn bạn đã ghé thăm! Tạm biệt! 👋");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private void viewMenu() {
        if (menuItems.isEmpty()) {
            System.out.println("\n⚠️ Thực đơn hiện đang trống.");
            return;
        }

        System.out.println("\n=== THỰC ĐƠN ===");
        System.out.printf("%-5s %-25s %-15s %-10s %-40s%n",
                "ID", "Tên món", "Loại", "Giá (VNĐ)", "Mô tả");
        System.out.println("-".repeat(100));

        for (MenuItem item : menuItems) {
            long price = Math.round(item.calculatePrice());
            System.out.printf("%-5d %-25s %-15s %-10d %-40s%n",
                    item.getId(),
                    item.getName(),
                    item.getCategory(),
                    price,
                    item.getDescription());
        }
    }

    private void placeOrder() {
        if (currentOrder == null) {
            System.out.println("\n=== TẠO ĐƠN HÀNG MỚI ===");
            System.out.println("1. Dine-in (Ăn tại quán)");
            System.out.println("2. Takeaway (Mang về)");
            System.out.print("Chọn loại dịch vụ (1-2): ");

            int serviceChoice = getIntInput();
            Order.ServiceType serviceType = (serviceChoice == 1) ? 
                Order.ServiceType.DINE_IN : Order.ServiceType.TAKEAWAY;

            currentOrder = new Order(nextOrderId++, 
                currentCustomer != null ? currentCustomer.getCustomerId() : 0, 
                serviceType);

            if (serviceType == Order.ServiceType.DINE_IN) {
                assignTable();
            }
        }

        viewMenu();
        System.out.print("\nNhập ID món muốn đặt: ");
        int itemId = getIntInput();

        MenuItem selectedItem = findMenuItemById(itemId);
        if (selectedItem == null) {
            System.out.println("❌ Không tìm thấy món có ID này.");
            return;
        }

        System.out.print("Nhập số lượng: ");
        int quantity = getIntInput();

        if (quantity <= 0) {
            System.out.println("❌ Số lượng phải lớn hơn 0.");
            return;
        }

        currentOrder.addItem(selectedItem, quantity);
        System.out.println("✅ Đã thêm " + selectedItem.getName() + " x" + quantity + " vào giỏ hàng!");
    }

    private void assignTable() {
        List<Table> availableTables = tables.stream()
                .filter(t -> t.getStatus() == Table.TableStatus.AVAILABLE)
                .toList();

        if (availableTables.isEmpty()) {
            System.out.println("⚠️ Hiện tại không có bàn trống. Vui lòng chờ hoặc chọn Takeaway.");
            return;
        }

        System.out.println("\n=== BÀN TRỐNG ===");
        System.out.printf("%-8s %-10s%n", "Số bàn", "Sức chứa");
        System.out.println("-".repeat(20));

        for (Table table : availableTables) {
            System.out.printf("%-8d %-10d%n", table.getTableNumber(), table.getCapacity());
        }

        System.out.print("Chọn số bàn: ");
        int tableNumber = getIntInput();

        Table selectedTable = findTableByNumber(tableNumber);
        if (selectedTable != null && selectedTable.getStatus() == Table.TableStatus.AVAILABLE) {
            selectedTable.occupyTable(currentCustomer != null ? currentCustomer.getCustomerId() : 0);
            currentOrder.setTableNumber(tableNumber);
            System.out.println("✅ Đã đặt bàn số " + tableNumber);
        } else {
            System.out.println("❌ Bàn không khả dụng.");
        }
    }

    private void viewCart() {
        if (currentOrder == null || currentOrder.isEmpty()) {
            System.out.println("\n🛒 Giỏ hàng trống.");
            return;
        }

        System.out.println("\n=== GIỎ HÀNG ===");
        System.out.println(currentOrder.toString());
    }

    private void checkout() {
        if (currentOrder == null || currentOrder.isEmpty()) {
            System.out.println("\n🛒 Giỏ hàng trống. Không thể thanh toán.");
            return;
        }

        System.out.println("\n=== THANH TOÁN ===");
        System.out.println(currentOrder.toString());

        System.out.print("\nXác nhận thanh toán? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("y") || confirm.equals("yes")) {
            currentOrder.setStatus(Order.OrderStatus.CONFIRMED);
            orders.add(currentOrder);

            if (currentCustomer != null) {
                currentCustomer.addOrder(currentOrder);
            }

            System.out.println("✅ Đơn hàng đã được xác nhận!");
            System.out.println("Mã đơn hàng: #" + currentOrder.getOrderId());
            System.out.println("Cảm ơn bạn đã đặt hàng! 🙏");

            currentOrder = null;
        } else {
            System.out.println("Đã hủy thanh toán.");
        }
    }

    private void registerCustomer() {
        System.out.println("\n=== ĐĂNG KÝ KHÁCH HÀNG ===");
        
        System.out.print("Nhập tên: ");
        String name = scanner.nextLine().trim();
        
        System.out.print("Nhập email: ");
        String email = scanner.nextLine().trim();
        
        System.out.print("Nhập số điện thoại: ");
        String phone = scanner.nextLine().trim();

        Customer newCustomer = new Customer(nextCustomerId++, name, email, phone);
        customers.add(newCustomer);
        currentCustomer = newCustomer;

        System.out.println("✅ Đăng ký thành công! Chào mừng " + name + "!");
    }

    private void loginCustomer() {
        System.out.println("\n=== ĐĂNG NHẬP ===");
        
        System.out.print("Nhập email: ");
        String email = scanner.nextLine().trim();
        
        System.out.print("Nhập số điện thoại: ");
        String phone = scanner.nextLine().trim();

        Customer foundCustomer = customers.stream()
                .filter(c -> c.getEmail().equals(email) && c.getPhoneNumber().equals(phone))
                .findFirst()
                .orElse(null);

        if (foundCustomer != null) {
            currentCustomer = foundCustomer;
            System.out.println("✅ Đăng nhập thành công! Chào mừng " + foundCustomer.getName() + "!");
        } else {
            System.out.println("❌ Không tìm thấy tài khoản. Vui lòng đăng ký trước.");
        }
    }

    private void viewOrderHistory() {
        if (currentCustomer == null) {
            System.out.println("\n⚠️ Vui lòng đăng nhập để xem lịch sử đơn hàng.");
            return;
        }

        List<Order> customerOrders = orders.stream()
                .filter(o -> o.getCustomerId() == currentCustomer.getCustomerId())
                .toList();

        if (customerOrders.isEmpty()) {
            System.out.println("\n📋 Bạn chưa có đơn hàng nào.");
            return;
        }

        System.out.println("\n=== LỊCH SỬ ĐƠN HÀNG ===");
        for (Order order : customerOrders) {
            System.out.println(order.toString());
            System.out.println("-".repeat(50));
        }
    }

    private void initializeData() {
        // Initialize sample menu items
        menuItems.add(new Coffee(1, "Cà phê đen", 25000, "Coffee", "Cà phê đen truyền thống"));
        menuItems.add(new Coffee(2, "Cà phê sữa", 30000, "Coffee", "Cà phê sữa đặc biệt"));
        menuItems.add(new Coffee(3, "Cappuccino", 45000, "Coffee", "Cappuccino Ý"));
        menuItems.add(new Coffee(4, "Latte", 50000, "Coffee", "Latte thơm ngon"));
        
        MenuItem sandwich = new MenuItem(5, "Bánh mì thịt", "Bánh mì thịt nướng", 35000, "Food") {
            @Override
            public String getItemType() { return "Food"; }
            @Override
            public double calculatePrice() { return getBasePrice(); }
        };
        menuItems.add(sandwich);

        MenuItem cake = new MenuItem(6, "Bánh ngọt", "Bánh ngọt tươi", 25000, "Dessert") {
            @Override
            public String getItemType() { return "Dessert"; }
            @Override
            public double calculatePrice() { return getBasePrice(); }
        };
        menuItems.add(cake);

        // Initialize sample tables
        for (int i = 1; i <= 10; i++) {
            tables.add(new Table(i, 4));
        }

        // Initialize sample customers
        customers.add(new Customer(1, "Nguyễn Văn A", "a@gmail.com", "0901234567"));
        customers.add(new Customer(2, "Trần Thị B", "b@gmail.com", "0912345678"));
    }

    private MenuItem findMenuItemById(int id) {
        return menuItems.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private Table findTableByNumber(int number) {
        return tables.stream()
                .filter(table -> table.getTableNumber() == number)
                .findFirst()
                .orElse(null);
    }

    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("⚠️ Vui lòng nhập số hợp lệ: ");
            }
        }
    }

    public static void main(String[] args) {
        CustomerView customerView = new CustomerView();
        customerView.start();
    }
}
