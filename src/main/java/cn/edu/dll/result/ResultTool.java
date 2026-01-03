package cn.edu.dll.result;

import cn.edu.dll.configure.XMLConfigureUtils;
import cn.edu.dll.struct.result.ColumnBean;
import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Element;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ResultTool {
    public static List<ColumnBean> getAttributeListFromConfigureFile(String configIdName)  {
        Element outerElement = XMLConfigureUtils.getFirstLayerElementByTagName("resultOutputFileConfig");
        Iterator iterator = outerElement.elementIterator();
        List<ColumnBean> resultList = new ArrayList<>();
        Object tempElement;
        //todo: 修改
        Element element = XMLConfigureUtils.getSubElementByAttribute(outerElement, "name", configIdName);
        Element resultAttributes = element.element("resultAttributes");
        Iterator<Element> attributesIterator = resultAttributes.elementIterator();

        try {
            while (attributesIterator.hasNext()) {
                Iterator<Element> attributeInnerIterator = attributesIterator.next().elementIterator();
                Map<String, String> tempMap = new HashMap<>();
                while (attributeInnerIterator.hasNext()) {
                    Element innerAttribute = attributeInnerIterator.next();
                    tempMap.put(innerAttribute.getName(), innerAttribute.getTextTrim());
                }
                ColumnBean bean = new ColumnBean();
                BeanUtils.populate(bean, tempMap);
                resultList.add(bean);
            }
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return resultList;
    }

    public static List<ColumnBean> getAttributeListFromConfigureFile() {
        return getAttributeListFromConfigureFile("default");
    }
}
