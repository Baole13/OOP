public class User {
  
    private String id;
    private String name;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String gender;
    private String dob;
    private String address;
    private String createdAt;



    public void login(String username, String password) {
        
    }

    public void logout() {
        System.out.println("User logged out.");
    }

    public void getInfor() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Gender: " + gender);
        System.out.println("Date of Birth: " + dob);
        System.out.println("Address: " + address);
        System.out.println("Created At: " + createdAt);
    }

    public void setInfor(String name, String phone, String email, String gender, String dob, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
    }

    
}
