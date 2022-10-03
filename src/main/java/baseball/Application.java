package baseball;

public class Application {

    private static final int GAME_CONTINUE_NUMBER = 1;
    private static final int GAME_END_NUMBER = 2;

    public static void play() {

        final Balls answer = BaseBallGame.createAnswer();
        System.out.println(answer);

        final BaseBallGame baseBallGame = new BaseBallGame(answer);

        while (!baseBallGame.isGameOver()) {

            InputView.printPredictNumberRequest();
            final String userNumberInput = InputView.getUserInput();

            baseBallGame.play(userNumberInput);
            OutputView.printGameResult(baseBallGame.getResult());

        }

        OutputView.printGameOver(baseBallGame.getResult());

    }

    public static void main(final String[] args) {

        while (true) {

            play();

            InputView.printContinueOrEnd(GAME_CONTINUE_NUMBER, GAME_END_NUMBER);
            final String continueOrEndNumber = InputView.getUserInput();
            if (GAME_CONTINUE_NUMBER != Integer.parseInt(continueOrEndNumber)) {
                break;
            }
        }

        // TODO : retry input

        // Note : 사용자가입력하는값은camp.nextstep.edu.missionutils.Console의readLine()을활용한다

        // Note : Random값추출은 camp.nextstep.edu.missionutils.Randoms의pickNumberInRange()를활용한다

    }
}
