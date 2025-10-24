import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderManager {
    private List<Order> orders = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Table> tables = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n=== QUẢN LÝ ĐƠN HÀNG ===");
            System.out.println("1. Xem tất cả đơn hàng");
            System.out.println("2. Xem đơn hàng đang xử lý");
            System.out.println("3. Cập nhật trạng thái đơn hàng");
            System.out.println("4. Quay lại menu chính");
            System.out.print("Chọn chức năng (1-4): ");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> viewAllOrders();
                case 2 -> viewPendingOrders();
                case 3 -> updateOrderStatus();
                case 4 -> {
                    System.out.println("Quay lại menu chính...");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
            }
        }
    }

    private void viewAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("Không có đơn hàng nào.");
            return;
        }
        displayOrders(orders, "Tất cả đơn hàng");
    }

    
    private void viewPendingOrders() {
        List<Order> pendingOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getStatus() == Order.OrderStatus.PENDING ||
                order.getStatus() == Order.OrderStatus.CONFIRMED ||
                order.getStatus() == Order.OrderStatus.PREPARING) {
                pendingOrders.add(order);
            }
        }
        displayOrders(pendingOrders, "Đơn hàng đang xử lý");
    }

    
    private void displayOrders(List<Order> orders, String title) {
        if (orders.isEmpty()) {
            System.out.println("\nKhông tìm thấy đơn hàng nào.");
            return;
        }

        System.out.println("\n=== " + title + " ===");
        System.out.printf("%-8s %-12s %-15s %-8s %-10s %-12s %-15s%n",
            "Mã đơn", "Khách hàng", "Loại dịch vụ", "Bàn", "Tổng", "Trạng thái", "Thời gian");
        System.out.println("-".repeat(90));

        for (Order order : orders) {
            String customerName = getCustomerNameById(order.getCustomerId());
            String tableInfo = (order.getTableNumber() > 0) ? String.valueOf(order.getTableNumber()) : "N/A";

            System.out.printf("%-8d %-12s %-15s %-8s %,-10.0f %-12s %-15s%n",
                order.getOrderId(),
                customerName,
                order.getServiceType(),
                tableInfo,
                order.getTotalAmount(),
                order.getStatus(),
                order.getOrderTime().format(DateTimeFormatter.ofPattern("MM/dd HH:mm")));
        }
    }

  
    private void updateOrderStatus() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã đơn hàng: ");
        int orderId = sc.nextInt();

        Order order = getOrderById(orderId);
        if (order == null) {
            System.out.println("Không tìm thấy đơn hàng.");
            return;
        }

        System.out.println("\nTrạng thái hiện tại: " + order.getStatus());
        System.out.println("Chọn trạng thái mới:");
        System.out.println("1. PENDING");
        System.out.println("2. CONFIRMED");
        System.out.println("3. PREPARING");
        System.out.println("4. READY");
        System.out.println("5. COMPLETED");
        System.out.println("6. CANCELLED");

        System.out.print("Lựa chọn (1-6): ");
        int choice = sc.nextInt();

        Order.OrderStatus newStatus = switch (choice) {
            case 1 -> Order.OrderStatus.PENDING;
            case 2 -> Order.OrderStatus.CONFIRMED;
            case 3 -> Order.OrderStatus.PREPARING;
            case 4 -> Order.OrderStatus.READY;
            case 5 -> Order.OrderStatus.COMPLETED;
            case 6 -> Order.OrderStatus.CANCELLED;
            default -> null;
        };

        if (newStatus == null) {
            System.out.println("Lựa chọn không hợp lệ.");
            return;
        }

        order.setStatus(newStatus);


        if (newStatus == Order.OrderStatus.COMPLETED &&
            order.getServiceType() == Order.ServiceType.DINE_IN &&
            order.getTableNumber() > 0) {

            Table table = getTableByNumber(order.getTableNumber());
            if (table != null) {
                table.makeAvailable();
                System.out.println("Bàn " + order.getTableNumber() + " đã được giải phóng.");
            }
        }

        System.out.println("Đã cập nhật trạng thái đơn hàng thành: " + newStatus);
    }


    private Order getOrderById(int id) {
        for (Order o : orders) {
            if (o.getOrderId() == id) return o;
        }
        return null;
    }

    private String getCustomerNameById(int id) {
        for (Customer c : customers) {
            if (c.getId() == id) return c.getName();
        }
        return "Không rõ";
    }

    private Table getTableByNumber(int num) {
        for (Table t : tables) {
            if (t.getTableNumber() == num) return t;
        }
        return null;
    }

    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.print("Vui lòng nhập số hợp lệ: ");
            }
        }
    }
}
