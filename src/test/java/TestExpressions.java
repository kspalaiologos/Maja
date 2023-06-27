import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.matrix.*;
import rocks.palaiologos.maja.expression.*;
import rocks.palaiologos.maja.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static rocks.palaiologos.maja.Maja.*;

public class TestExpressions {
    @Test
    public void testSimpleArithmetic() {
        assertThat(Evaluator.evaluate("""
        2 + 2;
        """, new Environment())).isEqualTo(4L);

        assertThat(Evaluator.evaluate("""
        four = 2 + 2;
        two = 6 - four;
        fourty_two = four * 10 + two;
        """, new Environment())).isEqualTo(42L);

        assertThat(Evaluator.evaluate("""
        2 + 2 * 2;
        """, new Environment())).isEqualTo(6L);
    }

    @Test
    public void testFunctionCalls() {
        assertThat(Evaluator.evaluate("""
        add(a, b) = a + b;
        add(2, 2);
        """, new Environment())).isEqualTo(4L);

        assertThat(Evaluator.evaluate("""
        signum(x) = if x > 0 then 1 else if x < 0 then -1 else 0;
        signum(2);
        """, new Environment())).isEqualTo(1L);

        assertThat(Evaluator.evaluate("""
        signum(x) = if x > 0 then 1 else if x < 0 then -1 else 0;
        signum(-2);
        """, new Environment())).isEqualTo(-1L);

        assertThat(Evaluator.evaluate("""
        a = -1;
        a = a + 1;
        a;
        """, new Environment())).isEqualTo(0L);

        assertThat(Evaluator.evaluate("""
        fib(x) {
            local a = 0;
            local b = 1;
            while x > 0 {
                local c = a + b;
                a = b;
                b = c;
                x = x - 1;
            };
            return a;
        };
        
        fib(10);
        """, new Environment())).isEqualTo(55L);
    }
}
