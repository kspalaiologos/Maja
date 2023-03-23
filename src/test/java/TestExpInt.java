import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;

public class TestExpInt {
    @Test
    public void testEi() {
        assertThat(Maja.expint(1)).isEqualTo(1.895117816355937);
        assertThat(Maja.expint(-1)).isEqualTo(-0.21938393439551984);
        assertThat(Maja.expint(0.5)).isEqualTo(0.45421990486317354);
        assertThat(Maja.expint(-0.5)).isEqualTo(-0.5597735947761607);
        assertThat(Maja.expint(-5.6)).isEqualTo(-5.708401696482114E-4);
        assertThat(Maja.expint(5.6)).isEqualTo(63.101785974299226);
        assertThat(Maja.expint(-12.6)).isEqualTo(-2.491344257486993E-7);
        assertThat(Maja.expint(12.6)).isEqualTo(25813.68766955508);
        assertThat(Maja.expint(-100.6)).isEqualTo(-2.0096604689686075E-46);
        assertThat(Maja.expint(100.6)).isEqualTo(4.918245900333886E41);
        assertThat(Maja.expint(0)).isEqualTo(Double.NEGATIVE_INFINITY);
        assertThat(Maja.expint(Double.POSITIVE_INFINITY)).isEqualTo(Double.POSITIVE_INFINITY);
        assertThat(Maja.expint(Double.NEGATIVE_INFINITY)).isEqualTo(0);
        assertThat(Maja.expint(Double.NaN)).isNaN();
    }
}
