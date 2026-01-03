package cn.edu.dll.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringUtil {
    public static String join(String split, Object ... objects) {
        int i = 0;
        String result = "";
        for (; i < objects.length - 1; i++) {
            result += objects[i] + split;
        }
        result += objects[i];
        return result;
    }

    public static String join(String split, Collection<? extends Object> collection) {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        List<Object> list = new ArrayList<>(collection);
        for (; i < list.size() - 1; i++) {
            stringBuilder.append(list.get(i)).append(split);
        }
        stringBuilder.append(list.get(i));
        return stringBuilder.toString();
    }

    public static String join(String split, Double[] values){
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (; i < values.length - 1; i++) {
            stringBuilder.append(values[i]).append(split);
        }
        stringBuilder.append(values[i]);
        return stringBuilder.toString();
    }

    public static String join(String split, String... strings) {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (; i < strings.length - 1; i++) {
            stringBuilder.append(strings[i]).append(split);
        }
        stringBuilder.append(strings[i]);
        return stringBuilder.toString();
    }
    public static String join(String split, String[] strings, int beginIndex, int endIndex) {
        int i = beginIndex;
        StringBuilder stringBuilder = new StringBuilder();
        for (; i < strings.length - 1 && i < endIndex; i++) {
            stringBuilder.append(strings[i]).append(split);
        }
        stringBuilder.append(strings[i]);
        return stringBuilder.toString();
    }
    public static String join(String split, String[] strings, int beginIndex) {
        int endIndex = strings.length - 1;
        return join(split, strings, beginIndex, endIndex);
    }

    public static String[] getStringArray(String... strs) {
        String[] resultStringArray = new String[strs.length];
        for (int i = 0; i < strs.length; i++) {
            resultStringArray[i] = strs[i];
        }
        return resultStringArray;
    }

    public static List<String> getStringList(String... strs) {
        List<String> resultStringList = new ArrayList<>(strs.length);
        for (String str : strs) {
            resultStringList.add(str);
        }
        return resultStringList;
    }

    public static String[] concatGiveString(String[] strArray, String... addStrs) {
        int size = strArray.length;
        String[] resultStrArray = new String[size];
        StringBuilder stringBuilder;
        for (int i = 0; i < size; i++) {
            stringBuilder = new StringBuilder(strArray[i]);
            for (int j = 0; j < addStrs.length; j++) {
                stringBuilder.append(addStrs[j]);
            }
            resultStrArray[i] = stringBuilder.toString();
        }
        return resultStrArray;
    }

    public static void main(String[] args) {
        Double[] values = new Double[]{
                1.2, 4.2, 6.1
        };
        String result = join(",", values);
        System.out.println(result);
    }

}
