package cn.edu.dll.map;

import java.util.*;

public class MapUtils {


    public static <K, V> void putInListValue(Map<K, List<V>> map, K key, V value) {
        List<V> valueList = map.get(key);
        if (valueList == null) {
            valueList = new ArrayList<>();
            map.put(key, valueList);
        }
        valueList.add(value);
    }
    public static <K, V> void putInSetValue(Map<K, Set<V>> map, K key, V value) {
        Set<V> valueSet = map.get(key);
        if (valueSet == null) {
            valueSet = new HashSet<>();
            map.put(key, valueSet);
        }
        valueSet.add(value);
    }

    public static <K, P, V> void addTwoIndexValue(Map<K, Map<P, V>> rawMap, K rawKey, P innerKey, V value) {
        rawMap.computeIfAbsent(rawKey, k -> new HashMap<>()).put(innerKey, value);
    }

    public static <K, P, V> void addMapAsValue(Map<K, Map<P, Set<V>>> rawMap, K rawKey, P innerKey, V value) {
        Map<P, Set<V>> innerMap = rawMap.get(rawKey);
        if (innerMap == null) {
            innerMap = new HashMap<>();
            rawMap.put(rawKey, innerMap);
        }
        putInSetValue(innerMap, innerKey, value);
    }

    /**
     * 将statisticValueArray中给定regionIndex位置的元素统计并按照元素排序
     * @param statisticValueArray
     * @param regionIndex
     * @return
     * @param <T>
     */
    public static <T extends Comparable<T>> TreeMap<T, List<Integer>> getSortResult(T[] statisticValueArray, List<Integer> regionIndex) {
        TreeMap<T, List<Integer>> orderMap = new TreeMap<>();
        T tempValue;
        int tempIndex;
        List<Integer> tempList;
        for (int i = 0; i < regionIndex.size(); i++) {
            tempIndex = regionIndex.get(i);
            tempValue = statisticValueArray[tempIndex];
            orderMap.computeIfAbsent(tempValue, k -> new ArrayList<>()).add(tempIndex);
        }
        return orderMap;
    }
    public static <T extends Comparable<T>> TreeMap<T, List<Integer>> getSortResult(TreeMap<Integer, T> statisticValueMap, List<Integer> regionIndex) {
        TreeMap<T, List<Integer>> orderMap = new TreeMap<>();
        T tempValue;
        int tempIndex;
        List<Integer> tempList;
        for (int i = 0; i < regionIndex.size(); i++) {
            tempIndex = regionIndex.get(i);
            tempValue = statisticValueMap.get(tempIndex);
            orderMap.computeIfAbsent(tempValue, k -> new ArrayList<>()).add(tempIndex);
        }
        return orderMap;
    }

    public static <T> Double getValueSum(TreeMap<T, Double> data) {
        Collection<Double> valueCollection = data.values();
        Double result = 0D;
        for (Double value : valueCollection) {
            result += value;
        }
        return result;
    }

    public static <T> Integer getIntegerValueSum(TreeMap<T, Integer> data) {
        Collection<Integer> valueCollection = data.values();
        Integer result = 0;
        for (Integer value : valueCollection) {
            result += value;
        }
        return result;
    }

    public static <K, V> Map<K, V> getInitializedMap(Collection<K> keyCollection, V defaultValue) {
        Map<K, V> result = new HashMap<>();
        for (K key : keyCollection) {
            result.put(key, defaultValue);
        }
        return result;
    }

}
