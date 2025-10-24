public class Coffee extends MenuItem {
    private String size;
    private String roastLevel;
    private boolean hasMilk;
    private boolean hasSugar;
    
    public Coffee(int id, String name, double basePrice, String category, String description) {
        super(id, name, description, basePrice, category);
        this.size = "Medium";
        this.roastLevel = "Medium";
        this.hasMilk = false;
        this.hasSugar = false;
    }
    
    public Coffee(int id, String name, double basePrice, String category, String description, 
                  String size, String roastLevel, boolean hasMilk, boolean hasSugar) {
        super(id, name, description, basePrice, category);
        this.size = size;
        this.roastLevel = roastLevel;
        this.hasMilk = hasMilk;
        this.hasSugar = hasSugar;
    }
    
    @Override
    public String getItemType() {
        return "Coffee";
    }
    
    @Override
    public double calculatePrice() {
        double price = getBasePrice();
    
        switch (size.toLowerCase()) {
            case "small":
                price *= 0.8;
                break;
            case "large":
                price *= 1.3;
                break;
            case "extra large":
                price *= 1.5;
                break;
            default: 
                break;
        }
        
       
        if (hasMilk) {
            price += 5000;
        }
        if (hasSugar) {
            price += 2000; 
        
        return price;
    }
    
    public String getSize() {
        return size;
    }
    
    public void setSize(String size) {
        this.size = size;
    }
    
    public String getRoastLevel() {
        return roastLevel;
    }
    
    public void setRoastLevel(String roastLevel) {
        this.roastLevel = roastLevel;
    }
    
    public boolean hasMilk() {
        return hasMilk;
    }
    
    public void setHasMilk(boolean hasMilk) {
        this.hasMilk = hasMilk;
    }
    
    public boolean hasSugar() {
        return hasSugar;
    }
    
    public void setHasSugar(boolean hasSugar) {
        this.hasSugar = hasSugar;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format("\nSize: %s | Roast: %s", size, roastLevel));
        if (hasMilk) sb.append(" | With Milk");
        if (hasSugar) sb.append(" | With Sugar");
        return sb.toString();
    }
}
}