package cn.edu.dll.struct.bean_structs;

public class Field {
    private String type;
    private String name;
    private String comment;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Field{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
