import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;

public class TestErf {
    @Test
    public void testErf() {
        assertThat(Maja.erf(0)).isEqualTo(0);
        assertThat(Maja.erf(1)).isEqualTo(0.8427007929497148);
        assertThat(Maja.erf(2)).isEqualTo(0.9953222650189527);
        assertThat(Maja.erf(-1)).isEqualTo(-0.8427007929497148);
        assertThat(Maja.erf(Double.NaN)).isNaN();
        assertThat(Maja.erf(Double.POSITIVE_INFINITY)).isEqualTo(1);
        assertThat(Maja.erf(Double.NEGATIVE_INFINITY)).isEqualTo(-1);
        assertThat(Maja.erf(0.5)).isEqualTo(0.5204998778130464);
        assertThat(Maja.erf(-0.5)).isEqualTo(-0.5204998778130464);
        assertThat(Maja.erf(1.5)).isEqualTo(0.9661051464753108);
        assertThat(Maja.erf(12.34)).isEqualTo(1.0);
    }

    @Test
    public void testErfc() {
        assertThat(Maja.erfc(0)).isEqualTo(1);
        assertThat(Maja.erfc(Double.NaN)).isNaN();
        assertThat(Maja.erfc(Double.POSITIVE_INFINITY)).isEqualTo(0);
        assertThat(Maja.erfc(Double.NEGATIVE_INFINITY)).isEqualTo(2);
        assertThat(Maja.erfc(0.5)).isEqualTo(0.4795001221869536);
        assertThat(Maja.erfc(-0.5)).isEqualTo(1.5204998778130463);
        assertThat(Maja.erfc(1.5)).isEqualTo(0.03389485352468924);
        assertThat(Maja.erfc(12.34)).isEqualTo(0.0);
        assertThat(Maja.erfc(-2.0)).isEqualTo(1.9953222650189528);
        assertThat(Maja.erfc(0.111)).isEqualTo(0.8752624187419497);
    }

    @Test
    public void testErfi() {
        assertThat(Maja.erfi(0)).isEqualTo(0);
        assertThat(Maja.erfi(Double.NaN)).isNaN();
        assertThat(Maja.erfi(Double.POSITIVE_INFINITY)).isEqualTo(Double.POSITIVE_INFINITY);
        assertThat(Maja.erfi(Double.NEGATIVE_INFINITY)).isEqualTo(Double.NEGATIVE_INFINITY);
        assertThat(Maja.erfi(0.5)).isEqualTo(0.6149520946965109);
        assertThat(Maja.erfi(-0.5)).isEqualTo(-0.6149520946965109);
        assertThat(Maja.erfi(1.5)).isEqualTo(4.5847332572844275);
        assertThat(Maja.erfi(12.34)).isEqualTo(6.223011839236671E64);
        assertThat(Maja.erfi(-2.0)).isEqualTo(-18.564802414575553);
        assertThat(Maja.erfi(0.111)).isEqualTo(0.1257663966329148);
    }

    @Test
    public void testDawson() {
        assertThat(Maja.dawsonMinus(0.0)).isEqualTo(0.0);
        assertThat(Maja.dawsonMinus(1.0)).isEqualTo(2.030078469278705);
        assertThat(Maja.dawsonMinus(2.0)).isEqualTo(48.16001211429123);
        assertThat(Maja.dawsonMinus(-1.0)).isEqualTo(-2.030078469278705);
        assertThat(Maja.dawsonMinus(Double.NaN)).isNaN();
        assertThat(Maja.dawsonMinus(Double.POSITIVE_INFINITY)).isEqualTo(Double.POSITIVE_INFINITY);
        assertThat(Maja.dawsonMinus(Double.NEGATIVE_INFINITY)).isEqualTo(Double.NEGATIVE_INFINITY);
        assertThat(Maja.dawsonMinus(0.5)).isEqualTo(0.5922965364693265);
        assertThat(Maja.dawsonMinus(-0.5)).isEqualTo(-0.5922965364693265);
        assertThat(Maja.dawsonMinus(1.5)).isEqualTo(8.123289304869214);
        assertThat(Maja.dawsonMinus(12.34)).isEqualTo(1.2022582036795712E66);
        assertThat(Maja.dawsonMinus(-6.77)).isEqualTo(-7.120664113754944E19);
        assertThat(Maja.dawsonMinus(4.55)).isEqualTo(8.680134450911534E8);
        assertThat(Maja.dawsonMinus(3.33)).isEqualTo(57995.18547618357);

        assertThat(Maja.dawsonPlus(0.0)).isEqualTo(0.0);
        assertThat(Maja.dawsonPlus(1.0)).isEqualTo(0.5380795069127684);
        assertThat(Maja.dawsonPlus(2.0)).isEqualTo(0.301340388923792);
        assertThat(Maja.dawsonPlus(-1.0)).isEqualTo(-0.5380795069127684);
        assertThat(Maja.dawsonMinus(Double.NaN)).isNaN();
        assertThat(Maja.dawsonPlus(Double.POSITIVE_INFINITY)).isEqualTo(0.0);
        assertThat(Maja.dawsonPlus(Double.NEGATIVE_INFINITY)).isEqualTo(-0.0);
        assertThat(Maja.dawsonPlus(0.5)).isEqualTo(0.4244363835020223);
        assertThat(Maja.dawsonPlus(-0.5)).isEqualTo(-0.4244363835020223);
        assertThat(Maja.dawsonPlus(1.5)).isEqualTo(0.42824907108539867);
        assertThat(Maja.dawsonPlus(12.34)).isEqualTo(0.04065301492124149);
        assertThat(Maja.dawsonPlus(-6.77)).isEqualTo(-0.07468887399692625);
        assertThat(Maja.dawsonPlus(4.55)).isEqualTo(0.11276478411723788);
        assertThat(Maja.dawsonPlus(3.33)).isEqualTo(0.1581685813429892);
    }
}
