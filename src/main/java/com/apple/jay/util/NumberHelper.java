package com.apple.jay.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum NumberHelper {
    INSTANCE;
    
    private NumberHelper() {}
    
    public double rounding(double value, int place, RoundingMode mode) {
        return new BigDecimal(value).setScale(place, mode).doubleValue();
    }

}
