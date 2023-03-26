package rocks.palaiologos.maja;

/**
 * A record representing a complex number in the cartesian form.
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

    public static final Complex NaN = new Complex(Double.NaN, Double.NaN);
    public static final Complex COMPLEX_INFINITY = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    public static final Complex ZERO = new Complex(0, 0);
    public static final Complex ONE = new Complex(1, 0);

    public static boolean isNaN(Complex c) {
        return Double.isNaN(c.re) || Double.isNaN(c.im);
    }

    public static boolean isInfinite(Complex c) {
        return Double.isInfinite(c.re) || Double.isInfinite(c.im);
    }
}
