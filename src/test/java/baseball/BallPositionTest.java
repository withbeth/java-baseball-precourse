package baseball;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BallPositionTest {

    @ParameterizedTest
    @ValueSource(ints = {-5, -1, 0, 4, 5, 10})
    void invalidPosition(int invalidPositionNo) {
        assertThatThrownBy(() -> BallPosition.of(invalidPositionNo)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void validPosition(int validPositionNo) {
        assertThatCode(() -> BallPosition.of(validPositionNo)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void equals(int validPositionNo) {
        assertThat(BallPosition.of(validPositionNo)).isEqualTo(BallPosition.of(validPositionNo));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void allCached(int validPositionNo) {
        assertThat(BallPosition.of(validPositionNo)).isEqualTo(BallPosition.of(validPositionNo));
    }


}