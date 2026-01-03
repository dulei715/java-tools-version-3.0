package cn.edu.dll.struct.pair;

public class IdentityCombinePair<T > extends CombinePair<T, T> {
    /**
     * Creates a new pair
     *
     * @param key   The key for this pair
     * @param value The value to use for this pair
     */
    public IdentityCombinePair(T key, T value) {
        super(key, value);
    }

    public void exchangeKeyValue() {
        T temp = this.getKey();
        this.key = this.value;
        this.value = temp;
    }




}
