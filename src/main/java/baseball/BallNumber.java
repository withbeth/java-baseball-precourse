package baseball;

import java.util.Objects;

public final class BallNumber {

    private static final int MIN = BaseBallGameConfig.MIN_BALL_NO;
    private static final int MAX = Math.max(BaseBallGameConfig.MAX_BALL_NO, MIN);
    private final int value;

    private BallNumber(final int value) {
        validate(value);
        this.value = value;
    }

    private static int parseInt(final char value) {
        return value - '0';
    }

    private static void validate(final int value) {
        if (!isInRange(value)) {
            throw new NumberFormatException(
                String.format("value is too small or too high: %d", value));
        }
    }

    private static boolean isInRange(final int value) {
        return MIN <= value && value <= MAX;
    }

    public static BallNumber from(final int value) {
        if (BallNumberCache.contains(value)) {
            return BallNumberCache.of(value);
        }
        throw new IllegalArgumentException("not allowed ball number");
    }

    public static BallNumber from(final char value) {
        return BallNumber.from(parseInt(value));
    }

    public int intValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BallNumber that = (BallNumber) o;
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

    private static final class BallNumberCache {

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

        public static boolean contains(final int value) {
            return cache[0].intValue() <= value && value <= cache[cache.length - 1].intValue();
        }

        public static BallNumber of(final int value) {
            return cache[value - 1];
        }
    }

}
