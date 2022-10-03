package baseball;

import java.util.Objects;

public final class Ball {

    private final BallPosition ballPosition;
    private final BallNumber ballNumber;

    private static void validate(BallPosition ballPosition, BallNumber ballNumber) {
        if (ballPosition == null || ballNumber == null) {
            throw new IllegalArgumentException("requires non-null objects");
        }
    }

    public static Ball from(int ballPosition, int ballNumber) {
        return new Ball(BallPosition.from(ballPosition), BallNumber.from(ballNumber));
    }

    // TODO : we can remove it
    public static Ball from(int ballPosition, BallNumber ballNumber) {
        return new Ball(BallPosition.from(ballPosition), ballNumber);
    }

    private Ball(BallPosition ballPosition, BallNumber ballNumber) {
        validate(ballPosition, ballNumber);
        this.ballPosition = ballPosition;
        this.ballNumber = ballNumber;
    }

    public BallResult judgeTo(Ball other) {
        if (other == null) {
            return BallResult.NOTHING;
        }
        // same position and same number
        if (this.equals(other)) {
            return BallResult.STRIKE;
        }
        // different position and same number
        if (this.ballNumber.equals(other.ballNumber)) {
            return BallResult.BALL;
        }
        // different position and different number
        return BallResult.NOTHING;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ball ball = (Ball) o;
        return Objects.equals(ballPosition, ball.ballPosition) && Objects.equals(ballNumber, ball.ballNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ballPosition, ballNumber);
    }
}
