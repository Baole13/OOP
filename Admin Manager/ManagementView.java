import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ManagementView {
    private final Scanner scanner;

    private final OrderManager orderManager;
    private final TableManager tableManager;
    private final MenuManagement menuManagement;
    private final CustomerManagement customerManagement;

    public ManagementView() {
        this.scanner = new Scanner(System.in);

        this.orderManager = new OrderManager();
        this.tableManager = new TableManager();
        this.menuManagement = new MenuManagement();
        this.customerManagement = new CustomerManagement();
    }

    public void start() {
        System.out.println("\n=== HỆ THỐNG QUẢN LÝ QUÁN CÀ PHÊ ===");

        if (!authenticate()) {
            System.out.println("Truy cập bị từ chối. Sai mật khẩu quản trị.");
            return;
        }

        showMainMenu();
    }

    // Đăng nhập quản trị viên
    private boolean authenticate() {
        System.out.print("Nhập mật khẩu quản trị: ");
        String password = scanner.nextLine().trim();
        return "admin123".equals(password);
    }

    // Menu chính
    private void showMainMenu() {
        while (true) {
            System.out.println("\n=== BẢNG ĐIỀU KHIỂN QUẢN LÝ ===");
            System.out.println("1. Quản lý đơn hàng");
            System.out.println("2. Quản lý bàn");
            System.out.println("3. Quản lý thực đơn");
            System.out.println("4. Quản lý khách hàng");
            System.out.println("5. Trạng thái hệ thống");
            System.out.println("6. Thoát");
            System.out.print("Chọn tùy chọn (1-6): ");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> orderManager.start();
                case 2 -> tableManager.tableManagement();
                case 3 -> menuManagement.menuManagement();
                case 4 -> customerManagement.customerManagement();
                case 5 -> systemStatus();
                case 6 -> {
                    System.out.println("Thoát hệ thống quản lý. Tạm biệt!");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    // Hiển thị trạng thái hệ thống
    private void systemStatus() {
        System.out.println("\n💡 [Trạng thái hệ thống]");
        System.out.println("Thời gian hiện tại: " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        System.out.println("Hệ thống hoạt động ổn định ");
    }

    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Vui lòng nhập số hợp lệ: ");
            }
        }
    }

    public static void main(String[] args) {
        ManagementView managementView = new ManagementView();
        managementView.start();
    }
}
