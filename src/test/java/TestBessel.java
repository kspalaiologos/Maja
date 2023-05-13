import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Complex;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;

public class TestBessel {
    // TODO: Asymptotic behaviours.

    private static boolean inRange(double a, double b, double error) {
        if(Math.abs(a - b) < error)
            return true;
        else {
            System.out.println("a = " + a + ", b = " + b);
            return false;
        }
    }

    @Test
    public void testI() {
        assertThat(inRange(Maja.besselI0(0.0), 1.0, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI0(1.0), 1.2660658777520082, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI0(2.0), 2.279585302336067, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI0(1.23), 1.4155275721584568, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI0(12.34), 26244.678473490312, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI0(-12.34), Maja.besselI0(12.34), 1e-10)).isTrue();
        assertThat(Maja.besselI0(Double.NaN)).isNaN();
        assertThat(Maja.besselI0(Double.POSITIVE_INFINITY)).isInfinite();
        assertThat(Maja.besselI0(Double.NEGATIVE_INFINITY)).isInfinite();

        assertThat(inRange(Maja.besselI1(0.0), 0.0, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI1(1.0), 0.5651591039924851, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI1(2.0), 1.5906368546373295, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI1(1.23), 0.7388712188459117, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI1(12.34), 25157.721338891875, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselI1(3.1415), 4.491081602656663, 1e-10)).isTrue();
    }

    @Test
    public void testK() {
        assertThat(inRange(Maja.besselK0(1.0), 0.42102443824070823, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselK0(2.0), 0.1138938727495334, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselK0(1.23), 0.3057709895976339, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselK0(12.34), 1.5451620707996024E-6, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselK0(3.1415), 0.029511826011480266, 1e-10)).isTrue();

        assertThat(inRange(Maja.besselK1(3.1415), 0.03391723759574595, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselK1(0.5), 1.656441120003301, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselKn(2, 0.5), 7.55018355124087, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselKn(-2, 0.5), 7.55018355124087, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselKn(-2, 20), 6.329543612292227e-10, 1e-15)).isTrue();
    }

    @Test
    public void testY() {
        assertThat(inRange(Maja.besselY0(1.0), 0.088256964215676957983, 1e-8)).isTrue();
        assertThat(inRange(Maja.besselY0(2.0), 0.51037567264974511960, 1e-8)).isTrue();
        assertThat(inRange(Maja.besselY0(1.23), 0.2463839282018762, 1e-8)).isTrue();
        assertThat(inRange(Maja.besselY0(12.34), -0.1936854633574015, 1e-8)).isTrue();
        assertThat(inRange(Maja.besselY0(3.1415), 0.328399558461145, 1e-8)).isTrue();

        assertThat(inRange(Maja.besselY1(3.1415), 0.3588530748613185, 1e-8)).isTrue();
        assertThat(inRange(Maja.besselY1(0.5), -1.471472392670243, 1e-8)).isTrue();
        assertThat(inRange(Maja.besselYn(2, 0.5), -5.441370837174266, 1e-8)).isTrue();
        assertThat(inRange(Maja.besselYn(-2, 0.5), -5.441370837174266, 1e-8)).isTrue();
        assertThat(inRange(Maja.besselYn(-2, 20), -0.079191758245635960748, 1e-8)).isTrue();
    }

    @Test
    public void testJ() {
        assertThat(inRange(Maja.besselJ0(0.0), 1.0, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ0(1.0), 0.7651976865579665, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ0(0.5), 0.938469807240813, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ0(0.25), 0.9844359292958527, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ0(1.23), 0.656070571706025, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ0(4.56), -0.30620449017839674, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ0(10.11), -0.2492010643917988, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ0(33.33), 0.05959635979399258, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ0(-1.0), 0.7651976865579665, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ0(-0.5), 0.938469807240813, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ0(-0.25), 0.9844359292958527, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ0(-1.23), 0.656070571706025, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ0(-4.56), -0.30620449017839674, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ0(-10.11), -0.2492010643917988, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ0(-33.33), 0.05959635979399258, 1e-10)).isTrue();
        assertThat(Maja.besselJ0(Double.NaN)).isNaN();
        assertThat(Maja.besselJ0(Double.POSITIVE_INFINITY)).isEqualTo(0);
        assertThat(Maja.besselJ0(Double.NEGATIVE_INFINITY)).isEqualTo(0);

        assertThat(inRange(Maja.besselJ1(0.0), 0.0, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ1(1.0), 0.44005058574493351596, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ1(0.5), 0.2422684576748739, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ1(0.25), 0.1240259773227269, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ1(1.23), 0.5058005726280962, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ1(4.56), -0.2467025915990849, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ1(10.11), 0.01588737750866174, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ1(33.33), 0.1255939750222267, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ1(-1.0), -0.4400505857449335, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ1(-0.5), -0.2422684576748739, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ1(-0.25), -0.1240259773227269, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ1(-1.23), -0.5058005726280962, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ1(-4.56), 0.2467025915990849, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ1(-10.11), -0.01588737750866174, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJ1(-33.33), -0.1255939750222267, 1e-10)).isTrue();
        assertThat(Maja.besselJ1(Double.NaN)).isNaN();
        assertThat(Maja.besselJ1(Double.POSITIVE_INFINITY)).isEqualTo(0);
        assertThat(Maja.besselJ1(Double.NEGATIVE_INFINITY)).isEqualTo(0);

        assertThat(inRange(Maja.besselJn(1, 1.0), 0.44005058574493351596, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJn(0, 1.0), 0.7651976865579665, 1e-10)).isTrue();

        assertThat(inRange(Maja.besselJn(3, -33.33), 0.1318417959227302, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJn(-3, -33.33), -0.1318417959227302, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJn(-4, -33.33), 0.02832607099768834, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJn(2, 1.23), 0.1663693837868141, 1e-10)).isTrue();
        assertThat(inRange(Maja.besselJn(2, 0), 0, 1e-10)).isTrue();
    }
}
