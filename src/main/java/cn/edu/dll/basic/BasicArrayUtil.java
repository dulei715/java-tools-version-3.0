package cn.edu.dll.basic;


import cn.edu.dll.io.print.MyPrint;
import cn.edu.dll.round.DecimalTool;
import cn.edu.dll.struct.pair.BasicPair;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Supplier;

public class BasicArrayUtil {
    public static void setIntArrayTo(int[] element, int value) {
        for (int i = 0; i < element.length; i++) {
            element[i] = value;
        }
    }

    public static void setIntArrayTo(Integer[] element, int value) {
        for (int i = 0; i < element.length; i++) {
            element[i] = value;
        }
    }

    public static void setIntArrayToZero(int[] element) {
        setIntArrayTo(element, 0);
    }

    public static void setIntArrayToZero(Integer[] element) {
        setIntArrayTo(element, 0);
    }

    public static void setIntArrayTo(Integer[][] element, int value) {
        for (int i = 0; i < element.length; i++) {
            for (int j = 0; j < element[0].length; j++) {
                element[i][j] = value;
            }
        }
    }

    public static void setIntArrayTo(int[][] element, int value) {
        for (int i = 0; i < element.length; i++) {
            for (int j = 0; j < element[0].length; j++) {
                element[i][j] = value;
            }
        }
    }

    public static void setDoubleArrayTo(double[] element, double value) {
        for (int i = 0; i < element.length; i++) {
            element[i] = value;
        }
    }

    public static void setDoubleArrayTo(double[][] element, double value) {
        for (int i = 0; i < element.length; i++) {
            for (int j = 0; j < element[0].length; j++) {
                element[i][j] = value;
            }
        }
    }

    public static void setDoubleArrayTo(Double[] element, double value) {
        for (int i = 0; i < element.length; i++) {
            element[i] = value;
        }
    }

    public static void setDoubleArrayTo(Double[][] element, double value) {
        for (int i = 0; i < element.length; i++) {
            for (int j = 0; j < element[0].length; j++) {
                element[i][j] = value;
            }
        }
    }


    public static double getLinearTransformValue(double oldLeftPoint, double oldRightPoint, double oldValue, double newLeftPoint, double newRightPoint) {
        return newLeftPoint + (newRightPoint - newLeftPoint) / (oldRightPoint - oldLeftPoint) * (oldValue - oldLeftPoint);
    }

    public static void fillLinearTransformValue(double leftValue, double rightValue, double[] toBeFilledArray, int leftIndex, int rightIndex, boolean whetherContainLeftValueAndRightValue) {
        int indexDiffer = rightIndex - leftIndex;
        if (indexDiffer < 0 || indexDiffer < 1 && whetherContainLeftValueAndRightValue) {
            throw new RuntimeException("The difference between rightIndex and leftIndex is illegal!");
        }
        int from, end, stepAddition;
        double step, tempValue;
        if (whetherContainLeftValueAndRightValue) {
            toBeFilledArray[leftIndex] = leftValue;
            toBeFilledArray[rightIndex] = rightValue;
            from = leftIndex + 1;
            end = rightIndex - 1;
            stepAddition = 0;
        } else {
            from = leftIndex;
            end = rightIndex;
            stepAddition = 2;
        }
        step = (rightValue - leftValue) / (rightIndex - leftIndex + stepAddition);
        tempValue = leftValue + step;
        for (int index = from; index <= end; index++) {
            toBeFilledArray[index] = tempValue;
            tempValue += step;
        }

    }

    public static void linearTransform(double[] element, double factorA, double constC) {
        for (int i = 0; i < element.length; i++) {
            element[i] = element[i] * factorA + constC;
        }
    }


    public static void linearTransform(Double[] element, double factorA, double constC) {
        for (int i = 0; i < element.length; i++) {
            element[i] = element[i] * factorA + constC;
        }
    }

    public static double[] getLinearTransform(double[] element, double factorA, double constC) {
        double[] result = new double[element.length];
        for (int i = 0; i < element.length; i++) {
            result[i] = element[i] * factorA + constC;
        }
        return result;
    }

    public static Double[] getLinearTransform(Double[] element, double factorA, double constC) {
        Double[] result = new Double[element.length];
        for (int i = 0; i < element.length; i++) {
            result[i] = element[i] * factorA + constC;
        }
        return result;
    }

    public static Double[] getLogTransform(Double[] element, double factorA, double constC) {
        Double[] result = new Double[element.length];
        for (int i = 0; i < element.length; i++) {
            result[i] = Math.log(element[i]) * factorA + constC;
        }
        return result;
    }

    public static Double[][] getLinearTransform(Double[][] element, double factorA, double constC) {
        Double[][] result = new Double[element.length][element[0].length];
        for (int i = 0; i < element.length; i++) {
            for (int j = 0; j < element[0].length; j++) {
                result[i][j] = element[i][j] * factorA + constC;
            }
        }
        return result;
    }

    public static Double[] getLinearTransform(List<Double> element, double factorA, double constC) {
        Double[] result = new Double[element.size()];
        for (int i = 0; i < element.size(); i++) {
            result[i] = element.get(i) * factorA + constC;
        }
        return result;
    }

    public static Double[] getLinearTransformFromIntegerList(List<Integer> element, double factorA, double constC) {
        Double[] result = new Double[element.size()];
        for (int i = 0; i < element.size(); i++) {
            result[i] = element.get(i) * factorA + constC;
        }
        return result;
    }

    public static Double[] getLinearTransformFromIntegerArray(Integer[] elementArray, double factorA, double constC) {
        Double[] result = new Double[elementArray.length];
        for (int i = 0; i < elementArray.length; i++) {
            result[i] = elementArray[i] * factorA + constC;
        }
        return result;
    }


    /**
     * 初始化数组每个值为一个集合。该集合不能是抽象类或接口
     *
     * @param collectionArray
     * @param clazz
     * @param <T>
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T extends Collection> void setToEmptyGroup(T[] collectionArray, Class<T> clazz) throws IllegalAccessException, InstantiationException {

        for (int i = 0; i < collectionArray.length; i++) {
            collectionArray[i] = clazz.newInstance();
        }
    }

    public static <T extends Map> void setToEmptyGroup(T[] mapArray, Class<T> clazz) throws IllegalAccessException, InstantiationException {

        for (int i = 0; i < mapArray.length; i++) {
            mapArray[i] = clazz.newInstance();
        }
    }


    public static <T extends Collection> void setGroupArrayTo(T[] elementArray, T initCollection, Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<T> constructor = clazz.getConstructor(Collection.class);
        for (int i = 0; i < elementArray.length; i++) {
            elementArray[i] = constructor.newInstance(initCollection);
        }
    }

    public static <T extends Map> void setGroupArrayTo(T[] elementArray, T initMap, Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<T> constructor = clazz.getConstructor(Map.class);
        for (int i = 0; i < elementArray.length; i++) {
            elementArray[i] = constructor.newInstance(initMap);
        }
    }

    public static void setIntegerListToContinuousNaturalNumber(List<Integer> list, Integer maxNaturalNumber) {
        for (int i = 0; i <= maxNaturalNumber; i++) {
            list.add(i);
        }
    }

    public static void setDoubleArrayToZero(double[] element) {
        setDoubleArrayTo(element, 0.0);
    }

    public static void setDoubleArrayToZero(double[][] element) {
        setDoubleArrayTo(element, 0.0);
    }

    public static void setDoubleArrayToZero(Double[] element) {
        setDoubleArrayTo(element, 0.0);
    }

    public static void setDoubleArrayToZero(Double[][] element) {
        setDoubleArrayTo(element, 0.0);
    }


    public static Integer[] toIntegerArray(String[] arr) {
        Integer[] result = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = Integer.valueOf(arr[i]);
        }
        return result;
    }


    public static int[] toIntArray(String[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = Integer.parseInt(arr[i]);
        }
        return result;
    }

    public static Double[] toDoubleArray(String[] arr) {
        Double[] result = new Double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = Double.valueOf(arr[i]);
        }
        return result;
    }

    public static double[] toDouArray(String[] arr) {
        double[] result = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = Double.parseDouble(arr[i]);
        }
        return result;
    }

    public static double[] toDouArray(Collection<Double> data) {
        double[] result = new double[data.size()];
        Iterator<Double> iterator = data.iterator();
        for (int i = 0; i < result.length; i++) {
            result[i] = iterator.next();
        }
        return result;
    }


    /**
     * 每个数组元素引用同一个元素
     *
     * @param value
     * @param size
     * @param <T>
     * @return
     */
    public static <T> T[] getInitializedArray(T value, int size) {
        Class clazz = value.getClass();
        Object array = null;
        array = Array.newInstance(clazz, size);
        for (int i = 0; i < size; i++) {
            Array.set(array, i, value);
        }
        T[] result = (T[]) array;
        return result;
    }

    /**
     * 返回初始化为value的List，如果是复杂对象类型，就只返回该对象，不赋值
     * @param value
     * @param size
     * @param <T>
     * @return
     */
    public static <T> List<T> getInitializedList(T value, int size) {
        Class clazz = value.getClass();
        List<T> result = new ArrayList<>();
        Supplier<T> constructor;
        // 为常见类型定义构造方式，只将合适类型的值传入
        if (value instanceof Integer || value instanceof Long || value instanceof Double
                || value instanceof Float || value instanceof Boolean || value instanceof Character || value instanceof String) {
            constructor = () -> value;
        } else {
            constructor = () -> {
                try {
                    return (T) clazz.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("Cannot construct!!!");
                }
            };
        }
        for (int i = 0; i < size; i++) {
            result.add(constructor.get());
        }
        return result;
    }


    public static Integer[] getIncreaseIntegerNumberArray(Integer startValue, int step, Integer maxValue) {
        int size = (maxValue - startValue) / step + 1;
        Integer[] result = new Integer[size];
        for (int value = startValue, i = 0; value <= maxValue; value += step, i++) {
            result[i] = value;
        }
        return result;
    }

    public static double[] getIncreasedoubleNumberArray(double startValue, double step, double maxValue, int precision) {
        int size = (int) Math.ceil((maxValue - startValue) / step + 1);
        double[] result = new double[size];
        double value;
        int i;
        for (value = startValue, i = 0; value <= maxValue; value += step, i++) {
            result[i] = DecimalTool.round(value, precision);
        }
        if (i < size) {
            result[i] = DecimalTool.round(maxValue, precision);
        }
        return result;
    }

    public static Double[] getIncreaseDoubleNumberArray(double startValue, double step, double maxValue) {
        int size = (int) Math.ceil((maxValue - startValue) / step + 1);
        Double[] result = new Double[size];
        double value;
        int i;
        for (value = startValue, i = 0; value <= maxValue; value += step, i++) {
            result[i] = value;
        }
        if (i < size) {
            result[i] = maxValue;
        }
        return result;
    }

    public static List<Integer> getIncreaseIntegerNumberList(Integer startValue, int step, Integer maxValue) {
        int size = (maxValue - startValue) / step + 1;
        List<Integer> result = new ArrayList<>(size);
        for (int value = startValue; value <= maxValue; value += step) {
            result.add(value);
        }
        return result;
    }

    public static List<String> getIncreaseIntegerNumberListAsStringList(Integer startValue, int step, Integer maxValue) {
        int size = (maxValue - startValue) / step + 1;
        List<String> result = new ArrayList<>(size);
        for (int value = startValue; value <= maxValue; value += step) {
            result.add(String.valueOf(value));
        }
        return result;
    }


    public static int getFirstFindValueIndex(int[] data, int value) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static List<Integer> getDeclaredValueIndexList(int[] data, int value) {
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            if (data[i] == value) {
                resultList.add(i);
            }
        }
        return resultList;
    }

    public static <T> List<T> getElementListInGivenIndexes(List<T> elementList, List<Integer> indexList) {
        List<T> result = new ArrayList<>(indexList.size());
        for (Integer index : indexList) {
            result.add(elementList.get(index));
        }
        return result;
    }
    public static <T> List<T> getElementListInGivenIndexes(T[] elementArray, List<Integer> indexList) {
        List<T> result = new ArrayList<>(indexList.size());
        for (Integer index : indexList) {
            result.add(elementArray[index]);
        }
        return result;
    }

    public static Double[] extractSubArrayInGivenIndexList(Double[] data, List<Integer> indexList) {
        Double[] result = new Double[indexList.size()];
        int k = 0;
        for (Integer tempIndex : indexList) {
            result[k++] = data[tempIndex];
        }
        return result;
    }

    public static double[] extractSubArrayInGivenIndexList(double[] data, List<Integer> indexList) {
        double[] result = new double[indexList.size()];
        int k = 0;
        for (int tempIndex : indexList) {
            result[k++] = data[tempIndex];
        }
        return result;
    }

    public static <T> List<T> extractSubListInGivenIndexCollection(List<T> data, Collection<Integer> indexCollection) {
        List<T> result = new ArrayList<>();
        for (Integer index : indexCollection) {
            result.add(data.get(index));
        }
        return result;
    }

    public static <T> int getFirstFindValueIndex(T[] data, T value) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> int getFirstFindValueIndexByGivenColum(T[][] data, int colum, T value) {
        for (int i = 0; i < data.length; i++) {
            if (data[i][colum].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> int getFirstFindValueIndex(List<T> data, T value) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(value)) {
                return i;
            }
        }
        return -1;
    }


    public static <T extends Comparable<T>> BasicPair<Integer, T> getMaxValuePair(T... objs) {
        T result = objs[0];
        Integer index = 0;
        for (int i = 1; i < objs.length; i++) {
            if (result.compareTo(objs[i]) < 0) {
                index = i;
                result = objs[i];
            }
        }
        return new BasicPair<>(index, result);
    }

    public static <T extends Comparable<T>> BasicPair<Integer, T> getMinValuePair(T... objs) {
        T result = objs[0];
        Integer index = 0;
        for (int i = 1; i < objs.length; i++) {
            if (result.compareTo(objs[i]) > 0) {
                index = i;
                result = objs[i];
            }
        }
        return new BasicPair<>(index, result);
    }

    public static Integer getSum(Integer... nums) {
        Integer sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }

    public static Double getSum(Double... nums) {
        Double sum = 0.0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }

    public static Double getDoubleSum(List<Double> nums) {
        Double sum = 0.0;
        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i);
        }
        return sum;
    }

    public static Double getSum(int fromIndex, int size, Double... nums) {
        Double sum = 0.0;
        int endIndex = fromIndex + size;
        for (int i = fromIndex; i < endIndex; ++i) {
            sum += nums[i];
        }
        return sum;
    }


    /**
     * 将两个数组合并成一个列表
     *
     * @param arrA
     * @param arrB
     * @return
     */
    public static List<Integer> getCombinedListOfTwoIntegerArray(int[] arrA, int[] arrB) {
        List<Integer> list = new ArrayList<>(arrA.length + arrB.length);
        for (int i = 0; i < arrA.length; i++) {
            list.add(arrA[i]);
        }
        for (int i = 0; i < arrB.length; i++) {
            list.add(arrB[i]);
        }
        return list;
    }


    public static Double getSumFromGivenIndexSets(List<List<Double>> numberList, Integer rowIndex, Collection<Integer> colIndexSet) {
        Double sum = 0.0;
        if (colIndexSet == null) {
            return sum;
        }
        List<Double> chosenList;
        chosenList = numberList.get(rowIndex);
        for (Integer yIndex : colIndexSet) {
            sum += chosenList.get(yIndex);
        }
        return sum;
    }

    public static Double getSumFromGivenIndexSets(List<List<Double>> numberList, Collection<Integer> rowIndexSet, Integer colIndex) {
        Double sum = 0.0;
        if (rowIndexSet == null) {
            return sum;
        }
        for (Integer xIndex : rowIndexSet) {
            sum += numberList.get(xIndex).get(colIndex);
        }
        return sum;
    }

    public static void setArrayToDeclaredValueByIndexList(int[] arr, List<Integer> indexList, int value) {
        for (Integer index : indexList) {
            arr[index] = value;
        }
    }

    public static int getIndexOfMinimalValueGreaterThanDeclaredValue(List<Integer> data, Integer value) {
        Integer minimalValue = Integer.MAX_VALUE;
        int index = -1;
        Integer tempValue;
        for (int i = 0; i < data.size(); i++) {
            tempValue = data.get(i);
            if (tempValue > value && tempValue < minimalValue) {
                minimalValue = tempValue;
                index = i;
            }
        }
        return index;
    }


    public static List<Integer> getIndexListWithValueGreaterThanDeclaredValue(Double[] data, Integer value) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            if (data[i] > value) {
                result.add(i);
            }
        }
        return result;
    }

    public static List<Integer> getIndexListWithValueGreaterThanDeclaredValue(double[] data, Integer value) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            if (data[i] > value) {
                result.add(i);
            }
        }
        return result;
    }

    public static boolean isAscending(double[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDescending(double[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static int binaryDescendSearch(double[] a, int fromIndex, int toIndex, double key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            double midVal = a[mid];

            if (midVal > key)
                low = mid + 1;  // Neither val is NaN, thisVal is smaller
            else if (midVal < key)
                high = mid - 1; // Neither val is NaN, thisVal is larger
            else {
                long midBits = Double.doubleToLongBits(midVal);
                long keyBits = Double.doubleToLongBits(key);
                if (midBits == keyBits)     // Values are equal
                    return mid;             // Key found
                else if (midBits > keyBits) // (-0.0, 0.0) or (!NaN, NaN)
                    low = mid + 1;
                else                        // (0.0, -0.0) or (NaN, !NaN)
                    high = mid - 1;
            }
        }
        return -(low + 1);  // key not found.
    }

    public static int binaryDescendSearch(double[] a, double key) {
        return binaryDescendSearch(a, 0, a.length, key);
    }

    /**
     * @param a
     * @param fromIndex
     * @param toIndex
     * @param key
     * @return the minimal element whose value larger than key
     */
    public static int binaryDescendRangeSearchEqualRightFindLeft(double[] a, int fromIndex, int toIndex, double key) {
        int low = fromIndex;
        int high = toIndex - 1;
        int result = 0;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            double midVal = a[mid];

            if (midVal > key)
                low = mid + 1;  // Neither val is NaN, thisVal is smaller
            else if (midVal < key)
                high = mid - 1; // Neither val is NaN, thisVal is larger
            else {
                long midBits = Double.doubleToLongBits(midVal);
                long keyBits = Double.doubleToLongBits(key);
                if (midBits == keyBits) {
                    result = mid - 1;             // Key found
                    if (result < 0) {
                        return -1;
                    }
                    return result;
                } else if (midBits > keyBits) // (-0.0, 0.0) or (!NaN, NaN)
                    low = mid + 1;
                else                        // (0.0, -0.0) or (NaN, !NaN)
                    high = mid - 1;
            }
        }
        result = low - 1;
        if (result < 0) {
            return -1;
        }
        return result;
    }

    public static int binaryDescendRangeSearchEqualRightFindLeft(double[] a, double key) {
        return binaryDescendRangeSearchEqualRightFindLeft(a, 0, a.length, key);
    }

    public static int binaryDescendRangeSearchEqualRightFindLeft(List<double[]> arrList, int fromIndex, int toIndex, double key) {
        int low = fromIndex;
        int high = toIndex - 1;
        int result = 0;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            double[] midVal = arrList.get(mid);

            if (midVal[0] > key)
                low = mid + 1;  // Neither val is NaN, thisVal is smaller
            else if (midVal[0] < key)
                high = mid - 1; // Neither val is NaN, thisVal is larger
            else {
                long midBits = Double.doubleToLongBits(midVal[0]);
                long keyBits = Double.doubleToLongBits(key);
                if (midBits == keyBits) {
                    result = mid - 1;             // Key found
                    if (result < 0) {
                        return -1;
                    }
                    return result;
                } else if (midBits > keyBits) // (-0.0, 0.0) or (!NaN, NaN)
                    low = mid + 1;
                else                        // (0.0, -0.0) or (NaN, !NaN)
                    high = mid - 1;
            }
        }
        result = low - 1;
        if (result < 0) {
            return -1;
        }
        return result;
    }

    public static int binaryDescendRangeSearchEqualRightFindLeft(List<double[]> arrList, double key) {
        return binaryDescendRangeSearchEqualRightFindLeft(arrList, 0, arrList.size(), key);
    }

    /**
     * @param arrList
     * @param fromIndex
     * @param toIndex
     * @param key
     * @return the minimal element whose value larger or equal than key
     */
    public static int binaryDescendRangeSearchEqualLeftFindLeft(List<double[]> arrList, int fromIndex, int toIndex, double key) {
        int low = fromIndex;
        int high = toIndex - 1;
        int result = 0;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            double[] midVal = arrList.get(mid);

            if (midVal[0] > key)
                low = mid + 1;  // Neither val is NaN, thisVal is smaller
            else if (midVal[0] < key)
                high = mid - 1; // Neither val is NaN, thisVal is larger
            else {
                long midBits = Double.doubleToLongBits(midVal[0]);
                long keyBits = Double.doubleToLongBits(key);
                if (midBits == keyBits) {
                    return mid;
                } else if (midBits > keyBits) // (-0.0, 0.0) or (!NaN, NaN)
                    low = mid + 1;
                else                        // (0.0, -0.0) or (NaN, !NaN)
                    high = mid - 1;
            }
        }
        result = low - 1;
        if (result < 0) {
            return -1;
        }
        return result;
    }

    public static int binaryDescendRangeSearchEqualLeftFindLeft(List<double[]> arrList, double key) {
        return binaryDescendRangeSearchEqualLeftFindLeft(arrList, 0, arrList.size(), key);
    }

    public static Double getDoubleMaxValue(List<Double> list) {
        if (list.isEmpty()) {
            return null;
        }
        double resultValue = list.get(0);
        double temp;
        for (int i = 1; i < list.size(); ++i) {
            temp = list.get(i);
            if (resultValue < temp) {
                resultValue = temp;
            }
        }
        return resultValue;
    }

    public static Integer getIntegerMaxValue(List<Integer> list) {
        if (list.isEmpty()) {
            return null;
        }
        int resultValue = list.get(0);
        int temp;
        for (int i = 1; i < list.size(); ++i) {
            temp = list.get(i);
            if (resultValue < temp) {
                resultValue = temp;
            }
        }
        return resultValue;
    }

    public static int getDoubleArrayMaxValueByFirstValue(List<double[]> list) {
        int resultIndex = -1;
        double resultValue = Double.MIN_VALUE;
        double temp;
        for (int i = 0; i < list.size(); i++) {
            temp = list.get(i)[0];
            if (resultValue < temp) {
                resultValue = temp;
                resultIndex = i;
            }
        }
        return resultIndex;
    }

    public static <T> void shiftAndSet(T[][] inputArray, T[] outputArray) {
        int rowLen = inputArray.length;
        int colLen = inputArray[0].length;
        int outputSize = outputArray.length;
        if (rowLen * colLen != outputSize) {
            throw new RuntimeException("The quantity of inputArray is not equal to that of outputArray!");
        }
        int k = 0;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                outputArray[k++] = inputArray[i][j];
            }
        }
    }

    public static <T> void reshapeAndSet(T[] inputArray, T[][] outputArray) {
        int rowLen = outputArray.length;
        int colLen = outputArray[0].length;
        int inputSize = inputArray.length;
        if (rowLen * colLen != inputSize) {
            throw new RuntimeException("The quantity of inputArray is not equal to that of outputArray!");
        }
        int k = 0;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                outputArray[i][j] = inputArray[k++];
            }
        }
    }

    /**
     * 将给定element引用在生成数组的每个元素上
     *
     * @param element
     * @param size
     * @param <T>
     * @return
     */
    public static <T> T[] copyToArrayGivenElement(T element, int size) {
        T[] resultArray = (T[]) Array.newInstance(element.getClass(), size);
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = element;
        }
        return resultArray;
    }

    public static <T> void swap(T[] elementArray, int i, int j) {
        T tempElement = elementArray[i];
        elementArray[i] = elementArray[j];
        elementArray[j] = tempElement;
    }

    public static <T> T[] combineArray(T[]... arrs) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < arrs.length; i++) {
            for (int j = 0; j < arrs[i].length; j++) {
                list.add(arrs[i][j]);
            }
        }
        return list.toArray(list.toArray(arrs[0]));
    }

    public static double[] toDoubleArray(Double... elements) {
        double[] result = new double[elements.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = elements[i];
        }
        return result;
    }

    public static Double[] toDoubleArray(double... elements) {
        Double[] result = new Double[elements.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = elements[i];
        }
        return result;
    }

    public static Double[] toDoubleArray(List<Double> elementList) {
        Double[] result = new Double[elementList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = elementList.get(i);
        }
        return result;
    }

    public static double[] toBasicDoubleArray(List<Double> elementList) {
        double[] result = new double[elementList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = elementList.get(i);
        }
        return result;
    }

    public static <T> T[] extractRowArray(T[][] data, int rowNumber) {
        return data[rowNumber];
    }

    public static <T> T[] extractColArray(T[][] data, int colNumber) {
        T[] result = (T[]) Array.newInstance(data[0][0].getClass(), data[0].length);
        for (int i = 0; i < result.length; i++) {
            result[i] = data[i][colNumber];
        }
        return result;
    }

    public static double getMinimalValue(double... values) {
        double result = Double.MAX_VALUE;
        for (double value : values) {
            if (value < result) {
                result = value;
            }
        }
        return result;
    }

    public static double getDoubleMinValue(List<Double> valueList) {
        double result = Double.MAX_VALUE;
        for (double value : valueList) {
            if (value < result) {
                result = value;
            }
        }
        return result;
    }

    public static Integer getDoubleMinValueIndex(List<Double> valueList) {
        double result = Double.MAX_VALUE, tempValue;
        Integer index = -1, size = valueList.size();
        for (int i = 0; i < size; i++) {
            tempValue = valueList.get(i);
            if (tempValue < result) {
                result = tempValue;
                index = i;
            }
        }
        return index;
    }
    public static Integer getIntegerMinValueIndex(List<Integer> valueList) {
        Integer result = Integer.MAX_VALUE, tempValue;
        Integer index = -1, size = valueList.size();
        for (int i = 0; i < size; i++) {
            tempValue = valueList.get(i);
            if (tempValue < result) {
                result = tempValue;
                index = i;
            }
        }
        return index;
    }

    public static boolean isAllNoMoreThanZero(double[] data) {
        for (double datum : data) {
            if (datum > 0) {
                return false;
            }
        }
        return true;
    }

    public static <T> List<T> getUniqueList(List<T> data) {
        Set<T> uniqueDataSet = new LinkedHashSet<>();
        uniqueDataSet.addAll(data);
        List<T> result = new ArrayList<>();
        result.addAll(uniqueDataSet);
        return result;
    }

    public static <T> LinkedHashMap<T, Integer> getUniqueListWithFirstAppearanceIndex(List<T> data) {
        LinkedHashMap<T, Integer> indexMap = new LinkedHashMap<>();
        int size = data.size();
        T datum;
        Set<T> containingSet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            datum = data.get(i);
            if (!containingSet.contains(datum)) {
                indexMap.put(datum, i);
                containingSet.add(datum);
            }
        }
        return indexMap;
    }

    public static <T> LinkedHashMap<T, Integer> getUniqueListWithCountList(List<T> data) {
        LinkedHashMap<T, Integer> countMap = new LinkedHashMap<>();
        Integer tempCount;
        for (T datum : data) {
            tempCount = countMap.getOrDefault(datum, 0);
            ++ tempCount;
            countMap.put(datum, tempCount);
        }
        return countMap;
    }

    public static <T> Map<T, Integer> getUniqueListWithCountList(List<T> data, Collection<T> candidateData) {
        TreeMap<T, Integer> countMap = new TreeMap<>();
        for (T candidateDatum : candidateData) {
            countMap.put(candidateDatum, 0);
        }
        Integer tempCount;
        for (T datum : data) {
            tempCount = countMap.get(datum);
            if (tempCount == null) {
                System.out.println("candidateData 不包含 datum=" + datum);
                System.out.println("split");
                MyPrint.showCollection(candidateData, "; ");
            }
            ++ tempCount;
            countMap.put(datum, tempCount);
        }
        return countMap;
    }

    public static <T> LinkedHashMap<T, Double> getUniqueListWithStatisticList(List<T> data) {
        LinkedHashMap<T, Integer> countMap = getUniqueListWithCountList(data);
        int size = data.size();
        LinkedHashMap<T, Double> resultMap = new LinkedHashMap<>();
        for (Map.Entry<T, Integer> entry : countMap.entrySet()) {
            resultMap.put(entry.getKey(), entry.getValue() * 1.0 / size);
        }
        return resultMap;
    }




    public static <T> List<T> toList(Collection<T> data) {
        List<T> result = new ArrayList<>();
        for (T datum : data) {
            result.add(datum);
        }
        return result;
    }


    public static void main(String[] args) {
        List<Double> data = new ArrayList<>();
        data.add(5.9);
        data.add(5.9);
        data.add(3.4);
        data.add(3.4);
        data.add(7.6);
        data.add(7.6);
        Map<Double, Integer> result = new TreeMap<>(getUniqueListWithFirstAppearanceIndex(data));
        MyPrint.showMap(result);
    }

}
