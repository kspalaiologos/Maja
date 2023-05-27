package rocks.palaiologos.maja.matrix;

import java.util.Objects;

/**
 * The result of LU decomposition.
 */
public class DoubleLUPDecompositionResult {
    private final DoubleMatrix lower;
    private final DoubleMatrix upper;
    private final int[] permutation;
    private final boolean nonsingular;
    private final double determinant;
    private final int n;
    private final int m;
    private final DoubleMatrix LU;

    public DoubleLUPDecompositionResult(DoubleMatrix lower, DoubleMatrix upper, int[] permutation,
                                        boolean nonsingular, double determinant, int n, int m, DoubleMatrix LU) {
        this.lower = lower;
        this.upper = upper;
        this.permutation = permutation;
        this.nonsingular = nonsingular;
        this.determinant = determinant;
        this.n = n;
        this.m = m;
        this.LU = LU;
    }

    private DoubleMatrix getMatrix(DoubleMatrix input, int[] r, int j0, int j1) {
        DoubleMatrix X = new DoubleMatrix(r.length, j1 - j0 + 1);
        try {
            for (int i = 0; i < r.length; i++)
                for (int j = j0; j <= j1; j++)
                    X.set(i, j - j0, input.get(r[i], j));
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Invalid permutation vector.");
        }
        return X;
    }

    /**
     * Solve A * X = B.
     *
     * @return X s.t. L * U * X = B(piv, :)
     */
    public DoubleMatrix solve(DoubleMatrix B) {
        if (B.height() != lower.height()) {
            throw new IllegalArgumentException("Matrix row dimensions must agree.");
        }
        if (nonsingular) {
            throw new RuntimeException("Matrix is singular.");
        }

        int nx = B.width();
        DoubleMatrix Xmat = getMatrix(B, permutation, 0, nx - 1);

        for (int k = 0; k < n; k++)
            for (int i = k + 1; i < n; i++)
                for (int j = 0; j < nx; j++)
                    Xmat.set(i, j, Xmat.get(i, j) - Xmat.get(k, j) * LU.get(i, k));

        for (int k = n - 1; k >= 0; k--) {
            for (int j = 0; j < nx; j++)
                Xmat.set(k, j, Xmat.get(k, j) / LU.get(k, k));
            for (int i = 0; i < k; i++)
                for (int j = 0; j < nx; j++)
                    Xmat.set(i, j, Xmat.get(i, j) - Xmat.get(k, j) * LU.get(i, k));
        }

        return Xmat;
    }

    /**
     * Get the lower triangular matrix.
     */
    public DoubleMatrix lower() {
        return lower;
    }

    /**
     * Get the upper triangular matrix.
     */
    public DoubleMatrix upper() {
        return upper;
    }

    /**
     * Get the permutation/pivot vector.
     */
    public int[] permutation() {
        return permutation;
    }

    /**
     * True whether the matrix is nonsingular.
     */
    public boolean nonsingular() {
        return nonsingular;
    }

    /**
     * Get the determinant of the matrix.
     * @return
     */
    public double determinant() {
        return determinant;
    }
}
