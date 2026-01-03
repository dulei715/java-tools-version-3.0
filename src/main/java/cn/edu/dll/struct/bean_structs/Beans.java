package cn.edu.dll.struct.bean_structs;

import java.util.ArrayList;
import java.util.List;

public class Beans {
    private List<Bean> beanList = new ArrayList<>();
    public List<Bean> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<Bean> beanList) {
        this.beanList = beanList;
    }
}
