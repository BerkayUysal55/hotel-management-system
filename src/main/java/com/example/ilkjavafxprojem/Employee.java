package com.example.ilkjavafxprojem;

public class Employee extends Person implements ILoginable {
    private String username;
    private String password;

    public Employee(String name, String id, String username, String password) { //constructor
        super(name, id);
        this.username =username;
        this.password =password;
    }


    public boolean login(String inputUser, String inputPass) {//login
        return username.equals(inputUser) && password.equals(inputPass);
    }


    public void logout() {//logout
        System.out.println(" logged out.");
    }


    public String getUsername() { return username; }//username getter
    public String getPassword() { return password; }//password getter
}