package com.example.foodbank;

public class UserList {

    private String Email;
    private String Fname;
    private String Lname;
    private String Password;

    public UserList(String email, String fname, String lname, String password) {
        this.Email = email;
        this.Fname = fname;
        this.Lname = lname;
        this.Password = password;
    }
public UserList(){

}
    public String getEmail() {
        return Email;
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public String getPassword() {
        return Password;
    }
}
