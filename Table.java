import java.time.LocalDateTime;

public class Table {
    public enum TableStatus { AVAILABLE, OCCUPIED, RESERVED, OUT_OF_SERVICE }

    private int tableNumber;
    private int capacity;
    private TableStatus status;
    private LocalDateTime reservedTime;

    public Table(int tableNumber, int capacity) {
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.status = TableStatus.AVAILABLE;
    }

    public int getTableNumber() { return tableNumber; }
    public int getCapacity() { return capacity; }
    public TableStatus getStatus() { return status; }

    public void makeAvailable() {
        this.status = TableStatus.AVAILABLE;
        this.reservedTime = null;
    }

    public void occupyTable(int customerId) {
        this.status = TableStatus.OCCUPIED;
    }

    public void reserveTable(LocalDateTime time) {
        this.status = TableStatus.RESERVED;
        this.reservedTime = time;
    }

    public void setOutOfService(String reason) {
        this.status = TableStatus.OUT_OF_SERVICE;
    }
}
