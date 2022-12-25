package com.example.exercise1;

public class Record {
    private String name;
    private int score;
    private String location;


    public Record() { }

    public Record(String name, int score, String location) {
        this.name = name;
        this.score = score;
        this.location= location;
    }

    public String getLocation() {
        return location;
    }

    public Record setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return String.valueOf(score);
    }

    public int getIntScore() {
        return score;
    }

    public Record setName(String name) {
        this.name = name;
        return this;
    }

    public Record setScore(int score) {
        this.score = score;
        return this;
    }
}
