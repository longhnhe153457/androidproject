package com.example.projectnews.model;

public class TaiKhoan {
    private String username;
    private String password;
    private String email;
    private String status;
    private String role;

    public TaiKhoan(String username, String password, String email, String status, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
        this.role = role;
    }

    public TaiKhoan(String username, String email) {
        this.username = username;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
