import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Complex;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;

public class TestQuadratures {
    @Test
    public void testSimpson() {
        assertThat(Math.abs(Maja.integrateSimpsonReal(Maja::exp, -1, 1, 100000)
                - 2.3504023872876029137647637011912016303114359626681917404591308260)).isLessThan(2e-5);
        assertThat(Math.abs(Maja.integrateSimpsonReal(Maja::exp, -1, 1, 1000000)
                - 2.3504023872876029137647637011912016303114359626681917404591308260)).isLessThan(2e-6);
        assertThat(Math.abs(Maja.integrateSimpsonReal(Maja::cos, -1, 1, 1000000)
                - 1.6829419696157930133050046432605979992451261215967421313455034199)).isLessThan(4e-7);
    }

    @Test
    public void testGaussLegendre() {
        // sqrt(pi)*erf(1)
        assertThat(Math.abs(Maja.integrateGaussLegendreReal(x -> Maja.exp(-x * x), -1, 1, 6)
                - 1.4936482656248540507989348722637060107089993736252126580553089979))
                    .isLessThan(7e-7);

        assertThat(Math.abs(Maja.integrateGaussLegendreReal(x -> Maja.exp(-x * x), -1, 1, 9)
                - 1.4936482656248540507989348722637060107089993736252126580553089979))
                    .isLessThan(1e-10);

        assertThat(Math.abs(Maja.integrateGaussLegendreReal(x -> Maja.exp(-x * x), -1, 1, 20)
                - 1.4936482656248540507989348722637060107089993736252126580553089979))
                .isLessThan(7e-12);
    }

    @Test
    public void testTanhSinh() {
        // sqrt(1-x**2) from -1 to 1 => pi/2
        assertThat(Math.abs(Maja.integrateTanhSinhReal(x -> Maja.sqrt(1 - x * x), -1, 1, 6, 1e-10)[0]
                - Maja.PI_2)).isLessThan(1e-10);
        assertThat(Math.abs(Maja.integrateTanhSinhReal(x -> Maja.sqrt(1 - x * x), -1, 1, 6, 1e-16)[0]
                - Maja.PI_2)).isLessThan(1.56e-15);
    }

    @Test
    public void testComplexGaussLegendre() {
        assertThat(Maja.integrateGaussLegendreComplex(Maja::exp, new Complex(1,2), new Complex(3,4), 10))
                .isEqualTo(new Complex(-11.997578697705027, -17.672511135071467));
    }
}
