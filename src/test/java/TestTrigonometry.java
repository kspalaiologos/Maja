import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;

public class TestTrigonometry {
    @Test
    public void testElementary() {
        // test sin, cos, tan, cot, sec, csc, asin, acos, atan, acot,
        // asec, acsc, sinh, cosh, tanh, coth, sech, csch, asinh,
        // acosh, atanh, acoth, asech, acsch on a single value.
        assertThat(Maja.sin(1.2)).isEqualTo(0.9320390859672263);
        assertThat(Maja.cos(1.2)).isEqualTo(0.3623577544766736);
        assertThat(Maja.tan(1.2)).isEqualTo(2.5721516221263188);
        assertThat(Maja.cot(1.2)).isEqualTo(0.38877956936820496);
        assertThat(Maja.sec(1.2)).isEqualTo(2.759703601332406);
        assertThat(Maja.csc(1.2)).isEqualTo(1.0729163777098973);
        assertThat(Maja.abs(Maja.asin(0.9320390859672263) - 1.2)).isLessThan(1e-14);
        assertThat(Maja.abs(Maja.acos(0.3623577544766736) - 1.2)).isLessThan(1e-14);
        assertThat(Maja.abs(Maja.atan(2.5721516221263188) - 1.2)).isLessThan(1e-14);
        assertThat(Maja.abs(Maja.acot(0.38877956936820496) - 1.2)).isLessThan(1e-14);
        assertThat(Maja.abs(Maja.asec(2.759703601332406) - 1.2)).isLessThan(1e-14);
        assertThat(Maja.abs(Maja.acsc(1.0729163777098973) - 1.2)).isLessThan(1e-14);
        assertThat(Maja.sinh(1.2)).isEqualTo(1.5094613554121725);
        assertThat(Maja.cosh(1.2)).isEqualTo(1.8106555673243747);
        assertThat(Maja.tanh(1.2)).isEqualTo(0.8336546070121552);
        assertThat(Maja.coth(1.2)).isEqualTo(1.1995375441923508);
        assertThat(Maja.sech(1.2)).isEqualTo(0.5522861542782048);
        assertThat(Maja.csch(1.2)).isEqualTo(0.6624879771943155);
        assertThat(Maja.abs(Maja.asinh(1.5094613554121725) - 1.2)).isLessThan(1e-14);
        assertThat(Maja.abs(Maja.acosh(1.8106555673243747) - 1.2)).isLessThan(1e-14);
        assertThat(Maja.abs(Maja.atanh(0.8336546070121552) - 1.2)).isLessThan(1e-14);
        assertThat(Maja.abs(Maja.acoth(1.1995375441923508) - 1.2)).isLessThan(1e-14);
        assertThat(Maja.abs(Maja.asech(0.5522861542782048) - 1.2)).isLessThan(1e-14);
        assertThat(Maja.abs(Maja.acsch(0.6624879771943155) - 1.2)).isLessThan(1e-14);
    }
}
