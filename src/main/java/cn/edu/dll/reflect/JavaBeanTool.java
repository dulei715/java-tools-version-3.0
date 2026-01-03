package cn.edu.dll.reflect;

@SuppressWarnings("ALL")
public class JavaBeanTool {
    public static String getGetMethodNameByFieldName(String fieldName) {
        StringBuilder stringBuilder = new StringBuilder("get");
        if (fieldName.length() > 1) {
            char firstCharacter = fieldName.charAt(0);
            if (Character.isLowerCase(firstCharacter) && Character.isLowerCase(fieldName.charAt(1))) {
                char newFirstChar = Character.toUpperCase(firstCharacter);
                stringBuilder.append(newFirstChar).append(fieldName.substring(1));
                return stringBuilder.toString();
            }
        } else if (fieldName.length() == 1 && Character.isLowerCase(fieldName.charAt(0))) {
            stringBuilder.append(fieldName.toUpperCase());
            return stringBuilder.toString();
        }
        stringBuilder.append(fieldName);
        return stringBuilder.toString();
    }
    public static String getSetMethodNameByFieldName(String fieldName) {
        StringBuilder stringBuilder = new StringBuilder("set");
        if (fieldName.length() > 1) {
            char firstCharacter = fieldName.charAt(0);
            if (Character.isLowerCase(firstCharacter) && Character.isLowerCase(fieldName.charAt(1))) {
                char newFirstChar = Character.toUpperCase(firstCharacter);
                stringBuilder.append(newFirstChar).append(fieldName.substring(1));
                return stringBuilder.toString();
            }
        } else if (fieldName.length() == 1 && Character.isLowerCase(fieldName.charAt(0))) {
            stringBuilder.append(fieldName.toUpperCase());
            return stringBuilder.toString();
        }
        stringBuilder.append(fieldName);
        return stringBuilder.toString();
    }
}
