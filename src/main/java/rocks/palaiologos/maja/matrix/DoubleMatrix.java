package rocks.palaiologos.maja.matrix;

import rocks.palaiologos.maja.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

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

    public DoubleMatrix(double[][] data) {
        super(data.length, data[0].length);
        for (int i = 0; i < data.length; i++)
            for (int j = 0; j < data[0].length; j++)
                set(i, j, data[i][j]);
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
            double acc = scalar.apply(a.get(a.height() - 1, 0), alt(minor(a, a.height() - 1, 0), vector, scalar));
            for (int i = a.height() - 2; i >= 0; i--)
                acc = vector.apply(scalar.apply(a.get(i, 0), alt(minor(a, i, 0), vector, scalar)), acc);
            return acc;
        }
    }

    /**
     * Generate an identity matrix of the given order.
     */
    public static DoubleMatrix identity(int n) {
        DoubleMatrix result = new DoubleMatrix(n, n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result.set(i, j, i == j ? 1.0 : 0.0);
        return result;
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
     */
    public DoubleLUDecompositionResult LU() {
        DoubleMatrix lower = new DoubleMatrix(height(), width());
        DoubleMatrix upper = new DoubleMatrix(height(), width());

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
                    return new DoubleLUDecompositionResult(null, null, true);
                }
            }
        }

        return new DoubleLUDecompositionResult(lower, upper, false);
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
        DoubleMatrix LU = new DoubleMatrix(data);
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

        DoubleMatrix L = new DoubleMatrix(m, n);
        DoubleMatrix U = new DoubleMatrix(n, n);
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

        return new DoubleLUPDecompositionResult(L, U, piv, !nonsingular, d, this.height(), this.width(), LU);
    }

    /**
     * Compute the trace of a matrix.
     */
    public double trace() {
        double trace = 0;
        int max = Math.min(height(), width());
        for (int i = 0; i < max; i++)
            trace += get(i, i);
        return trace;
    }

    /**
     * Perform the QR decomposition algorithm using the Householder transformation.
     */
    public DoubleQRDecompositionResult QR() {
        int m = height(), n = width();
        DoubleMatrix QR = new DoubleMatrix(data);
        double[] Rdiag = new double[n];
        for (int k = 0; k < n; k++) {
            double nrm = 0;
            for (int i = k; i < m; i++)
                nrm = Math.hypot(nrm, QR.get(i, k));
            if (nrm != 0.0) {
                if (QR.get(k, k) < 0)
                    nrm = -nrm;
                for (int i = k; i < m; i++)
                    QR.set(i, k, QR.get(i, k) / nrm);
                QR.set(k, k, QR.get(k, k) + 1.0);
                for (int j = k + 1; j < n; j++) {
                    double s = 0.0;
                    for (int i = k; i < m; i++)
                        s += QR.get(i, k) * QR.get(i, j);
                    s = -s / QR.get(k, k);
                    for (int i = k; i < m; i++)
                        QR.set(i, j, QR.get(i, j) + s * QR.get(i, k));
                }
            }
            Rdiag[k] = -nrm;
        }

        boolean isFullRank = true;
        for (double v : Rdiag) {
            if (v == 0) {
                isFullRank = false;
                break;
            }
        }

        DoubleMatrix H = new DoubleMatrix(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                if (i >= j) H.set(i, j, QR.get(i, j));
                else H.set(i, j, 0.0);
        }

        DoubleMatrix R = new DoubleMatrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                if (i < j)
                    R.set(i, j, QR.get(i, j));
                else if (i == j)
                    R.set(i, j, Rdiag[i]);
                else
                    R.set(i, j, 0.0);
        }

        DoubleMatrix Q = new DoubleMatrix(m, n);
        for (int k = n - 1; k >= 0; k--) {
            for (int i = 0; i < m; i++)
                Q.set(i, k, 0.0);
            Q.set(k, k, 1.0);
            for (int j = k; j < n; j++) {
                if (QR.get(k, k) != 0) {
                    double s = 0.0;
                    for (int i = k; i < m; i++)
                        s += QR.get(i, k) * Q.get(i, j);
                    s = -s / QR.get(k, k);
                    for (int i = k; i < m; i++)
                        Q.set(i, j, Q.get(i, j) + s * QR.get(i, k));
                }
            }
        }

        return new DoubleQRDecompositionResult(Q, R, H, isFullRank, this.height(), this.width(), QR, Rdiag);
    }

    /**
     * Perform the Cholesky decomposition algorithm.
     */
    public DoubleCholeskyDecompositonResult cholesky() {
        int n = height();
        DoubleMatrix L = new DoubleMatrix(n, n);
        boolean isspd = width() == n;
        for (int j = 0; j < n; j++) {
            List<Double> Lrowj = L.row(j);
            double d = 0.0;
            for (int k = 0; k < j; k++) {
                List<Double> Lrowk = L.row(k);
                double s = 0.0;
                for (int i = 0; i < k; i++)
                    s += Lrowk.get(i) * Lrowj.get(i);
                Lrowj.set(k, s = (get(j, k) - s) / L.get(k, k));
                d = d + s * s;
                isspd = isspd & Objects.equals(get(k, j), get(j, k));
            }
            d = get(j, j) - d;
            isspd = isspd & d > 0.0;
            L.set(j, j, Math.sqrt(Math.max(d, 0.0)));
            for (int k = j + 1; k < n; k++)
                L.set(j, k, 0.0);
        }
        return new DoubleCholeskyDecompositonResult(L, isspd);
    }

    /**
     * Perform eigenvalue decomposition.
     */
    public DoubleEigenvalueDecompositionResult eigen() {
        var i = new EigenvalueDecompositionImpl(this);
        return new DoubleEigenvalueDecompositionResult(i.getD(), i.getV(), i.getEigenvalues());
    }

    private DoubleMatrix inv2x2() {
        DoubleMatrix r = new DoubleMatrix(2, 2);
        double a = get(0, 0), b = get(0, 1), c = get(1, 0), d = get(1, 1);
        double det = a * d - b * c;
        if(det == 0.0)
            throw new IllegalArgumentException("Matrix is singular.");
        double invdet = 1.0 / det;
        r.set(0, 0, d * invdet);
        r.set(0, 1, -b * invdet);
        r.set(1, 0, -c * invdet);
        r.set(1, 1, a * invdet);
        return r;
    }

    private DoubleMatrix inv3x3() {
        DoubleMatrix r = new DoubleMatrix(3, 3);
        double a = get(0, 0), b = get(0, 1), c = get(0, 2);
        double d = get(1, 0), e = get(1, 1), f = get(1, 2);
        double g = get(2, 0), h = get(2, 1), i = get(2, 2);
        double A = e * i - f * h, B = f * g - d * i, C = d * h - e * g;
        double det = a * A + b * B + c * C;
        if(det == 0.0)
            throw new IllegalArgumentException("Matrix is singular");
        double invdet = 1.0 / det;
        r.set(0, 0, A * invdet);
        r.set(0, 1, (c * h - b * i) * invdet);
        r.set(0, 2, (b * f - c * e) * invdet);
        r.set(1, 0, B * invdet);
        r.set(1, 1, (a * i - c * g) * invdet);
        r.set(1, 2, (c * d - a * f) * invdet);
        r.set(2, 0, C * invdet);
        r.set(2, 1, (b * g - a * h) * invdet);
        r.set(2, 2, (a * e - b * d) * invdet);
        return r;
    }

    private DoubleMatrix inv4x4() {
        DoubleMatrix mat = new DoubleMatrix(4, 4);
        double M11 = get(0, 0);
        double M12 = get(0, 1);
        double M13 = get(0, 2);
        double M14 = get(0, 3);
        double M21 = get(1, 0);
        double M22 = get(1, 1);
        double M23 = get(1, 2);
        double M24 = get(1, 3);
        double M31 = get(2, 0);
        double M32 = get(2, 1);
        double M33 = get(2, 2);
        double M34 = get(2, 3);
        double M41 = get(3, 0);
        double M42 = get(3, 1);
        double M43 = get(3, 2);
        double M44 = get(3, 3);
        double det;
        double ans11, ans12, ans13, ans14, ans21, ans22, ans23, ans24, ans31, ans32, ans33, ans34, ans41, ans42, ans43, ans44;
        det = (M11 * M22 * M33 * M44 ) + (M11 * M23 * M34 * M42 ) + (M11 * M24 * M32 * M43 )
                - (M11 * M24 * M33 * M42 ) - (M11 * M23 * M32 * M44 ) - (M11 * M22 * M34 * M43 )
                - (M12 * M21 * M33 * M44 ) - (M13 * M21 * M34 * M42 ) - (M14 * M21 * M32 * M43 )
                + (M14 * M21 * M33 * M42 ) + (M13 * M21 * M32 * M44 ) + (M12 * M21 * M34 * M43 )
                + (M12 * M23 * M31 * M44 ) + (M13 * M24 * M31 * M42 ) + (M14 * M22 * M31 * M43 )
                - (M14 * M23 * M31 * M42 ) - (M13 * M22 * M31 * M44 ) - (M12 * M24 * M31 * M43 )
                - (M12 * M23 * M34 * M41 ) - (M13 * M24 * M32 * M41 ) - (M14 * M22 * M33 * M41 )
                + (M14 * M23 * M32 * M41 ) + (M13 * M22 * M34 * M41 ) + (M12 * M24 * M33 * M41 );
        ans11 = (M22*M33*M44 + M23*M34*M42 + M24*M32*M43 - M24*M33*M42 - M23*M32*M44 - M22*M34*M43)/det;
        ans12 = (-M12*M33*M44 - M13*M34*M42 - M14*M32*M43 + M14*M33*M42 + M13*M32*M44 + M12*M34*M43)/det;
        ans13 = (M12*M23*M44 + M13*M24*M42 + M14*M22*M43 - M14*M23*M42 - M13*M22*M44 - M12*M24*M43)/det;

        ans14 = (-M12*M23*M34 - M13*M24*M32 - M14*M22*M33 + M14*M23*M32 + M13*M22*M34 + M12*M24*M33)/det;

        ans21 = (-M21*M33*M44 - M23*M34*M41 - M24*M31*M43 + M24*M33*M41 + M23*M31*M44 + M21*M34*M43)/det;
        ans22 = (M11*M33*M44 + M13*M34*M41 + M14*M31*M43 - M14*M33*M41 - M13*M31*M44 - M11*M34*M43)/det;
        ans23 = (-M11*M23*M44 - M13*M24*M41 - M14*M21*M43 + M14*M23*M41 + M13*M21*M44 + M11*M24*M43)/det;
        ans24 = (M11*M23*M34 + M13*M24*M31 + M14*M21*M33 - M14*M23*M31 - M13*M21*M34 - M11*M24*M33)/det;
        ans31 = (M21*M32*M44 + M22*M34*M41 + M24*M31*M42 - M24*M32*M41 - M22*M31*M44 - M21*M34*M42)/det;
        ans32 = (-M11*M32*M44 - M12*M34*M41 - M14*M31*M42 + M14*M32*M41 + M12*M31*M44 + M11*M34*M42)/det;
        ans33 = (M11*M22*M44 + M12*M24*M41 + M14*M21*M42 - M14*M22*M41 - M12*M21*M44 - M11*M24*M42)/det;
        ans34 = (-M11*M22*M34 - M12*M24*M31 - M14*M21*M32 + M14*M22*M31 + M12*M21*M34 + M11*M24*M32)/det;
        ans41 = (-M21*M32*M43 - M22*M33*M41 - M23*M31*M42 + M23*M32*M41 + M22*M31*M43 + M21*M33*M42)/det;
        ans42 = (M11*M32*M43 + M12*M33*M41 + M13*M31*M42 - M13*M32*M41 - M12*M31*M43 - M11*M33*M42)/det;
        ans43 = (-M11*M22*M43 - M12*M23*M41 - M13*M21*M42 + M13*M22*M41 + M12*M21*M43 + M11*M23*M42)/det;
        ans44 = (M11*M22*M33 + M12*M23*M31 + M13*M21*M32 - M13*M22*M31 - M12*M21*M33 - M11*M23*M32)/det;
        mat.set(0, 0, ans11);
        mat.set(0, 1, ans12);
        mat.set(0, 2, ans13);
        mat.set(0, 3, ans14);
        mat.set(1, 0, ans21);
        mat.set(1, 1, ans22);
        mat.set(1, 2, ans23);
        mat.set(1, 3, ans24);
        mat.set(2, 0, ans31);
        mat.set(2, 1, ans32);
        mat.set(2, 2, ans33);
        mat.set(2, 3, ans34);
        mat.set(3, 0, ans41);
        mat.set(3, 1, ans42);
        mat.set(3, 2, ans43);
        mat.set(3, 3, ans44);
        return mat;
    }

    private DoubleMatrix smallMatrixInvert() {
        int h = height(), w = width();
        if(h == 1 && w == 1) {
            DoubleMatrix r = new DoubleMatrix(1, 1);
            r.set(0, 0, 1.0 / get(0, 0));
            return r;
        } else if(h == 2 && w == 2) {
            return inv2x2();
        } else if(h == 3 && w == 3) {
            return inv3x3();
        } else if(h == 4 && w == 4) {
            return inv4x4();
        } else {
            return null;
        }
    }

    /**
     * Invert the matrix.
     */
    public DoubleMatrix invert() {
        DoubleMatrix r = smallMatrixInvert();
        if (r != null) return r;
        return solve(DoubleMatrix.identity(height()));
    }

    /**
     * Invert the matrix given the LUP decomposition of the current matrix.
     */
    public DoubleMatrix invert(DoubleLUPDecompositionResult r) {
        DoubleMatrix x = smallMatrixInvert();
        if (x != null) return x;
        return r.solve(DoubleMatrix.identity(height()));
    }

    /**
     * Invert the matrix given the QR decomposition of the current matrix.
     */
    public DoubleMatrix invert(DoubleQRDecompositionResult r) {
        DoubleMatrix x = smallMatrixInvert();
        if (x != null) return x;
        return r.solve(DoubleMatrix.identity(height()));
    }

    /**
     * Solve A * X = B
     * @param B
     * @return solution X if A is square, least squares solution otherwise
     */
    public DoubleMatrix solve(DoubleMatrix B) {
        return height() == width() ? LUP().solve(B) : QR().solve(B);
    }

    @Override
    public DoubleMatrix copy() {
        return new DoubleMatrix(data);
    }

    @Override
    public DoubleMatrix transpose() {
        DoubleMatrix result = new DoubleMatrix(data.get(0).size(), data.size());
        for (int i = 0; i < data.size(); i++)
            for (int j = 0; j < data.get(i).size(); j++)
                result.set(j, i, data.get(i).get(j));
        return result;
    }

    @Override
    public DoubleMatrix map(Function<Double, Double> mapper) {
        DoubleMatrix result = new DoubleMatrix(data.size(), data.get(0).size());
        for (int i = 0; i < data.size(); i++)
            for (int j = 0; j < data.get(i).size(); j++)
                result.set(i, j, mapper.apply(data.get(i).get(j)));
        return result;
    }

    @Override
    public DoubleMatrix zipWith(Matrix<Double> other, BiFunction<Double, Double, Double> zipper) {
        DoubleMatrix result = new DoubleMatrix(data.size(), data.get(0).size());
        for (int i = 0; i < data.size(); i++)
            for (int j = 0; j < data.get(i).size(); j++)
                result.set(i, j, zipper.apply(data.get(i).get(j), other.get(i, j)));
        return result;
    }

    @Override
    public DoubleMatrix reverseFirst() {
        DoubleMatrix result = new DoubleMatrix(data.size(), data.get(0).size());
        for (int i = 0; i < data.size(); i++)
            for (int j = 0; j < data.get(i).size(); j++)
                result.set(i, j, data.get(data.size() - i - 1).get(j));
        return result;
    }

    @Override
    public DoubleMatrix reverseLast() {
        DoubleMatrix result = new DoubleMatrix(data.size(), data.get(0).size());
        for (int i = 0; i < data.size(); i++)
            for (int j = 0; j < data.get(i).size(); j++)
                result.set(i, j, data.get(i).get(data.get(i).size() - j - 1));
        return result;
    }

    @Override
    public DoubleMatrix mapIdx(BiFunction<Pair<Integer, Integer>, Double, Double> mapper) {
        DoubleMatrix result = new DoubleMatrix(data.size(), data.get(0).size());
        for (int i = 0; i < data.size(); i++)
            for (int j = 0; j < data.get(i).size(); j++)
                result.set(i, j, mapper.apply(new Pair<>(i, j), data.get(i).get(j)));
        return result;
    }

    @Override
    public DoubleMatrix dot(Matrix<Double> another, BiFunction<Double, Double, Double> scalar, BiFunction<Double, Double, Double> vector) {
        var a = rows();
        var b = another.columns();
        if (a.size() != b.size())
            throw new IllegalArgumentException("Matrices are not aligned.");
        DoubleMatrix result = new DoubleMatrix(a.size(), b.size());
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                List<Double> row = a.get(i);
                List<Double> column = b.get(j);
                double reduced = scalar.apply(row.get(0), column.get(0));
                for (int k = 1; k < row.size(); k++)
                    reduced = vector.apply(reduced, scalar.apply(row.get(k), column.get(k)));
                result.set(i, j, reduced);
            }
        }
        return result;
    }
}
