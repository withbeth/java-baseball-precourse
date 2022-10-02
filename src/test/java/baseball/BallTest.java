package baseball;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BallTest {

    private static Ball other;

    @BeforeAll
    static void setUp() {
        other = Ball.of(1, 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, -1, 0, 4, 5, 10})
    void invalidBallPosition(int invalidBallPosition) {
        assertThatThrownBy(() -> Ball.of(invalidBallPosition, 1)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 10})
    void invalidBallNo(int invalidBallNo) {
        assertThatThrownBy(() -> Ball.of(1, invalidBallNo)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void validBallPosition(int validBallPosition) {
        assertThatCode(() -> Ball.of(validBallPosition, 1)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void validBallNo(int validBallNo) {
        assertThatCode(() -> Ball.of(1, validBallNo)).doesNotThrowAnyException();
    }

    @Test
    void judgeStrike() {
        assertThat(other.judge(Ball.of(1, 1)))
            .isEqualTo(BallJudgeResult.STRIKE);
    }

    @Test
    void judgeBall() {
        assertThat(other.judge(Ball.of(2, 1)))
            .isEqualTo(BallJudgeResult.BALL);
        assertThat(other.judge(Ball.of(3, 1)))
            .isEqualTo(BallJudgeResult.BALL);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:2", "2:2", "3:2", "1:3", "2:3", "3:3"}, delimiter = ':')
    void judgeNothing(int eachPosition, int differentNumber) {
        assertThat(other.judge(Ball.of(eachPosition, differentNumber)))
            .isEqualTo(BallJudgeResult.NOTHING);
    }
}