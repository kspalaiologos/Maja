import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;

public class TestErf {
    @Test
    public void testErf() {
        assertThat(Maja.erf(0)).isEqualTo(0);
        assertThat(Maja.erf(1)).isEqualTo(0.8427007929497148);
        assertThat(Maja.erf(2)).isEqualTo(0.9953222650189527);
        assertThat(Maja.erf(-1)).isEqualTo(-0.8427007929497148);
        assertThat(Maja.erf(Double.NaN)).isNaN();
        assertThat(Maja.erf(Double.POSITIVE_INFINITY)).isEqualTo(1);
        assertThat(Maja.erf(Double.NEGATIVE_INFINITY)).isEqualTo(-1);
        assertThat(Maja.erf(0.5)).isEqualTo(0.5204998778130464);
        assertThat(Maja.erf(-0.5)).isEqualTo(-0.5204998778130464);
        assertThat(Maja.erf(1.5)).isEqualTo(0.9661051464753108);
        assertThat(Maja.erf(12.34)).isEqualTo(1.0);
    }

    @Test
    public void testErfc() {
        assertThat(Maja.erfc(0)).isEqualTo(1);
        assertThat(Maja.erfc(Double.NaN)).isNaN();
        assertThat(Maja.erfc(Double.POSITIVE_INFINITY)).isEqualTo(0);
        assertThat(Maja.erfc(Double.NEGATIVE_INFINITY)).isEqualTo(2);
        assertThat(Maja.erfc(0.5)).isEqualTo(0.47950009227675744);
        assertThat(Maja.erfc(-0.5)).isEqualTo(-0.47950009227675744);
        assertThat(Maja.erfc(1.5)).isEqualTo(0.033894850349524795);
        assertThat(Maja.erfc(12.34)).isEqualTo(3.359252262812403E-68);
        assertThat(Maja.erfc(-2.0)).isEqualTo(-0.004677734989334044);
        assertThat(Maja.erfc(0.111)).isEqualTo(0.8752624198776775);
    }
}
