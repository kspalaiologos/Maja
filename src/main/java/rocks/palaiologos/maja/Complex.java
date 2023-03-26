package rocks.palaiologos.maja;

/**
 * A record representing a complex number.
 * @param re real part
 * @param im imaginary part
 */
public record Complex(double re, double im) {
    /**
     * Construct a complex value with the given real part and zero imaginary part.
     * @param re
     */
    public Complex(double re) {
        this(re, 0);
    }

    /**
     * Construct a complex value with zero real and imaginary parts.
     */
    public Complex() {
        this(0, 0);
    }
}
