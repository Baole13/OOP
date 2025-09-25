import java.util.List;
import java.util.Map;

public class Customer {
    private String id;
    private String name;
    private Map<String, String> defaultPreferences;
    private double loyaltyPoint;

  
    public Customer(String id, String name, Map<String, String> defaultPreferences, double loyaltyPoint) {
        this.id = id;
        this.name = name;
        this.defaultPreferences = defaultPreferences;
        this.loyaltyPoint = loyaltyPoint;
    }

    
    public List<Product> viewMenu(Menu menu) {
       
    }

    public void addNote(String note) {
        
    }

    public String addFeedback(String orderId) {
        
    }
}
