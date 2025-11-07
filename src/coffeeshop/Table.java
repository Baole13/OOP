package coffeeshop;

import java.time.LocalDateTime;

public class Table {
    public enum TableStatus {
        AVAILABLE, OCCUPIED, RESERVED, OUT_OF_SERVICE
    }
    
    private int tableNumber;
    private int capacity;
    private TableStatus status;
    private int currentCustomerId;
    private LocalDateTime occupiedSince;
    private LocalDateTime reservedUntil;
    private String notes;
    
    public Table(int tableNumber, int capacity) {
        if (tableNumber <= 0) throw new IllegalArgumentException("Table number must be positive");
        if (capacity <= 0) throw new IllegalArgumentException("Table capacity must be positive");
        
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.status = TableStatus.AVAILABLE;
        this.currentCustomerId = -1;
        this.notes = "";
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public TableStatus getStatus() {
        return status;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }

    public int getCurrentCustomerId() {
        return currentCustomerId;
    }

    public void setCurrentCustomerId(int currentCustomerId) {
        this.currentCustomerId = currentCustomerId;
    }

    public LocalDateTime getOccupiedSince() {
        return occupiedSince;
    }

    public void setOccupiedSince(LocalDateTime occupiedSince) {
        this.occupiedSince = occupiedSince;
    }

    public LocalDateTime getReservedUntil() {
        return reservedUntil;
    }

    public void setReservedUntil(LocalDateTime reservedUntil) {
        this.reservedUntil = reservedUntil;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    

}
