package cn.edu.dll.result;

import cn.edu.dll.constant_values.ConstantValues;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("Duplicates")
public class ExperimentResult implements Cloneable {
    public List<String> attributeList = null;
    public List<String> valueList = null;

    public ExperimentResult() {
        this.attributeList = new ArrayList<>();
        this.valueList = new ArrayList<>();
    }

    public ExperimentResult(List<String> attributeList, List<String> valueList) {
        this.attributeList = attributeList;
        this.valueList = valueList;
    }

    public ExperimentResult(List<String> attributeList, String ... values) {
        this.attributeList = attributeList;
        if (values.length != this.attributeList.size()) {
            throw new RuntimeException("The value array length is not equal to the attribute list length!");
        }
        this.valueList = new ArrayList<>(values.length);
        for (String value : values) {
            valueList.add(value);
        }
    }



    public void addPair(String key, String value) {
        this.attributeList.add(key);
        this.valueList.add(value);
    }

    public void addPair(int index, String key, String value) {
        this.attributeList.add(index, key);
        this.valueList.add(index, value);
    }

    public void setPair(String key, String value) {
        int index = this.attributeList.indexOf(key);
        if (index < 0) {
            addPair(key, value);
        } else {
            this.valueList.set(index, value);
        }
    }

    /**
     * 为List中的每项添加相应的对儿
     * @param originalResultList
     * @param index
     * @param key
     * @param value
     */
    public static void addPair(List<ExperimentResult> originalResultList, int index, String key, String value) {
        for (ExperimentResult experimentResult : originalResultList) {
            experimentResult.addPair(index, key, value);
        }
    }

    /**
     * 为Map中的每个entry的List添加相应的对儿
     * @param originalResultMap
     * @param index
     * @param key
     * @param value
     * @param <T>
     */
    public static <T> void addPair(Map<T, List<ExperimentResult>> originalResultMap, int index, String key, String value) {
        for (Map.Entry<T, List<ExperimentResult>> entry : originalResultMap.entrySet()) {
            addPair(entry.getValue(), index, key, value);
        }
    }

    public String getValue(String attribute) {
        int index = this.attributeList.indexOf(attribute);
        if (index > -1) {
            return this.valueList.get(index);
        }
        return null;
    }

    public static <T> List<ExperimentResult> getCombineResultList(Map<T, List<ExperimentResult>> rawMap) {
        List<ExperimentResult> resultList = new ArrayList<>();
        List<ExperimentResult> tempValue;
        for (Map.Entry<T, List<ExperimentResult>> entry : rawMap.entrySet()) {
            tempValue = entry.getValue();
            resultList.addAll(tempValue);
        }
        return resultList;
    }

    public List<String> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<String> attributeList) {
        this.attributeList = attributeList;
    }

    public List<String> getValueList() {
        return valueList;
    }

    public void setValueList(List<String> valueList) {
        this.valueList = valueList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int size = this.attributeList.size();
        int i;
        for (i = 0; i < size - 1; i++) {
            stringBuilder.append(this.attributeList.get(i));
            stringBuilder.append(": ");
            stringBuilder.append(this.valueList.get(i));
            stringBuilder.append("; ");
        }
        stringBuilder.append(this.attributeList.get(i));
        stringBuilder.append(": ");
        stringBuilder.append(this.valueList.get(i));
        stringBuilder.append(ConstantValues.LINE_SPLIT);
        return stringBuilder.toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        List<String> attributeList = new ArrayList<>(this.attributeList);
        List<String> valueList = new ArrayList<>(this.valueList);
        return new ExperimentResult(attributeList, valueList);
    }
}
