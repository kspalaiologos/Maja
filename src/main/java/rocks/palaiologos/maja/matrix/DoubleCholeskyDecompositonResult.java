package rocks.palaiologos.maja.matrix;

/**
 * The result of Cholesky decomposition of a matrix.
 *
 * @param l   The lower triangular matrix.
 * @param spd Whether the matrix is symmetric positive definite.
 * @author Palaiologos
 */
public record DoubleCholeskyDecompositonResult(DoubleMatrix l, boolean spd) {
    /**
     * Solve A * X = B.
     *
     * @return X s.t. L * L' * X = B
     */
    public DoubleMatrix solve(DoubleMatrix B) {
        if (B.height() != l.height()) {
            throw new IllegalArgumentException("Matrix row dimensions must be the same.");
        }

        if (!spd) {
            throw new IllegalArgumentException("Matrix is not symmetric positive definite.");
        }

        int n = l.height();
        int nx = B.width();
        DoubleMatrix X = B.copy();

        for (int k = 0; k < n; k++) {
            for (int j = 0; j < nx; j++) {
                for (int i = 0; i < k; i++)
                    X.set(k, j, X.get(k, j) - X.get(i, j) * l().get(k, i));
                X.set(k, j, X.get(k, j) / l().get(k, k));
            }
        }

        for (int k = n - 1; k >= 0; k--) {
            for (int j = 0; j < nx; j++) {
                for (int i = k + 1; i < n; i++)
                    X.set(k, j, X.get(k, j) - X.get(i, j) * l().get(i, k));
                X.set(k, j, X.get(k, j) / l().get(k, k));
            }
        }

        return X;
    }
}
