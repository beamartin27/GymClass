package com.gym.domain;

import java.time.LocalDate;

public class FitnessProgress {
    private int progressId;
    private int userId;
    private LocalDate workoutDate;
    private String exerciseType; // "BENCH_PRESS", "SQUAT", "DEADLIFT", "RUNNING"
    private double metricValue; // weight lifted, distance, time
    private String metricUnit; // "kg", "km", "minutes", "reps"
    private String notes;

    // Constructor for new progress entry
    public FitnessProgress(int userId, LocalDate workoutDate, String exerciseType,
                           double metricValue, String metricUnit, String notes) {
        this.userId = userId;
        this.workoutDate = workoutDate;
        this.exerciseType = exerciseType;
        this.metricValue = metricValue;
        this.metricUnit = metricUnit;
        this.notes = notes;
    }

    // Constructor for existing progress from database
    public FitnessProgress(int progressId, int userId, LocalDate workoutDate,
                           String exerciseType, double metricValue, String metricUnit, String notes) {
        this.progressId = progressId;
        this.userId = userId;
        this.workoutDate = workoutDate;
        this.exerciseType = exerciseType;
        this.metricValue = metricValue;
        this.metricUnit = metricUnit;
        this.notes = notes;
    }

    // Getters
    public int getProgressId() { return progressId; }
    public int getUserId() { return userId; }
    public LocalDate getWorkoutDate() { return workoutDate; }
    public String getExerciseType() { return exerciseType; }
    public double getMetricValue() { return metricValue; }
    public String getMetricUnit() { return metricUnit; }
    public String getNotes() { return notes; }

    // Setters
    public void setProgressId(int progressId) { this.progressId = progressId; }
    public void setMetricValue(double metricValue) { this.metricValue = metricValue; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getFormattedMetric() {
        return String.format("%.2f %s", metricValue, metricUnit);
    }

    @Override
    public String toString() {
        return "FitnessProgress{" +
                "progressId=" + progressId +
                ", userId=" + userId +
                ", workoutDate=" + workoutDate +
                ", exerciseType='" + exerciseType + '\'' +
                ", metric=" + getFormattedMetric() +
                ", notes='" + notes + '\'' +
                '}';
    }
}