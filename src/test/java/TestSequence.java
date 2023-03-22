import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;

public class TestSequence {
    @Test
    public void testFib() {
        assertThat(Maja.fib(0)).isEqualTo(0);
        assertThat(Maja.fib(1)).isEqualTo(1);
        assertThat(Maja.fib(2)).isEqualTo(1);
        long a = 1, b = 1, c;
        for (int i = 3; i < 75; i++) {
            c = a + b;
            a = b;
            b = c;
            assertThat(Maja.fib(i)).isEqualTo(c);
        }
    }
}
