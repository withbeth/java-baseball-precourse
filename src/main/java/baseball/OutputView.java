package baseball;

public final class OutputView {

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
        final StringBuilder result = new StringBuilder();
        result.append(createBallResultMessage(gameResult));
        result.append(createStrikeResultMessage(gameResult));
        result.append(createNothingResultMessage(gameResult));
        return result.toString();
    }

    private static String createNothingResultMessage(final GameResult gameResult) {
        if (gameResult.hasStrikes() || gameResult.hasBalls()) {
            return "";
        }
        return "낫싱";
    }

    private static String createBallResultMessage(final GameResult gameResult) {
        if (!gameResult.hasBalls()) {
            return "";
        }
        return String.format("%d볼", gameResult.getBallCount());
    }

    private static String createStrikeResultMessage(final GameResult gameResult) {
        if (!gameResult.hasStrikes()) {
            return "";
        }
        return String.format("%d스트라이크", gameResult.getStrikeCount());
    }

    private static String createGameOverMessage(final GameResult gameResult) {
        return String.format("%d개의 숫자를 모두 맞히셨습니다! 게임종료", gameResult.getAnswerCount());
    }
}
