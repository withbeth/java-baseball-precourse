package baseball;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BallsTest {

    private static Balls otherBalls;

    @BeforeAll
    static void setUp() {
        otherBalls = Balls.from(Arrays.asList(1, 2, 3));
    }

    @Test
    void nullOrEmptyBallNumbers() {
        assertThatThrownBy(() -> Balls.from(null))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Balls.from(Collections.emptyList()))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void tooFewOrTooManyBallNumbers() {
        assertThatThrownBy(() -> Balls.from(Collections.singletonList(1)))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Balls.from(Arrays.asList(1, 2)))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Balls.from(Arrays.asList(1, 2, 3, 4)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validBallNumberSize() {
        assertThatCode(() -> Balls.from(Arrays.asList(1, 2, 3)))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void nonUniqueBallNumbers(int ballNumber) {
        assertThatThrownBy(() -> Balls.from(Arrays.asList(ballNumber, ballNumber, ballNumber)))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Balls.from(Arrays.asList(1, ballNumber, ballNumber)))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Balls.from(Arrays.asList(ballNumber, 1, ballNumber)))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Balls.from(Arrays.asList(ballNumber, ballNumber, 1)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void judgeBall_null() {
        assertThat(otherBalls.judgeTo((Ball) null)).isEqualTo(BallResult.NOTHING);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:1", "2:2", "3:3"}, delimiter = ':')
    void judgeBall_strike(int ballPosition, int ballNumber) {
        assertThat(otherBalls.judgeTo(Ball.from(ballPosition, ballNumber)))
            .isEqualTo(BallResult.STRIKE);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:2", "1:3", "2:3", "2:1", "3:1", "3:2"}, delimiter = ':')
    void judgeBall_ball(int ballPosition, int ballNumber) {
        assertThat(otherBalls.judgeTo(Ball.from(ballPosition, ballNumber)))
            .isEqualTo(BallResult.BALL);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    void judgeBall_nothing(int ballNumber) {
        assertThat(otherBalls.judgeTo(Ball.from(1, ballNumber)))
            .isEqualTo(BallResult.NOTHING);
        assertThat(otherBalls.judgeTo(Ball.from(2, ballNumber)))
            .isEqualTo(BallResult.NOTHING);
        assertThat(otherBalls.judgeTo(Ball.from(3, ballNumber)))
            .isEqualTo(BallResult.NOTHING);
    }

    @Test
    void judgeBalls_null() {
        GameResult gameResult = otherBalls.judgeTo((Balls) null);
        assertThat(gameResult.getStrikeCount()).isEqualTo(0);
        assertThat(gameResult.getBallCount()).isEqualTo(0);
        assertThat(gameResult.isGameOver()).isFalse();
    }

    @Test
    void judgeBalls_3strike() {
        // 반사성
        GameResult gameResult = otherBalls.judgeTo(otherBalls);
        assertThat(gameResult.getStrikeCount()).isEqualTo(3);
        assertThat(gameResult.isGameOver()).isTrue();

        // 대치성
        gameResult = otherBalls.judgeTo(Balls.from(Arrays.asList(1, 2, 3)));
        assertThat(gameResult.getStrikeCount()).isEqualTo(3);
        assertThat(gameResult.isGameOver()).isTrue();

        gameResult = Balls.from(Arrays.asList(1, 2, 3)).judgeTo(otherBalls);
        assertThat(gameResult.getStrikeCount()).isEqualTo(3);
        assertThat(gameResult.isGameOver()).isTrue();
    }

    @Test
    void judgeBalls_2strike() {
        GameResult gameResult = otherBalls.judgeTo(Balls.from(Arrays.asList(1, 2, 4)));
        assertThat(gameResult.getStrikeCount()).isEqualTo(2);
        assertThat(gameResult.getBallCount()).isEqualTo(0);
        assertThat(gameResult.isGameOver()).isFalse();
    }
    
    @Test
    void judgeBalls_1strike_2ball() {
        GameResult gameResult = otherBalls.judgeTo(Balls.from(Arrays.asList(1, 3, 2)));
        assertThat(gameResult.getStrikeCount()).isEqualTo(1);
        assertThat(gameResult.getBallCount()).isEqualTo(2);
        assertThat(gameResult.isGameOver()).isFalse();
    }

    @Test
    void judgeBalls_1strike_1ball() {
        GameResult gameResult = otherBalls.judgeTo(Balls.from(Arrays.asList(1, 3, 4)));
        assertThat(gameResult.getStrikeCount()).isEqualTo(1);
        assertThat(gameResult.getBallCount()).isEqualTo(1);
        assertThat(gameResult.isGameOver()).isFalse();
    }

    @Test
    void judgeBalls_3ball() {
        GameResult gameResult = otherBalls.judgeTo(Balls.from(Arrays.asList(2, 3, 1)));
        assertThat(gameResult.getStrikeCount()).isEqualTo(0);
        assertThat(gameResult.getBallCount()).isEqualTo(3);
        assertThat(gameResult.isGameOver()).isFalse();
    }

    @Test
    void judgeBalls_2ball() {
        GameResult gameResult = otherBalls.judgeTo(Balls.from(Arrays.asList(2, 3, 9)));
        assertThat(gameResult.getStrikeCount()).isEqualTo(0);
        assertThat(gameResult.getBallCount()).isEqualTo(2);
        assertThat(gameResult.isGameOver()).isFalse();
    }

    @Test
    void judgeBalls_1ball() {
        GameResult gameResult = otherBalls.judgeTo(Balls.from(Arrays.asList(3, 8, 9)));
        assertThat(gameResult.getStrikeCount()).isEqualTo(0);
        assertThat(gameResult.getBallCount()).isEqualTo(1);
        assertThat(gameResult.isGameOver()).isFalse();
    }

    @Test
    void judgeBalls_nothing() {
        GameResult gameResult = otherBalls.judgeTo(Balls.from(Arrays.asList(7, 8, 9)));
        assertThat(gameResult.getStrikeCount()).isEqualTo(0);
        assertThat(gameResult.getBallCount()).isEqualTo(0);
        assertThat(gameResult.isGameOver()).isFalse();
    }


}
