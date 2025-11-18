package com.gym;

import com.gym.domain.User;
import com.gym.repository.UserRepository;
import com.gym.repository.sqlite.SqliteDatabaseManager;
import com.gym.repository.sqlite.SqliteUserRepository;

public class Main {
    public static void main(String[] args) {
        SqliteDatabaseManager.initializeDatabase();

        System.out.println("\n=== TESTING USER REPOSITORY ===\n");

        UserRepository userRepo = new SqliteUserRepository();

        User admin = new User("admin", "admin123", "admin@gym.com", "ADMIN");
        User member = new User("john_doe", "password123", "john@email.com", "MEMBER");

        userRepo.save(admin);
        userRepo.save(member);

        System.out.println("\n--- Finding user by username ---");
        User found = userRepo.findByUsername("admin");
        System.out.println(found);

        System.out.println("\n--- All users ---");
        userRepo.findAll().forEach(System.out::println);

        System.out.println("\n--- Testing login ---");
        User loggedIn = userRepo.validateLogin("admin", "admin123");
        if (loggedIn != null) {
            System.out.println("Login successful: " + loggedIn.getUsername());
        } else {
            System.out.println("Login failed");
        }

        System.out.println("\nAll tests passed!");
    }
}