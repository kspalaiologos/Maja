import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Test
    public void testSqrt() {
        assertThat(Maja.sqrt(0)).isEqualTo(0);
        assertThat(Maja.sqrt(1)).isEqualTo(1);
        assertThat(Maja.sqrt(-1)).isNaN();
        assertThat(Maja.sqrt(8)).isEqualTo(2.8284271247461903);
        assertThat(Maja.sqrt(-8)).isNaN();
        assertTrue(Double.isNaN(Maja.sqrt(Double.NaN)));
        assertThat(Maja.sqrt(Double.POSITIVE_INFINITY)).isEqualTo(Double.POSITIVE_INFINITY);
        assertThat(Maja.sqrt(Double.NEGATIVE_INFINITY)).isNaN();

        // Try out some more sophisticated results:
        assertThat(Maja.sqrt(27)).isEqualTo(5.196152422706632);
        assertThat(Maja.sqrt(64)).isEqualTo(8);
        assertThat(Maja.sqrt(182.35)).isEqualTo(13.503703195790404);
        assertThat(Maja.sqrt(12345.6789)).isEqualTo(111.11111060555555);
    }

    @Test
    public void testISqrt() {
        assertThat(Maja.isqrt(0)).isEqualTo(0);
        assertThat(Maja.isqrt(1)).isEqualTo(1);
        assertThat(Maja.isqrt(-1)).isEqualTo(-1);
        assertThat(Maja.isqrt(8)).isEqualTo(2);
        assertThat(Maja.isqrt(-8)).isEqualTo(-2);

        // Try out some more sophisticated results:
        assertThat(Maja.isqrt(27)).isEqualTo(5);
        assertThat(Maja.isqrt(64)).isEqualTo(8);
        assertThat(Maja.isqrt(182)).isEqualTo(13);
        assertThat(Maja.isqrt(12345)).isEqualTo(111);
    }

    @Test
    public void testICbrt() {
        assertThat(Maja.icbrt(0)).isEqualTo(0);
        assertThat(Maja.icbrt(1)).isEqualTo(1);
        assertThat(Maja.icbrt(-1)).isEqualTo(-1);
        assertThat(Maja.icbrt(8)).isEqualTo(2);
        assertThat(Maja.icbrt(-8)).isEqualTo(-2);

        // Try out some more sophisticated results:
        assertThat(Maja.icbrt(27)).isEqualTo(3);
        assertThat(Maja.icbrt(64)).isEqualTo(4);
        assertThat(Maja.icbrt(182)).isEqualTo(5);
        assertThat(Maja.icbrt(12345)).isEqualTo(23);
    }

    @Test
    public void testIsSquare() {
        // Negative numbers are not perfect squares.
        assertFalse(Maja.isPerfectSquare(-5));
        assertFalse(Maja.isPerfectSquare(-2));
        // Zero is a perfect square.
        assertTrue(Maja.isPerfectSquare(0));
        // (x & 10b) != 0 => not perfect square.
        assertFalse(Maja.isPerfectSquare(2));
        assertFalse(Maja.isPerfectSquare(6));
        assertFalse(Maja.isPerfectSquare(10));
        // (x & 7) == 5 => not a perfect square.
        assertFalse(Maja.isPerfectSquare(13));
        // (x & 0b1011) == 0b1000 => not a perfect square.
        assertFalse(Maja.isPerfectSquare(0b1100));
        assertFalse(Maja.isPerfectSquare(0b1000));
        assertTrue(Maja.isPerfectSquare(65536));
        assertTrue(Maja.isPerfectSquare(17 * 17));
    }
}
