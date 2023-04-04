import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Complex;
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
        // Despite looking rather large, the worst-case relative error is at most 4.418687638008123E-12%.
        assertThat(Maja.abs(Maja.airyBi(5.6) - 2540.18283758149890523387595324256875012096)).isLessThan(2e-12);
        assertThat(Maja.abs(Maja.airyBi(-12.6) - 0.2052016805306034694915364528812494365753)).isLessThan(1e-15);
        assertThat(Maja.abs(Maja.airyBi(12.6) - 2.67139640130579937853574376851794776585679e12)).isLessThan(1e-2);
        assertThat(Maja.abs(Maja.airyBi(-100.6) - 0.071120276667991626826926966029165362298975)).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.airyBi(100.6) - 2.4555381213245226172411883474564151744705065217434285e291)).isLessThan(1.1E278);
        assertThat(Maja.airyBi(Double.NaN)).isNaN();
    }

    @Test
    public void testBip() {
        assertThat(Maja.abs(Maja.airyBip(1) - 0.9324359333927756329594514536744353442695653752386)).isLessThan(1e-15);
        assertThat(Maja.abs(Maja.airyBip(-1) - 0.592375626422792350816779229181600973276795883367)).isLessThan(1e-15);
        assertThat(Maja.abs(Maja.airyBip(-5.6) + 0.177837595579460472064086640978407456618028224981)).isLessThan(1e-15);
        assertThat(Maja.abs(Maja.airyBip(5.6) - 5891.67408620813233496772479301623855645524466691141)).isLessThan(8.2e-12);
        assertThat(Maja.airyBip(Double.NaN)).isNaN();
    }

    @Test
    public void testComplexAiry() {
        assertThat(Maja.abs(Maja.sub(Maja.airyAi(new Complex(0.0, 0.0)), new Complex(0.3550280538878172, 0.0)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyBi(new Complex(0.0, 0.0)), new Complex(0.6149266274460007, 0.0)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyAip(new Complex(0.0, 0.0)), new Complex(-0.2588194037928068, 0.0)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyBip(new Complex(0.0, 0.0)), new Complex(0.4482883573538264, 0.0)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyAi(new Complex(0.0, 1.0)), new Complex(0.33149330543214117, -0.3174498589684438)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyBi(new Complex(0.0, 1.0)), new Complex(0.648858208330395, 0.34495863476804844)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyAip(new Complex(0.0, 1.0)), new Complex(-0.4324926598418071, 0.09804785622924324)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyBip(new Complex(0.0, 1.0)), new Complex(0.13502664671081904, -0.1288373867812549)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyAi(new Complex(1.0, 2.0)), new Complex(-0.21938625498142755, -0.17538591140810944)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyBi(new Complex(1.0, 2.0)), new Complex(0.04882203245306118, 0.1332740579917484)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyAip(new Complex(1.0, 2.0)), new Complex(0.1704449781789149, 0.3876224394132951)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyBip(new Complex(1.0, 2.0)), new Complex(-0.857239258605362, 0.4955063363095674)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyAi(new Complex(2.0, 3.0)), new Complex(0.008104457809530716, 0.1311783826045662)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyBi(new Complex(2.0, 3.0)), new Complex(-0.39636825504039286, -0.5697309129559506)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyAip(new Complex(2.0, 3.0)), new Complex(0.09665817903311283, -0.2319871853854855)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyBip(new Complex(2.0, 3.0)), new Complex(0.3494576719294665, -1.105328588933856)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyAi(new Complex(3.14, 4.123)), new Complex(-0.003774997833930135, -0.041771631836508574)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyBi(new Complex(3.14, 4.123)), new Complex(0.6442857963384823, 1.5485619499613679)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyAip(new Complex(3.14, 4.123)), new Complex(-0.03287826949831132, 0.09016844477636717)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyBip(new Complex(3.14, 4.123)), new Complex(-0.5084782421439571, 3.724440496272063)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyAi(new Complex(-12.5, 3.0)), new Complex(-1.1476605668096626E10, 3.332393985724673E9)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyBi(new Complex(-12.5, 3.0)), new Complex(-3.332393988211891E9, -1.1476605654396492E10)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyAip(new Complex(-12.5, 3.0)), new Complex(1.332235505218332E10, 3.1537533412608437E10)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyBip(new Complex(-12.5, 3.0)), new Complex(-3.1537533452420063E10, 1.3322355049692102E10)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyAi(new Complex(0.0, -1.0)), new Complex(0.33149330543214117, 0.3174498589684438)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyBi(new Complex(0.0, -1.0)), new Complex(0.648858208330395, -0.34495863476804844)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyAip(new Complex(0.0, -1.0)), new Complex(-0.4324926598418071, -0.09804785622924324)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyBip(new Complex(0.0, -1.0)), new Complex(0.13502664671081904, 0.1288373867812549)))).isLessThan(2e-14);
        // The real error in this approximation is under 1.5192e-103%!
        assertThat(Maja.abs(Maja.sub(Maja.airyAi(new Complex(46.0, -0.66)), new Complex(-1.1867613952534584E-92, -5.013535780433795E-92)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyBi(new Complex(46.0, -0.66)), new Complex(-1.0808670780287277E89, 4.4243448428565356E89)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyAip(new Complex(46.0, -0.66)), new Complex(8.299192743117375E-92, 3.397386724517858E-91)))).isLessThan(2e-14);
        assertThat(Maja.abs(Maja.sub(Maja.airyBip(new Complex(46.0, -0.66)), new Complex(-7.109489567932175E89, 3.0036723875386964E90)))).isLessThan(2e-14);
    }
}
