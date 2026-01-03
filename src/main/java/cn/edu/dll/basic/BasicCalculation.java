package cn.edu.dll.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicCalculation {
    public static double getPrecisionValue(double originalValue, int precision) {
        String formatStr = "%." + precision + "f";
        String doubleStr =  String.format(formatStr, originalValue);
        return Double.valueOf(doubleStr);
    }
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

    public static  <T> List<List<T>> getTransposition(List<List<T>> data) {
        int rowSize = data.size(), colSize = data.get(0).size();
        List<T> tempList;
        List[] listArray = new List[colSize];
        for (int i = 0; i < listArray.length; i++) {
            listArray[i] = new ArrayList();
        }
        for (List<T> dataList : data) {
            for (int i = 0; i < dataList.size(); i++) {
                listArray[i].add(dataList.get(i));
            }
        }
        return Arrays.asList(listArray);
    }
}
