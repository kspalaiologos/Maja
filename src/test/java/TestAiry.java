import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Maja;

import static org.assertj.core.api.Assertions.assertThat;

public class TestAiry {
    @Test
    public void testAi() {
        assertThat(Maja.airy(1)).isEqualTo(0.13529241631288103);
        assertThat(Maja.airy(-1)).isEqualTo(0.5355608832923521);
        assertThat(Maja.airy(-5.6)).isEqualTo(-0.06833069968569841);
        assertThat(Maja.airy(5.6)).isEqualTo(2.650061329682329E-5);
        assertThat(Maja.airy(-12.6)).isEqualTo(-0.21808009991809604);
        assertThat(Maja.airy(12.6)).isEqualTo(1.6823857378748593E-14);
        assertThat(Maja.airy(-100.6)).isEqualTo(0.16333347669613552);
        assertThat(Maja.airy(100.6)).isEqualTo(6.462779351850713E-294);
        assertThat(Maja.airy(0)).isEqualTo(0.355028053887817);
        assertThat(Maja.airy(Double.POSITIVE_INFINITY)).isEqualTo(0);
        assertThat(Maja.airy(Double.NEGATIVE_INFINITY)).isEqualTo(0);
        assertThat(Maja.airy(Double.NaN)).isNaN();
    }

    @Test
    public void testAid() {
        assertThat(Maja.airyDerivative(1)).isEqualTo(-0.15914744129679362);
        assertThat(Maja.airyDerivative(-1)).isEqualTo(-0.010160567116645397);
        assertThat(Maja.airyDerivative(-5.6)).isEqualTo(0.8500325600489483);
        assertThat(Maja.airyDerivative(5.6)).isEqualTo(-6.384458124610766E-5);
        assertThat(Maja.airyDerivative(-12.6)).isEqualTo(-0.7327754709170626);
        assertThat(Maja.airyDerivative(12.6)).isEqualTo(-6.00525762013223E-14);
        assertThat(Maja.airyDerivative(-100.6)).isEqualTo(-0.7129273956799224);
        assertThat(Maja.airyDerivative(100.6)).isEqualTo(-6.483744752807934E-293);
        assertThat(Maja.airyDerivative(0)).isEqualTo(-0.258819403792807);
        assertThat(Maja.airyDerivative(Double.POSITIVE_INFINITY)).isEqualTo(0);
        assertThat(Maja.airyDerivative(Double.NEGATIVE_INFINITY)).isNaN();
        assertThat(Maja.airyDerivative(Double.NaN)).isNaN();
    }
}
