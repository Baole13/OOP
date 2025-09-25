import java.util.*;

public class Order {
    private String order_id;
    private Costomer customer_id;
    private List<OrderItem> items;
    private OrderStatus status;
    private ServiceType service_type;
    private Integer table_number;
    private String notes;
    private double discount;
    private String statusText;
    private String created_at;

    public void add_item(Product p, int qty, Map options) {
       
    }

    public void remove_item(String product_id) {
       
    }

    public void apply_discount(double amount) {
    
    }

    public double subtotal() {
    
        return 0.0;
    }

    public double tax() {
       
        return 0.0;
    }

    public double total() {
        
        return 0.0;
    }

    public void set_status() {
        
    }
}

