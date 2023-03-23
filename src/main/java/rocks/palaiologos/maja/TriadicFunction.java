package rocks.palaiologos.maja;

/**
 * A function that takes three arguments.
 *
 * @author Palaiologos
 */
public interface TriadicFunction {
    /**
     * Applies the function to the given arguments.
     *
     * @param x First argument.
     * @param y Second argument.
     * @param z Third argument.
     * @return The result of the function application.
     */
    double apply(double x, double y, double z);
}
