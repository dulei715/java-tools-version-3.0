package cn.edu.dll.configure;

import cn.edu.dll.struct.pair.CombinePair;
import cn.edu.dll.struct.pair.CombineTriple;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class XMLConfigure {
    private Document document;

    private static final String splitTag = ",";

    public XMLConfigure(Document document) {
        this.document = document;
    }

    public XMLConfigure(String fileAbPath) {
        SAXReader reader = new SAXReader();
//        InputStream inputStream = XMLConfigure.class.getClassLoader().getResourceAsStream(filePath);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(fileAbPath);
            this.document = reader.read(inputStream);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



    public Document getDocument() {
        return document;
    }

    public <T> CombineTriple<String, T, List<T>> getIndependentData(String independentVariableName, String singleName, String varianceName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Node> nodeList = this.document.selectNodes("/root/independentVariables/attribute[@name='" + independentVariableName + "']");
        Element variableElement = (Element)nodeList.get(0);
        String dataType = variableElement.element("dataType").getTextTrim();
        Element singleElement = (Element) variableElement.selectNodes("./single[@name='"+singleName+"']").get(0);
        Element varianceElement = (Element) variableElement.selectNodes("./variance[@name='"+varianceName+"']").get(0);
        Class clazz = Class.forName(dataType);
        Method method = ("java.lang.String".equals(dataType)) ? clazz.getMethod("valueOf", Object.class) : clazz.getMethod("valueOf", String.class);
        T singleValue = (T) method.invoke(null, singleElement.getTextTrim());
        List<T> valueList = new ArrayList<>();
        String[] valueArray = varianceElement.getTextTrim().split(splitTag);
        for (int i = 0; i < valueArray.length; i++) {
            valueList.add((T) method.invoke(null, valueArray[i]));
        }
        return new CombineTriple<>(dataType, singleValue, valueList);
    }


    public <T> CombinePair<String, T> getDependentData(String dependentVariableName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Node> nodeList = this.document.selectNodes("/root/dependentVariables/attribute[@name='" + dependentVariableName + "']");
        Element variableElement = (Element)nodeList.get(0);
        String dataType = variableElement.element("dataType").getTextTrim();
        String defaultValueString = variableElement.element("defaultValue").getTextTrim();
        Class clazz = Class.forName(dataType);
        Method method = ("java.lang.String".equals(dataType)) ? clazz.getMethod("valueOf", Object.class) : clazz.getMethod("valueOf", String.class);
        T defaultValue = (T) method.invoke(null, defaultValueString);
        return new CombinePair<>(dataType, defaultValue);
    }



    public static void main(String[] args) {
    }

}
