package baseball;

import java.util.ArrayList;
import java.util.Collections;
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

    public static Balls from(final List<Integer> ballNumbers) {
        return new Balls(mapToBallNumbers(ballNumbers));
    }

    private Balls(final List<BallNumber> ballNumbers) {
        validate(ballNumbers);
        this.balls = mapToBalls(ballNumbers);
    }

    public GameResult judgeTo(final Balls other) {
        GameResult result = new GameResult();
        if (other == null) {
            result.add(BallResult.NOTHING);
            return result;
        }
        for (Ball otherBall : other.balls) {
            result.add(judgeTo(otherBall));
        }
        return result;
    }

    BallResult judgeTo(final Ball other) {
        List<BallResult> result = mapToBallJudgeResults(balls, other);
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

    private static Set<Ball> mapToBalls(final List<BallNumber> ballNumbers) {
        Set<Ball> result = new HashSet<>(MAX_SIZE);
        for (int i = 0; i < MAX_SIZE; i++) {
            result.add(Ball.from(i + 1, ballNumbers.get(i)));
        }
        return result;
    }

    private static List<BallNumber> mapToBallNumbers(final List<Integer> ballNumbers) {
        if (ballNumbers == null || ballNumbers.isEmpty()) {
            return Collections.emptyList();
        }
        List<BallNumber> result = new ArrayList<>();
        for (Integer ballNumber : ballNumbers) {
            result.add(BallNumber.from(ballNumber));
        }
        return result;
    }

    private static List<BallResult> mapToBallJudgeResults(Set<Ball> balls, Ball other) {
        if (other == null) {
            return Collections.emptyList();
        }
        List<BallResult> result = new ArrayList<>();
        for (Ball ball : balls) {
            result.add(ball.judgeTo(other));
        }
        return result;
    }

}
