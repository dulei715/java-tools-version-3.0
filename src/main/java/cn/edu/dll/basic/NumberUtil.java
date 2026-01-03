package cn.edu.dll.basic;

import java.text.DecimalFormat;

public class NumberUtil {
    public static boolean isNumber(String str) {
        Double parseValue;
        try {
            parseValue = Double.parseDouble(str);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    public static String toFormatString(Integer value, int digitSize) {
        return String.format("%0"+digitSize+"d", value);
    }

    public static Double roundFormat(Double value, int precision) {
        if (precision < 0) {
            throw new RuntimeException("The precision should not be negative!");
        }
        double randomValue, candidateValue;
        String tag = "0";
        if (precision > 0) {
            tag += ".";
        }
        for (int i = 0; i < precision; i++) {
            tag += "0";
        }
        DecimalFormat decimalFormat = new DecimalFormat(tag);
        return Double.valueOf(decimalFormat.format(value));
    }

    public static void main(String[] args) {
//        String dataA = "-23a";
//        System.out.println(isNumber(dataA));
        Double result = roundFormat(0.060000000000000005, 2);
        System.out.println(result);
    }
}
