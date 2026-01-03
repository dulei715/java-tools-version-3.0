package cn.edu.dll.configure;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;

public class XMLConfigureUtils {
    public static Element getFirstLayerElementByTagName(String tagName) {
        SAXReader reader = new SAXReader();
        InputStream inputStream = XMLConfigureUtils.class.getClassLoader().getResourceAsStream("result_config.xml");
        Document document;
        Element element = null;
        try {
            document = reader.read(inputStream);
            Element rootElement = document.getRootElement();
            element = rootElement.element(tagName);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return element;
    }

    public static Element getSubElementByAttribute(Element currentElement, String attributeName, String attributeValue) {
        Iterator<Element> iterator = currentElement.elementIterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            if (element.attributeValue(attributeName).equals(attributeValue)) {
                return element;
            }
        }
        return null;
    }

    public static Element getElementByID(String filePath, String id) throws DocumentException {
        SAXReader reader = new SAXReader();
        InputStream inputStream = XMLConfigure.class.getClassLoader().getResourceAsStream(filePath);
        Document document = reader.read(inputStream);
        Element basicElement = (Element) document.selectNodes("//field[@id='" + id + "']").get(0);
        return basicElement;
    }

    public static void main(String[] args) {
        String tagName = "resultOutputFileConfig";
        Element resultElement = getFirstLayerElementByTagName(tagName);
        System.out.println(resultElement.asXML());
    }

}
