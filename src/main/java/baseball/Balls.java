package baseball;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Balls {

    private static final int MAX_SIZE = 3;

    private final Set<Ball> balls;

    private static void validate(final List<BallNumber> ballNumbers) {
        if (!isValidSize(ballNumbers)) {
            throw new IllegalArgumentException("too many or too few ball numbers");
        }
        for (BallNumber ballNumber : ballNumbers) {
            validate(ballNumber);
        }
        if (!isAllUnique(ballNumbers)) {
            throw new IllegalArgumentException("all ball numbers should be unique");
        }
    }

    private static boolean isValidSize(List<BallNumber> ballNumbers) {
        return ballNumbers != null && ballNumbers.size() == MAX_SIZE;
    }

    private static boolean isAllUnique(List<BallNumber> ballNumbers) {
        return ballNumbers.size() == new HashSet<>(ballNumbers).size();
    }

    private static void validate(final BallNumber ballNumber) {
        if (ballNumber == null) {
            throw new IllegalArgumentException("requires non-null ball number");
        }
    }

    public static Balls of(final List<BallNumber> ballNumbers) {
        return new Balls(ballNumbers);
    }

    private Balls(final List<BallNumber> ballNumbers) {
        validate(ballNumbers);
        this.balls = createBalls(ballNumbers);
    }

    private Set<Ball> createBalls(final List<BallNumber> ballNumbers) {
        Set<Ball> result = new HashSet<>(MAX_SIZE);
        for (int i = 0; i < MAX_SIZE; i++) {
            result.add(Ball.of(i + 1, ballNumbers.get(i)));
        }
        return result;
    }

}
