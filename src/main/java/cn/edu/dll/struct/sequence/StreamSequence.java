package cn.edu.dll.struct.sequence;

import java.util.Iterator;

public interface StreamSequence<T> {
    void add(T element);
    Iterator<T> getReverseIterator();
}
