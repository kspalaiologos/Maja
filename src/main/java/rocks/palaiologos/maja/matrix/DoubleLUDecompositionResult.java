package rocks.palaiologos.maja.matrix;

/**
 * The result of LU decomposition.
 * @param lower The lower triangular matrix.
 * @param upper The upper triangular matrix.
 * @param singular Whether the matrix is singular.
 *
 * If the matrix is singular, the upper and lower triangular matrices will be null.
 */
public record DoubleLUDecompositionResult(Matrix<Double> lower, Matrix<Double> upper, boolean singular) {
}
