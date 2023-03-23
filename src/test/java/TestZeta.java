
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

    @Test
    public void testHurwitz() {
        assertThat(Maja.hurwitzZeta(0.5,0.5)).isEqualTo(-0.6049394572448313);
        assertThat(Maja.hurwitzZeta(1.01,1.15)).isEqualTo(100.35428457218312);
        assertThat(Maja.hurwitzZeta(-1.0,2.23)).isEqualTo(-1.4546960238181506);
        assertThat(Maja.hurwitzZeta(0.3,5.0)).isEqualTo(-4.095812113543416);
        assertThat(Maja.hurwitzZeta(-0.3,100000.0)).isEqualTo(-2432505.4656642997);
        assertThat(Maja.hurwitzZeta(4.0,12.0)).isEqualTo(2.1834829681679626E-4);
        assertThat(Maja.hurwitzZeta(-4.0,6.23)).isEqualTo(-1204.1989289815126);
        assertThat(Maja.hurwitzZeta(10.123,1.111)).isEqualTo(0.34506754462671935);
        assertThat(Maja.hurwitzZeta(-10.123,9.9)).isEqualTo(-5.685551437279248E9);
    }
}
