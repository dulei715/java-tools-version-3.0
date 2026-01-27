package cn.edu.dll.struct.sequence;

import java.util.Iterator;

public interface StreamSequence<T> {
    boolean isEmpty();
    void add(T element);
    Iterator<T> getReverseIterator();
}
