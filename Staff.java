import java.util.*;
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
        return 0.0f;
    }
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public String getPhone() { return phone; }

    public float getSalary() { return salary; }
}
