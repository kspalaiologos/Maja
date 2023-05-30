import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.matrix.*;
import rocks.palaiologos.maja.expression.*;
import rocks.palaiologos.maja.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
    }
}
