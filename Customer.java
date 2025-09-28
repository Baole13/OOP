//import catalog.Menu;
//import catalog.Product;
import java.util.List;

public class Customer {
    private String id;
    private String name;
    private double loyalty_point;

    public Customer(String id,String name,double loyalty_point) {
        this.id = id;
        this.name = name;
        this.loyalty_point = loyalty_point;
    }

    public List<Product> viewMenu(Menu menu) { return null; }
    public void addNote(String note) {}
    public void addFeedback(String orderId) {}

    
    public String getId() { return id; }
    public String getName() { return name; }
    public double getLoyaltyPoint() { return loyalty_point; }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }
}
