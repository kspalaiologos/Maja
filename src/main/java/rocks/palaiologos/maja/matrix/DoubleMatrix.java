package rocks.palaiologos.maja.matrix;

import rocks.palaiologos.maja.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * A class representing a two-dimensional matrix of double precision floating point numbers.
 *
 * @author Palaiologos
 */
public class DoubleMatrix extends Matrix<Double> {
    public DoubleMatrix(Double[][] data) {
        super(data);
    }

    public DoubleMatrix(int rows, int columns) {
        super(rows, columns);
    }

    public DoubleMatrix(List<List<Double>> data) {
        super(data);
    }

    private static Matrix<Double> minor(Matrix<Double> a, int x, int y) {
        int length = a.height() - 1;
        Matrix<Double> result = new Matrix<>(length, length);
        for (int i = 0; i < length; i++)
            for (int j = 0; j < length; j++) {
                if (i < x && j < y) {
                    result.set(i, j, a.get(i, j));
                } else if (i >= x && j < y) {
                    result.set(i, j, a.get(i + 1, j));
                } else if (i < x) {
                    result.set(i, j, a.get(i, j + 1));
                } else {
                    result.set(i, j, a.get(i + 1, j + 1));
                }
            }
        return result;
    }

    private static double det(Matrix<Double> a) {
        if (a.height() != a.width())
            throw new IllegalArgumentException("Matrix must be square.");
        if (a.height() == 1) {
            return a.get(0, 0);
        } else {
            int sign = 1;
            double sum = 0;
            for (int i = 0; i < a.height(); i++) {
                sum += sign * a.get(0, i) * det(minor(a, 0, i));
                sign *= -1;
            }
            return sum;
        }
    }

    private static double perm(Matrix<Double> a) {
        if (a.height() != a.width())
            throw new IllegalArgumentException("Matrix must be square.");
        if (a.height() == 1) {
            return a.get(0, 0);
        } else {
            double sum = 0;
            for (int i = 0; i < a.height(); i++)
                sum += a.get(0, i) * perm(minor(a, 0, i));
            return sum;
        }
    }

    private static double alt(Matrix<Double> a, BiFunction<Double, Double, Double> vector, BiFunction<Double, Double, Double> scalar) {
        if (a.height() != a.width())
            throw new IllegalArgumentException("Matrix must be square.");
        if (a.height() == 1) {
            return a.get(0, 0);
        } else {
            double sum = scalar.apply(a.get(0, 0), alt(minor(a, 0, 0), vector, scalar));
            for (int i = 1; i < a.height(); i++)
                sum = vector.apply(sum, scalar.apply(a.get(0, i), alt(minor(a, 0, i), vector, scalar)));
            return sum;
        }
    }

    /**
     * Compute the determinant of the matrix.
     */
    public double det() {
        return det(this);
    }

    /**
     * Compute the permanent of the matrix.
     */
    public double perm() {
        return perm(this);
    }

    /**
     * The expressions <code>A.alt(Maja::sub, Maja::mul)</code> and <code>A.alt(Maja::add, Maja::mul)</code>
     * are for square matrix arguments A, the determinant and the permanent of mathematics. The generalization
     * to arguments other than plus, minus and times is based on construing the determinant as an alternating
     * sum over products over the diagonals of tables obtained by permuting the major cells of A.
     */
    public double alt(BiFunction<Double, Double, Double> vector, BiFunction<Double, Double, Double> scalar) {
        return alt(this, vector, scalar);
    }

    /**
     * Computes the LU decomposition of a matrix using the Doolittle algorithm.
     *
     * @return A pair of matrices, the first being the lower triangular matrix and the second the upper triangular matrix.
     * If the matrix is singular, the pair will contain null values.
     * @throws IllegalArgumentException If the matrix is not square.
     */
    public Pair<Matrix<Double>, Matrix<Double>> LU() {
        Matrix<Double> lower = new DoubleMatrix(height(), width());
        Matrix<Double> upper = new DoubleMatrix(height(), width());

        if (height() != width())
            throw new IllegalArgumentException("Matrix must be square.");

        for (int i = 0; i < height(); i++) {
            for (int k = i; k < height(); k++) {
                double sum = 0;

                for (int j = 0; j < i; j++)
                    sum += lower.get(i, j) * upper.get(j, k);

                upper.set(i, k, get(i, k) - sum);
            }

            // Lower Triangular
            for (int k = i; k < height(); k++) {
                if (i == k)
                    lower.set(i, i, 1.0);
                else {
                    double sum = 0;
                    for (int j = 0; j < i; j++)
                        sum += lower.get(k, j) * upper.get(j, i);

                    lower.set(k, i, get(k, i) * sum / upper.get(i, i));
                }
            }
        }

        // check if lower/upper contains NaN
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < height(); j++) {
                if (Double.isNaN(lower.get(i, j)) || Double.isNaN(lower.get(j, i))) {
                    return new Pair<>(null, null);
                }
            }
        }

        return new Pair<>(lower, upper);
    }

    /**
     * Computes the LUP decomposition of a rectangular or square matrix.
     * Also determines whether the matrix is singular and computes its determinant.
     */
    public DoubleLUPDecompositionResult LUP() {
        int m = height();
        int n = width();
        int pivsign = 1;
        int[] piv = new int[m];
        for (int i = 0; i < m; i++)
            piv[i] = i;
        Matrix<Double> LU = this.copy();
        List<Double> LUrowi;
        double[] LUcolj = new double[m];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++)
                LUcolj[i] = LU.get(i, j);

            for (int i = 0; i < m; i++) {
                LUrowi = LU.row(i);
                int kmax = Math.min(i, j);
                double s = 0.0;
                for (int k = 0; k < kmax; k++)
                    s += LUrowi.get(k) * LUcolj[k];
                LUrowi.set(j, LUcolj[i] -= s);
            }

            int p = j;
            for (int i = j + 1; i < m; i++)
                if (Math.abs(LUcolj[i]) > Math.abs(LUcolj[p]))
                    p = i;
            if (p != j) {
                for (int k = 0; k < n; k++)
                    LU.swap(p, k, j, k);
                int k = piv[p];
                piv[p] = piv[j];
                piv[j] = k;
                pivsign = -pivsign;
            }

            if (j < m & LU.get(j, j) != 0.0)
                for (int i = j + 1; i < m; i++)
                    LU.set(i, j, LU.get(i, j) / LU.get(j, j));
        }

        // Determine if the matrix is nonsingular.
        boolean nonsingular = true;
        for (int j = 0; j < n; j++) {
            if (LU.get(j, j) == 0) {
                nonsingular = false;
                break;
            }
        }

        Matrix<Double> L = new DoubleMatrix(m, n);
        Matrix<Double> U = new DoubleMatrix(n, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > j) {
                    L.set(i, j, LU.get(i, j));
                } else if (i == j) {
                    L.set(i, j, 1.0);
                } else {
                    L.set(i, j, 0.0);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i <= j) {
                    U.set(i, j, LU.get(i, j));
                } else {
                    U.set(i, j, 0.0);
                }
            }
        }
        double d = pivsign;
        for (int j = 0; j < n; j++) {
            d *= LU.get(j, j);
        }

        return new DoubleLUPDecompositionResult(L, U, piv, nonsingular, d);
    }

    /**
     * Compute the trace of a matrix.
     * @return
     */
    public double trace() {
        double trace = 0;
        int max = Math.min(height(), width());
        for (int i = 0; i < max; i++)
            trace += get(i, i);
        return trace;
    }
}
