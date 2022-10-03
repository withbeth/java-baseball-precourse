package baseball;

public enum BallResult {

    STRIKE,

    BALL,

    NOTHING;

    public boolean isStrike() {
        return this == STRIKE;
    }

    public boolean isBall() {
        return this == BALL;
    }
}
