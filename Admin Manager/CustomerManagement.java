import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CustomerManagement {
    private Scanner scanner = new Scanner(System.in);
    private List<Customer> customers = new ArrayList<>();
    private int nextId = 1;

    public void customerManagement() {
        while (true) {
            System.out.println("\n=== QUẢN LÝ KHÁCH HÀNG ===");
            System.out.println("1. Xem tất cả khách hàng");
            System.out.println("2. Tìm kiếm khách hàng");
            System.out.println("3. Xem chi tiết khách hàng");
            System.out.println("4. Cập nhật thông tin khách hàng");
            System.out.println("5. Thống kê khách hàng");
            System.out.println("6. Quay lại menu chính");
            System.out.print("Chọn chức năng (1-6): ");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> viewAllCustomers();
                case 2 -> searchCustomers();
                case 3 -> viewCustomerDetails();
                case 4 -> updateCustomer();
                case 5 -> customerStatistics();
                case 6 -> {
                    System.out.println("Quay lại menu chính...");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
            }
        }
    }


    private void viewAllCustomers() {
        displayCustomers(customers, "Danh sách khách hàng");
    }

    private void displayCustomers(List<Customer> list, String title) {
        if (list.isEmpty()) {
            System.out.println("\nKhông có khách hàng nào trong danh sách.");
            return;
        }

        System.out.println("\n=== " + title + " ===");
        System.out.printf("%-5s %-20s %-25s %-15s %-8s %-12s%n",
                "ID", "Tên khách", "Email", "Số điện thoại", "Đơn hàng", "Điểm thưởng");
        System.out.println("-".repeat(90));

        for (Customer c : list) {
            System.out.printf("%-5d %-20s %-25s %-15s %-8d %-12d%n",
                    c.getCustomerId(),
                    c.getName(),
                    c.getEmail(),
                    c.getPhoneNumber(),
                    c.getTotalOrders(),
                    c.getLoyaltyPoints());
        }
    }

    
    private void searchCustomers() {
        System.out.print("Nhập tên hoặc email khách hàng: ");
        String searchTerm = scanner.nextLine().trim().toLowerCase();

        List<Customer> results = customers.stream()
                .filter(c -> c.getName().toLowerCase().contains(searchTerm)
                        || c.getEmail().toLowerCase().contains(searchTerm))
                .toList();

        displayCustomers(results, "Kết quả tìm kiếm cho: " + searchTerm);
    }

 
    private void viewCustomerDetails() {
        System.out.print("Nhập ID khách hàng: ");
        int customerId = getIntInput();

        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("❌ Không tìm thấy khách hàng có ID này.");
            return;
        }

        System.out.println("\n=== CHI TIẾT KHÁCH HÀNG ===");
        System.out.println("ID: " + customer.getCustomerId());
        System.out.println("Tên: " + customer.getName());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("Số điện thoại: " + customer.getPhoneNumber());
        System.out.println("Ngày đăng ký: " + customer.getRegistrationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Tổng số đơn hàng: " + customer.getTotalOrders());
        System.out.printf("Tổng chi tiêu: %.0f VNĐ%n", customer.getTotalSpent());
        System.out.println("Điểm thưởng: " + customer.getLoyaltyPoints());
    }

    // ====== CẬP NHẬT KHÁCH HÀNG ======
    private void updateCustomer() {
        System.out.print("Nhập ID khách hàng: ");
        int customerId = getIntInput();

        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Không tìm thấy khách hàng.");
            return;
        }

        System.out.println("\n--- Thông tin hiện tại ---");
        System.out.println("Tên: " + customer.getName());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("SĐT: " + customer.getPhoneNumber());

        System.out.print("\nNhập tên mới (Enter để giữ nguyên): ");
        String newName = scanner.nextLine().trim();
        if (!newName.isEmpty()) customer.setName(newName);

        System.out.print("Nhập email mới (Enter để giữ nguyên): ");
        String newEmail = scanner.nextLine().trim();
        if (!newEmail.isEmpty()) customer.setEmail(newEmail);

        System.out.print("Nhập số điện thoại mới (Enter để giữ nguyên): ");
        String newPhone = scanner.nextLine().trim();
        if (!newPhone.isEmpty()) customer.setPhoneNumber(newPhone);

        System.out.println("Cập nhật thông tin thành công!");
    }

    // thống kê khách hàng
    private void customerStatistics() {
        if (customers.isEmpty()) {
            System.out.println("\n⚠️ Chưa có dữ liệu khách hàng để thống kê.");
            return;
        }

        int total = customers.size();
        double totalSpending = customers.stream().mapToDouble(Customer::getTotalSpent).sum();
        Customer topCustomer = customers.stream()
                .max(Comparator.comparingDouble(Customer::getTotalSpent))
                .orElse(null);

        System.out.println("\n=== THỐNG KÊ KHÁCH HÀNG ===");
        System.out.println("Tổng số khách hàng: " + total);
        System.out.printf("Tổng chi tiêu: %.0f VNĐ%n", totalSpending);
        if (topCustomer != null)
            System.out.println("Khách chi tiêu nhiều nhất: " + topCustomer.getName() +
                    " (" + (int) topCustomer.getTotalSpent() + " VNĐ)");
    }

    // ====== HÀM HỖ TRỢ ======
    private Customer findCustomerById(int id) {
        for (Customer c : customers) {
            if (c.getCustomerId() == id) return c;
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

    private double getDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.print("Vui lòng nhập giá trị hợp lệ: ");
            }
        }
    }

    //ví dụ
    public void addSampleCustomers() {
        customers.add(new Customer(nextId++, "Nguyễn Văn A", "a@gmail.com", "0901234567", LocalDate.now().minusDays(10), 3, 1500000, 120));
        customers.add(new Customer(nextId++, "Trần Thị B", "b@gmail.com", "0912345678", LocalDate.now().minusDays(5), 5, 3200000, 200));
        customers.add(new Customer(nextId++, "Lê C", "c@gmail.com", "0987654321", LocalDate.now().minusDays(2), 1, 500000, 50));
    }
}
