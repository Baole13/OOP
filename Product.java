import java.util.*;

public class Product {
    private String product_id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String image_url;
    private boolean is_available;

    public Product(String product_id,String name,String description,double price,String category,String image_url,boolean is_available) {
        this.product_id = product_id;
        this.name = name;
        this.description = description; 
        this.price = price;
        this.category = category;
        this.image_url = image_url;
        this.is_available = is_available;
    }

    public double calculatePrice(Map<String, Object> options) {
         return price; 
    }

    public void updatePrice(double new_price) { 
        this.price = new_price; 
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }
    
    public void setDescription(String description) {
        this.description = description != null ? description.trim() : "";
    }
    
    public String getProductId() { return product_id; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public double getPrice() { return price; }

    public String getCategory() { return category; }

    public String getImageUrl() { return image_url; }

    public boolean isAvailable() { return is_available; }
}
