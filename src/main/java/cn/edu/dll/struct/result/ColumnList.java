package cn.edu.dll.struct.result;

import java.util.ArrayList;
import java.util.List;

public class ColumnList {
    private ColumnBean columnBean = null;
    private List<Object> valueList = null;

    public ColumnList(ColumnBean columnBean) {
        this.columnBean = columnBean;
        valueList = new ArrayList<>();
    }

    public ColumnList(ColumnBean columnBean, List<Object> valueList) throws ClassNotFoundException {
        this.columnBean = columnBean;
        //todo: 这里没有添加对数据类型一致性的判断（保证valueList中元素本身和columBean中的dataType类型保持一致）
        this.valueList = valueList;
    }

    public void addValue(Object value) {
        this.valueList.add(value);
    }

    public void removeValueByIndex(int index) {
        this.valueList.remove(index);
    }

    public void removeValueByObject(Object value) {
        this.valueList.remove(value);
    }

    public void insertValue(int index, Object value) {
        this.valueList.add(index, value);
    }

    public Object getValue(int index) {
        return this.valueList.get(index);
    }

}
