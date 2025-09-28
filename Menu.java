import java.util.*;

public class Menu {
    private List<Product> products;

    public Menu() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product p) {}
    public void removeProduct(String id) {}
    public List<Product> findByCategory(String cat) { return null; }
    public List<Product> search(String keyword) { return null; }

    public List<Product> getProducts() { return products; }
}
