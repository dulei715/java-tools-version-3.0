package cn.edu.dll.result;

import cn.edu.dll.struct.result.ColumnBean;
import cn.edu.dll.struct.result.ColumnList;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BasicResult {
    private static final List<ColumnBean> attributeList = ResultTool.getAttributeListFromConfigureFile();
    private List<ColumnList> attributeObjectList = null;
    private Map<String, Integer> reverseIndex = null;

    // 构造代码块
    {
        this.attributeObjectList = new ArrayList<>();
        ColumnBean tempColumnBean;
        this.reverseIndex = new HashMap<>(attributeList.size());
        for (int i = 0; i < attributeList.size(); i++) {
            tempColumnBean = attributeList.get(i);
            this.attributeObjectList.add(new ColumnList(tempColumnBean));
            this.reverseIndex.put(tempColumnBean.getName(), i);
        }
    }

    private BasicResult() {

    }

//    public BasicResult getInstance() {
//        return null;
//    }


    public TreeMap<ColumnBean, Object> getItemAsMap(int index) {
        TreeMap<ColumnBean, Object> resultMap = new TreeMap<>();
        ColumnBean tempColumnBean;
        Object tempValue;
        for (int i = 0; i < attributeList.size(); i++) {
            tempColumnBean = attributeList.get(i);
            tempValue = this.attributeObjectList.get(tempColumnBean.getSequenceNumber()).getValue(i);
            resultMap.put(tempColumnBean, tempValue);
        }
        return resultMap;
    }

    public List<Object> getItemAsList() {
        List<Object> resultList = new ArrayList<>();
        Object tempValue;
        for (int i = 0; i < attributeList.size(); i++) {
            tempValue = this.attributeObjectList.get(attributeList.get(i).getSequenceNumber()).getValue(i);
            resultList.add(tempValue);
        }
        return resultList;
    }

//    public Object getFieldValue(String fieldName) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        Method method = this.dynamicClassClass.getMethod(JavaBeanTool.getGetMethodNameByFieldName(fieldName));
//        return method.invoke(this.dynamicClassInstance);
//    }



    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
//        BasicResult result = new  BasicResult().getInstance();
//        Object value = result.getFieldValue("DataSetName");
//        System.out.println(value);
    }

}
