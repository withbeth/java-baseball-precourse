package baseball;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public final class Balls {

    private static final int BALL_SIZE = BaseBallGameConfig.MAX_BALL_COUNT;

    private final List<Ball> balls;

    private Balls(final List<BallNumber> ballNumbers) {
        validate(ballNumbers);
        this.balls = mapToBalls(ballNumbers);
    }

    private static void validate(final List<BallNumber> ballNumbers) {
        if (!isValidSize(ballNumbers)) {
            throw new IllegalArgumentException("too many or too few ball numbers");
        }
        for (final BallNumber ballNumber : ballNumbers) {
            validate(ballNumber);
        }
        if (!isAllUnique(ballNumbers)) {
            throw new IllegalArgumentException("all ball numbers should be unique");
        }
    }

    private static boolean isValidSize(final List<BallNumber> ballNumbers) {
        return ballNumbers != null && ballNumbers.size() == BALL_SIZE;
    }

    private static boolean isAllUnique(final List<BallNumber> ballNumbers) {
        return ballNumbers.size() == new HashSet<>(ballNumbers).size();
    }

    private static void validate(final BallNumber ballNumber) {
        if (ballNumber == null) {
            throw new IllegalArgumentException("requires non-null ball number");
        }
    }

    public static Balls from(final List<Integer> ballNumbers) {
        return new Balls(mapToBallNumbers(ballNumbers));
    }

    private static List<Ball> mapToBalls(final List<BallNumber> ballNumbers) {
        final List<Ball> result = new ArrayList<>(BALL_SIZE);
        for (int i = 0; i < BALL_SIZE; i++) {
            result.add(Ball.from(i + 1, ballNumbers.get(i)));
        }
        return result;
    }

    private static List<BallNumber> mapToBallNumbers(final List<Integer> ballNumbers) {
        if (ballNumbers == null || ballNumbers.isEmpty()) {
            return Collections.emptyList();
        }
        final List<BallNumber> result = new ArrayList<>();
        for (final Integer ballNumber : ballNumbers) {
            result.add(BallNumber.from(ballNumber));
        }
        return result;
    }

    private static List<BallResult> mapToBallJudgeResults(final List<Ball> balls,
        final Ball other) {
        if (other == null) {
            return Collections.emptyList();
        }
        final List<BallResult> result = new ArrayList<>();
        for (final Ball ball : balls) {
            result.add(ball.judgeTo(other));
        }
        return result;
    }

    public GameResult judgeTo(final Balls other) {
        final GameResult result = new GameResult();
        if (other == null) {
            result.add(BallResult.NOTHING);
            return result;
        }
        for (final Ball otherBall : other.balls) {
            result.add(judgeTo(otherBall));
        }
        return result;
    }

    BallResult judgeTo(final Ball other) {
        final List<BallResult> result = mapToBallJudgeResults(balls, other);
        if (result.isEmpty()) {
            return BallResult.NOTHING;
        }
        if (result.contains(BallResult.STRIKE)) {
            return BallResult.STRIKE;
        }
        if (result.contains(BallResult.BALL)) {
            return BallResult.BALL;
        }
        return BallResult.NOTHING;
    }

    @Override
    public String toString() {
        return "Balls{" +
            "balls=" + balls +
            '}';
    }
}
