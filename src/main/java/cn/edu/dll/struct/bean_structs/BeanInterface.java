package cn.edu.dll.struct.bean_structs;

public interface BeanInterface<T> {
    T toBean(String[] parameters);
    String toFormatString();
}
