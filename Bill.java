import java.util.*;

public class Bill {
    private String bill_id;
    private Order order;
    private List<String> payments;
    private Date issued_at;

    public Bill(String bill_id,Order order,List<String> payments,Date issued_at) {
        this.bill_id = bill_id;
        this.order = order;
        this.payments = payments;
        this.issued_at = issued_at;
    }

    public void addPayment(String payment) {}
    public boolean isPaid() { return false; }
    public void toPDF() {}
    public void toText() {}

    public String getBillId() { return bill_id; }
    public Order getOrder() { return order; }
    public List<String> getPayments() { return payments; }
    public Date getIssuedAt() { return issued_at; }
}
