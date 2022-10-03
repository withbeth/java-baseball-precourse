package baseball;

public class Application {

    private static final int INPUT_NUMBER_GAME_CONTINUE = BaseBallGameConfig.INPUT_NUMBER_GAME_CONTINUE;
    private static final int INPUT_NUMBER_GAME_END = BaseBallGameConfig.INPUT_NUMBER_GAME_END;

    public static void main(final String[] args) {

        boolean isPlayable = true;

        while (isPlayable) {

            play();

            isPlayable = canContinue();

        }
    }

    private static boolean canContinue() {
        InputView.printContinueOrEnd(INPUT_NUMBER_GAME_CONTINUE, INPUT_NUMBER_GAME_END);
        final String continueOrEndInput = InputView.getUserInput();

        try {
            return INPUT_NUMBER_GAME_CONTINUE == Integer.parseInt(continueOrEndInput);
        } catch (final NumberFormatException numberFormatException) {
            return false;
        }
    }

    private static void play() {
        final BaseBallGame baseBallGame = new BaseBallGame();
        while (!baseBallGame.isGameOver()) {
            InputView.printPredictNumberRequest();
            final String userNumberInput = InputView.getUserInput();

            baseBallGame.play(userNumberInput);

            OutputView.printGameResult(baseBallGame.getResult());
        }
        OutputView.printGameOver(baseBallGame.getResult());
    }
}
