package rocks.palaiologos.maja.matrix;

public record DoubleLUPDecompositionResult(Matrix<Double> lower, Matrix<Double> upper, int[] permutation,
                                           boolean nonsingular, double determinant) {
}
