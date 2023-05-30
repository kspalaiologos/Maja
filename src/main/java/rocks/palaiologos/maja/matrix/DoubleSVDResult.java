package rocks.palaiologos.maja.matrix;

/**
 * The result of SV decomposition.
 *
 * @param rank The effective numerical rank.
 * @param norm L_2 norm of the matrix.
 * @param conditionNumber Ratio of largest to smallest singular value.
 * @param inverseConditionNumber Ratio of smallest to largest singular value.
 * @param singularValues The singular values.
 * @param U The left singular vectors.
 * @param V The right singular vectors.
 * @author Palaiologos
 */
public record DoubleSVDResult(int rank, double norm, double conditionNumber, double inverseConditionNumber, double[] singularValues,
                              DoubleMatrix U, DoubleMatrix V) {
}
