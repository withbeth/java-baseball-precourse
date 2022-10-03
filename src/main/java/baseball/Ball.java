package baseball;

import java.util.Objects;

public final class Ball {

    private final BallPosition ballPosition;
    private final BallNumber ballNumber;

    private Ball(final BallPosition ballPosition, final BallNumber ballNumber) {
        validate(ballPosition, ballNumber);
        this.ballPosition = ballPosition;
        this.ballNumber = ballNumber;
    }

    private static void validate(final BallPosition ballPosition, final BallNumber ballNumber) {
        if (ballPosition == null || ballNumber == null) {
            throw new IllegalArgumentException("requires non-null objects");
        }
    }

    public static Ball from(final int ballPosition, final int ballNumber) {
        return new Ball(BallPosition.from(ballPosition), BallNumber.from(ballNumber));
    }

    public static Ball from(final int ballPosition, final BallNumber ballNumber) {
        return new Ball(BallPosition.from(ballPosition), ballNumber);
    }

    public BallResult judgeTo(final Ball other) {
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
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Ball ball = (Ball) o;
        return Objects.equals(ballPosition, ball.ballPosition) && Objects.equals(ballNumber,
            ball.ballNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ballPosition, ballNumber);
    }

    @Override
    public String toString() {
        return "Ball{" +
            "ballNumber=" + ballNumber +
            '}';
    }
}
