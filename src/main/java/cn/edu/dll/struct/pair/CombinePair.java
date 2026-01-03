package cn.edu.dll.struct.pair;

public class CombinePair <K, V> {
    protected K key;
    protected V value;

    public CombinePair(K key, V value) {
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
    public String toString() {
        return "CombinePair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
