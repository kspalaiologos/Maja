package rocks.palaiologos.maja.matrix;

/**
 * The result of QR decomposition.
 * @author Palaiologos
 */
public class DoubleQRDecompositionResult {
    private final DoubleMatrix q;
    private final DoubleMatrix r;
    private final DoubleMatrix h;
    private final boolean fullrank;
    private final int n;
    private final int m;
    private final DoubleMatrix QR;
    private final double[] Rdiag;

    public DoubleQRDecompositionResult(DoubleMatrix q, DoubleMatrix r, DoubleMatrix h,
                                       boolean fullrank, int n, int m, DoubleMatrix QR,
                                       double[] Rdiag) {
        this.q = q;
        this.r = r;
        this.h = h;
        this.fullrank = fullrank;
        this.n = n;
        this.m = m;
        this.QR = QR;
        this.Rdiag = Rdiag;
    }

    private DoubleMatrix getMatrix(DoubleMatrix m, int i0, int i1, int j0, int j1) {
        DoubleMatrix X = new DoubleMatrix(i1-i0+1,j1-j0+1);
        try {
            for (int i = i0; i <= i1; i++)
                for (int j = j0; j <= j1; j++)
                    X.set(i-i0, j-j0, m.get(i, j));
        } catch(IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Invalid matrix slice.");
        }
        return X;
    }

    /**
     * Least squares solution of A*X = B.
     *
     * @param B
     * @return X that minimizes the two norm of Q*R*X-B.
     */
    public DoubleMatrix solve(DoubleMatrix B) {
        if (B.height() != m) {
            throw new IllegalArgumentException("Matrix row dimensions must agree.");
        }
        if (!this.fullrank()) {
            throw new RuntimeException("Matrix is rank deficient.");
        }

        int nx = B.width();
        DoubleMatrix X = B.copy();

        for (int k = 0; k < n; k++) {
            for (int j = 0; j < nx; j++) {
                double s = 0.0;
                for (int i = k; i < m; i++)
                    s += QR.get(i, k) * X.get(i, j);
                s = -s / QR.get(k, k);
                for (int i = k; i < m; i++)
                    X.set(i, j, X.get(i, j) + s * QR.get(i, k));
            }
        }

        for (int k = n - 1; k >= 0; k--) {
            for (int j = 0; j < nx; j++)
                X.set(k, j, X.get(k, j) / Rdiag[k]);
            for (int i = 0; i < k; i++)
                for (int j = 0; j < nx; j++)
                    X.set(i, j, X.get(i, j) - X.get(k, j) * QR.get(i, k));
        }
        return getMatrix(X, 0, n - 1, 0, nx - 1);
    }

    /**
     * The Q matrix.
     */
    public DoubleMatrix q() {
        return q;
    }

    /**
     * The R matrix.
     */
    public DoubleMatrix r() {
        return r;
    }

    /**
     * The householder matrix; lower trapezoidal matrix whose columns define the reflections.
     */
    public DoubleMatrix h() {
        return h;
    }

    /**
     * True if the matrix is full rank.
     */
    public boolean fullrank() {
        return fullrank;
    }
}
