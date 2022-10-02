package baseball;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class BallsTest {

    @Test
    void nullOrEmptyBallNumbers() {
        assertThatThrownBy(() -> Balls.of(null))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Balls.of(Collections.emptyList()))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void tooFewOrTooManyBallNumbers() {
        assertThatThrownBy(() -> Balls.of(Collections.singletonList(BallNumber.of(1))))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Balls.of(Arrays.asList(BallNumber.of(1), BallNumber.of(2))))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Balls.of(Arrays.asList(BallNumber.of(1), BallNumber.of(2), BallNumber.of(3), BallNumber.of(4))))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validBallNumberSize() {
        assertThatCode(() -> Balls.of(Arrays.asList(BallNumber.of(1), BallNumber.of(2), BallNumber.of(3))))
            .doesNotThrowAnyException();
    }

    @Test
    void nonUniqueBallNumbers() {
        assertThatThrownBy(() -> Balls.of(Arrays.asList(BallNumber.of(1), BallNumber.of(1), BallNumber.of(1))))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Balls.of(Arrays.asList(BallNumber.of(9), BallNumber.of(1), BallNumber.of(1))))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
