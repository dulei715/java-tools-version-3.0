package cn.edu.dll.struct.pair;


import java.util.Objects;

public class BasicPair<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<BasicPair> {
    protected K key;
    protected V value;
    /**
     * Creates a new pair
     *
     * @param key   The key for this pair
     * @param value The value to use for this pair
     */
    public BasicPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public int compareTo(BasicPair basicPair) {
        int keyCompare = this.key.compareTo((K)basicPair.key);
        if (keyCompare != 0) {
            return keyCompare;
        }
        return this.value.compareTo((V)basicPair.value);
    }

    @Override
    public String toString() {
        return this.key + "=" + this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicPair<?, ?> basicPair = (BasicPair<?, ?>) o;
        return Objects.equals(key, basicPair.key) &&
                Objects.equals(value, basicPair.value);
    }

    @Override
    public int hashCode() {
        return key.hashCode() * 13 + (value == null ? 0 : value.hashCode());
    }



}
