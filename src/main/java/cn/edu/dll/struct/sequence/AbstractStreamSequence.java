package cn.edu.dll.struct.sequence;

import cn.edu.dll.basic.ValidationUtil;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public abstract class AbstractStreamSequence<T> implements StreamSequence<T>{
    protected final Integer reserveHistorySize;
    protected Deque<T> deque;

    public AbstractStreamSequence(Integer reserveHistorySize) {
        ValidationUtil.requirePositive(reserveHistorySize, "Need positive value!");
        this.reserveHistorySize = reserveHistorySize;
        this.deque = new ArrayDeque<>();
    }

    @Override
    public boolean isEmpty() {
        return this.deque.isEmpty();
    }

    @Override
    public void add(T element) {
        if (this.deque.size() >= this.reserveHistorySize) {
            this.deque.poll();
        }
        this.deque.offer(element);
    }

    @Override
    public Iterator<T> getReverseIterator() {
        return this.deque.descendingIterator();
    }
}
