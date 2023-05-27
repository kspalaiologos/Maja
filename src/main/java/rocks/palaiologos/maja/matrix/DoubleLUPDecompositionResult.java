package rocks.palaiologos.maja.matrix;

/**
 * The result of LU composition.
 * @param lower The lower triangular matrix.
 * @param upper The upper triangular matrix.
 * @param permutation The permutation vector.
 * @param nonsingular Whether the matrix is singular.
 * @param determinant The determinant of the matrix.
 */
public record DoubleLUPDecompositionResult(Matrix<Double> lower, Matrix<Double> upper, int[] permutation,
                                           boolean nonsingular, double determinant) {
}
