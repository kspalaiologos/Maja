import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;

public class TestExpressions {
    @Test
    public void testExpressions() {
        assertThat(Maja.eval("2 + 2")).isEqualTo(4);
        assertThat(Maja.eval("(sin(10) ** 2) + (cos(10) ** 2)")).isEqualTo(1);
        assertThat(Maja.eval("simpson([exp(x)], -1, 1, dx, 10000)")).isEqualTo(
                Maja.integrateSimpson(Maja::exp, -1, 1, 10000));
        assertThat(Maja.eval("pick(0, sin(1), sin(2), sin(3))")).isEqualTo(Maja.sin(1));
        assertThat(Maja.eval("pick(1, sin(1), sin(2), sin(3))")).isEqualTo(Maja.sin(2));
        assertThat(Maja.eval("pick(2, sin(1), sin(2), sin(3))")).isEqualTo(Maja.sin(3));
    }
}
