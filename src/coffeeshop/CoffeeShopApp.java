package coffeeshop;

public class CoffeeShopApp {
    public static void main(String[] args) {
        Coffee coffee = new Coffee(1, "Cappuccino", "Rich foam and espresso", 45000, 
                                   Coffee.CoffeeType.CAPPUCCINO, Coffee.Size.MEDIUM, true);
        coffee.addCustomization("Extra shot");
        coffee.addCustomization("Oat milk");
        
        Customer customer = new Customer(1001, "Le Bao", "0901234567", "baole@123.com");
        
        Order order = new Order(5001, customer.getCustomerId());
        order.addItem(new OrderItem(1, coffee, 2));
        order.completeOrder();
        
        Payment payment = new Payment(7001, order.getOrderId(), Payment.PaymentMethod.CASH, order.getTotalAmount());
        
        Table table = new Table(3, 4);
        
        System.out.println(customer);
        System.out.println(order);
        System.out.println(payment);
        System.out.println(table);
    }
}
