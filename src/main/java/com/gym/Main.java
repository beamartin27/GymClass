package com.gym;

import com.gym.domain.*;
import com.gym.repository.*;
import com.gym.repository.sqlite.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        SqliteDatabaseManager.initializeDatabase();

        System.out.println("\n=== TESTING MULTI-CATEGORY PROGRESS SYSTEM ===\n");

        UserRepository userRepo = new SqliteUserRepository();
        ClassRepository classRepo = new SqliteClassRepository();
        ProgressRepository progressRepo = new SqliteProgressRepository();

        // Create user
        User member = new User("sarah_fit", "password123", "sarah@gym.com", "MEMBER");
        userRepo.save(member);

        // Create classes with arbitrary names
        GymClass bootcamp = new GymClass("Bootcamp Blast", "Mike Thunder",
                "High intensity full body workout", 15, 45, "HIIT");
        classRepo.saveClass(bootcamp);

        GymClass yoga = new GymClass("Zen Flow Yoga", "Luna Peace",
                "Relaxing yoga for mind and body", 20, 60, "YOGA");
        classRepo.saveClass(yoga);

        GymClass strength = new GymClass("Iron Warrior", "Max Steel",
                "Heavy lifting and muscle building", 12, 90, "STRENGTH");
        classRepo.saveClass(strength);

        System.out.println("--- Created Classes ---");
        System.out.println(bootcamp);
        System.out.println(yoga);
        System.out.println(strength);

        // Initialize progress for all categories
        String[] categories = {"CARDIO", "STRENGTH", "FLEXIBILITY", "ENDURANCE", "LEGS", "ARMS", "CORE"};
        for (String category : categories) {
            FitnessProgress progress = new FitnessProgress(member.getUserId(), category, 0);
            progressRepo.save(progress);
        }

        // Define point mappings for each class type
        Map<String, Map<String, Integer>> pointSystem = new HashMap<>();

        Map<String, Integer> hiitPoints = new HashMap<>();
        hiitPoints.put("CARDIO", 70);
        hiitPoints.put("STRENGTH", 60);
        hiitPoints.put("ENDURANCE", 50);
        hiitPoints.put("LEGS", 40);
        pointSystem.put("HIIT", hiitPoints);

        Map<String, Integer> yogaPoints = new HashMap<>();
        yogaPoints.put("FLEXIBILITY", 50);
        yogaPoints.put("CORE", 30);
        yogaPoints.put("STRENGTH", 20);
        pointSystem.put("YOGA", yogaPoints);

        Map<String, Integer> strengthPoints = new HashMap<>();
        strengthPoints.put("STRENGTH", 80);
        strengthPoints.put("ARMS", 60);
        strengthPoints.put("LEGS", 60);
        strengthPoints.put("CORE", 40);
        pointSystem.put("STRENGTH", strengthPoints);

        // Simulate attending "Bootcamp Blast" (HIIT class)
        System.out.println("\n--- Attending: " + bootcamp.getClassName() + " ---");
        Map<String, Integer> earnedPoints = pointSystem.get(bootcamp.getClassType());
        for (Map.Entry<String, Integer> entry : earnedPoints.entrySet()) {
            FitnessProgress progress = progressRepo.findByUserIdAndCategory(member.getUserId(), entry.getKey());
            progress.addPoints(entry.getValue());
            progressRepo.update(progress);
            System.out.println("  +" + entry.getValue() + " " + entry.getKey() + " points");
        }

        // Simulate attending "Zen Flow Yoga"
        System.out.println("\n--- Attending: " + yoga.getClassName() + " ---");
        earnedPoints = pointSystem.get(yoga.getClassType());
        for (Map.Entry<String, Integer> entry : earnedPoints.entrySet()) {
            FitnessProgress progress = progressRepo.findByUserIdAndCategory(member.getUserId(), entry.getKey());
            progress.addPoints(entry.getValue());
            progressRepo.update(progress);
            System.out.println("  +" + entry.getValue() + " " + entry.getKey() + " points");
        }

        // Simulate attending "Iron Warrior"
        System.out.println("\n--- Attending: " + strength.getClassName() + " ---");
        earnedPoints = pointSystem.get(strength.getClassType());
        for (Map.Entry<String, Integer> entry : earnedPoints.entrySet()) {
            FitnessProgress progress = progressRepo.findByUserIdAndCategory(member.getUserId(), entry.getKey());
            progress.addPoints(entry.getValue());
            progressRepo.update(progress);
            System.out.println("  +" + entry.getValue() + " " + entry.getKey() + " points");
        }

        // Show final progress
        System.out.println("\n--- " + member.getUsername() + "'s Fitness Progress ---");
        progressRepo.findByUserId(member.getUserId()).forEach(progress -> {
            if (progress.getTotalPoints() > 0) {
                System.out.println(progress);
            }
        });

        System.out.println("\n✅ Multi-category progress system working!");
    }
}