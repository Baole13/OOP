import java.util.*;

public class MenuManagement {
    private Scanner scanner = new Scanner(System.in);
    private List<MenuItem> menuItems = new ArrayList<>();
    private int nextId = 1;

    public void menuManagement() {
        while (true) {
            System.out.println("\n=== QUẢN LÝ THỰC ĐƠN ===");
            System.out.println("1. Xem tất cả món");
            System.out.println("2. Thêm món mới");
            System.out.println("3. Cập nhật món");
            System.out.println("4. Xóa món");
            System.out.println("5. Tìm kiếm món");
            System.out.println("6. Quay lại menu chính");
            System.out.print("Chọn chức năng (1-6): ");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> viewAllMenuItems();
                case 2 -> addNewMenuItem();
                case 3 -> updateMenuItem();
                case 4 -> removeMenuItem();
                case 5 -> searchMenuItems();
                case 6 -> {
                    System.out.println("Quay lại menu chính...");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
            }
        }
    }

    
    private void viewAllMenuItems() {
        displayMenuItems(menuItems, "Danh sách món hiện có");
    }

    private void displayMenuItems(List<MenuItem> list, String title) {
        if (list.isEmpty()) {
            System.out.println("\n Không có món nào trong danh sách.");
            return;
        }

        System.out.println("\n=== " + title + " ===");
        System.out.printf("%-5s %-20s %-10s %-15s %-40s%n",
                "ID", "Tên món", "Giá (VNĐ)", "Loại", "Mô tả");
        System.out.println("-".repeat(95));

        for (MenuItem item : list) {
            System.out.printf("%-5d %-20s %-10.0f %-15s %-40s%n",
                    item.getId(),
                    item.getName(),
                    item.getBasePrice(),
                    item.getCategory(),
                    item.getDescription());
        }
    }

    private void addNewMenuItem() {
        System.out.print("Nhập tên món: ");
        String name = scanner.nextLine().trim();

        System.out.print("Nhập giá cơ bản (VNĐ): ");
        double basePrice = getDoubleInput();

        System.out.print("Nhập loại món (vd: Coffee, Food...): ");
        String category = scanner.nextLine().trim();

        System.out.print("Nhập mô tả: ");
        String description = scanner.nextLine().trim();

        System.out.print("Có phải món cà phê không? (y/n): ");
        String isCoffee = scanner.nextLine().trim().toLowerCase();

        MenuItem newItem;
        if (isCoffee.equals("y") || isCoffee.equals("yes")) {
            newItem = new Coffee(nextId++, name, basePrice, category, description);
        } else {
            // Nếu không phải Coffee, tạo một món ăn cơ bản
            newItem = new MenuItem(nextId++, name, description, basePrice, category) {
                @Override
                public String getItemType() {
                    return "Food";
                }

                @Override
                public double calculatePrice() {
                    return getBasePrice();
                }
            };
        }

        menuItems.add(newItem);
        System.out.println("Đã thêm món '" + name + "' thành công!");
    }

    private void updateMenuItem() {
        System.out.print("Nhập ID món cần sửa: ");
        int itemId = getIntInput();

        MenuItem item = findMenuItemById(itemId);
        if (item == null) {
            System.out.println("Không tìm thấy món có ID: " + itemId);
            return;
        }

        System.out.println("\n--- Thông tin hiện tại ---");
        System.out.println("Tên: " + item.getName());
        System.out.println("Giá: " + item.getBasePrice() + " VNĐ");
        System.out.println("Loại: " + item.getCategory());
        System.out.println("Mô tả: " + item.getDescription());

        System.out.print("\nNhập tên mới (Enter để giữ nguyên): ");
        String newName = scanner.nextLine().trim();
        if (!newName.isEmpty()) item.setName(newName);

        System.out.print("Nhập giá mới (-1 để giữ nguyên): ");
        double newPrice = getDoubleInput();
        if (newPrice >= 0) item.setBasePrice(newPrice);

        System.out.print("Nhập loại mới (Enter để giữ nguyên): ");
        String newCategory = scanner.nextLine().trim();
        if (!newCategory.isEmpty()) item.setCategory(newCategory);

        System.out.print("Nhập mô tả mới (Enter để giữ nguyên): ");
        String newDescription = scanner.nextLine().trim();
        if (!newDescription.isEmpty()) item.setDescription(newDescription);

        System.out.println("Cập nhật món thành công!");
    }

  
    private void removeMenuItem() {
        System.out.print("Nhập ID món cần xóa: ");
        int itemId = getIntInput();

        MenuItem item = findMenuItemById(itemId);
        if (item == null) {
            System.out.println("Không tìm thấy món có ID: " + itemId);
            return;
        }

        System.out.print("Bạn có chắc muốn xóa '" + item.getName() + "' không? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("y") || confirm.equals("yes")) {
            menuItems.remove(item);
            System.out.println("Đã xóa món '" + item.getName() + "'.");
        } else {
            System.out.println("Đã hủy thao tác.");
        }
    }

   
    private void searchMenuItems() {
        System.out.print("Nhập từ khóa (tên hoặc loại món): ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        List<MenuItem> results = menuItems.stream()
                .filter(m -> m.getName().toLowerCase().contains(keyword)
                        || m.getCategory().toLowerCase().contains(keyword))
                .toList();

        displayMenuItems(results, "Kết quả tìm kiếm cho: " + keyword);
    }

   
    private MenuItem findMenuItemById(int id) {
        for (MenuItem item : menuItems) {
            if (item.getId() == id) return item;
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
}
