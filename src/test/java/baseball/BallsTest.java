package baseball;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class BallsTest {

    @Test
    void nullOrEmptyBallNumbers() {
        assertThatThrownBy(() -> Balls.from(null))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Balls.from(Collections.emptyList()))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void tooFewOrTooManyBallNumbers() {
        assertThatThrownBy(() -> Balls.from(Collections.singletonList(BallNumber.from(1))))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Balls.from(Arrays.asList(BallNumber.from(1), BallNumber.from(2))))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Balls.from(Arrays.asList(BallNumber.from(1), BallNumber.from(2), BallNumber.from(3), BallNumber.from(4))))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validBallNumberSize() {
        assertThatCode(() -> Balls.from(Arrays.asList(BallNumber.from(1), BallNumber.from(2), BallNumber.from(3))))
            .doesNotThrowAnyException();
    }

    @Test
    void nonUniqueBallNumbers() {
        assertThatThrownBy(() -> Balls.from(Arrays.asList(BallNumber.from(1), BallNumber.from(1), BallNumber.from(1))))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Balls.from(Arrays.asList(BallNumber.from(9), BallNumber.from(1), BallNumber.from(1))))
            .isInstanceOf(IllegalArgumentException.class);
    }



}
