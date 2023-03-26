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

    @Test
    public void testCi() {
        assertThat(Maja.Ci(0.123)).isEqualTo(-1.522135125273761);
        assertThat(Maja.Ci(1.23)).isEqualTo(0.42906163794495855);
        assertThat(Maja.Ci(Double.NaN)).isNaN();
        assertThat(Maja.Ci(-5)).isNaN();
        assertThat(Maja.Ci(Double.POSITIVE_INFINITY)).isEqualTo(0);
        assertThat(Maja.Ci(Double.NEGATIVE_INFINITY)).isNaN();
        assertThat(Maja.Ci(10)).isEqualTo(-0.04545643300445537);

        // Test one or two points for Cin(), as it is a special case of Ci already.
        assertThat(Maja.Cin(12)).isEqualTo(3.111902321573647);
        assertThat(Maja.Cin(0.123)).isEqualTo(0.0037798665655739416);
    }

    @Test
    public void testShiChi() {
        assertThat(Maja.ShiChi(0.123)).isEqualTo(new double[] { 0.12310342843383613, -1.5145706236706007 });
        assertThat(Maja.ShiChi(1.23)).isEqualTo(new double[] { 1.3381963993643304, 1.187115234991678 });
        assertThat(Maja.ShiChi(Double.NaN)).isEqualTo(new double[] { Double.NaN, Double.NaN });
        assertThat(Maja.ShiChi(-5)).isEqualTo(new double[] { -20.09321182569723, 20.092063530105953 });
        assertThat(Maja.ShiChi(Double.POSITIVE_INFINITY)).isEqualTo(new double[] { Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY });
        assertThat(Maja.ShiChi(Double.NEGATIVE_INFINITY)).isEqualTo(new double[] { Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY });
        assertThat(Maja.ShiChi(10)).isEqualTo(new double[] { 1246.1144901994235, 1246.1144860424545 });
        assertThat(Maja.ShiChi(0)).isEqualTo(new double[] { 0, Double.NEGATIVE_INFINITY });
        assertThat(Maja.ShiChi(70)).isEqualTo(new double[] { 1.8231763797898678E28, 1.8231763797898678E28 });
    }
}
