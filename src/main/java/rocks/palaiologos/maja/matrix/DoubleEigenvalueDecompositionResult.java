package rocks.palaiologos.maja.matrix;

import rocks.palaiologos.maja.Complex;

/**
 * The result of eigenvalue decomposition.
 * <p>
 * If A is symmetric, then A = V*D*V' where the eigenvalue matrix D is
 * diagonal and the eigenvector matrix V is orthogonal.
 * i.e. A = V.times(D.times(V.transpose())) and
 * V.times(V.transpose()) equals the identity matrix.
 * <p>
 * If A is not symmetric, then the eigenvalue matrix D is block diagonal
 * with the real eigenvalues in 1-by-1 blocks and any complex eigenvalues,
 * lambda + i*mu, in 2-by-2 blocks, [lambda, mu; -mu, lambda].  The
 * columns of V represent the eigenvectors in the sense that A*V = V*D,
 * i.e. A.times(V) equals V.times(D).  The matrix V may be badly
 * conditioned, or even singular.
 * <p>
 * The complex eigenvalues are stored in a Complex array 'e'.
 */
public record DoubleEigenvalueDecompositionResult(DoubleMatrix V, DoubleMatrix D, Complex[] e) {
}
