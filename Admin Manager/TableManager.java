import java.util.*;
import java.time.LocalDateTime;

public class TableManager {
    private final List<Table> tables = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void tableManagement() {
        while (true) {
            System.out.println("\n=== Quản lý bàn ===");
            System.out.println("1. Xem tất cả bàn");
            System.out.println("2. Xem bàn trống");
            System.out.println("3. Xem bàn đang có khách");
            System.out.println("4. Thêm bàn mới");
            System.out.println("5. Cập nhật trạng thái bàn");
            System.out.println("6. Xóa bàn");
            System.out.println("7. Thống kê bàn");
            System.out.println("8. Quay lại menu chính");
            System.out.print("Chọn (1–8): ");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> viewAllTables();
                case 2 -> viewAvailableTables();
                case 3 -> viewOccupiedTables();
                case 4 -> addNewTable();
                case 5 -> updateTableStatus();
                case 6 -> removeTable();
                case 7 -> tableStatistics();
                case 8 -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
            }
        }
    }

 
    private void viewAllTables() {
        displayTables(tables, "Tất cả bàn");
    }

    private void viewAvailableTables() {
        List<Table> available = tables.stream()
                .filter(t -> t.getStatus() == Table.TableStatus.AVAILABLE)
                .toList();
        displayTables(available, "Bàn trống");
    }

 
    private void viewOccupiedTables() {
        List<Table> occupied = tables.stream()
                .filter(t -> t.getStatus() == Table.TableStatus.OCCUPIED)
                .toList();
        displayTables(occupied, "Bàn đang có khách");
    }

    private void addNewTable() {
        System.out.print("Nhập số bàn: ");
        int tableNumber = getIntInput();

        if (getTableByNumber(tableNumber) != null) {
            System.out.println("Bàn số này đã tồn tại!");
            return;
        }

        System.out.print("Nhập sức chứa của bàn: ");
        int capacity = getIntInput();

        Table table = new Table(tableNumber, capacity);
        tables.add(table);

        System.out.println("Thêm bàn thành công!");
    }


    private void updateTableStatus() {
        System.out.print("Nhập số bàn: ");
        int tableNumber = getIntInput();
        Table table = getTableByNumber(tableNumber);

        if (table == null) {
            System.out.println("Không tìm thấy bàn.");
            return;
        }

        System.out.println("\nTrạng thái hiện tại: " + table.getStatus());
        System.out.println("1. AVAILABLE (Trống)");
        System.out.println("2. OCCUPIED (Đang dùng)");
        System.out.println("3. RESERVED (Đã đặt trước)");
        System.out.println("4. OUT_OF_SERVICE (Hỏng)");

        System.out.print("Chọn trạng thái mới (1–4): ");
        int choice = getIntInput();

        switch (choice) {
            case 1 -> table.makeAvailable();
            case 2 -> table.occupyTable(0); // 0 = khách ẩn danh
            case 3 -> table.reserveTable(LocalDateTime.now().plusHours(1));
            case 4 -> table.setOutOfService("Thủ công");
            default -> {
                System.out.println("Lựa chọn không hợp lệ.");
                return;
            }
        }

        System.out.println("Cập nhật trạng thái thành công: " + table.getStatus());
    }

    private void removeTable() {
        System.out.print("Nhập số bàn cần xóa: ");
        int tableNumber = getIntInput();
        Table table = getTableByNumber(tableNumber);

        if (table == null) {
            System.out.println("Không tìm thấy bàn.");
            return;
        }

        if (table.getStatus() == Table.TableStatus.OCCUPIED) {
            System.out.println("Không thể xóa bàn đang có khách.");
            return;
        }

        System.out.print("Bạn có chắc muốn xóa bàn " + tableNumber + "? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("y")) {
            tables.remove(table);
            System.out.println("Đã xóa bàn " + tableNumber);
        } else {
            System.out.println("Hủy thao tác.");
        }
    }

   
    private void tableStatistics() {
        long total = tables.size();
        long available = tables.stream().filter(t -> t.getStatus() == Table.TableStatus.AVAILABLE).count();
        long occupied = tables.stream().filter(t -> t.getStatus() == Table.TableStatus.OCCUPIED).count();
        long reserved = tables.stream().filter(t -> t.getStatus() == Table.TableStatus.RESERVED).count();

        System.out.println("\n=== Thống kê bàn ===");
        System.out.println("Tổng số bàn: " + total);
        System.out.println("Bàn trống: " + available);
        System.out.println("Bàn có khách: " + occupied);
        System.out.println("Bàn đã đặt: " + reserved);

        double occupancyRate = (total > 0) ? (double) occupied / total * 100 : 0;
        System.out.printf("Tỷ lệ sử dụng: %.1f%%%n", occupancyRate);
    }

 
    private void displayTables(List<Table> tables, String title) {
        if (tables.isEmpty()) {
            System.out.println("\nKhông có bàn nào.");
            return;
        }

        System.out.println("\n=== " + title + " ===");
        System.out.printf("%-8s %-10s %-15s%n", "Số bàn", "Sức chứa", "Trạng thái");
        System.out.println("-".repeat(40));

        for (Table t : tables) {
            System.out.printf("%-8d %-10d %-15s%n", t.getTableNumber(), t.getCapacity(), t.getStatus());
        }
    }


    private Table getTableByNumber(int number) {
        for (Table t : tables) {
            if (t.getTableNumber() == number) return t;
        }
        return null;
    }

    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.print("Nhập lại số hợp lệ: ");
            }
        }
    }
}
