package com.example.saint.convertproject;

/**
 * Created by saint on 09.03.2018.
 */

public class LengthSolver {

    //main constant is from millimeter to
    public final static double MM = 1;
    public final static double CM = 0.1;
    public final static double INCH = 0.03937;
    public final static double FEET = 0.00328;
    public final static double YARD = 0.0010936;
    public final static double METER = 0.001;
    public final static double KM = 0.000001;
    public final static double MILE = 0.00000062137;

    public static double converter(double x, double y){
        return y/x;
    }
}
