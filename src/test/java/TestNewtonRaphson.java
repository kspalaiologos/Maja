import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;

public class TestNewtonRaphson {
    @Test
    public void testPolyRoot() {
        // Try to find the root of "x^2 - 2x + 1" using Newton-Raphson method.
        // The root is either -1 or 1. We start with an estimate of 0.8.
        // The derivative of the function is 2x - 2.
        double result = Maja.newtonRaphson(x -> x*x - 2*x + 1, x -> 2*x - 2, 0.8, Maja.EPSILON);
        assertThat(Maja.abs(result - 1.0)).isLessThan(1.2e-9);
    }
}
