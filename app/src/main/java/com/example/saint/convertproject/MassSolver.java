package com.example.saint.convertproject;

/**
 * Created by saint on 15.03.2018.
 */

public class MassSolver {

    public final static double GRAM = 1;
    public final static double OUNCE = 0.03527;
    public final static double POUND = 0.0022;
    public final static double KG = 0.001;
    public final static double TONNE = 0.000001;

    public static double converter(double x, double y){
        return y/x;
    }
}
