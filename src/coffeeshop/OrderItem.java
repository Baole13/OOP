package coffeeshop;

public class OrderItem {
    private int orderItemId;
    private MenuItem menuItem;
    private int quantity;
    private double subtotal;
    
    public OrderItem(int orderItemId, MenuItem menuItem, int quantity) {
        if (menuItem == null || quantity <= 0) {
            throw new IllegalArgumentException("Invalid item or quantity");
        }
        this.orderItemId = orderItemId;
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.subtotal = menuItem.calculatePrice() * quantity;
    }
    
    public int getOrderItemId() {
        return orderItemId;
    }
    
    public MenuItem getMenuItem() {
        return menuItem;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public double getSubtotal() {
        return subtotal;
    }
    
    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
            this.subtotal = menuItem.calculatePrice() * quantity;
        }
    }
    
    public double calculateSubtotal() {
        this.subtotal = menuItem.calculatePrice() * quantity;
        return this.subtotal;
    }
    
    @Override
    public String toString() {
        return String.format("%s x%d - %s", menuItem.getName(), quantity, subtotal);
    }
}
