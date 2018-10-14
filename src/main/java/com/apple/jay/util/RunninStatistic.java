package com.apple.jay.util;

import java.util.ArrayList;
import java.util.List;

public enum RunninStatistic {
    INSTANCE;
    
    private RunninStatistic() {};
    private int numberOfValues;
    private double sum;
    
    /**
     *  Did not find way to calculate standard deviation correctly without knowing the values, 
     *  By far, the Welford's method is most accurate and efficient way to compute standard deviation, but it require all the values to 
     *  be available while computing standard deviation, thus need a data structure to save the pushed values;
     *  Reference: http://jonisalonen.com/2013/deriving-welfords-method-for-computing-variance/
     */
    private List<Double> list = new ArrayList<>();
    
    public void push(double value) {
        list.add(value);
        numberOfValues++;
        sum += value;
    }
    
    public double mean() {
        return (numberOfValues > 0) ? (sum / numberOfValues) : 0.0;
    }
    
    public double variance() {
        return (numberOfValues > 1) ? sum/(numberOfValues - 1) : 0.0;
    }
    
    /**
     * By far, the Welford's method is most accurate and efficient way to compute standard deviation
     * Reference: http://jonisalonen.com/2013/deriving-welfords-method-for-computing-variance/
     * @return standardDeviation
     */
    public double standardDeviation()
    {
        double m = 0.0;
        double s = 0.0;
        int k = 1;
        for (double value : list) {
            double tmpM = m;
            m += (value - tmpM) / k;
            s += (value - tmpM) * (value - m);
            k++;
        }
        return Math.sqrt(s / (k-1));
    }
}
