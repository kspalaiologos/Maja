package rocks.palaiologos.maja;

/**
 * A function that takes an indeterminate amount of arguments.
 *
 * @author Palaiologos
 */
public interface PolyadicFunction {
    /**
     * Applies the function to the given arguments.
     *
     * @param args Arguments.
     * @return The result of the function application.
     */
    double apply(double... args);
}
