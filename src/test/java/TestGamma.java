import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Complex;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;

public class TestGamma {
    private static boolean inRange(double a, double b, double error) {
        if(Math.abs(a - b) < error)
            return true;
        else {
            System.out.println("a = " + a + ", b = " + b + ". abs error: " + Math.abs(a - b) + ", rel error: " + Math.abs(a - b) * 100 / Math.abs(a) + "%");
            return false;
        }
    }

    private static boolean inRange(Complex a, Complex b, double error) {
        if(Maja.abs(Maja.sub(a, b)) < error)
            return true;
        else {
            System.out.println("a = " + a + ", b = " + b);
            return false;
        }
    }

    @Test
    public void testLogGamma() {
        assertThat(inRange(Maja.loggamma(new Complex(2, 3)), new Complex(-2.0928517530927333496, 2.3023965434668676262), 1e-10)).isTrue();
        assertThat(inRange(Maja.loggamma(new Complex(2, -3)), new Complex(-2.0928517530927333496, -2.3023965434668676262), 1e-10)).isTrue();
        assertThat(inRange(Maja.loggamma(new Complex(0.1, -3)), new Complex(-4.232218700260561, 0.3453402012115774), 1e-8)).isTrue();
    }

    private void singlePQTest(double a, double b) {
        assertThat(inRange(Maja.gammaP(a, b), Maja.liGamma(a, b) / Maja.gamma(a), 1e-10)).isTrue();
        assertThat(inRange(Maja.gammaQ(a, b), 1 - Maja.gammaP(a, b), 1e-10)).isTrue();
    }

    @Test
    public void testGammaP() {
        singlePQTest(2, 3);
        singlePQTest(2, 2.1);
        singlePQTest(0.1, 1.1);
        singlePQTest(0.1, 2.3);
        singlePQTest(6, 4);
    }

    @Test
    public void testTrigamma() {
        assertThat(inRange(Maja.trigamma(new Complex(4, 4)), new Complex(0.124353113946791703820257508879800737068228888002131562871920706, -0.141280016184178366603774041062938465023329265448130240189479533), 1e-13)).isTrue();
        assertThat(inRange(Maja.trigamma(new Complex(1.2, 3.4)), new Complex(0.059284583118724857330067943571590924350816923118478555649, -0.28382246859381821383250103826361199655527017131703531353), 1e-13)).isTrue();
        assertThat(inRange(Maja.trigamma(new Complex(1.2, -3.4)), new Complex(0.059284583118724857330067943571590924350816923118478555649, 0.28382246859381821383250103826361199655527017131703531353), 1e-13)).isTrue();
        assertThat(inRange(Maja.trigamma(new Complex(-1.2, -3.4)), new Complex(-0.1191662463589674588359003922550124223360402786764159714, +0.2355384251187193790380136419543691354562933412585958068), 1e-13)).isTrue();
        assertThat(inRange(Maja.trigamma(new Complex(12.1, -0.6)), new Complex(0.08592447095958903929649030193395280599200403347496249462, 0.004438903816100206215755320207688402550826527364403977528), 1e-13)).isTrue();

        assertThat(inRange(Maja.trigamma(4), 0.2838229557371153253613040555349140781078387900956873266244471182, 1e-13)).isTrue();
        assertThat(inRange(Maja.trigamma(1.2), 1.2673772054237791233024662150319078883898839908004093844566, 1e-13)).isTrue();
        assertThat(inRange(Maja.trigamma(-1.2), 27.993918581946144682948499926196889172435040166287452112090, 1e-13)).isTrue();
        assertThat(inRange(Maja.trigamma(12), 0.0869018728717683907503001804617749357046271228147559764290276587, 1e-13)).isTrue();
    }

    @Test
    public void testDigamma() {
        assertThat(inRange(Maja.digamma(4), 1.2561176684318007, 1e-13)).isTrue();
        assertThat(inRange(Maja.digamma(1.2), -0.28903989659218654, 1e-13)).isTrue();
        assertThat(inRange(Maja.digamma(-1.2), 4.868324766627196, 1e-13)).isTrue();
        assertThat(inRange(Maja.digamma(12), 2.4426616799758127, 1e-13)).isTrue();
    }

    @Test
    public void testGamma() {
        assertThat(inRange(Maja.gamma(4.5), 11.631728396567448929144224109426265262108918305803165528903, 1e-13)).isTrue();
        // a = 2.6582715747884676E54, b = 2.658271574788449E54.
        // relative error 0.0000000000007%. absolute error 1.86E40.
        assertThat(inRange(Maja.gamma(45), 2.658271574788448768043625811014615890319638528e54, 2e40)).isTrue();
        assertThat(inRange(Maja.gamma(34), 8.68331761881188649551819440128e36, 1e23)).isTrue();
        assertThat(inRange(Maja.gamma(32), 8.22283865417792281772556288e33, 1.2e18)).isTrue();
    }
}
