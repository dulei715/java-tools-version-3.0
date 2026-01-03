package cn.edu.dll.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicCalculation {

    public static Double get2Norm(List<Double> pointA, List<Double> pointB) {
        if (pointA.size() != pointB.size()) {
            throw new RuntimeException("The dimensionality of two points are not equal!");
        }
        int len = pointA.size();
        Double result = 0D;
        for (int i = 0; i < len; i++) {
            result += Math.pow(pointA.get(i)-pointB.get(i), 2);
        }
        return Math.sqrt(result);
    }


}
