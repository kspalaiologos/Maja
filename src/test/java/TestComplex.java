import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Complex;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;

public class TestComplex {
    @Test
    public void testArithmetic() {
        assertThat(Maja.add(new Complex(1, 2), new Complex(3, 4))).isEqualTo(new Complex(4, 6));
        assertThat(Maja.add(new Complex(1, 2), 3)).isEqualTo(new Complex(4, 2));
        assertThat(Maja.add(3, new Complex(1, 2))).isEqualTo(new Complex(4, 2));

        assertThat(Maja.sub(new Complex(1, 2), new Complex(3, 4))).isEqualTo(new Complex(-2, -2));
        assertThat(Maja.sub(new Complex(1, 2), 3)).isEqualTo(new Complex(-2, 2));
        assertThat(Maja.sub(3, new Complex(1, 2))).isEqualTo(new Complex(2, 2));

        assertThat(Maja.mul(new Complex(1, 2), new Complex(3, 4))).isEqualTo(new Complex(-5, 10));
        assertThat(Maja.mul(new Complex(1, 2), 3)).isEqualTo(new Complex(3, 6));
        assertThat(Maja.mul(3, new Complex(1, 2))).isEqualTo(new Complex(3, 6));

        assertThat(Maja.div(new Complex(1, 2), new Complex(3, 4))).isEqualTo(new Complex(11.0 / 25, 2.0 / 25));
        assertThat(Maja.div(new Complex(1, 2), 3)).isEqualTo(new Complex(1.0 / 3, 2.0 / 3));
        assertThat(Maja.div(3, new Complex(1, 2))).isEqualTo(new Complex(0.6, -1.2));
    }

    @Test
    public void testConjugate() {
        assertThat(Maja.conj(new Complex(1, 2))).isEqualTo(new Complex(1, -2));
    }

    @Test
    public void testAbsSqrt() {
        assertThat(Maja.abs(new Complex(3, 4))).isEqualTo(5);
        assertThat(Maja.abs(new Complex(3, -4))).isEqualTo(5);
        assertThat(Maja.sqrt(new Complex(3, 4))).isEqualTo(new Complex(2, 1));
        assertThat(Maja.sqrt(new Complex(4, 0))).isEqualTo(new Complex(2, 0));
    }
}
