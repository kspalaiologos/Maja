package rocks.palaiologos.maja.matrix;

/**
 * The result of Cholesky decomposition of a matrix.
 * @param l The lower triangular matrix.
 * @param spd Whether the matrix is symmetric positive definite.
 */
public record DoubleCholeskyDecompositonResult(Matrix<Double> l, boolean spd) {
}
