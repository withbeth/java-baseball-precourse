package baseball;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameResultTest {

    private GameResult gameResult;

    @BeforeEach
    void setUp() {
        gameResult = new GameResult();
    }

    @Test
    void reflect_nothing() {
        assertThat(gameResult.getAnswerCount()).isEqualTo(0);
        assertThat(gameResult.getBallCount()).isEqualTo(0);
        assertThat(gameResult.getStrikeCount()).isEqualTo(0);

        assertThat(gameResult.isGameOver()).isFalse();
        assertThat(gameResult.hasStrikes()).isFalse();
        assertThat(gameResult.hasBallsAndStrikes()).isFalse();
        assertThat(gameResult.hasOnlyBalls()).isFalse();
        assertThat(gameResult.hasOnlyStrikes()).isFalse();
        assertThat(gameResult.hasNothing()).isTrue();
    }

    @Test
    void reflect_strikes() {
        gameResult.reflect(BallResult.STRIKE);
        gameResult.reflect(BallResult.STRIKE);
        gameResult.reflect(BallResult.STRIKE);

        assertThat(gameResult.getAnswerCount()).isEqualTo(3);
        assertThat(gameResult.getBallCount()).isEqualTo(0);
        assertThat(gameResult.getStrikeCount()).isEqualTo(3);

        assertThat(gameResult.isGameOver()).isTrue();
        assertThat(gameResult.hasStrikes()).isTrue();
        assertThat(gameResult.hasBallsAndStrikes()).isFalse();
        assertThat(gameResult.hasOnlyBalls()).isFalse();
        assertThat(gameResult.hasOnlyStrikes()).isTrue();
        assertThat(gameResult.hasNothing()).isFalse();
    }

    @Test
    void reflect_strikes_balls() {

        gameResult.reflect(BallResult.STRIKE);
        gameResult.reflect(BallResult.STRIKE);
        gameResult.reflect(BallResult.BALL);

        assertThat(gameResult.getAnswerCount()).isEqualTo(2);
        assertThat(gameResult.getBallCount()).isEqualTo(1);
        assertThat(gameResult.getStrikeCount()).isEqualTo(2);

        assertThat(gameResult.isGameOver()).isFalse();
        assertThat(gameResult.hasStrikes()).isTrue();
        assertThat(gameResult.hasBallsAndStrikes()).isTrue();
        assertThat(gameResult.hasOnlyBalls()).isFalse();
        assertThat(gameResult.hasOnlyStrikes()).isFalse();
        assertThat(gameResult.hasNothing()).isFalse();
    }

    @Test
    void reflect_balls() {
        gameResult.reflect(BallResult.BALL);
        gameResult.reflect(BallResult.BALL);
        gameResult.reflect(BallResult.BALL);

        assertThat(gameResult.getAnswerCount()).isEqualTo(0);
        assertThat(gameResult.getBallCount()).isEqualTo(3);
        assertThat(gameResult.getStrikeCount()).isEqualTo(0);

        assertThat(gameResult.isGameOver()).isFalse();
        assertThat(gameResult.hasStrikes()).isFalse();
        assertThat(gameResult.hasBallsAndStrikes()).isFalse();
        assertThat(gameResult.hasOnlyBalls()).isTrue();
        assertThat(gameResult.hasOnlyStrikes()).isFalse();
        assertThat(gameResult.hasNothing()).isFalse();
    }


}