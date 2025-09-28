import java.util.*;

public class OrderItem {
    private Product product;
    private int quantity;
    private Map<String, Object> options;
    private double unit_price;

    public OrderItem(Product product,int quantity,Map<String, Object> options,double unit_price) {
        this.product = product;
        this.quantity = quantity;
        this.options = options;
        this.unit_price = unit_price;
    }

    public double lineTotal() { return 0.0; }

    public Product getProduct() { return product; }

    public int getQuantity() { return quantity; }

    public Map<String, Object> getOptions() { return options; }

    public double getUnitPrice() { return unit_price; }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
    }
}
