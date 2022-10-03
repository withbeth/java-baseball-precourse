package baseball;

import camp.nextstep.edu.missionutils.Console;

public final class InputView {

    public static void printPredictNumberRequest() {
        System.out.print("숫자를 입력해주세요: ");
    }

    public static void printContinueOrEnd(final int continueNumber, final int endNumber) {
        System.out.printf("게임을 새로 시작하려면 %d, 종료하려면 %d를 입력하세요.%n", continueNumber, endNumber);
    }

    public static String getUserInput() {
        return Console.readLine();
    }

}
