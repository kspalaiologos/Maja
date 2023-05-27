package rocks.palaiologos.maja.matrix;

/**
 * The result of QR decomposition.
 *
 * @param q        The Q matrix.
 * @param r        The R matrix.
 * @param h        The householder matrix; lower trapezoidal matrix whose columns define the reflections.
 * @param fullrank Whether the matrix is full rank.
 */
public record DoubleQRDecompositionResult(DoubleMatrix q, DoubleMatrix r, DoubleMatrix h,
                                          boolean fullrank) {
}
