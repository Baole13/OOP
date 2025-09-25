import java.util.*;

class Product {
    private String product_id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String image_url;
    private boolean is_available;

    public Product(String product_id, String name, String description, double price, String category, String image_url, boolean is_available) {
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image_url = image_url;
        this.is_available = is_available;
    }

    public String getProductId() {
        return product_id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return is_available;
    }

    public double calculate_price(Map<String, Double> options) {
        return;
    }

    public void update_price(double new_price) {
        return
    }

    public String toString() {
        return;
    }
}