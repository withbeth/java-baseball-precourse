package baseball;

import java.util.HashMap;
import java.util.Map;

// TODO
public final class GameResult {

    private static final int MAX_BALL_COUNT = BaseBallGameConfig.MAX_BALL_COUNT;

    private final Map<BallResult, Integer> ballResultCounterMap = new HashMap<>();

    public boolean isGameOver() {
        return getStrikeCount() == MAX_BALL_COUNT;
    }

    public int getBallResultCount(final BallResult ballResult) {
        if (ballResult == null) {
            return 0;
        }
        return ballResultCounterMap.getOrDefault(ballResult, 0);
    }

    public void add(final BallResult ballResult) {
        if (ballResult == null) {
            return;
        }
        ballResultCounterMap.put(ballResult, ballResultCounterMap.getOrDefault(ballResult, 0) + 1);
    }

    public int getStrikeCount() {
        return getBallResultCount(BallResult.STRIKE);
    }

    public int getBallCount() {
        return getBallResultCount(BallResult.BALL);
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
