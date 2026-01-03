package cn.edu.dll.math.round;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecimalTool {
    public static double getPrecisionValue(double originalValue, int precision) {
        return new BigDecimal(originalValue).setScale(precision, RoundingMode.HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        double roundB = getPrecisionValue(2.0, 0);
        System.out.println(roundB);
    }

}
