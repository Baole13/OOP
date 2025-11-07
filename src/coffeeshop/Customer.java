package coffeeshop;

import java.time.LocalDate;

public class Customer {
    private int customerId;
    private String name;
    private String phone;
    private String email;
    private LocalDate dateJoined;
    private int loyaltyPoints;
    private boolean isActive;
    
    public Customer(int customerId, String name, String phone, String email) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.dateJoined = LocalDate.now();
        this.loyaltyPoints = 0;
        this.isActive = true;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public void addPoints(int points) {
        if (points > 0) loyaltyPoints += points;
    }
    
    public boolean redeemPoints(int points) {
        if (points > 0 && loyaltyPoints >= points) {
            loyaltyPoints -= points;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return String.format("Customer #%d: %s\nPhone: %s | Email: %s\nJoined: %s | Points: %d | Active: %s",
                customerId, name, phone, email, dateJoined, loyaltyPoints, isActive ? "Yes" : "No");
    }
}
