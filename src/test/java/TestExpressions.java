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
    }
}
