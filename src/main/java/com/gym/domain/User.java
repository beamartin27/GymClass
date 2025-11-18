package com.gym.domain;

public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String role;

    // Constructor for new user
    public User(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    // Getters
    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getRole() { return role; }

    // Setters
    public void setUserId(int userId) { this.userId = userId; }

    // Check if admin
    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(role);
    }

    // Check if member
    public boolean isMember() {
        return "MEMBER".equalsIgnoreCase(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}