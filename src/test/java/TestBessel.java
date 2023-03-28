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
    public void testI0() {
        assertThat(inRange(Maja.besselI0(0.0), 1.0, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI0(1.0), 1.2660658777520082, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI0(2.0), 2.279585302336067, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI0(1.23), 1.4155275721584568, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI0(12.34), 26244.678473490312, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI0(-12.34), Maja.besselI0(12.34), 1e-10)).isTrue();
        assertThat(Maja.besselI0(Double.NaN)).isNaN();
        assertThat(Maja.besselI0(Double.POSITIVE_INFINITY)).isInfinite();
        assertThat(Maja.besselI0(Double.NEGATIVE_INFINITY)).isInfinite();
    }
}
