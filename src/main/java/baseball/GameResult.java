package baseball;

import java.util.HashMap;
import java.util.Map;

// TODO : maybe we can refactor data structure later for view
public final class GameResult {

    private static final int MAX_STRIKE_COUNT = 3;

    private final Map<BallResult, Integer> ballResultCounterMap = new HashMap<>();

    public boolean isGameOver() {
        return getStrikeCount() == MAX_STRIKE_COUNT;
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
}
