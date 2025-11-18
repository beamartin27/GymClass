package com.gym;

import com.gym.domain.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("TESTING ALL DOMAIN ENTITIES...\n");

        // User
        User member = new User("john_dune", "password123", "john@email.com", "MEMBER");
        member.setUserId(1);
        System.out.println("1. " + member);

        // GymClass
        GymClass yoga = new GymClass(
                "Morning Yoga",
                "Sarah Jah",
                "Relaxing yoga",
                20, 60, "YOGA"
        );
        yoga.setClassId(1);
        System.out.println("\n2. " + yoga);

        // ClassSchedule
        ClassSchedule schedule = new ClassSchedule(
                1,
                LocalDate.of(2025, 11, 20),
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                20
        );
        schedule.setScheduleId(1);
        System.out.println("\n3. " + schedule);
        System.out.println("Is full?" + schedule.isFull());

        // Booking
        Booking booking = new Booking(1, 1, "CONFIRMED");
        booking.setBookingId(1);
        System.out.println("\n4. " + booking);
        System.out.println("Is confirmed?" + booking.isConfirmed());

        // Simulate booking
        System.out.println("\nSimulating booking...");
        schedule.decrementSpots();
        System.out.println("Available spots now: " + schedule.getAvailableSpots());

        // FitnessProgress
        FitnessProgress progress = new FitnessProgress(
                1,
                LocalDate.now(),
                "BENCH_PRESS",
                80.5,
                "kg",
                "New personal record!"
        );
        progress.setProgressId(1);
        System.out.println("\n5. " + progress);
        System.out.println("\nAll domain entities working correctly");
    }
}