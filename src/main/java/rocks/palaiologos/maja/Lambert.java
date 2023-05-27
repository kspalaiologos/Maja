package rocks.palaiologos.maja;

import static rocks.palaiologos.maja.Maja.*;

class Lambert {
    private static final double[] c = {-1.0, 2.331643981597124203363536062168,
            -1.812187885639363490240191647568, 1.936631114492359755363277457668,
            -2.353551201881614516821543561516, 3.066858901050631912893148922704,
            -4.175335600258177138854984177460, 5.858023729874774148815053846119,
            -8.401032217523977370984161688514, 12.250753501314460424,
            -18.100697012472442755, 27.029044799010561650};

    private Lambert() {
    }

    private static double halley_iteration(double x, double w_initial, int max_iters) {
        double w = w_initial;
        int i;

        for (i = 0; i < max_iters; i++) {
            double tol;
            final double e = Math.exp(w);
            final double p = w + 1.0;
            double t = w * e - x;

            if (w > 0) {
                t = t / p / e;
            } else {
                t /= e * p - 0.5 * (p + 1.0) * t / p;
            }

            w -= t;

            tol = 10 * Maja.EPSILON
                    * Math.max(Math.abs(w), 1.0 / (Math.abs(p) * e));

            if (Math.abs(t) < tol) {
                return w;
            }
        }

        return Double.NaN;
    }

    private static double series_eval(double r) {
        final double t_8 = c[8] + r * (c[9] + r * (c[10] + r * c[11]));
        final double t_5 = c[5] + r * (c[6] + r * (c[7] + r * t_8));
        final double t_1 = c[1]
                + r * (c[2] + r * (c[3] + r * (c[4] + r * t_5)));
        return c[0] + r * t_1;
    }

    static public double lambert0(double x) {
        if (x < -1 / Math.E) {
            return Double.NaN;
        }

        final double one_over_E = 1.0 / Math.E;
        final double q = x + one_over_E;

        if (x == 0.0) {
            return 0.0;
        } else if (q < 0.0) {
            return -1.0;
        } else if (q == 0.0) {
            return -1.0;
        } else if (q < 1.0e-03) {
            final double r = Math.sqrt(q);
            return series_eval(r);
        } else {
            final int MAX_ITERS = 10;
            double w;

            if (x < 1.0) {
                final double p = Math.sqrt(2.0 * Math.E * q);
                w = -1.0 + p * (1.0 + p * (-1.0 / 3.0 + p * 11.0 / 72.0));
            } else {
                w = Math.log(x);
                if (x > 3.0) {
                    w -= Math.log(w);
                }
            }

            return halley_iteration(x, w, MAX_ITERS);
        }
    }

    static public double lambertn1(double x) {
        if (x < -1 / Math.E || x >= 0) {
            return Double.NaN;
        }

        final int MAX_ITERS = 32;
        final double one_over_E = 1.0 / Math.E;
        final double q = x + one_over_E;
        double w;

        if (q < 0.0) {
            return -1.0;
        }

        if (x < -1.0e-6) {
            final double r = -Math.sqrt(q);
            w = series_eval(r);
            if (q < 3.0e-3) {
                return w;
            }
        } else {
            final double L_1 = Math.log(-x);
            final double L_2 = Math.log(-L_1);
            w = L_1 - L_2 + L_2 / L_1;
        }

        return halley_iteration(x, w, MAX_ITERS);
    }

    private static Complex pevl(final double[] coeffs, Complex z) {
        return add(mul(z, fma(2 * z.re(), coeffs[0], coeffs[1])), fma(-z.re() * z.re() - z.im() * z.im(), coeffs[0], coeffs[2]));
    }

    private static Complex lwA(Complex z, long k) {
        Complex w = add(log(z), mul(TWO_PI, mul(I, k)));
        return sub(w, log(w));
    }

    private static Complex lwPade0(Complex z) {
        final double[] P1 = {12.85106382978723404255, 12.34042553191489361902, 1.0};
        final double[] P2 = {32.53191489361702127660, 14.34042553191489361702, 1.0};
        return div(mul(z, pevl(P1, z)), pevl(P2, z));
    }

    private static Complex lwBpt(Complex z) {
        final double[] coeffs = {-1.0 / 3.0, 1.0, -1.0};
        Complex p = sqrt(mul(2, add(mul(E, z), 1)));
        return pevl(coeffs, p);
    }

    public static Complex lambertW(Complex z, long k) {
        if (Complex.isNaN(z))
            return z;
        if (Complex.isInfinite(z))
            return add(z, mul(TWO_PI * k, I));
        if (eq(z, Complex.ZERO)) {
            if (k == 0)
                return z;
            return Complex.COMPLEX_INFINITY;
        } else if (eq(z, Complex.ONE) && k == 0) {
            // Series expansion fails to converge.
            return new Complex(0.56714329040978387299997);
        }

        double absz = abs(z);
        Complex w;
        if (k == 0) {
            if (abs(add(z, 0.36787944117144232159553)) < 0.3) {
                w = lwBpt(z);
            } else if (z.re() > -1 && z.re() < 1.5 && abs(z.im()) < 1 && -2.5 * abs(z.im()) - 0.2 < z.re()) {
                w = lwPade0(z);
            } else {
                w = lwA(z, k);
            }
        } else if (k == -1) {
            if (absz <= 0.36787944117144232159553 && z.im() == 0 && z.re() < 0) {
                w = log(new Complex(-z.re()));
            } else {
                w = lwA(z, k);
            }
        } else {
            w = lwA(z, k);
        }

        if (w.re() >= 0) {
            for (int i = 0; i < 100; i++) {
                Complex ew = exp(negate(w));
                Complex wewz = sub(w, mul(z, ew));
                Complex wn = sub(w, div(wewz, add(w, sub(1, div(mul(add(w, 2), wewz), add(mul(2, w), 2))))));
                if (abs(sub(wn, w)) <= EPSILON * abs(wn))
                    return wn;
                else
                    w = wn;
            }
        } else {
            for (int i = 0; i < 100; i++) {
                Complex ew = exp(w);
                Complex wew = mul(w, ew);
                Complex wewz = sub(wew, z);
                Complex wn = sub(w, div(wewz, add(wew, sub(ew, div(mul(add(w, 2), wewz), add(mul(2, w), 2))))));
                if (abs(sub(wn, w)) <= 1e-15 * abs(wn))
                    return wn;
                else
                    w = wn;
            }
        }

        return Complex.NaN;
    }
}
