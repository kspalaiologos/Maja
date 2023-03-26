import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;

public class TestSpence {
    @Test
    public void testSpence() {
        assertThat(Maja.spence(0)).isEqualTo(1.6449340668482264); // pi^2/6
        assertThat(Maja.spence(1)).isEqualTo(0);
        assertThat(Maja.spence(0.5)).isEqualTo(0.5822405264650135);
        assertThat(Maja.spence(0.25)).isEqualTo(0.9784693929303059);
        assertThat(Maja.spence(1.23)).isEqualTo(-0.2179742127832413);
        assertThat(Maja.spence(4.56)).isEqualTo(-2.1877749674714857);
        assertThat(Maja.spence(10.11)).isEqualTo(-3.9787023268997883);
        assertThat(Maja.spence(33.33)).isEqualTo(-7.655511670013137);
        assertThat(Maja.spence(Double.NaN)).isNaN();
        assertThat(Maja.spence(Double.POSITIVE_INFINITY)).isEqualTo(Double.NEGATIVE_INFINITY);
        assertThat(Maja.spence(Double.NEGATIVE_INFINITY)).isEqualTo(Double.NEGATIVE_INFINITY);
    }
}
