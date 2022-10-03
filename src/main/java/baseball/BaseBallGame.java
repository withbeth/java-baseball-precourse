package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class BaseBallGame {

    private static final int MIN_BALL_NO = BaseBallGameConfig.MIN_BALL_NO;
    private static final int MAX_BALL_NO = BaseBallGameConfig.MAX_BALL_NO;
    private static final int BALL_SIZE = BaseBallGameConfig.MAX_BALL_COUNT;

    private final Balls answer;
    private GameResult result;

    public BaseBallGame(final Balls answer) {
        if (answer == null) {
            throw new IllegalArgumentException("requires non-null answer");
        }
        this.answer = answer;
        this.result = new GameResult();
    }

    public BaseBallGame() {
        this(createAnswer());
    }

    private static List<Integer> parseIntegers(final String ballNumbers) {
        if (ballNumbers == null || ballNumbers.isEmpty()) {
            return Collections.emptyList();
        }
        final List<Integer> result = new ArrayList<>(ballNumbers.length());
        for (int i = 0; i < ballNumbers.length(); i++) {
            result.add(toInt(ballNumbers.charAt(i)));
        }
        return result;
    }

    private static int toInt(final char letter) {
        return letter - '0';
    }

    public static Balls createAnswer() {
        return Balls.from(createRandomUniqueIntegers());
    }

    private static List<Integer> createRandomUniqueIntegers() {
        final Set<Integer> randomUniqueIntegers = new LinkedHashSet<>(BALL_SIZE);
        while (randomUniqueIntegers.size() < BALL_SIZE) {
            randomUniqueIntegers.add(Randoms.pickNumberInRange(MIN_BALL_NO, MAX_BALL_NO));
        }
        return new ArrayList<>(randomUniqueIntegers);
    }

    public boolean isGameOver() {
        return result.isGameOver();
    }

    public void play(final String input) {
        final Balls playerBalls = Balls.from(parseIntegers(input));
        result = answer.judgeTo(playerBalls);
    }

    public GameResult getResult() {
        return result;
    }

}
