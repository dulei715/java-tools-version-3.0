package cn.edu.dll.round;

public class DecimalTool {
    public static double round(double originalData, int precision) {
        double factor = Math.pow(10, precision);
        double enlargeData = originalData * factor;
        enlargeData = Math.round(enlargeData);
        return enlargeData / factor;
    }

    public static void main(String[] args) {
        double a = 0.01;
        double b = 0.07;
//        double a = 0.07;
//        double b = 0.01;
        System.out.println(a/b);
        double result = round(a / b, 3);
//        double result = round(a / b, 2);
        System.out.println(result);
        System.out.println(Math.ceil(result));
    }

}
