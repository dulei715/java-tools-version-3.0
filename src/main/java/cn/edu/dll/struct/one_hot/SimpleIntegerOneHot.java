package cn.edu.dll.struct.one_hot;

public class SimpleIntegerOneHot extends OneHot<Integer>{
    public SimpleIntegerOneHot(int areaSize) {
        super(areaSize);
    }

    @Override
    public void setElement(Integer element) {
        this.data[toOneHotDataIndex(element)] = ONE;
    }

//    public SimpleIntegerOneHot(int areaSize, Integer element) {
//        super(areaSize, element);
//    }

    protected SimpleIntegerOneHot(boolean... data) {
        this.data = data;
    }

    @Override
    public OneHot<Integer> getInstance(boolean... data) {
        return new SimpleIntegerOneHot(data);
    }

    //    @Override
    protected int toOneHotDataIndex(Integer element) {
        if (element < 0 || element >= this.areaSize) {
            throw new RuntimeException("Illegal Element!");
        }
        return element;
    }
}
