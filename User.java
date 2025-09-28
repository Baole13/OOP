
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
    private String created_at;

    public User(String id, String name, String username, String password,String phone, String email, String gender,String dob,String address,String created_at ) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this. gender = gender;
        this.dob = dob;
        this.address = address;
        this.created_at = created_at;
    }

    public void init() {}
    public void login() {}
    public void logout() {}

    public String getId() { 
        return id; 
    }

    public String getName() { 
        return name; 
    }

    public void setName(String name) {
            if (name != null && !name.trim().isEmpty()) {
                this.name = name.trim();
            }
        }
    public String getUsername() { 
        return username; 
    }

    public String getPassword() { 
        return password; 
    }

    public String getPhone() { 
        return phone; 
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            this.phone = phoneNumber.trim();
        }
    }

    public String getEmail() { 
        return email; 
    }

    public void setEmail(String email) {
            if (email != null && email.contains("@")) {
                this.email = email.trim();
            }
        }
    public String getGender() { 
        return gender; 
    }

    public String getDob() { 
        return dob; 
    }

    public String getAddress() { 
        return address; 
    }

    public String getCreatedAt() { 
        return created_at; 
    }
}
