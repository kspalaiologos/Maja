import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestArithmetic {
    @Test
    public void testArithmetic() {
        assertThat(Maja.add(0, 0)).isEqualTo(0);
        assertThat(Maja.add(1, 1)).isEqualTo(2);
        assertThat(Maja.add(1, 2)).isEqualTo(3);

        assertThat(Maja.sub(0, 0)).isEqualTo(0);
        assertThat(Maja.sub(1, 1)).isEqualTo(0);
        assertThat(Maja.sub(1, 2)).isEqualTo(-1);
        assertThat(Maja.sub(10, 2)).isEqualTo(8);

        assertThat(Maja.mul(0, 0)).isEqualTo(0);
        assertThat(Maja.mul(1, 1)).isEqualTo(1);
        assertThat(Maja.mul(1, 2)).isEqualTo(2);
        assertThat(Maja.mul(10, 2)).isEqualTo(20);

        assertTrue(Double.isNaN(Maja.div(0, 0)));
        assertThat(Maja.div(1, 1)).isEqualTo(1);
        assertThat(Maja.div(1, 2)).isEqualTo(0.5);
        assertThat(Maja.div(10, 2)).isEqualTo(5);

        // The difference between the modulus and remainder is important to point out:
        assertThat(Maja.mod(5, 3)).isEqualTo(2);
        assertThat(Maja.mod(-5, 3)).isEqualTo(1);
        assertThat(Maja.mod(5, -3)).isEqualTo(-1);
        assertThat(Maja.mod(-5, -3)).isEqualTo(-2);
        assertTrue(Double.isNaN(Maja.mod(0, 0)));

        assertThat(Maja.rem(5, 3)).isEqualTo(2);
        assertThat(Maja.rem(-5, 3)).isEqualTo(-2);
        assertThat(Maja.rem(5, -3)).isEqualTo(2);
        assertThat(Maja.rem(-5, -3)).isEqualTo(-2);
        assertTrue(Double.isNaN(Maja.rem(0, 0)));
    }

    @Test
    public void testAbs() {
        assertThat(Maja.abs(0)).isEqualTo(0);
        assertThat(Maja.abs(1)).isEqualTo(1);
        assertThat(Maja.abs(-1)).isEqualTo(1);
        assertThat(Maja.abs(10)).isEqualTo(10);
        assertThat(Maja.abs(-10)).isEqualTo(10);
        assertTrue(Double.isNaN(Maja.abs(Double.NaN)));
        assertThat(Maja.abs(Double.POSITIVE_INFINITY)).isEqualTo(Double.POSITIVE_INFINITY);
        assertThat(Maja.abs(Double.NEGATIVE_INFINITY)).isEqualTo(Double.POSITIVE_INFINITY);
    }

    @Test
    public void testCbrt() {
        assertThat(Maja.cbrt(0)).isEqualTo(0);
        assertThat(Maja.cbrt(1)).isEqualTo(1);
        assertThat(Maja.cbrt(-1)).isEqualTo(-1);
        assertThat(Maja.cbrt(8)).isEqualTo(2);
        assertThat(Maja.cbrt(-8)).isEqualTo(-2);
        assertTrue(Double.isNaN(Maja.cbrt(Double.NaN)));
        assertThat(Maja.cbrt(Double.POSITIVE_INFINITY)).isEqualTo(Double.POSITIVE_INFINITY);
        assertThat(Maja.cbrt(Double.NEGATIVE_INFINITY)).isEqualTo(Double.NEGATIVE_INFINITY);

        // Try out some more sophisticated results:
        assertThat(Maja.cbrt(27)).isEqualTo(3);
        assertThat(Maja.cbrt(64)).isEqualTo(4);
        assertThat(Maja.cbrt(182.35)).isEqualTo(5.6706815069811505);
        assertThat(Maja.cbrt(12345.6789)).isEqualTo(23.112042408247962);
    }
}
