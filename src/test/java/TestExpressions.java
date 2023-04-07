import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Complex;
import rocks.palaiologos.maja.Maja;
import rocks.palaiologos.maja.MonadicFunction;

import static org.assertj.core.api.Assertions.assertThat;
import rocks.palaiologos.maja.Number;

import java.util.function.Function;

public class TestExpressions {
    @Test
    public void testExpressions() {
        assertThat(Maja.eval("2 + 2")).isEqualTo(new Number(4));
        assertThat(Maja.eval("(sin(10) ** 2) + (cos(10) ** 2)")).isEqualTo(new Number(1.0));
        assertThat(Maja.eval("simpson([exp(x)], -1, 1, dx, 10000)")).isEqualTo(new Number(Maja.integrateSimpson((MonadicFunction) Maja::exp, -1, 1, 10000)));
        assertThat(Maja.eval("gausslegendre([exp(x)], -1, 1, dx, 5)")).isEqualTo(new Number(Maja.integrateGaussLegendre((MonadicFunction) Maja::exp, -1, 1, 5)));
        assertThat(Maja.eval("tanhsinh([exp(x)], -1, 1, dx, 5, 1e-10)")).isEqualTo(new Number(Maja.integrateTanhSinh((MonadicFunction) Maja::exp, -1, 1, 5, 1e-10)[0]));
        assertThat(Maja.eval("linetanhsinh([exp(x)], -1+2i, 1-3i, dx, 5, 1e-10)")).isEqualTo(new Number(Maja.integrateTanhSinh(Maja::exp, new Complex(-1, 2), new Complex(1, -3), 5, 1e-10)[0]));
        assertThat(Maja.eval("linegausslegendre([exp(x)], -1+2i, 1-3i, dx, 5)")).isEqualTo(new Number(Maja.integrateGaussLegendre(Maja::exp, new Complex(-1, 2), new Complex(1, -3), 5)));
        assertThat(Maja.eval("pick(0, sin(1), sin(2), sin(3))")).isEqualTo(new Number(Maja.sin(1)));
        assertThat(Maja.eval("pick(1, sin(1), sin(2), sin(3))")).isEqualTo(new Number(Maja.sin(2)));
        assertThat(Maja.eval("pick(2, sin(1), sin(2), sin(3))")).isEqualTo(new Number(Maja.sin(3)));
        assertThat(Maja.eval("sin(pi * 1.2e+5) ** 2").getDouble()).isLessThan(Maja.EPSILON);
        assertThat(Maja.eval("exp(4)-1").getDouble()).isEqualTo(53.598150033144236);
        assertThat(Maja.eval("2*2").getLong()).isEqualTo(4);
        assertThat(Maja.eval("2 pi").getDouble()).isEqualTo(Maja.TWO_PI);
        assertThat(Maja.eval("2pi").getDouble()).isEqualTo(Maja.TWO_PI);
        // test rem, mod, gcd, lcm:
        assertThat(Maja.eval("10 rem 3").getLong()).isEqualTo(1);
        assertThat(Maja.eval("10 mod 3").getLong()).isEqualTo(1);
        assertThat(Maja.eval("10 mod -3").getLong()).isEqualTo(-2);
        assertThat(Maja.eval("-10 mod 3").getLong()).isEqualTo(2);
        // test factorial:
        assertThat(Maja.eval("!0").getDouble()).isEqualTo(1);
        assertThat(Maja.eval("!5").getDouble()).isEqualTo(120);
    }
}
