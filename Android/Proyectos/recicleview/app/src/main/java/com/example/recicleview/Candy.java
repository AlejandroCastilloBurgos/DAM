package com.example.recicleview;

// Candy.java
public class Candy {
    private String name;
    private boolean hasNuts;
    private int calories;

    public Candy(String name, boolean hasNuts, int calories) {
        this.name = name;
        this.hasNuts = hasNuts;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public boolean hasNuts() {
        return hasNuts;
    }

    public int getCalories() {
        return calories;
    }
}
