# Maja

[![Maven Package](https://github.com/kspalaiologos/Maja/actions/workflows/maven.yml/badge.svg)](https://github.com/kspalaiologos/Maja/actions/workflows/maven.yml)
[![codecov](https://codecov.io/gh/kspalaiologos/Maja/branch/trunk/graph/badge.svg?token=3MB6GY2GVV)](https://codecov.io/gh/kspalaiologos/Maja)
[![Maven Central](https://img.shields.io/maven-central/v/rocks.palaiologos/maja)](https://mvnrepository.com/artifact/rocks.palaiologos/maja)
[![DOI](https://zenodo.org/badge/DOI/10.5281/zenodo.7793154.svg)](https://doi.org/10.5281/zenodo.7793154)
[![wakatime](https://wakatime.com/badge/user/c3a8c589-783c-4ab2-be05-93fa48bc9a94/project/4ced514f-b93b-4671-b37d-602677409fd0.svg)](https://wakatime.com/badge/user/c3a8c589-783c-4ab2-be05-93fa48bc9a94/project/4ced514f-b93b-4671-b37d-602677409fd0)

A slick numerics-oriented Mathematical library for Java. Maja implements around 300 different functions for use in Java programs, including high-performance cached variants of the `Math` equivalents. [Javadoc](https://kspalaiologos.github.io/Maja/javadoc/rocks/palaiologos/maja/package-summary.html).

## Installation

From Maven Central:

```xml
<!-- https://mvnrepository.com/artifact/rocks.palaiologos/maja -->
<dependency>
    <groupId>rocks.palaiologos</groupId>
    <artifactId>maja</artifactId>
    <version>0.1.9</version>
</dependency>
```

## Features

Constants:
- Apery, Catalan, E, Euler-Mascheroni, Feigenbaum, Glaisher, Golden ratio, Golomb-Dickman, Khinchin, Mills, various standard logarithms and precise values of 1/pi, pi, pi/2, pi/4, 2*pi.

Real Functions:
- Elementary (abs, add, cbrt, ceil, clamp, compare, copySign, div, eq, exp, expm1, fib, floor, fma, frexp, ge, getExponent, gt, hypot, icbrt, iexp, ilog10, isPerfectSquare, isPowerOfTwo, isqrt, le, log, log10, log1p, log2, lt, max, min, mod, mul, ne, nextAfter, nextDown, nextPowerOfTwo, nextUp, pow, rem, round, scalb, signum, sqrt, sub, ulp)
- Interpolation (linearInterpolate, linearMap, linearNorm)
- Random number generation.
- Sigmoid (logistic) functions (squash, stretch)
- Batch operations (map, reduce)
- Trigonometry (acos, acosh, acot, acoth, acsc, acsch, asec, asech, asin, asinh, atan, atan2, atanh, cos, cosh, cot, coth, csc, csch, sec, sech, sin, sinc, sinh, tan, tanh, toDegrees, toRadians)
- High-performance trigonometry (fastSin, fastCos)
- Airy Ai function (Ai, Ai', Bi, Bi')
- Bessel functions (i0, i1, j0, j1, jn, k0, k1, kn, y0, y1, yn, jv, yv)
- Gamma function family (beta, digamma, factorial, gamma, lower incomplete gamma, logabsgamma, logbeta, loggamma, pochhammer, polygamma, trigamma, upper incomplete gamma, gammaP, gammaQ)
- Trigonometric integrals (Chi, Ci, Cin, Shi, ShiChi, si, Si)
- Exponential integral (expint, li)
- Error function family (Dawson-, Dawson+, erf, erfc, erfi)
- Polylogarithm family (dilog, polylog, Spence)
- Fresnel integrals (fresnelC, fresnelS)
- Zeta family (Hurwitz Zeta, Lerch Transcendent, Riemann Zeta)
- Lambert W (W0, W-1)
- Hypergeometric function (2F1, 1F1, 1F2, 3F0).
- Numerical integration (Simpson, Tanh-Sinh, Gauss-Legendre)
- Legendre elliptic integrals (F, E, D, Pi)
- Root finding (Newton-Raphson method).
- Landau functions.
- Chi squared and Normal distribution quantiles.
- Struve functions (H0, H1, Hx for double precision x, L0, L1)

Complex functions:
- Elementary (add, sub, mul, div, conj, abs, sqrt, exp, log, eq, ne, cbrt, root)
- Trigonometry (sin, cos, tan, cot, sec, csc, asin, acos, atan, acot, asec, acsc, sinh, cosh, tanh, sech, csch, asinh, acosh, atanh, acoth, asech, acsch)
- Gamma function family (beta, gamma, loggamma, logbeta, pochhammer, polygamma, digamma, trigamma, lower incomplete gamma, upper incomplete gamma)
- Airy functions (Ai, Bi, Ai', Bi')
- Error function family (Dawson-, Dawson+, erf, erfc, erfi).
- Fresnel integrals (fresnelS, fresnelC).
- Exponential integral family (Ein, E_1, li, E_n)
- Zeta function family (Riemann Zeta, Hurwitz Zeta, Lerch Transcendent)
- Trigonometric integral family (Si, si, Ci, Cin, Shi, Chi, ShiChi).
- Polylogarithm family (dilog, Spence, polylog)
- Legendre elliptic integrals (F, E, D, Pi)
- Numerical integration (Simpson, Tanh-Sinh, Gauss-Legendre), contour integration.
- Lambert W.

Linear algebra:
- Matrix operations: transposition, obtaining rows, columns, generalised dot product, leading/trailing axis reduction, cell mapping, ravelling, robust stringification. Reversing alongside leading and trailing axis, zipping matrices.
- Real Cholesky, Eigenvalue and Eigenvector, QR, LU and LUP decomposition, trace, determinant, permanent, alternant computation.
- Specialised code for 1x1...4x4 matrices.
- Solvers for linear systems using Cholesky, LUP and QR decompositions (least squares).

Also, a simple expression evaluator (with operator precedence, grouping, variables, special syntax, etc...).

## License

GNU LGPL v3.

## Acknowledgements

Literature:
- NIST DLMF (https://dlmf.nist.gov/).
- Abramowitz & Stegun.
- Wolfram MathWorld (https://mathworld.wolfram.com/).
- [Implementation of the Combined--Nonlinear Condensation Transformation](https://arxiv.org/abs/math/0207086) - S. V. Aksenov, M. A. Savageau, et al; Comput.Phys.Commun. 150 (2003) 1-20
- [On the Evaluation of the Complex-Valued Exponential Integral](https://www.sci.utah.edu/~vpegorar/research/2011_JGT.pdf) - Vincent Pegoraro and Philipp Slusallek; Journal of Graphics, GPU, and Game Tools, 15(3): 183-198, 2011
- [Improving the Double Exponential Quadrature Tanh-Sinh, Sinh-Sinh and Exp-Sinh Formulas](https://www.genivia.com/files/qthsh.pdf) -  Dr. Robert A. van Engelen, Genivia Labs.
- [GalSim: The modular galaxy image simulation toolkit](https://arxiv.org/abs/1407.7676) - B. Rowe, et al; Instrumentation and Methods for Astrophysics (astro-ph.IM); Cosmology and Nongalactic Astrophysics (astro-ph.CO)
- [Computing the Lerch transcendent](https://fredrikj.net/blog/2022/02/computing-the-lerch-transcendent/) - Fredrik Johansson
- [Dyalog APL - alternant](https://dfns.dyalog.com/n_alt.htm) - Adám Brudzewsky, SAX 6.1 manual.
- [Permanent and Determinant](https://math.berkeley.edu/~bernd/ban275.pdf) - Frank Ban, Berkeley Math dept.
- Linear Algebra. A Pure Mathematical Approach - Harvey E. Rose.
- Various other papers and books on numerical computing.

Software:
- Many lookup tables (e.g. Chebyshev coefficients, Pade approximants) have been generated using Wolfram Mathematica or Dyalog APL one-liners.
- Some code in Maja uses similar algorithms & the same lookup tables as the ones in the Cephes project (MIT).
- Error function code was inspired by libcerf (MIT; S. G. Johnson, J. Wuttke: libcerf, numeric library for complex error functions).
- Some Bessel functions follow the same algorithms as the Jama library (Public Domain).
- Complex polylogarithm was inspired by mpmath code (MIT).

## Citation

Cite as:

> Kamila Szewczyk. (2023). Maja - A numerics-oriented Mathematical library for Java (v0.1.9). Zenodo. https://doi.org/10.5281/zenodo.7793154
