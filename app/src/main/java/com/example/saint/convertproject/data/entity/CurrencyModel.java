package com.example.saint.convertproject.data.entity;

/**
 * Created by saint on 19.03.2018.
 */

public class CurrencyModel {

    private String name;
    private double rate;

    public CurrencyModel(String name, double rate){
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
