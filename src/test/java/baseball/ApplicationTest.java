package baseball;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {

    @Test
    void 게임종료_후_재시작() {
        assertRandomNumberInRangeTest(
            () -> {
                run("246", "135", "1", "597", "589", "2");
                assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
            },
            1, 3, 5, 5, 8, 9
        );
    }

    @Test
    void 게임종료() {
        assertRandomNumberInRangeTest(
            () -> {
                run("123", "145", "671", "216", "713", "2");
                assertThat(output()).contains("1볼 1스트라이크", "1볼", "2볼", "1스트라이크", "3스트라이크", "게임 종료");
            },
            7, 1, 3
        );
    }

    @Test
    void 대표적인패턴() {
        assertRandomNumberInRangeTest(
            () -> {
                run("789", "451", "231", "216", "132", "139", "123", "2");
                assertThat(output()).contains("낫싱", "1볼", "2볼", "3볼", "1볼 1스트라이크", "2볼 1스트라이크",
                    "3스트라이크", "게임 종료");
            },
            1, 2, 3
        );
    }


    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("1234"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
