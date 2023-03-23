package rocks.palaiologos.maja;

/**
 * A function that takes a single argument.
 *
 * @author Palaiologos
 */
public interface MonadicFunction {
    /**
     * Applies the function to the given argument.
     *
     * @param x The argument.
     * @return The result of the function application.
     */
    double apply(double x);
}
