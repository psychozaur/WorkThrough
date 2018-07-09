package com.programming_calendar.workthrough;

public class ProgHours {
    private String date;
    private int color;
    private String job;
    private int hours;

    public ProgHours() {
    }

    public ProgHours(String date, int color, String job, int hours) {
        this.date = date;
        this.color = color;
        this.job = job;
        this.hours = hours;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}