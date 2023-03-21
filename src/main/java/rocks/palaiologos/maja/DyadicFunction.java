package rocks.palaiologos.maja;

/**
 * A function that takes two arguments.
 * @author Palaiologos
 */
public interface DyadicFunction {
    /**
     * Applies the function to the given arguments.
     * @param x First argument.
     * @param y Second argument.
     * @return The result of the function application.
     */
    double apply(double x, double y);
}
