package rocks.palaiologos.maja;

import java.util.List;

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

    /**
     * Computes the LU decomposition of a matrix using the Doolittle algorithm.
     * @return A pair of matrices, the first being the lower triangular matrix and the second the upper triangular matrix.
     */
    public Pair<Matrix<Double>, Matrix<Double>> LU() {
        Matrix<Double> lower = new DoubleMatrix(height(), width());
        Matrix<Double> upper = new DoubleMatrix(height(), width());

        if(height() != width())
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
                    throw new ArithmeticException("Singular matrix.");
                }
            }
        }

        return new Pair<>(lower, upper);
    }

}
