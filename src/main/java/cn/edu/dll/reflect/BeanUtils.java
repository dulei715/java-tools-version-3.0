package cn.edu.dll.reflect;

public class BeanUtils {
//    public static <T> T toBean(Element beanElement, Class<T> clazz) throws DocumentException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
//        T instance = clazz.newInstance();
//        List<Element> list = beanElement.selectNodes("./field");
//        Method tempMethod;
//        String tempName, tempType;
//        String valueStr;
//        for (Element subElement : list) {
//            tempName = subElement.attributeValue("name");
//            tempType = subElement.attributeValue("type");
//            valueStr = subElement.getTextTrim();
//            tempMethod = clazz.getDeclaredMethod(JavaBeanTool.getSetMethodNameByFieldName(tempName), Class.forName(tempType));
//            tempMethod.invoke(instance, (tempType) ReflectTool.getObjectWithGivenClassType(tempType, valueStr));
//        }
//    }
}
