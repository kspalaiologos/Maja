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

    @Test
    public void testComplexExp() {
        assertThat(Maja.exp(new Complex(0, 0))).isEqualTo(new Complex(1, 0));
        assertThat(Maja.exp(new Complex(1, 0))).isEqualTo(new Complex(Math.E, 0));
        assertThat(Maja.exp(new Complex(0, 1))).isEqualTo(new Complex(Math.cos(1), Math.sin(1)));
        assertThat(Maja.exp(new Complex(1.2, 3.4))).isEqualTo(new Complex(Math.cos(3.4) * Math.exp(1.2), Math.sin(3.4) * Math.exp(1.2)));
        assertThat(Maja.exp(new Complex(1.2, -3.4))).isEqualTo(new Complex(Math.cos(-3.4) * Math.exp(1.2), Math.sin(-3.4) * Math.exp(1.2)));
    }

    @Test
    public void testComplexLog() {
        assertThat(Maja.log(new Complex(1, 0))).isEqualTo(new Complex(0, 0));
        assertThat(Maja.log(new Complex(0, 1))).isEqualTo(new Complex(0, Math.PI / 2));
        assertThat(Maja.log(new Complex(0, -1))).isEqualTo(new Complex(0, -Math.PI / 2));
        assertThat(Maja.log(new Complex(1, 1))).isEqualTo(new Complex(0.3465735902799727, 0.7853981633974483));
        assertThat(Maja.log(new Complex(1, -1))).isEqualTo(new Complex(0.3465735902799727, -0.7853981633974483));
        assertThat(Maja.log(new Complex(1, 2))).isEqualTo(new Complex(0.8047189562170503, 1.1071487177940904));
        assertThat(Maja.log(new Complex(1, -2))).isEqualTo(new Complex(0.8047189562170503, -1.1071487177940904));
    }

    @Test
    public void testCmp() {
        assertThat(Maja.eq(new Complex(1, 2), new Complex(3, 4))).isFalse();
        assertThat(Maja.eq(new Complex(1, 2), new Complex(1, 2))).isTrue();

        assertThat(Maja.ne(new Complex(1, 2), new Complex(3, 4))).isTrue();
        assertThat(Maja.ne(new Complex(1, 2), new Complex(1, 2))).isFalse();
    }
}
