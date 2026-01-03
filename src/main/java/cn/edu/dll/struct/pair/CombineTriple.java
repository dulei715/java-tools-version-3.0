package cn.edu.dll.struct.pair;


import java.util.Objects;

public class CombineTriple<K, V, T> {
    protected K key;
    protected V value;
    protected T tag;
    /**
     * Creates a new pair
     *
     * @param key   The key for this pair
     * @param value The value to use for this pair
     */
    public CombineTriple(K key, V value, T tag) {
        this.key = key;
        this.value = value;
        this.tag = tag;
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

    public T getTag() {
        return tag;
    }

    public void setTag(T tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "CombineTriple{" +
                "key=" + key +
                ", value=" + value +
                ", tag=" + tag +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CombineTriple)) return false;
        CombineTriple<?, ?, ?> that = (CombineTriple<?, ?, ?>) o;
        return Objects.equals(key, that.key) && Objects.equals(value, that.value) && Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value, tag);
    }
}
