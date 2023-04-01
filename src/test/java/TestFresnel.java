import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;

public class TestFresnel {
    private static boolean inRange(double a, double b, double error) {
        if(Math.abs(a - b) < error)
            return true;
        else {
            System.out.println("a = " + a + ", b = " + b);
            return false;
        }
    }

    @Test
    public void testC() {
        assertThat(inRange(Maja.fresnelC(0.0), 0.0, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelC(1.0), 0.5170097423886774, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelC(0.5), 0.315476242886483, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelC(0.25), 0.19939323587645366, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelC(1.23), 0.7793505896726802, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelC(4.56), 0.5820857381412772, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelC(10.11), 0.5392386378758506, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelC(33.33), 0.48869767887476634, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelC(-1.0), -0.5170097423886774, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelC(-0.5), -0.315476242886483, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelC(-0.25), -0.19939323587645366, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelC(-1.23), -0.7793505896726802, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelC(-4.56), -0.5820857381412772, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelC(-10.11), -0.5392386378758506, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelC(-33.33), -0.48869767887476634, 1e-10)).isTrue();

        assertThat(Maja.fresnelC(Double.NaN)).isNaN();
        assertThat(Maja.fresnelC(Double.POSITIVE_INFINITY)).isEqualTo(0.5);
        assertThat(Maja.fresnelC(Double.NEGATIVE_INFINITY)).isEqualTo(-0.5);
    }

    @Test
    public void testS() {
        assertThat(inRange(Maja.fresnelS(0.0), 0.0, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelS(1.0), 0.37899219456207334, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelS(0.5), 0.3502398714936612, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelS(0.25), 0.0041544893980753495, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelS(1.23), 0.41966775788853194, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelS(4.56), 0.5299047913388094, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelS(10.11), 0.504153056558591, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelS(33.33), 0.4960597597742731, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelS(-1.0), -0.37899219456207334, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelS(-0.5), -0.3502398714936612, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelS(-0.25), -0.0041544893980753495, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelS(-1.23), -0.41966775788853194, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelS(-4.56), -0.5299047913388094, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelS(-10.11), -0.504153056558591, 1e-10)).isTrue();
        assertThat(inRange(Maja.fresnelS(-33.33), -0.4960597597742731, 1e-10)).isTrue();

        assertThat(Maja.fresnelS(Double.NaN)).isNaN();
        assertThat(Maja.fresnelS(Double.POSITIVE_INFINITY)).isEqualTo(0.5);
        assertThat(Maja.fresnelS(Double.NEGATIVE_INFINITY)).isEqualTo(-0.5);
    }
}
