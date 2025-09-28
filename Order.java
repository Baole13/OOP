import java.util.*;

public class Order {
    public enum ServiceType {
        DINE_IN, TAKEAWAY
    }

    private String order_id;
    private Customer customer_id;
    private List<OrderItem> items;
    private String status;
    private ServiceType serviceType;
    private String service_type;
    private String notes;
    private double discount;
    private String created_at;
    private int tableNumber;

    public Order(String order_id, Customer customer_id,List<OrderItem> items,String service_type, String notes, double discount,String created_at, int tableNumber, ServiceType serviceType) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.items = items;
        this.service_type = service_type;
        this.notes = notes;
        this.discount = discount;
        this.created_at = created_at;
        this.tableNumber = tableNumber;
        this.service_type = service_type;
    }

    public void addItem(Product p, int qty, Map<String, Object> options) {}
    public void removeItem(String productId) {}
    public void applyDiscount(double amount) {}
    public double subtotal() { return 0.0; }
    public double tax() { return 0.0; }
    public double total() { return 0.0; }
    public void setStatus(String status) { this.status = status; }

    public String getOrderId() { return order_id; }
    public Customer getCustomer() { return customer_id; }
    public List<OrderItem> getItems() { return items; }
    public String getServiceType() { return service_type; }
    public String getNotes() { return notes; }
    public double getDiscount() { return discount; }
    public String getCreatedAt() { return created_at; }

    public void setTableNumber(int tableNumber) {
        if (serviceType == ServiceType.DINE_IN && tableNumber > 0) {
            this.tableNumber = tableNumber;
        }
    }

    public void setDiscount(double discount) {
        if (discount >= 0) {
            this.discount = discount;
            total();
        }
    }
}
