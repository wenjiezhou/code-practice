package com.apple.jay.util;

import java.util.ArrayList;
import java.util.List;

public enum RunninStatistic {
    INSTANCE;
    
    private RunninStatistic() {};
    private static int numberOfValues;
    private static double sum;
    
    /**
     *  By far, the Welford's method is most accurate and efficient way to compute standard deviation, the following 2 values are derived from Welford's method,
     *  By keep saving m and s, we do not need to save values history
     *  Reference: http://jonisalonen.com/2013/deriving-welfords-method-for-computing-variance/
     */   
    private double m;
    private double s;
    
 
    public void push(double value) {
        numberOfValues++;
        sum += value;
    }
    
    public double mean() {
        return (numberOfValues > 0) ? (sum / numberOfValues) : 0.0;
    }
    
    /**
     * By far, the Welford's method is most accurate and efficient way to compute standard deviation
     * Reference: http://jonisalonen.com/2013/deriving-welfords-method-for-computing-variance/
     * @return standardDeviation
     */
    public double standardDeviation(double value)
    {
        double temM = m;
        m += (value - temM) / numberOfValues;
        s += (value - temM) * (value - m);
        return Math.sqrt(s / numberOfValues);
    }
}
