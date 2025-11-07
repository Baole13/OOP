package coffeeshop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    public enum OrderStatus {
        NEW, PROCESSING, COMPLETED, CANCELLED
    }
    
    private int orderId;
    private int customerId;
    private LocalDateTime orderTime;
    private List<OrderItem> items;
    private OrderStatus status;
    private double totalAmount;
    
    public Order(int orderId, int customerId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderTime = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.status = OrderStatus.NEW;
        this.totalAmount = 0.0;
    }
    
    public int getOrderId() { 
        return orderId; 
    }
    public int getCustomerId() { 
        return customerId; 
    }
    public LocalDateTime getOrderTime() { 
        return orderTime; 
    }
    public List<OrderItem> getItems() { 
        return new ArrayList<>(items); 
    }
    public OrderStatus getStatus() { 
        return status; 
    }
    public double getTotalAmount() { 
        return totalAmount; 
    }
    
    public void setStatus(OrderStatus status) { 
        this.status = status; 
    }
    
    public void addItem(OrderItem item) {
        if (item != null) {
            items.add(item);
            recalculateTotal();
        }
    }
    
    public void removeItem(OrderItem item) {
        if (item != null && items.remove(item)) {
            recalculateTotal();
        }
    }
    
    private void recalculateTotal() {
        totalAmount = items.stream().mapToDouble(OrderItem::getSubtotal).sum();
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    public void completeOrder() {
        if (!items.isEmpty()) {
            this.status = OrderStatus.COMPLETED;
        }
    }
    
    public void cancelOrder() {
        this.status = OrderStatus.CANCELLED;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Order #%d (Customer #%d)\n", orderId, customerId));
        sb.append(String.format("Status: %s | Time: %s\n", status, orderTime));
        sb.append("Items:\n");
        for (OrderItem item : items) {
            sb.append("  - ").append(item.toString()).append("\n");
        }
        sb.append(String.format("Total: %s\n", totalAmount));
        return sb.toString();
    }
}
