package cn.edu.dll.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTool {


    public static String getFieldDeclaredString(String privilege, boolean isStatic, String fieldType, String fieldName) {
        StringBuilder stringBuilder = new StringBuilder();
        if (privilege != null) {
            stringBuilder.append(privilege);
            stringBuilder.append(" ");
        }
        if (isStatic) {
            stringBuilder.append("static").append(" ");
        }
        stringBuilder.append(fieldType).append(" ");
        stringBuilder.append(fieldName).append(";");
        return stringBuilder.toString();
    }

    public static String getGetMethodString(String privilege, boolean isStatic, String fieldType, String fieldName) {
        StringBuilder stringBuilder = new StringBuilder();
        if (privilege != null) {
            stringBuilder.append(privilege).append(" ");
        }
        if (isStatic) {
            stringBuilder.append("static").append(" ");
        }
        stringBuilder.append(fieldType).append(" ");
        stringBuilder.append(JavaBeanTool.getGetMethodNameByFieldName(fieldName)).append("()");
        stringBuilder.append("{return this." + fieldName + ";}");
        return stringBuilder.toString();
    }
    public static String getSetMethodString(String privilege, boolean isStatic, String fieldType, String fieldName) {
        StringBuilder stringBuilder = new StringBuilder();
        if (privilege != null) {
            stringBuilder.append(privilege).append(" ");
        }
        if (isStatic) {
            stringBuilder.append("static").append(" ");
        }
        stringBuilder.append("void").append(" ");
        stringBuilder.append(JavaBeanTool.getSetMethodNameByFieldName(fieldName));
        stringBuilder.append("(" + fieldType + " " + fieldName + ")");
        stringBuilder.append("{this." + fieldName + "=" + fieldName + ";}");
        return stringBuilder.toString();
    }

    /**
     * 仅支持 String, Integer, Double, Long 类型
     * @param className
     * @param valueStr
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object getObjectWithGivenClassType(String className, String valueStr) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if ("java.lang.String".equals(className)) {
            return valueStr;
        }
        Class<?> clazz = Class.forName(className);
        Method valueOfMethod = clazz.getMethod("valueOf", String.class);
        return valueOfMethod.invoke(null, valueStr);
    }

    /**
     * 以第一个参数dataA为准，输出和dataA同类型的值。String类型的合并仅支持相同的String值。
     * @param dataA
     * @param dataB
     * @return
     */
    public static Object combineObject(Object dataA, Object dataB) {
        if (dataA instanceof String) {
            return dataA;
        }
        if (dataA instanceof Double) {
            Double dataADouble = (Double) dataA;
            if (dataB instanceof String) {
                dataB = Double.valueOf((String) dataB);
            }
            Double dataBDouble = (Double) dataB;
            return dataADouble + dataBDouble;
        }
        if (dataA instanceof Integer) {
            Integer dataAInteger = (Integer) dataA;
            if (dataB instanceof String) {
                dataB = Integer.valueOf((String)dataB);
            }
            Integer dataBInteger = (Integer) dataB;
            return dataAInteger + dataBInteger;
        }
        if (dataA instanceof Long) {
            Long dataALong = (Long) dataA;
            if (dataB instanceof String) {
                dataB = Long.valueOf((String)dataB);
            }
            Long dataBLong = (Long) dataB;
            return dataALong + dataBLong;
        }
        if (dataA instanceof Float) {
            Float dataAFloat = (Float) dataA;
            if (dataB instanceof String) {
                dataB = Float.valueOf((String) dataB);
            }
            Float dataBFloat = (Float) dataB;
            return dataAFloat + dataBFloat;
        }
        throw new RuntimeException("Not support combination for " + dataA.getClass() + " and " + dataB.getClass() + "!");
    }
    public static Object fromNaNToGivenValue(Object dataFrom, String givenData) {
        if (dataFrom instanceof String) {
            return givenData;
        }
        if (dataFrom instanceof Double) {
            return Double.valueOf(givenData);
        }
        if (dataFrom instanceof Integer) {
            return Integer.valueOf(givenData);
        }
        if (dataFrom instanceof Long) {
            return Long.valueOf(givenData);
        }
        if (dataFrom instanceof Float) {
            return Float.valueOf(givenData);
        }
        throw new RuntimeException("Not support transform for " + dataFrom.getClass() + " and " + givenData.getClass() + "!");
    }
    public static Object[] filterNaNToGivenValue(Object dataFrom, String givenData) {
        Object[] result = new Object[2];
        if (dataFrom instanceof String && "NaN".equals(dataFrom)) {
            result[0] = givenData;
            result[1] = true;
            return result;
        }
        if (dataFrom instanceof Double && Double.valueOf("NaN").equals(dataFrom)) {
            result[0] = Double.valueOf(givenData);
            result[1] = true;
            return result;
        }
        if (dataFrom instanceof Float && Float.valueOf("NaN").equals(dataFrom)) {
            result[0] =  Float.valueOf(givenData);
            result[1] = true;
            return result;
        }
        result[0] = dataFrom;
        result[1] = false;
        return result;
    }



    public static Object divide(Object data, int divideValue) {
        if (data instanceof Double) {
            Double dataDouble = (Double) data;
            return dataDouble / divideValue;
        }
        if (data instanceof Integer) {
            Integer dataInteger = (Integer) data;
            return dataInteger / divideValue;
        }
        if (data instanceof Long) {
            Long dataLong = (Long) data;
            return dataLong / divideValue;
        }
        throw new RuntimeException("Not support combination for " + data.getClass() + "!");
    }

    public static void main(String[] args) {
        String word = getSetMethodString("public", false, "Integer", "name");
        System.out.println(word);
//        String word = getGetMethodString("public", false, "Integer", "name");
//        System.out.println(word);
//        String word = getFieldDeclaredString("public", false, "Integer", "name");
//        System.out.println(word);
    }


}
