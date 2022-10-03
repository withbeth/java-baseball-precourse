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
        other = Ball.from(1, 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, -1, 0, 4, 5, 10})
    void invalidBallPosition(int invalidBallPosition) {
        assertThatThrownBy(() -> Ball.from(invalidBallPosition, 1)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 10})
    void invalidBallNo(int invalidBallNo) {
        assertThatThrownBy(() -> Ball.from(1, invalidBallNo)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void validBallPosition(int validBallPosition) {
        assertThatCode(() -> Ball.from(validBallPosition, 1)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void validBallNo(int validBallNo) {
        assertThatCode(() -> Ball.from(1, validBallNo)).doesNotThrowAnyException();
    }

    @Test
    void judgeNull() {
        assertThat(other.judgeTo(null)).isEqualTo(BallResult.NOTHING);
    }

    @Test
    void judgeStrike() {
        assertThat(other.judgeTo(other)).isEqualTo(BallResult.STRIKE);
        assertThat(other.judgeTo(Ball.from(1, 1))).isEqualTo(BallResult.STRIKE);
        assertThat(Ball.from(1, 1).judgeTo(other)).isEqualTo(BallResult.STRIKE);
    }

    @Test
    void judgeBall() {
        assertThat(other.judgeTo(Ball.from(2, 1))).isEqualTo(BallResult.BALL);
        assertThat(Ball.from(2, 1).judgeTo(other)).isEqualTo(BallResult.BALL);

        assertThat(other.judgeTo(Ball.from(3, 1))).isEqualTo(BallResult.BALL);
        assertThat(Ball.from(3, 1).judgeTo(other)).isEqualTo(BallResult.BALL);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:2", "2:2", "3:2", "1:3", "2:3", "3:3"}, delimiter = ':')
    void judgeNothing(int eachPosition, int differentNumber) {
        assertThat(other.judgeTo(Ball.from(eachPosition, differentNumber))).isEqualTo(BallResult.NOTHING);
        assertThat(Ball.from(eachPosition, differentNumber).judgeTo(other)).isEqualTo(BallResult.NOTHING);
    }
}