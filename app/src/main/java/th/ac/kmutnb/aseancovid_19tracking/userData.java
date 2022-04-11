package th.ac.kmutnb.aseancovid_19tracking;

import java.io.Serializable;

public class userData implements Serializable {
    private String userName;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String sex;
    private String password;
    private int age;

    public userData(){

    }

    public userData(String userName, String name, String email, String phoneNumber, String address, String sex, String password, int age) {
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.sex = sex;
        this.password = password;
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
