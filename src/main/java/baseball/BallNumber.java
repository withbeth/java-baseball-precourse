package baseball;

import java.util.Objects;

// TODO : move validation ?
public class BallNumber {

    private static final int MIN = GameConfiguration.MIN_BALL_NUM;
    private static final int MAX = GameConfiguration.MAX_BALL_NUM;

    private static class BallNumberCache {

        private static final BallNumber[] cache;

        static {
            cache = new BallNumber[MAX - MIN + 1];
            int j = MIN;
            for (int i = 0; i < cache.length; i++) {
                cache[i] = new BallNumber(j++);
            }
        }

        private BallNumberCache() {
        }

        public static boolean contains(int value) {
            return cache[0].intValue() <= value && value <= cache[cache.length - 1].intValue();
        }

        public static BallNumber of(int value) {
            return cache[value - 1];
        }
    }

    private static int parseInt(char value) {
        return value - '0';
    }

    private static void validate(int value) {
        if (!isInValidNumericRange(value)) {
            throw new NumberFormatException(String.format("value is too small or too high: %d", value));
        }
    }

    private static boolean isInValidNumericRange(int value) {
        return MIN <= value && value <= MAX;
    }

    public static BallNumber of(int value) {
        if (BallNumberCache.contains(value)) {
            return BallNumberCache.of(value);
        }
        return new BallNumber(value);
    }

    public static BallNumber of(char value) {
        return BallNumber.of(parseInt(value));
    }

    private final int value;

    private BallNumber(int value) {
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
        BallNumber that = (BallNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "BallNumber{" +
            "value=" + value +
            '}';
    }

}
