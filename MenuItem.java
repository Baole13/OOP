
public abstract class MenuItem {
    private int id;
    private String name;
    private String description;
    private double price;
    private boolean available;
    private String category;
    
    public MenuItem(int id, String name, String description, double price, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.available = true;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public double getPrice() {
        return price;
    }
    
    public double getBasePrice() {
        return price;
    }
    
    public boolean isAvailable() {
        return available;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }
    
    public void setBasePrice(double price) {
        setPrice(price);
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
   
    public abstract String getItemType();
    
    public double calculatePrice() {
        return this.price;
    }
    
    @Override
    public String toString() {
        long vnd = Math.round(price);
        return String.format("%s - %dđ\n%s", name, vnd, description);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MenuItem menuItem = (MenuItem) obj;
        return id == menuItem.id;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}