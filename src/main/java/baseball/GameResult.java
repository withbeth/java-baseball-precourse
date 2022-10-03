package baseball;

public final class GameResult {

    private static final int MAX_BALL_COUNT = BaseBallGameConfig.MAX_BALL_COUNT;

    private int strikeCount = 0;
    private int ballCount = 0;

    public void reflect(final BallResult ballResult) {
        if (ballResult == null) {
            return;
        }
        if (ballResult.isStrike()) {
            strikeCount++;
            return;
        }
        if (ballResult.isBall()) {
            ballCount++;
        }
    }

    public boolean isGameOver() {
        return getStrikeCount() == MAX_BALL_COUNT;
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public int getBallCount() {
        return ballCount;
    }

    public int getAnswerCount() {
        return getStrikeCount();
    }

    public boolean hasBalls() {
        return getBallCount() > 0;
    }

    public boolean hasStrikes() {
        return getStrikeCount() > 0;
    }

    public boolean hasNothing() {
        return !hasBalls() && !hasStrikes();
    }

    public boolean hasOnlyStrikes() {
        return !hasBalls() && hasStrikes();
    }

    public boolean hasOnlyBalls() {
        return hasBalls() && !hasStrikes();
    }

    public boolean hasBallsAndStrikes() {
        return hasBalls() && hasStrikes();
    }
}
