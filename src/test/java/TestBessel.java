import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Complex;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;

public class TestBessel {
    private static boolean inRange(double a, double b, double error) {
        if(Math.abs(a - b) < error)
            return true;
        else {
            System.out.println("a = " + a + ", b = " + b);
            return false;
        }
    }

    @Test
    public void testI() {
        assertThat(inRange(Maja.besselI0(0.0), 1.0, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI0(1.0), 1.2660658777520082, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI0(2.0), 2.279585302336067, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI0(1.23), 1.4155275721584568, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI0(12.34), 26244.678473490312, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI0(-12.34), Maja.besselI0(12.34), 1e-10)).isTrue();
        assertThat(Maja.besselI0(Double.NaN)).isNaN();
        assertThat(Maja.besselI0(Double.POSITIVE_INFINITY)).isInfinite();
        assertThat(Maja.besselI0(Double.NEGATIVE_INFINITY)).isInfinite();

        assertThat(inRange(Maja.besselI1(0.0), 0.0, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI1(1.0), 0.5651591039924851, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI1(2.0), 1.5906368546373295, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI1(1.23), 0.7388712188459117, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI1(12.34), 25157.721338891875, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI1(3.1415), 4.491081602656663, 1e-10)).isTrue();
    }

    @Test
    public void testK() {
        assertThat(inRange(Maja.besselK0(1.0), 0.42102443824070823, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselK0(2.0), 0.1138938727495334, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselK0(1.23), 0.3057709895976339, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselK0(12.34), 1.5451620707996024E-6, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselK0(3.1415), 0.029511826011480266, 1e-10)).isTrue();
    }
}
