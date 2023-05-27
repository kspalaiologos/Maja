package rocks.palaiologos.maja.matrix;

/**
 * The result of QR composition.
 * @param q The Q matrix.
 * @param r The R matrix.
 * @param h The H matrix.
 * @param fullrank Whether the matrix is full rank.
 */
public record DoubleQRDecompositionResult(Matrix<Double> q, Matrix<Double> r, Matrix<Double> h,
                                          boolean fullrank) {
}
