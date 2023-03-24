import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;

public class TestQuadratures {
    @Test
    public void testSimpson() {
        assertThat(Math.abs(Maja.integrateSimpson(Maja::exp, -1, 1, 100000)
                - 2.3504023872876029137647637011912016303114359626681917404591308260)).isLessThan(2e-5);
        assertThat(Math.abs(Maja.integrateSimpson(Maja::exp, -1, 1, 1000000)
                - 2.3504023872876029137647637011912016303114359626681917404591308260)).isLessThan(2e-6);
        assertThat(Math.abs(Maja.integrateSimpson(Maja::cos, -1, 1, 1000000)
                - 1.6829419696157930133050046432605979992451261215967421313455034199)).isLessThan(4e-7);
    }
}
