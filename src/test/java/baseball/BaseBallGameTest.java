package baseball;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BaseBallGameTest {

    private BaseBallGame game;

    @BeforeEach
    void setUp() {
        game = new BaseBallGame();
    }

    @Test
    void play_nullOrSpace() {
        assertThatThrownBy(() -> game.play(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> game.play("")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> game.play(" ")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void play_nonNumeric() {
        assertThatThrownBy(() -> game.play("!@#")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> game.play("#$%")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> game.play("ABC")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> game.play("abc")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> game.play("12#")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> game.play("!23")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void play_invalidNumber() {
        assertThatThrownBy(() -> game.play("-123")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> game.play("-2-10")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> game.play("-101")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> game.play("000")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> game.play("001")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> game.play("002")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> game.play("003")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void play_tooFewValidUniqueNumbers() {
        assertThatThrownBy(() -> game.play("1")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> game.play("12")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void play_nonUniqueNumberInput() {
        assertThatThrownBy(() -> game.play("111")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> game.play("112")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> game.play("211")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> game.play("121")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void play_randomUniqueNumbers() {
        assertThatCode(() -> game.play("123")).doesNotThrowAnyException();
        assertThatCode(() -> game.play("456")).doesNotThrowAnyException();
        assertThatCode(() -> game.play("789")).doesNotThrowAnyException();
    }

    @Test
    void createRandomUniqueAnswer() {
        for (int i = 0; i < 100000; i++) {
            assertThatCode(BaseBallGame::createAnswer).doesNotThrowAnyException();
        }
    }

}