package com.example.exercise1;

public class Record {
    private String name;
    private int score;
    //maybe location here


    public Record() { }

    public Record(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return String.valueOf(score);
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
