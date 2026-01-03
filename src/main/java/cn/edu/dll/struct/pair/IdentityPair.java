package cn.edu.dll.struct.pair;

public class IdentityPair<T extends Comparable<T>> extends BasicPair<T, T> {
    /**
     * Creates a new pair
     *
     * @param key   The key for this pair
     * @param value The value to use for this pair
     */
    public IdentityPair(T key, T value) {
        super(key, value);
    }

    public void exchangeKeyValue() {
        T temp = this.getKey();
        this.key = this.value;
        this.value = temp;
    }




}
