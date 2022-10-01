package baseball;

import java.util.Objects;

public class BaseBallNumber {

    private static final int MIN = 1;
    private static final int MAX = 9;

    private static class BaseBallNumberCache {

        private static final BaseBallNumber[] cache;

        static {
            cache = new BaseBallNumber[MAX - MIN + 1];
            int j = MIN;
            for (int i = 0; i < cache.length; i++) {
                cache[i] = new BaseBallNumber(j++);
            }
        }

        private BaseBallNumberCache() {
        }

        public static boolean contains(int value) {
            return cache[0].intValue() <= value && value <= cache[cache.length - 1].intValue();
        }

        public static BaseBallNumber of(int value) {
            return cache[value - 1];
        }
    }

    private static int parseInt(char value) {
        return value - '0';
    }

    private static void validate(int value) {
        if (!isInValidRange(value)) {
            throw new NumberFormatException(String.format("value is too small or too high: %d", value));
        }
    }

    private static boolean isInValidRange(int value) {
        return MIN <= value && value <= MAX;
    }

    public static BaseBallNumber of(int value) {
        if (BaseBallNumberCache.contains(value)) {
            return BaseBallNumberCache.of(value);
        }
        return new BaseBallNumber(value);
    }

    public static BaseBallNumber of(char value) {
        return BaseBallNumber.of(parseInt(value));
    }

    private final int value;

    private BaseBallNumber(int value) {
        validate(value);
        this.value = value;
    }

    public int intValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseBallNumber that = (BaseBallNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "BaseBallNumber{" +
            "value=" + value +
            '}';
    }

}
