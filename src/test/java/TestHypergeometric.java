import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;

public class TestHypergeometric {
    @Test
    public void test2f1() {
        // 2F1(-1/2, 1/3, 4/3, -1) ~= 1.1114479705325755
        assertThat(Maja.hypergeo2F1(-0.5, 1.0 / 3.0, 4.0 / 3.0, -1)).isEqualTo(1.1114479705325755);
    }
}
