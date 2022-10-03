package baseball;

public final class OutputView {

    private static final String EMPTY_STRING = "";
    private static final String SPACE = " ";

    public static void printGameResult(final GameResult gameResult) {
        if (gameResult == null) {
            return;
        }
        System.out.println(createGameResultMessages(gameResult));
    }

    public static void printGameOver(final GameResult gameResult) {
        if (gameResult == null || !gameResult.isGameOver()) {
            return;
        }
        System.out.println(createGameOverMessage(gameResult));
    }

    private static String createGameResultMessages(final GameResult gameResult) {
        if (gameResult.hasNothing()) {
            return createNothingResultMessage();
        }
        if (gameResult.hasOnlyBalls()) {
            return createBallResultMessage(gameResult.getBallCount());
        }
        if (gameResult.hasOnlyStrikes()) {
            return createStrikeResultMessage(gameResult.getStrikeCount());
        }
        if (gameResult.hasBallsAndStrikes()) {
            return createBallAndStrikeMessage(gameResult.getBallCount(),
                gameResult.getStrikeCount());
        }
        return EMPTY_STRING;
    }

    private static String createBallAndStrikeMessage(final int ballCount, final int strikeCount) {
        return new StringBuilder()
            .append(createBallResultMessage(ballCount))
            .append(SPACE)
            .append(createStrikeResultMessage(strikeCount))
            .toString();
    }

    private static String createNothingResultMessage() {
        return "낫싱";
    }

    private static String createBallResultMessage(final int count) {
        return String.format("%d볼", count);
    }

    private static String createStrikeResultMessage(final int count) {
        return String.format("%d스트라이크", count);
    }

    private static String createGameOverMessage(final GameResult gameResult) {
        return String.format("%d개의 숫자를 모두 맞히셨습니다! 게임 종료", gameResult.getAnswerCount());
    }
}
