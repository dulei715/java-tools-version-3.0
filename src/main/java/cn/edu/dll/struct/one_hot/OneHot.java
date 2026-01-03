package cn.edu.dll.struct.one_hot;

import java.util.Arrays;
import java.util.Objects;

public abstract class OneHot<T> {
    public static final boolean ONE = true;
    public static final boolean ZERO = false;
    protected int areaSize;
    protected boolean[] data;

    public OneHot(int areaSize) {
        this.areaSize = areaSize;
        this.data = new boolean[areaSize];
    }

//    public void setElement(T element) {
//        this.data[toOneHotDataIndex(element)] = ONE;
//    }
    public abstract void setElement(T element);
//    public OneHot(int areaSize, T element) {
//        this(areaSize);
//        setElement(element);
//    }

//    public OneHot(int length, int position) {
//        this(length);
//        this.data[position] = true;
//    }

//    public OneHot(boolean[] data) {
//        this.data = data;
//    }

    protected OneHot(boolean... data) {
        this.areaSize = data.length;
        this.data = data;
    }

    public abstract OneHot<T> getInstance(boolean... data);

    public int getAreaSize() {
        return this.data.length;
    }

    public void setPosition(int position) {
        this.data[position] = true;
    }

    public void clearPosition(int position) {
        this.data[position] = false;
    }

    public boolean getValueByPosition(int position) {
        return this.data[position];
    }

    public boolean[] getData() {
        return this.data;
    }

    public String toOriginalString() {
        return Arrays.toString(data);
    }

    @Override
    public String toString() {
        String originalString =  Arrays.toString(data);
        return originalString.replaceAll("true", "1").replaceAll("false", "0");
    }


//    protected abstract int toOneHotDataIndex(T element);


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OneHot<?> oneHot = (OneHot<?>) o;
        return areaSize == oneHot.areaSize && Arrays.equals(data, oneHot.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(areaSize);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}
