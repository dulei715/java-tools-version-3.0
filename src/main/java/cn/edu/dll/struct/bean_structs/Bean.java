package cn.edu.dll.struct.bean_structs;

import java.util.ArrayList;
import java.util.List;

public class Bean {
    private String className;
    private String classComment;
    private List<Field> listField = new ArrayList<>();

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassComment() {
        return classComment;
    }

    public void setClassComment(String classComment) {
        this.classComment = classComment;
    }

    public List<Field> getListField() {
        return listField;
    }

    public void setListField(List<Field> listField) {
        this.listField = listField;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Bean{");
        stringBuilder.append("className='" + className + '\'' + ", classComment='" + classComment + '\'');
        stringBuilder.append(", listField={");
        for (Field field : listField) {
            stringBuilder.append(field.toString()).append("; ");
        }
        stringBuilder.append("}");
        stringBuilder.append("'}'");
        return  stringBuilder.toString();
    }
}
