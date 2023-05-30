package rocks.palaiologos.maja.matrix;

import rocks.palaiologos.maja.Maja;

// Adapted from JAMA, which adapted its code from LINPACK.
class SVDImpl {
    private static final double TINY = 0x1.0p-966;
    private final double[] singularValues;
    private final int n;
    private final DoubleMatrix cachedU, cachedV;
    private final double tol;

    public SVDImpl(final DoubleMatrix matrix) {
        final double[][] A;

        int matrixRows = matrix.height();
        int matrixColumns = matrix.width();

        boolean transposed;
        int m;
        if (matrixRows < matrixColumns) {
            transposed = true;
            DoubleMatrix tp = matrix.transpose();
            A = new double[matrixColumns][matrixRows];
            for (int i = 0; i < matrixColumns; i++)
                for (int j = 0; j < matrixRows; j++)
                    A[i][j] = tp.get(i, j);
            m = matrixColumns;
            n = matrixRows;
        } else {
            transposed = false;
            A = new double[matrixRows][matrixColumns];
            for (int i = 0; i < matrixRows; i++)
                for (int j = 0; j < matrixColumns; j++)
                    A[i][j] = matrix.get(i, j);
            m = matrixRows;
            n = matrixColumns;
        }

        singularValues = new double[n];
        final double[][] U = new double[m][n];
        final double[][] V = new double[n][n];
        final double[] e = new double[n];
        final double[] work = new double[m];
        final int nct = Math.min(m - 1, n);
        final int nrt = Math.max(0, n - 2);
        for (int k = 0; k < Math.max(nct, nrt); k++) {
            if (k < nct) {
                singularValues[k] = 0;
                for (int i = k; i < m; i++)
                    singularValues[k] = Math.hypot(singularValues[k], A[i][k]);
                if (singularValues[k] != 0) {
                    if (A[k][k] < 0)
                        singularValues[k] = -singularValues[k];
                    for (int i = k; i < m; i++)
                        A[i][k] /= singularValues[k];
                    A[k][k] += 1;
                }
                singularValues[k] = -singularValues[k];
            }
            for (int j = k + 1; j < n; j++) {
                if (k < nct && singularValues[k] != 0) {
                    double t = 0;
                    for (int i = k; i < m; i++)
                        t += A[i][k] * A[i][j];
                    t = -t / A[k][k];
                    for (int i = k; i < m; i++)
                        A[i][j] += t * A[i][k];
                }
                e[j] = A[k][j];
            }
            if (k < nct)
                for (int i = k; i < m; i++)
                    U[i][k] = A[i][k];
            if (k < nrt) {
                e[k] = 0;
                for (int i = k + 1; i < n; i++)
                    e[k] = Math.hypot(e[k], e[i]);
                if (e[k] != 0) {
                    if (e[k + 1] < 0)
                        e[k] = -e[k];
                    for (int i = k + 1; i < n; i++)
                        e[i] /= e[k];
                    e[k + 1] += 1;
                }
                e[k] = -e[k];
                if (k + 1 < m && e[k] != 0) {
                    for (int i = k + 1; i < m; i++)
                        work[i] = 0;
                    for (int j = k + 1; j < n; j++)
                        for (int i = k + 1; i < m; i++)
                            work[i] += e[j] * A[i][j];
                    for (int j = k + 1; j < n; j++) {
                        final double t = -e[j] / e[k + 1];
                        for (int i = k + 1; i < m; i++)
                            A[i][j] += t * work[i];
                    }
                }

                for (int i = k + 1; i < n; i++)
                    V[i][k] = e[i];
            }
        }

        int p = n;
        if (nct < n)
            singularValues[nct] = A[nct][nct];
        if (m < p)
            singularValues[p - 1] = 0;
        if (nrt + 1 < p)
            e[nrt] = A[nrt][p - 1];
        e[p - 1] = 0;

        for (int j = nct; j < n; j++) {
            for (int i = 0; i < m; i++)
                U[i][j] = 0;
            U[j][j] = 1;
        }
        for (int k = nct - 1; k >= 0; k--) {
            if (singularValues[k] != 0) {
                for (int j = k + 1; j < n; j++) {
                    double t = 0;
                    for (int i = k; i < m; i++)
                        t += U[i][k] * U[i][j];
                    t = -t / U[k][k];
                    for (int i = k; i < m; i++)
                        U[i][j] += t * U[i][k];
                }
                for (int i = k; i < m; i++)
                    U[i][k] = -U[i][k];
                U[k][k] = 1 + U[k][k];
                for (int i = 0; i < k - 1; i++)
                    U[i][k] = 0;
            } else {
                for (int i = 0; i < m; i++)
                    U[i][k] = 0;
                U[k][k] = 1;
            }
        }

        for (int k = n - 1; k >= 0; k--) {
            if (k < nrt && e[k] != 0) {
                for (int j = k + 1; j < n; j++) {
                    double t = 0;
                    for (int i = k + 1; i < n; i++)
                        t += V[i][k] * V[i][j];
                    t = -t / V[k + 1][k];
                    for (int i = k + 1; i < n; i++)
                        V[i][j] += t * V[i][k];
                }
            }
            for (int i = 0; i < n; i++)
                V[i][k] = 0;
            V[k][k] = 1;
        }

        final int pp = p - 1;
        while (p > 0) {
            int k;
            int kase;
            for (k = p - 2; k >= 0; k--) {
                final double threshold
                        = TINY + Maja.EPSILON * (Math.abs(singularValues[k]) +
                        Math.abs(singularValues[k + 1]));
                if (!(Math.abs(e[k]) > threshold)) {
                    e[k] = 0;
                    break;
                }
            }

            if (k == p - 2)
                kase = 4;
            else {
                int ks;
                for (ks = p - 1; ks >= k; ks--) {
                    if (ks == k)
                        break;
                    final double t = (ks != p ? Math.abs(e[ks]) : 0) +
                            (ks != k + 1 ? Math.abs(e[ks - 1]) : 0);
                    if (Math.abs(singularValues[ks]) <= TINY + Maja.EPSILON * t) {
                        singularValues[ks] = 0;
                        break;
                    }
                }
                if (ks == k) {
                    kase = 3;
                } else if (ks == p - 1) {
                    kase = 1;
                } else {
                    kase = 2;
                    k = ks;
                }
            }
            k++;
            double f;
            switch (kase) {
                case 1:
                    f = e[p - 2];
                    e[p - 2] = 0;
                    for (int j = p - 2; j >= k; j--) {
                        double t = Math.hypot(singularValues[j], f);
                        final double cs = singularValues[j] / t;
                        final double sn = f / t;
                        singularValues[j] = t;
                        if (j != k) {
                            f = -sn * e[j - 1];
                            e[j - 1] = cs * e[j - 1];
                        }

                        for (int i = 0; i < n; i++) {
                            t = cs * V[i][j] + sn * V[i][p - 1];
                            V[i][p - 1] = -sn * V[i][j] + cs * V[i][p - 1];
                            V[i][j] = t;
                        }
                    }
                    break;
                case 2:
                    f = e[k - 1];
                    e[k - 1] = 0;
                    for (int j = k; j < p; j++) {
                        double t = Math.hypot(singularValues[j], f);
                        final double cs = singularValues[j] / t;
                        final double sn = f / t;
                        singularValues[j] = t;
                        f = -sn * e[j];
                        e[j] = cs * e[j];

                        for (int i = 0; i < m; i++) {
                            t = cs * U[i][j] + sn * U[i][k - 1];
                            U[i][k - 1] = -sn * U[i][j] + cs * U[i][k - 1];
                            U[i][j] = t;
                        }
                    }
                    break;
                case 3:
                    final double maxPm1Pm2 = Math.max(Math.abs(singularValues[p - 1]), Math.abs(singularValues[p - 2]));
                    final double scale = Math.max(Math.max(Math.max(maxPm1Pm2, Math.abs(e[p - 2])), Math.abs(singularValues[k])), Math.abs(e[k]));
                    final double sp = singularValues[p - 1] / scale;
                    final double spm1 = singularValues[p - 2] / scale;
                    final double epm1 = e[p - 2] / scale;
                    final double sk = singularValues[k] / scale;
                    final double ek = e[k] / scale;
                    final double b = ((spm1 + sp) * (spm1 - sp) + epm1 * epm1) / 2.0;
                    final double c = sp * epm1 * (sp * epm1);
                    double shift = 0;
                    if (b != 0 || c != 0) {
                        shift = Math.sqrt(b * b + c);
                        if (b < 0)
                            shift = -shift;
                        shift = c / (b + shift);
                    }
                    f = (sk + sp) * (sk - sp) + shift;
                    double g = sk * ek;
                    for (int j = k; j < p - 1; j++) {
                        double t = Math.hypot(f, g);
                        double cs = f / t;
                        double sn = g / t;
                        if (j != k)
                            e[j - 1] = t;
                        f = cs * singularValues[j] + sn * e[j];
                        e[j] = cs * e[j] - sn * singularValues[j];
                        g = sn * singularValues[j + 1];
                        singularValues[j + 1] = cs * singularValues[j + 1];

                        for (int i = 0; i < n; i++) {
                            t = cs * V[i][j] + sn * V[i][j + 1];
                            V[i][j + 1] = -sn * V[i][j] + cs * V[i][j + 1];
                            V[i][j] = t;
                        }
                        t = Math.hypot(f, g);
                        cs = f / t;
                        sn = g / t;
                        singularValues[j] = t;
                        f = cs * e[j] + sn * singularValues[j + 1];
                        singularValues[j + 1] = -sn * e[j] + cs * singularValues[j + 1];
                        g = sn * e[j + 1];
                        e[j + 1] = cs * e[j + 1];
                        if (j < m - 1) {
                            for (int i = 0; i < m; i++) {
                                t = cs * U[i][j] + sn * U[i][j + 1];
                                U[i][j + 1] = -sn * U[i][j] + cs * U[i][j + 1];
                                U[i][j] = t;
                            }
                        }
                    }
                    e[p - 2] = f;
                    break;
                default:
                    if (singularValues[k] <= 0) {
                        singularValues[k] = singularValues[k] < 0 ? -singularValues[k] : 0;
                        for (int i = 0; i <= pp; i++)
                            V[i][k] = -V[i][k];
                    }
                    while (k < pp) {
                        if (singularValues[k] >= singularValues[k + 1])
                            break;
                        double t = singularValues[k];
                        singularValues[k] = singularValues[k + 1];
                        singularValues[k + 1] = t;
                        if (k < n - 1) {
                            for (int i = 0; i < n; i++) {
                                t = V[i][k + 1];
                                V[i][k + 1] = V[i][k];
                                V[i][k] = t;
                            }
                        }
                        if (k < m - 1) {
                            for (int i = 0; i < m; i++) {
                                t = U[i][k + 1];
                                U[i][k + 1] = U[i][k];
                                U[i][k] = t;
                            }
                        }
                        k++;
                    }
                    p--;
                    break;
            }
        }

        tol = Math.max(m * singularValues[0] * Maja.EPSILON,
                Math.sqrt(Double.MIN_NORMAL));

        if (!transposed) {
            cachedU = new DoubleMatrix(U);
            cachedV = new DoubleMatrix(V);
        } else {
            cachedU = new DoubleMatrix(V);
            cachedV = new DoubleMatrix(U);
        }
    }

    public DoubleMatrix getU() {
        return cachedU;
    }

    public double[] getSingularValues() {
        return singularValues.clone();
    }

    public DoubleMatrix getV() {
        return cachedV;
    }

    public double getNorm() {
        // L_2 norm.
        return singularValues[0];
    }

    public double getConditionNumber() {
        // Condition number is ratio of largest to smallest singular value.
        return singularValues[0] / singularValues[n - 1];
    }

    public double getInverseConditionNumber() {
        return singularValues[n - 1] / singularValues[0];
    }


    public int getRank() {
        int r = 0;
        for (double singularValue : singularValues) {
            if (singularValue > tol) {
                r++;
            }
        }
        return r;
    }
}
