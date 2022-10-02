package baseball;

import java.util.Objects;

public final class BallPosition {

    private static final int MIN = 1;
    private static final int MAX = 3;

    private static final class BallPositionCache {

        private static final BallPosition[] cache;

        static {
            cache = new BallPosition[MAX - MIN + 1];
            int j = MIN;
            for (int i = 0; i < cache.length; i++) {
                cache[i] = new BallPosition(j++);
            }
        }

        private BallPositionCache() {
        }

        public static boolean contains(final int value) {
            return cache[0].value <= value && value <= cache[cache.length - 1].value;
        }

        public static BallPosition of(final int value) {
            return cache[value - 1];
        }
    }

    private static void validate(final int value) {
        if (!isInRange(value)) {
            throw new NumberFormatException(String.format("value is too small or too high: %d", value));
        }
    }

    private static boolean isInRange(final int value) {
        return MIN <= value && value <= MAX;
    }

    private final int value;

    private BallPosition(final int value) {
        validate(value);
        this.value = value;
    }

    public static BallPosition of(final int value) {
        if (BallPositionCache.contains(value)) {
            return BallPositionCache.of(value);
        }
        throw new IllegalArgumentException("not allowed ball position");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BallPosition that = (BallPosition) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "BallPosition{" +
            "value=" + value +
            '}';
    }
}
