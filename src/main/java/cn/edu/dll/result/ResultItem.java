package cn.edu.dll.result;

import cn.edu.dll.struct.result.ColumnBean;

import java.util.*;

public class ResultItem {
    private Integer columnSize = null;
    private TreeMap<ColumnBean, Object> itemData = null;

    public ResultItem() {
    }

    public ResultItem(TreeMap<ColumnBean, Object> itemData) {
        this.columnSize = itemData.size();
        this.itemData = itemData;
    }

    public Integer getColumnSize() {
        return columnSize;
    }

    public TreeMap<ColumnBean, Object> getItemData() {
        return itemData;
    }

    public void setItemData(TreeMap<ColumnBean, Object> itemData) {
        this.itemData = itemData;
        this.columnSize = this.itemData.size();
    }

    public List<ColumnBean> getColumnBeanList() {
        return new ArrayList<>(this.itemData.keySet());
    }

    public List<Object> getValueListAsObjects() {
        return new ArrayList<>(this.itemData.values());
    }

    public Object getValue(ColumnBean columnBean) {
        return this.itemData.get(columnBean);
    }

    public Object getValue(Integer columnSequence) {
        // todo:这里用的是线性查找，如果有需要可以改成二分查找
        for (Map.Entry<ColumnBean, Object> entry : this.itemData.entrySet()) {
            if (entry.getKey().getSequenceNumber().equals(columnSequence)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public Object getValue(String name) {
        for (Map.Entry<ColumnBean, Object> entry : this.itemData.entrySet()) {
            if (entry.getKey().getName().equals(name)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public ResultItem plus(ResultItem addedResultItem) {
        if (!this.columnSize.equals(addedResultItem.columnSize)) {
            throw new RuntimeException("The column size of these two ResultItem instances is not consistent!");
        }
        Iterator<Map.Entry<ColumnBean, Object>> thisIterator = this.itemData.entrySet().iterator();
        Iterator<Map.Entry<ColumnBean, Object>> addedIterator = addedResultItem.itemData.entrySet().iterator();
        Map.Entry<ColumnBean, Object> thisEntry, addedEntry;
        ColumnBean thisColumnBean, addedColumnBean;
        Object thisValue, addedValue, resultValue = null;

        TreeMap<ColumnBean, Object> resultItemData = new TreeMap<>();
        while (thisIterator.hasNext()) {
            thisEntry = thisIterator.next();
            addedEntry = addedIterator.next();

            thisColumnBean = thisEntry.getKey();
            thisValue = thisEntry.getValue();
            addedColumnBean = addedEntry.getKey();
            addedValue = addedEntry.getValue();


            Integer tempSequenceNumber;
            String tempName, tempDataType;
            boolean tempComposable;

            tempSequenceNumber = thisColumnBean.getSequenceNumber();
            if (!tempSequenceNumber.equals(addedColumnBean.getSequenceNumber())) {
                throw new RuntimeException("The sequenceNumber is not consistent!");
            }
            tempName = thisColumnBean.getName();
            if (!tempName.equals(addedColumnBean.getName())) {
                throw new RuntimeException("The name is not consistent!");
            }
            tempDataType = thisColumnBean.getDataType();
            if (!tempDataType.equals(addedColumnBean.getDataType())) {
                throw new RuntimeException("The dataType is not consistent!");
            }
            tempComposable = thisColumnBean.isComposable();
            if (tempComposable != addedColumnBean.isComposable()) {
                throw new RuntimeException("The composable state is not consistent!");
            }

            if (tempComposable) {
                switch (tempDataType) {
                    case "java.lang.Integer":
                        Integer thisIntegerValue = (Integer) thisValue;
                        Integer addedIntegerValue = (Integer) addedValue;
                        resultValue = thisIntegerValue + addedIntegerValue;
                        break;
                    case "java.lang.Long":
                        Long thisLongValue = (Long) thisValue;
                        Long addedLongValue = (Long) addedValue;
                        resultValue = thisLongValue + addedLongValue;
                        break;
                    case "java.lang.Double":
                        Double thisDoubleValue = (Double) thisValue;
                        Double addedDoubleValue = (Double) addedValue;
                        resultValue = thisDoubleValue + addedDoubleValue;
                        break;
                    case "java.lang.float":
                        Float thisFloatValue = (Float) thisValue;
                        Float addedFloatValue = (Float) addedValue;
                        resultValue = thisFloatValue + addedFloatValue;
                        break;
                    case "java.lang.String":
                        String thisStringValue = (String) thisValue;
                        String addedStringValue = (String) addedValue;
                        resultValue = thisStringValue + addedStringValue;
                }
            } else {
                resultValue = thisValue;
            }

            if (resultValue == null) {
                throw new RuntimeException("Not support dataType:" + tempDataType + " !");
            }
            resultItemData.put(thisColumnBean, resultValue);

        }
        return new ResultItem(resultItemData);
    }

    public static ResultItem toItem(List<ColumnBean> columnBeanList, List<String> valueStringList) {
        ColumnBean tempColumnBean;
        String tempType, stringValue;
        Object tempObject;
        TreeMap<ColumnBean, Object> map = new TreeMap();
        for (int i = 0; i < columnBeanList.size(); i++) {
            tempColumnBean = columnBeanList.get(i);
            tempType = tempColumnBean.getDataType();
            stringValue = valueStringList.get(i);
            switch (tempType) {
                case "java.lang.Integer":
                    tempObject = Integer.valueOf(stringValue);
                    break;
                case "java.lang.Long":
                    tempObject = Long.valueOf(stringValue);
                    break;
                case "java.lang.Double":
                    tempObject = Double.valueOf(stringValue);
                    break;
                case "java.lang.float":
                    tempObject = Float.valueOf(stringValue);
                    break;
                case "java.lang.String":
                    tempObject = stringValue;
                    break;
                default:
                    throw new RuntimeException("Not supporting type for \"" + tempType + "\"");
            }
            map.put(tempColumnBean, tempObject);
        }
        return new ResultItem(map);
    }


}
