package com.example.saint.convertproject;

/**
 * Created by saint on 19.03.2018.
 */

public class Currency {

    private String name;
    private double rate;

    public Currency(String name, double rate){
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }
}
