public class Staff {
    private String id;
    private String name;
    private String phone;
    private String shift;
    private float salary;


    public Staff(String id, String name, String phone, String shift, float salary) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.shift = shift;
        this.salary = salary;
    }

    
    public float calculatorSalary() {
        return this.salary;
    }

    public String setShift(String shift) {
    }
}
