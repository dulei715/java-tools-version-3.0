package cn.edu.dll.map;

import cn.edu.dll.basic.ValidationUtil;
import cn.edu.dll.constant_values.ConstantValues;
import cn.edu.dll.struct.pair.CombinePair;

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

    public static <K, P, V> V getTwoIndexValueOrDefault(Map<K, Map<P, V>> rawMap, K rawKey, P innerKey, V defaultValue) {
        Map<P, V> innerMap = rawMap.get(rawKey);
        if (innerMap == null) {
            return defaultValue;
        }
        V value = innerMap.get(innerKey);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }
    public static <K, P, V> V getTwoIndexValue(Map<K, Map<P, V>> rawMap, K rawKey, P innerKey) {
        Map<P, V> innerMap = rawMap.get(rawKey);
        return innerMap.get(innerKey);
    }

    public static <K, P> void shrink(Map<K, Map<P, Integer>> rawMap, Integer shrinkNumber) {
        for (Map.Entry<K, Map<P, Integer>> entry : rawMap.entrySet()) {
            K key = entry.getKey();
            Map<P, Integer> innerMap = entry.getValue();
            for (Map.Entry<P, Integer> innerEntry : innerMap.entrySet()) {
                P innerKey = innerEntry.getKey();
                Integer value = innerEntry.getValue();
                value /= shrinkNumber;
                innerMap.put(innerKey, value);
            }
        }
    }

    public static <K, P, V> void addMapAsValue(Map<K, Map<P, Set<V>>> rawMap, K rawKey, P innerKey, V value) {
        Map<P, Set<V>> innerMap = rawMap.get(rawKey);
        if (innerMap == null) {
            innerMap = new HashMap<>();
            rawMap.put(rawKey, innerMap);
        }
        putInSetValue(innerMap, innerKey, value);
    }

    public static  <K, P, V> String getTwoIndexMapString(Map<K, Map<P, V>> data) {
        StringBuilder stringBuilder = new StringBuilder();
        Integer outerSize = data.size(), innerSize, outerIndex = 0, innerIndex;
        for (Map.Entry<K, Map<P, V>> entry : data.entrySet()) {
            ++outerIndex;
            K key = entry.getKey();
            Map<P, V> innerMap = entry.getValue();
            innerSize = innerMap.size();
            innerIndex = 0;
            for (Map.Entry<P, V> innerEntry : innerMap.entrySet()) {
                ++innerIndex;
                P innerKey = innerEntry.getKey();
                V value = innerEntry.getValue();
                if (innerIndex == innerSize) {
                    stringBuilder.append(String.format("(%s, %s)->%s", key, innerKey, value));
                    if (outerIndex < outerSize) {
                        stringBuilder.append(ConstantValues.LINE_SPLIT);
                    }
                } else {
                    stringBuilder.append(String.format("(%s, %s)->%s; ", key, innerKey, value));
                }
            }
        }
        return stringBuilder.toString();
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
    public static <K> Map<K, Integer> getInitializedMap(Collection<K> keyCollection, Integer startValue, Integer step) {
        Map<K, Integer> result = new HashMap<>();
        Integer tempValue = startValue;
        for (K key : keyCollection) {
            result.put(key, tempValue);
            tempValue += step;
        }
        return result;
    }

    public static <K> Map<K, K> getSelfMap(Collection<K> data) {
        Map<K, K> resultMap = new HashMap<>(data.size());
        for (K datum : data) {
            resultMap.put(datum, datum);
        }
        return resultMap;
    }

    public static <K, V> Map<K, V> generateMapByKVCollection(Collection<K> kCollection, Collection<V> vCollection) {
        Integer size = kCollection.size();
        ValidationUtil.requireEqual(kCollection, vCollection.size(), "The sizes of these two lists are not equal!");
        Map<K, V> resultMap = new HashMap<>(size);
        K key;
        V value;
        Iterator<K> kIterator = kCollection.iterator();
        Iterator<V> vIterator = vCollection.iterator();
        while (kIterator.hasNext()) {
            key = kIterator.next();
            value = vIterator.next();
            resultMap.put(key, value);
        }
        return resultMap;
    }

    public static <K, P, V> CombinePair<List<V>, List<CombinePair<K, P>>> toTwoIndexValueKeyListPair(Map<K, Map<P, V>> data) {
        List<V> valueList = new ArrayList<>();
        List<CombinePair<K, P>> keyPairList = new ArrayList<>();
        Map<P, V> innerMap;
        K outerKey;
        P innerKey;
        V value;
        for (Map.Entry<K, Map<P, V>> entry : data.entrySet()) {
            outerKey = entry.getKey();
            innerMap = entry.getValue();
            for (Map.Entry<P, V> innerEntry : innerMap.entrySet()) {
                innerKey = innerEntry.getKey();
                value = innerEntry.getValue();
                valueList.add(value);
                keyPairList.add(new CombinePair<>(outerKey, innerKey));
            }
        }
        return new CombinePair<>(valueList, keyPairList);
    }



    // 如果kCollection出现originalMap中的key，会替换相应的值
    public static <K, V> Map<K, V> addMapByKVCollection(Map<K, V> originalMap, Collection<K> kCollection, Collection<V> vCollection) {
        ValidationUtil.requireEqual(kCollection, vCollection.size(), "The sizes of these two lists are not equal!");
        K key;
        V value;
        Iterator<K> kIterator = kCollection.iterator();
        Iterator<V> vIterator = vCollection.iterator();
        while (kIterator.hasNext()) {
            key = kIterator.next();
            value = vIterator.next();
            originalMap.put(key, value);
        }
        return originalMap;
    }

    public static <K, V> Map<K, V> extractSubMap(final Map<K, V> totalMap, final Collection<K> keyCollection) {
        Map<K, V> resultMap = new HashMap<>();
        V tempValue;
        for (K key : keyCollection) {
            tempValue = totalMap.get(key);
            if (tempValue == null) {
                continue;
            }
            resultMap.put(key, tempValue);
        }
        return resultMap;
    }


}
