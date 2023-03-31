import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;

public class TestAiry {
    @Test
    public void testAi() {
        assertThat(Maja.airyAi(1)).isEqualTo(0.13529241631288147);
        assertThat(Maja.airyAi(-1)).isEqualTo(0.5355608832923522);
        assertThat(Maja.airyAi(-5.6)).isEqualTo(-0.06833069968616749);
        assertThat(Maja.airyAi(5.6)).isEqualTo(2.6500613296849975E-5);
        assertThat(Maja.airyAi(-12.6)).isEqualTo(-0.2180800999180254);
        assertThat(Maja.airyAi(12.6)).isEqualTo(1.6785354028490574E-14);
        assertThat(Maja.airyAi(-100.6)).isEqualTo(0.1633334766961162);
        assertThat(Maja.airyAi(100.6)).isEqualTo(6.462112688957056E-294);
        assertThat(Maja.airyAi(0)).isEqualTo(0.3550280538878172);
        assertThat(Maja.airyAi(Double.POSITIVE_INFINITY)).isEqualTo(0);
        assertThat(Maja.airyAi(Double.NEGATIVE_INFINITY)).isEqualTo(0);
        assertThat(Maja.airyAi(Double.NaN)).isNaN();
    }

    @Test
    public void testAid() {
        assertThat(Maja.airyAip(1)).isEqualTo(-0.15914744129679328);
        assertThat(Maja.airyAip(-1)).isEqualTo(-0.010160567116645175);
        assertThat(Maja.airyAip(-5.6)).isEqualTo(0.8500325600489317);
        assertThat(Maja.airyAip(5.6)).isEqualTo(-6.384458124617724E-5);
        assertThat(Maja.airyAip(-12.6)).isEqualTo(-0.7327754709173728);
        assertThat(Maja.airyAip(12.6)).isEqualTo(-5.991063349331567E-14);
        assertThat(Maja.airyAip(-100.6)).isEqualTo(-0.7129273956803683);
        assertThat(Maja.airyAip(100.6)).isEqualTo(-6.483074934003161E-293);
        assertThat(Maja.airyAip(0)).isEqualTo(-0.2588194037928068);
        assertThat(Maja.airyAip(Double.POSITIVE_INFINITY)).isEqualTo(0);
        assertThat(Maja.airyAip(Double.NEGATIVE_INFINITY)).isNaN();
        assertThat(Maja.airyAip(Double.NaN)).isNaN();
    }

    @Test
    public void testBi() {
        assertThat(Maja.abs(Maja.airyBi(1) - 1.2074235949528712594363788170282869953853)).isLessThan(1e-15);
        assertThat(Maja.abs(Maja.airyBi(-1) - 0.1039973894969446118886899909785991446370)).isLessThan(1e-15);
        assertThat(Maja.abs(Maja.airyBi(-5.6) + 0.360172225437713344245843596994549992815)).isLessThan(1e-15);
        // Accuracy diminishes for larger values of x, but this is not a huge problem.
        assertThat(Maja.abs(Maja.airyBi(5.6) - 2540.18283758149890523387595324256875012096)).isLessThan(2e-12);
        assertThat(Maja.abs(Maja.airyBi(-12.6) - 0.2052016805306034694915364528812494365753)).isLessThan(1e-15);
        assertThat(Maja.abs(Maja.airyBi(12.6) - 2.67139640130579937853574376851794776585679e12)).isLessThan(1e-2);
        assertThat(Maja.abs(Maja.airyBi(-100.6) - 0.071120276667991626826926966029165362298975)).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.airyBi(100.6) - 2.4555381213245226172411883474564151744705065217434285e291)).isLessThan(1.1E278);
    }
}
