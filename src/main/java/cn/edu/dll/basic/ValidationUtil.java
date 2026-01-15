package cn.edu.dll.basic;

public final class ValidationUtil {
    private ValidationUtil() {}

    // int
    public static int requirePositive(int value, String message) {
        if (value <= 0) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }


    // long
    public static long requirePositive(long value, String message) {
        if (value <= 0L) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }


    // float
    public static float requirePositive(float value, String message) {
        if (value <= 0.0f || Float.isNaN(value)) {
            throw new IllegalArgumentException(message);
        }
        return value;

    }


    // double
    public static double requirePositive(double value, String message) {
        if (value <= 0.0 || Double.isNaN(value)) {
            throw new IllegalArgumentException(message);
        }
        return value;

    }


    // BigInteger
    public static java.math.BigInteger requirePositive(java.math.BigInteger value, String message) {
        if (value == null || value.signum() <= 0) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }


    // BigDecimal
    public static java.math.BigDecimal requirePositive(java.math.BigDecimal value, String message) {
        if (value == null || value.signum() <= 0) {
            throw new IllegalArgumentException(message);
        }
        return value;

    }

    public static <T extends Comparable<T>> boolean requireNoMoreThan(T dataA, T dataB, String message) {
        if (dataA == null || dataB == null || dataA.compareTo(dataB) > 0) {
            throw new IllegalArgumentException(message);
        }
        return true;
    }
}
