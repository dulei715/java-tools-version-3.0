package cn.edu.dll.struct.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ColumnBean implements Serializable, Comparable<ColumnBean> {
    // 两个ColumnBean的相等或大小比较完全依照sequenceNumber
    private Integer sequenceNumber = null;
    private String name = null;
    private String dataType = null;
    private boolean composable = false;

    public ColumnBean() {
    }

    public ColumnBean(Integer sequenceNumber, String name, String dataType, boolean composable) {
        this.sequenceNumber = sequenceNumber;
        this.name = name;
        this.dataType = dataType;
        this.composable = composable;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public boolean isComposable() {
        return composable;
    }

    public void setComposable(boolean composable) {
        this.composable = composable;
    }

    @Override
    public String toString() {
        return "ColumnBean{" +
                "sequenceNumber=" + sequenceNumber +
                ", name='" + name + '\'' +
                ", dataType='" + dataType + '\'' +
                ", composable=" + composable +
                '}';
    }


    @Override
    public int compareTo(ColumnBean columnBean) {
        return this.sequenceNumber - columnBean.sequenceNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColumnBean that = (ColumnBean) o;
        return sequenceNumber.equals(that.sequenceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequenceNumber);
    }


    public static List<String> getNameList(List<ColumnBean> columnBeanList) {
        int size = columnBeanList.size();
        List<String> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(columnBeanList.get(i).getName());
        }
        return result;
    }


}
