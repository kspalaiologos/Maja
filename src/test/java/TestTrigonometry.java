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

        // Test fast trig by sampling a few values.
        assertThat(Maja.abs(Maja.fastSin(0.123f) - Maja.sin(0.123))).isLessThan(1e-3);
        assertThat(Maja.abs(Maja.fastSin(0.456f) - Maja.sin(0.456))).isLessThan(1e-3);
        assertThat(Maja.abs(Maja.fastSin(12.34f) - Maja.sin(12.34))).isLessThan(1e-3);
        assertThat(Maja.abs(Maja.fastSin(-6.77f) - Maja.sin(-6.77))).isLessThan(1e-3);
        assertThat(Maja.abs(Maja.fastSin(4.55f) - Maja.sin(4.55))).isLessThan(1e-3);
        assertThat(Maja.abs(Maja.fastSin(3.33f) - Maja.sin(3.33))).isLessThan(1e-3);

        assertThat(Maja.abs(Maja.fastCos(0.123f) - Maja.cos(0.123))).isLessThan(1e-3);
        assertThat(Maja.abs(Maja.fastCos(0.456f) - Maja.cos(0.456))).isLessThan(1e-3);
        assertThat(Maja.abs(Maja.fastCos(12.34f) - Maja.cos(12.34))).isLessThan(1e-3);
        assertThat(Maja.abs(Maja.fastCos(-6.77f) - Maja.cos(-6.77))).isLessThan(1e-3);
        assertThat(Maja.abs(Maja.fastCos(4.55f) - Maja.cos(4.55))).isLessThan(1e-3);
        assertThat(Maja.abs(Maja.fastCos(3.33f) - Maja.cos(3.33))).isLessThan(1e-3);
    }

    @Test
    public void testSi() {
        assertThat(Maja.Si(0.123)).isEqualTo(0.12289666540969063);
        assertThat(Maja.Si(1.23)).isEqualTo(1.131191899402606);
        assertThat(Maja.Si(Double.NaN)).isNaN();
        assertThat(Maja.Si(-5)).isNaN();
        assertThat(Maja.Si(Double.POSITIVE_INFINITY)).isEqualTo(Maja.PI_2);
        assertThat(Maja.Si(Double.NEGATIVE_INFINITY)).isEqualTo(-Maja.PI_2);
        assertThat(Maja.Si(10)).isEqualTo(1.658347594218874);

        // Test one or two points for si(), as it is a special case of Si already.
        assertThat(Maja.si(12)).isEqualTo(-0.06582508526852338);
        assertThat(Maja.si(0.123)).isEqualTo(-1.447899661385206);
    }
}
