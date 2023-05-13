import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;

public class TestHypergeometric {
    private static boolean inRange(double a, double b, double error) {
        if(Math.abs(a - b) < error)
            return true;
        else {
            System.out.println("a = " + a + ", b = " + b + ". abs error: " + Math.abs(a - b) + ", rel error: " + Math.abs(a - b) * 100 / Math.abs(a) + "%");
            return false;
        }
    }

    @Test
    public void test2f1() {
        // 2F1(-1/2, 1/3, 4/3, -1) ~= 1.1114479705325755
        assertThat(Maja.hypergeo2F1(-0.5, 1.0 / 3.0, 4.0 / 3.0, -1)).isEqualTo(1.1114479705325755);
        assertThat(Maja.hypergeo2F1(-0.5, 1.0 / 3.0, -4.0 / 3.0, -1)).isEqualTo(0.8428895622758903);
        assertThat(Maja.hypergeo2F1(-0.5, 1.0 / 3.0, -4.0 / 3.0, 0)).isEqualTo(1);
        assertThat(Maja.hypergeo2F1(-3, -1, -2, 1)).isEqualTo(-0.5);
        assertThat(Maja.hypergeo2F1(-0.5, 1.0 / 4.0, -4.0 / 3.0, -3)).isEqualTo(0.6919237698061466);
        assertThat(Maja.hypergeo2F1(-0.5, 1.0 / 4.0, -4.0 / 3.0, -1.5)).isEqualTo(0.8274559676019738);
        assertThat(Maja.hypergeo2F1(0.5, 1.0 / 3.0, 4.0 / 3.0, 1)).isEqualTo(1.4021821053254542);
        assertThat(Maja.hypergeo2F1(-5, 1.0 / 3.0, 4.0 / 3.0, 1)).isEqualTo(0.5006868131868132);
        assertThat(Maja.hypergeo2F1(-0.5, 3.0, 1.0, 0.2)).isEqualTo(0.6638326808202499);
        // Try to hit some obscure special case with psi.
        assertThat(Maja.hypergeo2F1(2, 2.0, 3.0, 0.95)).isEqualTo(35.466521277442645);
        assertThat(Maja.hypergeo2F1(2, 2.0, 3.01, 0.95)).isEqualTo(34.81013407640587);
        assertThat(Maja.hypergeo2F1(20, 2.0, 3.01, 0.95)).isEqualTo(5.513090059384266E23);
        assertThat(Maja.hypergeo2F1(0.123, 2.0, 3.01, 0.95)).isEqualTo(1.166719619920923);
        assertThat(Maja.hypergeo2F1(-1, 2.0, 3.01, 0.95)).isEqualTo(0.3687707641196013);
    }

    @Test
    public void test1f1() {
        assertThat(Maja.hypergeo1F1(-0.5, 1.0 / 3.0, -1)).isEqualTo(2.269314995817403);
    }

    @Test
    public void test1f2() {
        assertThat(Maja.hypergeo1F2(-5, 1.0 / 3.0, 1.0, 1)).isEqualTo(-4.29743131868132);
    }

    @Test
    public void test3f0() {
        assertThat(Maja.hypergeo3F0(-5, 1.0 / 3.0, 1.0, 1)).isEqualTo(-1436.7160493827155);
    }
}
