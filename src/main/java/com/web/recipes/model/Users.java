package com.web.recipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Users {

    private int userId;
    private String username;
    @JsonIgnore
    private String password;

    public Users(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Users() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
