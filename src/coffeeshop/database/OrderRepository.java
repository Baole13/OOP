package coffeeshop.database;

import coffeeshop.model.Order;
import java.util.List;

public interface OrderRepository {

    List<Order> loadOrders();

    void saveOrders(List<Order> orders);

    void addOrder(Order order);

    List<Order> getOrdersByUsername(String username);

    int getNextOrderId();
}


