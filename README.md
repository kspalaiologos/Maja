# Maja

[![Maven Package](https://github.com/kspalaiologos/Maja/actions/workflows/maven.yml/badge.svg)](https://github.com/kspalaiologos/Maja/actions/workflows/maven.yml)
[![codecov](https://codecov.io/gh/kspalaiologos/Maja/branch/trunk/graph/badge.svg?token=3MB6GY2GVV)](https://codecov.io/gh/kspalaiologos/Maja)
[![Lines Of Code](https://tokei.rs/b1/github/kspalaiologos/Maja?category=code)](https://github.com/kspalaiologos/Maja)

A slick numerics-oriented Mathematical library for Java. Maja implements around 300 different functions for use in Java programs, including high-performance cached variants of the `Math` equivalents. [Javadoc](https://kspalaiologos.github.io/Maja/javadoc/rocks/palaiologos/maja/package-summary.html).

## Installation

From Maven Central:

```xml
<!-- https://mvnrepository.com/artifact/rocks.palaiologos/maja -->
<dependency>
    <groupId>rocks.palaiologos</groupId>
    <artifactId>maja</artifactId>
    <version>0.1.3</version>
</dependency>
```

## Features

Constants:
- Apery, Catalan, E, Euler-Mascheroni, Feigenbaum, Glaisher, Golden ratio, Golomb-Dickman, Khinchin, Mills, various standard logarithms and precise values of 1/pi, pi, pi/2, pi/4, 2*pi.

Real Functions:
- Elementary (abs, add, cbrt, ceil, clamp, compare, copySign, div, eq, exp, expm1, fib, floor, fma, frexp, ge, getExponent, gt, hypot, icbrt, iexp, ilog10, isPerfectSquare, isPowerOfTwo, isqrt, le, log, log10, log1p, log2, lt, amx, min, mod, mul, ne, nextAfter, nextDown, nextPowerOfTwo, nextUp, pow, rem, round, scalb, signum, sqrt, sub, ulp)
- Interpolation (linearInterpolate, linearMap, linearNorm)
- Random number generation.
- Sigmoid (logistic) functions (squash, stretch)
- Batch operations (map, reduce)
- Trigonometry (acos, acosh, acot, acoth, acsc, acsch, asec, asech, asin, asinh, atan, atan2, atanh, cos, cosh, cot, coth, csc, csch, sec, sech, sin, sinc, sinh, tan, tanh, toDegrees, toRadians)
- High-performance trigonometry (fastSin, fastCos)
- Airy Ai function (airy, airyDerivative)
- Bessel functions (i0, i1, j0, j1, jn, k0, k1, kn, y0, y1, yn, jv, yv)
- Gamma function family (beta, digamma, factorial, gamma, lower incomplete gamma, logabsgamma, logbeta, loggamma, pochhammer, polygamma, trigamma, upper incomplete gamma)
- Trigonometric integrals (Chi, Ci, Cin, Shi, ShiChi, si, Si)
- Exponential integral (expint)
- Error function family (Dawson-, Dawson+, erf, erfc, erfcInv, erfi, erfInv)
- Polylogarithm family (dilog, polylog, Spence)
- Fresnel integrals (fresnelC, fresnelS)
- Zeta family (Hurwitz Zeta, Lerch Transcendent, Riemann Zeta)
- Lambert W (W0, W-1)
- Hypergeometric function (2F1, 1F1, 1F2, 3F0, Struve).
- Numerical integration (Simpson, Tanh-Sinh, Gauss-Legendre)
- Root finding (Newton-Raphson method).
- Expression evaluator (with operator precedence, grouping, variables, special syntax, etc...).

Complex functions:
- Elementary (add, sub, mul, div, conj, abs, sqrt, exp, log, eq, ne, cbrt, root)
- Trigonometry (sin, cos, tan, cot, sec, csc, asin, acos, atan, acot, asec, acsc, sinh, cosh, tanh, sech, csch, asinh, acosh, atanh, acoth, asech, acsch)

## License

GNU LGPL v3.
