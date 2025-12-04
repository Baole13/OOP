package coffeeshop.database;

import coffeeshop.model.Order;
import java.util.List;

public class FileOrderRepository implements OrderRepository {

    @Override
    public List<Order> loadOrders() {
        return FileDatabase.loadOrders();
    }

    @Override
    public void saveOrders(List<Order> orders) {
        FileDatabase.saveOrders(orders);
    }

    @Override
    public void addOrder(Order order) {
        FileDatabase.addOrder(order);
    }

    @Override
    public List<Order> getOrdersByUsername(String username) {
        return FileDatabase.getOrdersByUsername(username);
    }

    @Override
    public int getNextOrderId() {
        return FileDatabase.getNextOrderId();
    }
}


