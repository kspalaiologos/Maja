package rocks.palaiologos.maja;

class Hypergeometric {
    public static double hyp2f1(double a, double b, double c, double x) {
        double d, d1, d2, e;
        double p, q, r, s, y = Double.NaN, ax;
        double ia, ib, ic, id;
        DoublePtr err = new DoublePtr();
        double t1;
        int i, aid;
        int neg_int_a = 0, neg_int_b = 0;
        int neg_int_ca_or_cb = 0;

        err.value = 0.0;
        ax = Math.abs(x);
        s = 1.0 - x;
        ia = Math.round(a);        /* nearest integer to a */
        ib = Math.round(b);

        if (x == 0.0) {
            return 1.0;
        }

        d = c - a - b;
        id = Math.round(d);

        if ((a == 0 || b == 0) && c != 0) {
            return 1.0;
        }

        if (a <= 0 && Math.abs(a - ia) < Maja.EPSILON) {    /* a is a negative integer */
            neg_int_a = 1;
        }

        if (b <= 0 && Math.abs(b - ib) < Maja.EPSILON) {    /* b is a negative integer */
            neg_int_b = 1;
        }

        if (d <= -1 && !(Math.abs(d - id) > Maja.EPSILON && s < 0)
                && !(neg_int_a != 0 || neg_int_b != 0)) {
            return Math.pow(s, d) * hyp2f1(c - a, c - b, c, x);
        }
        if (d <= 0 && x == 1 && !(neg_int_a != 0 || neg_int_b != 0))
            return Double.POSITIVE_INFINITY;

        if (ax < 1.0 || x == -1.0) {
            /* 2F1(a,b;b;x) = (1-x)**(-a) */
            if (Math.abs(b - c) < Maja.EPSILON) {    /* b = c */
                y = Math.pow(s, -a);    /* s to the -a power */
                return y;
            }
            if (Math.abs(a - c) < Maja.EPSILON) {    /* a = c */
                y = Math.pow(s, -b);    /* s to the -b power */
                return y;
            }
        }


        if (c <= 0.0) {
            ic = Math.round(c);        /* nearest integer to c */
            if (Math.abs(c - ic) < Maja.EPSILON) {    /* c is a negative integer */
                /* check if termination before explosion */
                if (neg_int_a != 0 && (ia > ic)) {
                    y = hyt2f1(a, b, c, x, err);
                    return y;
                }
                if (neg_int_b != 0 && (ib > ic)) {
                    y = hyt2f1(a, b, c, x, err);
                    return y;
                }
                return Double.POSITIVE_INFINITY;
            }
        }

        if (neg_int_a != 0 || neg_int_b != 0)    /* function is a polynomial */ {
            y = hyt2f1(a, b, c, x, err);
            return y;
        }

        t1 = Math.abs(b - a);
        if (x < -2.0 && Math.abs(t1 - Math.round(t1)) > Maja.EPSILON) {
            /* This transform has a pole for b-a integer, and
             * may produce large cancellation errors for |1/x| close 1
             */
            p = hyp2f1(a, 1 - c + a, 1 - b + a, 1.0 / x);
            q = hyp2f1(b, 1 - c + b, 1 - a + b, 1.0 / x);
            p *= Math.pow(-x, -a);
            q *= Math.pow(-x, -b);
            t1 = Gamma.gamma(c);
            s = t1 * Gamma.gamma(b - a) / (Gamma.gamma(b) * Gamma.gamma(c - a));
            y = t1 * Gamma.gamma(a - b) / (Gamma.gamma(a) * Gamma.gamma(c - b));
            return s * p + y * q;
        } else if (x < -1.0) {
            if (Math.abs(a) < Math.abs(b)) {
                return Math.pow(s, -a) * hyp2f1(a, c - b, c, x / (x - 1));
            } else {
                return Math.pow(s, -b) * hyp2f1(b, c - a, c, x / (x - 1));
            }
        }

        if (ax > 1.0)        /* series diverges  */
            return Double.POSITIVE_INFINITY;

        p = c - a;
        ia = Math.round(p);        /* nearest integer to c-a */
        if ((ia <= 0.0) && (Math.abs(p - ia) < Maja.EPSILON))    /* negative int c - a */
            neg_int_ca_or_cb = 1;

        r = c - b;
        ib = Math.round(r);        /* nearest integer to c-b */
        if ((ib <= 0.0) && (Math.abs(r - ib) < Maja.EPSILON))    /* negative int c - b */
            neg_int_ca_or_cb = 1;

        id = Math.round(d);        /* nearest integer to d */

        /* Thanks to Christian Burger <BURGER@DMRHRZ11.HRZ.Uni-Marburg.DE>
         * for reporting a bug here.  */
        if (Math.abs(ax - 1.0) < Maja.EPSILON) {    /* |x| == 1.0   */
            if (x > 0.0) {
                if (neg_int_ca_or_cb != 0) {
                    if (d >= 0.0) {
                        y = Math.pow(s, d) * hys2f1(c - a, c - b, c, x, err);
                        return y;
                    } else
                        return Double.POSITIVE_INFINITY;
                }
                if (d <= 0.0)
                    return Double.POSITIVE_INFINITY;
                y = Gamma.gamma(c) * Gamma.gamma(d) / (Gamma.gamma(p) * Gamma.gamma(r));
                return y;
            }
            if (d <= -1.0)
                return Double.POSITIVE_INFINITY;
        }

        /* Conditionally make d > 0 by recurrence on c
         * AMS55 #15.2.27
         */
        if (d < 0.0) {
            /* Try the power series first */
            y = hyt2f1(a, b, c, x, err);
            if (err.value < 1.0e-10)
                return y;
            /* Apply the recurrence if power series fails */
            err.value = 0.0;
            aid = (int) (2 - id);
            e = c + aid;
            d2 = hyp2f1(a, b, e, x);
            d1 = hyp2f1(a, b, e + 1.0, x);
            q = a + b + 1.0;
            for (i = 0; i < aid; i++) {
                r = e - 1.0;
                y = (e * (r - (2.0 * e - q) * x) * d2 +
                        (e - a) * (e - b) * x * d1) / (e * r * s);
                e = r;
                d1 = d2;
                d2 = y;
            }
            return y;
        }

        if (neg_int_ca_or_cb != 0) {
            y = Math.pow(s, d) * hys2f1(c - a, c - b, c, x, err);
            return y;
        }

        y = hyt2f1(a, b, c, x, err);
        return y;
    }

    public static double hyp2f1ra(double a, double b, double c, double x,
                                  DoublePtr loss) {
        double f2, f1, f0;
        long n, m, da;
        double t;
        DoublePtr err = new DoublePtr();

        /* Don't cross c or zero */
        if ((c < 0 && a <= c) || (c >= 0 && a >= c)) {
            da = Math.round(a - c);
        } else {
            da = Math.round(a);
        }
        t = a - da;

        loss.value = 0;

        assert da != 0;

        if (Math.abs(da) > 10000) {
            loss.value = 1.0;
            return Double.NaN;
        }

        if (da < 0) {
            /* Recurse down */
            f2 = 0;
            f1 = hys2f1(t, b, c, x, err);
            loss.value += err.value;
            f0 = hys2f1(t - 1, b, c, x, err);
            loss.value += err.value;
            t -= 1;
            for (n = 1; n < -da; ++n) {
                f2 = f1;
                f1 = f0;
                f0 = -(2 * t - c - t * x + b * x) / (c - t) * f1 - t * (x -
                        1) /
                        (c - t) * f2;
                t -= 1;
            }
        } else {
            /* Recurse up */
            f2 = 0;
            f1 = hys2f1(t, b, c, x, err);
            loss.value += err.value;
            f0 = hys2f1(t + 1, b, c, x, err);
            loss.value += err.value;
            t += 1;
            for (n = 1; n < da; ++n) {
                f2 = f1;
                f1 = f0;
                f0 = -((2 * t - c - t * x + b * x) * f1 +
                        (c - t) * f2) / (t * (x - 1));
                t += 1;
            }
        }

        return f0;
    }

    public static double hys2f1(double a, double b, double c, double x, DoublePtr loss) {
        double f, g, h, k, m, s, u, umax, t;
        int i;
        long ia, ib, intflag = 0;

        if (Math.abs(b) > Math.abs(a)) {
            /* Ensure that |a| > |b| ... */
            f = b;
            b = a;
            a = f;
        }

        ia = Math.round(a);
        ib = Math.round(b);

        if (Math.abs(b - ib) < Maja.EPSILON && ib <= 0 && Math.abs(b) < Math.abs(a)) {
            /* .. except when `b` is a smaller negative integer */
            f = b;
            b = a;
            a = f;
            intflag = 1;
        }

        if ((Math.abs(a) > Math.abs(c) + 1 || intflag != 0) && Math.abs(c - a) > 2
                && Math.abs(a) > 2) {
            /* |a| >> |c| implies that large cancellation error is to be expected.
             *
             * We try to reduce it with the recurrence relations
             */
            return hyp2f1ra(a, b, c, x, loss);
        }

        i = 0;
        umax = 0.0;
        f = a;
        g = b;
        h = c;
        s = 1.0;
        u = 1.0;
        k = 0.0;
        do {
            if (Math.abs(h) < Maja.EPSILON) {
                loss.value = 1.0;
                return Double.POSITIVE_INFINITY;
            }
            m = k + 1.0;
            u = u * ((f + k) * (g + k) * x / ((h + k) * m));
            s += u;
            k = Math.abs(u);        /* remember largest term summed */
            if (k > umax)
                umax = k;
            k = m;
            if (++i > 10000) {    /* should never happen */
                loss.value = 1.0;
                return (s);
            }
        }
        while (s == 0 || Math.abs(u / s) > Maja.EPSILON);

        /* return estimated relative error */
        loss.value = (Maja.EPSILON * umax) / Math.abs(s) + (Maja.EPSILON * i);

        return (s);
    }

    public static double hyt2f1(double a, double b, double c, double x, DoublePtr loss) {
        double p, q, r, s, t, y, w, d;
        DoublePtr err = new DoublePtr();
        DoublePtr err1 = new DoublePtr();
        double ax, id, d1, d2, e, y1;
        int i, aid, sign;

        long ia, ib, neg_int_a = 0, neg_int_b = 0;

        ia = Math.round(a);
        ib = Math.round(b);

        if (a <= 0 && Math.abs(a - ia) < Maja.EPSILON) {    /* a is a negative integer */
            neg_int_a = 1;
        }

        if (b <= 0 && Math.abs(b - ib) < Maja.EPSILON) {    /* b is a negative integer */
            neg_int_b = 1;
        }

        err.value = 0.0;
        s = 1.0 - x;
        if (x < -0.5 && !(neg_int_a != 0 || neg_int_b != 0)) {
            if (b > a)
                y = Math.pow(s, -a) * hys2f1(a, c - b, c, -x / s, err);

            else
                y = Math.pow(s, -b) * hys2f1(c - a, b, c, -x / s, err);

            loss.value = err.value;
            return (y);
        }

        d = c - a - b;
        id = Math.round(d);        /* nearest integer to d */

        if (x > 0.9 && !(neg_int_a != 0 || neg_int_b != 0)) {
            if (Math.abs(d - id) > Maja.EPSILON) {
                /* test for integer c-a-b */
                /* Try the Math.power series first */
                y = hys2f1(a, b, c, x, err);
                if (err.value < 1.0e-12) {
                    loss.value = err.value;
                    return (y);
                }
                /* If Math.power series fails, then apply AMS55 #15.3.6 */
                q = hys2f1(a, b, 1.0 - d, s, err);
                sign = 1;
                double[] result;
                result = Gamma.lgam(d);
                w = result[0];
                sign *= result[1];
                result = Gamma.lgam(c - a);
                w -= result[0];
                sign *= result[1];
                result = Gamma.lgam(c - b);
                w -= result[0];
                sign *= result[1];
                q *= sign * Math.exp(w);
                r = Math.pow(s, d) * hys2f1(c - a, c - b, d + 1.0, s, err1);
                sign = 1;
                result = Gamma.lgam(-d);
                w = result[0];
                sign *= result[1];
                result = Gamma.lgam(a);
                w -= result[0];
                sign *= result[1];
                result = Gamma.lgam(b);
                w -= result[0];
                sign *= result[1];
                r *= sign * Math.exp(w);
                y = q + r;

                q = Math.abs(q);    /* estimate cancellation error */
                r = Math.abs(r);
                if (q > r)
                    r = q;
                err.value += err1.value + (Maja.EPSILON * r) / y;

                y *= Gamma.gamma(c);
                loss.value = err.value;
                return (y);
            } else {
                /* Psi function expansion, AMS55 #15.3.10, #15.3.11, #15.3.12
                 *
                 * Although AMS55 does not explicitly state it, this expansion fails
                 * for negative integer a or b, since the psi and Gamma.gamma functions
                 * involved have poles.
                 */

                if (id >= 0.0) {
                    e = d;
                    d1 = d;
                    d2 = 0.0;
                    aid = (int) id;
                } else {
                    e = -d;
                    d1 = 0.0;
                    d2 = d;
                    aid = (int) -id;
                }

                ax = Math.log(s);

                /* sum for t = 0 */
                y = Gamma.digamma(1.0) + Gamma.digamma(1.0 + e) - Gamma.digamma(a + d1) - Gamma.digamma(b + d1) - ax;
                y /= Gamma.gamma(e + 1.0);

                p = (a + d1) * (b + d1) * s / Gamma.gamma(e + 2.0);    /* Poch for t=1 */
                t = 1.0;
                do {
                    r = Gamma.digamma(1.0 + t) + Gamma.digamma(1.0 + t + e) - Gamma.digamma(a + t + d1)
                            - Gamma.digamma(b + t + d1) - ax;
                    q = p * r;
                    y += q;
                    p *= s * (a + t + d1) / (t + 1.0);
                    p *= (b + t + d1) / (t + 1.0 + e);
                    t += 1.0;
                    if (t > 10000) {
                        loss.value = 1.0;
                        return Double.NaN;
                    }
                }
                while (y == 0 || Math.abs(q / y) > Maja.EPSILON);

                if (id == 0.0) {
                    y *= Gamma.gamma(c) / (Gamma.gamma(a) * Gamma.gamma(b));
                    loss.value = err.value;
                    return (y);
                }

                y1 = 1.0;

                if (aid == 1) {
                    p = Gamma.gamma(c);
                    y1 *= Gamma.gamma(e) * p / (Gamma.gamma(a + d1) * Gamma.gamma(b + d1));

                    y *= p / (Gamma.gamma(a + d2) * Gamma.gamma(b + d2));
                    y = -y;

                    q = Math.pow(s, id);    /* s to the id Math.power */
                    if (id > 0.0)
                        y *= q;
                    else
                        y1 *= q;

                    y += y1;
                    loss.value = err.value;
                    return (y);
                }

                t = 0.0;
                p = 1.0;
                for (i = 1; i < aid; i++) {
                    r = 1.0 - e + t;
                    p *= s * (a + t + d2) * (b + t + d2) / r;
                    t += 1.0;
                    p /= t;
                    y1 += p;
                }
                p = Gamma.gamma(c);
                y1 *= Gamma.gamma(e) * p / (Gamma.gamma(a + d1) * Gamma.gamma(b + d1));

                y *= p / (Gamma.gamma(a + d2) * Gamma.gamma(b + d2));
                if ((aid & 1) != 0)
                    y = -y;

                q = Math.pow(s, id);    /* s to the id Math.power */
                if (id > 0.0)
                    y *= q;
                else
                    y1 *= q;

                y += y1;
                loss.value = err.value;
                return (y);
            }

        }

        /* Use defining Math.power series if no special cases */
        y = hys2f1(a, b, c, x, err);
        loss.value = err.value;
        return (y);
    }

    static double hy1f1p(double a, double b, double x, DoublePtr err) {
        double n, a0, sum, t, u, temp;
        double an, bn, maxt, pcanc;


        /* set up for power series summation */
        an = a;
        bn = b;
        a0 = 1.0;
        sum = 1.0;
        n = 1.0;
        t = 1.0;
        maxt = 0.0;


        while (t > Maja.EPSILON) {
            if (bn == 0)            /* check bn first since if both	*/ {
                return (Double.POSITIVE_INFINITY);    /* an and bn are zero it is	*/
            }
            if (an == 0)            /* a singularity		*/
                return (sum);
            if (n > 200)
                break;
            u = x * (an / (bn * n));

            /* check for blowup */
            temp = Math.abs(u);
            if ((temp > 1.0) && (maxt > (Double.MAX_VALUE / temp))) {
                pcanc = 1.0;    /* estimate 100% error */
                err.value = pcanc;

                return (sum);
            }

            a0 *= u;
            sum += a0;
            t = Math.abs(a0);
            if (t > maxt)
                maxt = t;
            an += 1.0;
            bn += 1.0;
            n += 1.0;
        }

        /* estimate error due to roundoff and cancellation */
        if (sum != 0.0)
            maxt /= Math.abs(sum);
        maxt *= Maja.EPSILON;    /* this way avoids multiply overflow */
        pcanc = Math.abs(Maja.EPSILON * n + maxt);

        err.value = pcanc;

        return (sum);
    }

    static double hy1f1a(double a, double b, double x, DoublePtr err) {
        double h1, h2, t, u, temp, acanc, asum;
        DoublePtr err1 = new DoublePtr(), err2 = new DoublePtr();

        if (x == 0) {
            acanc = 1.0;
            asum = Double.POSITIVE_INFINITY;
            err.value = acanc;
            return (asum);
        }
        temp = Math.log(Math.abs(x));
        t = x + temp * (a - b);
        u = -temp * a;

        if (b > 0) {
            temp = Gamma.lgam(b)[0];
            t += temp;
            u += temp;
        }

        h1 = hyp2f0(a, a - b + 1, -1.0 / x, 1, err1);

        temp = Math.exp(u) / Gamma.gamma(b - a);
        h1 *= temp;
        err1.value *= temp;

        h2 = hyp2f0(b - a, 1.0 - a, 1.0 / x, 2, err2);

        if (a < 0)
            temp = Math.exp(t) / Gamma.gamma(a);
        else
            temp = Math.exp(t - Gamma.lgam(a)[0]);

        h2 *= temp;
        err2.value *= temp;

        if (x < 0.0)
            asum = h1;
        else
            asum = h2;

        acanc = Math.abs(err1.value) + Math.abs(err2.value);


        if (b < 0) {
            temp = Gamma.gamma(b);
            asum *= temp;
            acanc *= Math.abs(temp);
        }


        if (asum != 0.0)
            acanc /= Math.abs(asum);

        acanc *= 30.0;    /* fudge factor, since error of asymptotic formula
         * often seems this much larger than advertised */

        err.value = acanc;
        return (asum);
    }

    static double hyp2f0(double a, double b, double x, int type, DoublePtr err) {
        double a0, alast, t, tlast, maxt;
        double n, an, bn, u, sum, temp;

        an = a;
        bn = b;
        a0 = 1.0e0;
        alast = 1.0e0;
        sum = 0.0;
        n = 1.0e0;
        t = 1.0e0;
        tlast = 1.0e9;
        maxt = 0.0;

        do {
            if (an == 0 || bn == 0) {
                /* estimate error due to roundoff and cancellation */
                err.value = Math.abs(Maja.EPSILON * (n + maxt));

                alast = a0;
                sum += alast;
                return (sum);
            }

            u = an * (bn * x / n);

            /* check for blowup */
            temp = Math.abs(u);
            if ((temp > 1.0) && (maxt > (Double.MAX_VALUE / temp))) {
                err.value = Double.MAX_VALUE;
                return (sum);
            }

            a0 *= u;
            t = Math.abs(a0);

            /* terminating condition for asymptotic series */
            if (t > tlast) {
                /* The following "Converging factors" are supposed to improve accuracy,
                 * but do not actually seem to accomplish very much. */

                n -= 1.0;
                x = 1.0 / x;

                switch (type)    /* "type" given as subroutine argument */ {
                    case 1 -> alast *= (0.5 + (0.125 + 0.25 * b - 0.5 * a + 0.25 * x - 0.25 * n) / x);
                    case 2 -> alast *= 2.0 / 3.0 - b + 2.0 * a + x - n;
                }

                /* estimate error due to roundoff, cancellation, and nonconvergence */
                err.value = Maja.EPSILON * (n + maxt) + Math.abs(a0);

                sum += alast;
                return (sum);
            }

            tlast = t;
            sum += alast;    /* the sum is one term behind */
            alast = a0;

            if (n > 200) {
                /* The following "Converging factors" are supposed to improve accuracy,
                 * but do not actually seem to accomplish very much. */

                n -= 1.0;
                x = 1.0 / x;

                switch (type)    /* "type" given as subroutine argument */ {
                    case 1 -> alast *= (0.5 + (0.125 + 0.25 * b - 0.5 * a + 0.25 * x - 0.25 * n) / x);
                    case 2 -> alast *= 2.0 / 3.0 - b + 2.0 * a + x - n;
                }

                /* estimate error due to roundoff, cancellation, and nonconvergence */
                err.value = Maja.EPSILON * (n + maxt) + Math.abs(a0);

                sum += alast;
                return (sum);
            }

            an += 1.0e0;
            bn += 1.0e0;
            n += 1.0e0;
            if (t > maxt)
                maxt = t;
        }
        while (t > Maja.EPSILON);

        /* estimate error due to roundoff and cancellation */
        err.value = Math.abs(Maja.EPSILON * (n + maxt));

        alast = a0;
        sum += alast;
        return (sum);

    }

    public static double hyperg(double a, double b, double x) {
        double asum, psum, temp;
        DoublePtr acanc = new DoublePtr();
        DoublePtr pcanc = new DoublePtr();

        temp = b - a;
        if (Math.abs(temp) < 0.001 * Math.abs(a))
            return (Math.exp(x) * hyperg(temp, b, -x));

        psum = hy1f1p(a, b, x, pcanc);
        if (pcanc.value < 1.0e-15)
            return (psum);

        asum = hy1f1a(a, b, x, acanc);

        if (acanc.value < pcanc.value) {
            pcanc.value = acanc.value;
            psum = asum;
        }

        return (psum);
    }

    static class DoublePtr {
        double value;
    }
}
