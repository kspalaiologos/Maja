
import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;

public class TestZeta {
    @Test
    public void testRiemann() {
        assertThat(Maja.zeta(0.0)).isEqualTo(-0.5);
        assertThat(Maja.zeta(1.0)).isEqualTo(Double.POSITIVE_INFINITY);
        assertThat(Maja.zeta(-1.0)).isEqualTo(-0.08333333333333323);
        assertThat(Maja.zeta(0.3)).isEqualTo(-0.9045592572539808);
        assertThat(Maja.zeta(-0.3)).isEqualTo(-0.29381306812971947);
        assertThat(Maja.zeta(4.0)).isEqualTo(1.0823232337111377);
        assertThat(Maja.zeta(-4.0)).isEqualTo(1.244890756463312E-18);
        assertThat(Maja.zeta(10.123)).isEqualTo(1.0009124539043512);
        assertThat(Maja.zeta(-10.123)).isEqualTo(0.002466566204624113);
        assertThat(Maja.zeta(Double.POSITIVE_INFINITY)).isEqualTo(1);
        assertThat(Maja.zeta(Double.NEGATIVE_INFINITY)).isNaN();
        assertThat(Maja.zeta(Double.NaN)).isNaN();
    }
}
