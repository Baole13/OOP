
public class OrderItem {
    private MenuItem menuItem;
    private int quantity;
    private String customizations;

    public OrderItem(MenuItem menuItem, int quantity) {
        if (menuItem == null) {
            throw new IllegalArgumentException("Menu trống");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Vui lòng nhập số lượng");
        }
        
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.customizations = "";
    }
    
    
    public OrderItem(MenuItem menuItem, int quantity, String customizations) {
        this(menuItem, quantity);
        this.customizations = customizations != null ? customizations : "";
    }
    
   
    public MenuItem getMenuItem() {
        return menuItem;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public String getCustomizations() {
        return customizations;
    }
    

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
    }
    
    public void setCustomizations(String customizations) {
        this.customizations = customizations != null ? customizations : "";
    }
 
    public double getItemTotal() {
        return menuItem.calculatePrice() * quantity;
    }
    
    public double getUnitPrice() {
        return menuItem.calculatePrice();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        long vnd = Math.round(getItemTotal());
        sb.append(String.format("%s x%3d = %dđ", 
                menuItem.getName(), quantity, vnd));
        
        if (!customizations.isEmpty()) {
            sb.append(String.format(" (Ghi chú: %s)", customizations));
        }
        
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderItem orderItem = (OrderItem) obj;
        return menuItem.getId() == orderItem.menuItem.getId();
    }
    
    @Override
    public int hashCode() {
        return menuItem.hashCode();
    }
}