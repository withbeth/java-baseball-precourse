package baseball;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BallNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {-100, -50, -10, -1, 0, 10, 50, 100, 500})
    void invalidNumber(int invalidNumber) {
        assertThatThrownBy(() -> BallNumber.from(invalidNumber)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(chars = {'0', 'a', 'z', '!', '#', '$', ';', ':', '?', '+', '-', '='})
    void invalidCharNumber(int invalidNumber) {
        assertThatThrownBy(() -> BallNumber.from(invalidNumber)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void validNumber(int validNumber) {
        assertThatCode(() -> BallNumber.from(validNumber)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(chars = {'1', '2', '3', '4', '5', '6', '7', '8', '9'})
    void validCharNumber(char validChar) {
        assertThatCode(() -> BallNumber.from(validChar)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:1", "2:2", "3:3", "4:4", "5:5", "6:6", "7:7", "8:8", "9:9"}, delimiter = ':')
    void equals(int validNumber, char validChar) {
        assertThat(BallNumber.from(validNumber)).isEqualTo(BallNumber.from(validNumber));
        assertThat(BallNumber.from(validNumber)).isEqualTo(BallNumber.from(validChar));
        assertThat(BallNumber.from(validChar)).isEqualTo(BallNumber.from(validChar));
        assertThat(BallNumber.from(validChar)).isEqualTo(BallNumber.from(validNumber));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void allCached(int validNumber) {
        assertThat(BallNumber.from(validNumber)).isSameAs(BallNumber.from(validNumber));
    }

}