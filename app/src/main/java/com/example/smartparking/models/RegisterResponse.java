package com.example.smartparking.models;

public class RegisterResponse {
    private String password;
    private String username;
    private  int id;


    public RegisterResponse(String password, String username, int id) {
        this.password = password;
        this.username = username;
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
