package baseball;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OutputViewTest {

    private GameResult gameResult;

    @BeforeEach
    void setUp() {
        gameResult = new GameResult();
    }

    @Test
    void createGameResultMessages_nothing() {
        assertThat(OutputView.createGameResultMessages(gameResult))
            .isEqualTo("낫싱");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void createGameResultMessages_strikes(final int count) {
        for (int i = 0; i < count; i++) {
            gameResult.reflect(BallResult.STRIKE);
            gameResult.reflect(BallResult.NOTHING);
        }
        assertThat(OutputView.createGameResultMessages(gameResult))
            .isEqualTo(String.format("%d스트라이크", count));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void createGameResultMessages_ball(final int count) {
        for (int i = 0; i < count; i++) {
            gameResult.reflect(BallResult.BALL);
            gameResult.reflect(BallResult.NOTHING);
        }
        assertThat(OutputView.createGameResultMessages(gameResult))
            .isEqualTo(String.format("%d볼", count));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void createGameResultMessages_strikes_ball(final int count) {
        for (int i = 0; i < count; i++) {
            gameResult.reflect(BallResult.BALL);
            gameResult.reflect(BallResult.STRIKE);
            gameResult.reflect(BallResult.NOTHING);
        }
        assertThat(OutputView.createGameResultMessages(gameResult))
            .isEqualTo(String.format("%d볼 %d스트라이크", count, count));
    }

}