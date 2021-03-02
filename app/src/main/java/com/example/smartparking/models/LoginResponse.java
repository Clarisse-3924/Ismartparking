package com.example.smartparking.models;

import java.io.Serializable;

public class LoginResponse  implements Serializable {

    private String token;
    private String access;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}
