
package model;

/**
 *
 * @author Duy Phuong
 */
public class User {
    private String username;
    private String password;
    private int role;
    private String fullname;
    private String city;
    private String country;
    private String address;
    private String phone;

    public User() {
    }

    public User(String username, String password, int role, String fullname, String city, String country, String address, String phone) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.fullname = fullname;
        this.city = city;
        this.country = country;
        this.address = address;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", role=" + role + ", fullname=" + fullname + '}';
    }
    
}
