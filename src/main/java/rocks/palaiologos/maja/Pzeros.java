package rocks.palaiologos.maja;

import java.util.Arrays;

/**
 * Originally written by D. Bini in Fortran77.
 */
public class Pzeros {
    static Pzeros pz = new Pzeros();
    doublecomplex c_b35 = new doublecomplex(1., 0.);

    /**
     * Calculates the roots of polynomial functions
     * y=a[0]+a[1]*x+a[2]*x^2+a[3]*x^3+...+a[n]*x^n
     * of any degree n having arbitrary complex coefficients a[i].
     * The method is derived from
     * <a href=http://netlib.org/numeralgo/na10>pzeros.f</a>.
     * <p>
     * The real and imaginary parts of the coefficients are
     * passed in two double arrays. The roots are returned
     * in the same arrays. There are (n+1) coefficients and
     * n roots. The coefficient a[n] must not be zero. Errors
     * are returned in a boolean array separately for each root.
     * Success is indicated by false, i.e. no error. On return
     * each root should be checked against its flag.
     * <p>
     * All three arrays must be equal sized. This is a conveniance
     * method which sets most accessible parameters to reasonable
     * defaults. It also transparently deflates the polynomial
     * in case the constant coefficient is zero. See the sources
     * for a description of the method.
     *
     * @param ar  realpart of polynomial coefficients a.
     * @param ai  imaginary part of polynomial coefficients a.
     * @param err error flag for each root.
     */
    public static void aberth(double[] ar, double[] ai, boolean[] err) {
        Arrays.fill(err, true);
        if (ar.length != ai.length || ar.length != err.length)
            return;
        int n_zero = 0;
        while (n_zero < ar.length &&
                ar[n_zero] == 0. && ai[n_zero] == 0.) n_zero++;
        if (n_zero == ar.length) return;

        doublecomplex[] poly = new doublecomplex[ar.length - n_zero];
        for (int i = 0; i < poly.length; i++) {
            poly[i] = Pzeros.pz.dc(ar[i + n_zero], ai[i + n_zero]);
        }
        int n = poly.length - 1;
        double eps = 2.22044604925031e-16;
        double big = Double.MAX_VALUE;
        double theSmall = Double.MIN_VALUE;
        int nitmax = 100;
        doublecomplex[] root = new doublecomplex[n];
        double[] radius = new double[n];
        boolean[] errs = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            root[i] = Pzeros.pz.dc();
            radius[i] = 1.0;
            errs[i] = true;
        }
        int[] iter = new int[1];
        iter[0] = 0;
        double[] apoly = new double[n + 1];
        double[] apolyr = new double[n + 1];
        for (int i = 0; i < n + 1; i++) {
            apoly[i] = 1.0;
            apolyr[i] = 1.0;
        }
        Pzeros.pz.polzeros_(n, poly, eps, big, theSmall, nitmax, root,
                radius, errs, iter, apoly, apolyr);
        for (int i = 0; i < n_zero; i++) {
            ar[i] = 0.0;
            ai[i] = 0.0;
            err[i] = false;
        }
        for (int i = n_zero; i < ar.length - 1; i++) {
            ar[i] = root[i - n_zero].r;
            ai[i] = root[i - n_zero].i;
            err[i] = errs[i - n_zero];
        }
    }

    double z_abs(doublecomplex z) {
        double temp, real, imag;

        real = z.r;
        imag = z.i;

        if (real < 0)
            real = -real;
        if (imag < 0)
            imag = -imag;
        if (imag > real) {
            temp = real;
            real = imag;
            imag = temp;
        }
        if (real + imag == real)
            return real;

        temp = imag / real;
        temp = real * Math.sqrt(1.0 + temp * temp);
        return temp;
    }

    int pow_ii(int ap, int bp) {
        int pow, x, n;
        long u;

        x = ap;
        n = bp;

        if (n <= 0) {
            if (n == 0 || x == 1)
                return 1;
            if (x != -1)
                return x == 0 ? 1 / x : 0;
            n = -n;
        }
        u = n;
        for (pow = 1; ; ) {
            if ((u & 1) != 0)
                pow *= x;
            if ((u >>= 1) != 0)
                x *= x;
            else
                break;
        }
        return pow;
    }

    void z_div(doublecomplex c, doublecomplex a, doublecomplex b) {
        double ratio, den;
        double abr, abi, cr;

        if ((abr = b.r) < 0.)
            abr = -abr;
        if ((abi = b.i) < 0.)
            abi = -abi;
        if (abr <= abi) {
            if (abi == 0) {
                c.r = 1.0;
                c.i = 1.0;
                return;
            }
            ratio = b.r / b.i;
            den = b.i * (1 + ratio * ratio);
            cr = (a.r * ratio + a.i) / den;
            c.i = (a.i * ratio - a.r) / den;
        } else {
            ratio = b.i / b.r;
            den = b.r * (1 + ratio * ratio);
            cr = (a.r + a.i * ratio) / den;
            c.i = (a.i - a.r * ratio) / den;
        }
        c.r = cr;
    }

    doublecomplex dc() {
        return new doublecomplex(0., 0.);
    }

    doublecomplex dc(double r, double i) {
        return new doublecomplex(r, i);
    }

    void polzeros_(int n, doublecomplex[] poly, double eps,
                   double big, double theSmall, int nitmax, doublecomplex[] root,
                   double[] radius, boolean[] err, int[] iter, double[] apoly, double[] apolyr) {
        int i__1, i__2, i__3, i__4;
        double d__1, d__2;
        doublecomplex z__1 = dc(), z__2 = dc(), z__3 = dc(), z__4 = dc();

        double amax;
        doublecomplex corr = dc();
        int i;

        doublecomplex abcorr = dc();
        int[] nzeros = new int[1];

        if (z_abs(poly[n]) == 0.) {
            throw new RuntimeException("Inconsistent data: the leading coefficient is zero");
        }
        if (z_abs(poly[0]) == 0.) {
            throw new RuntimeException("The constant term is zero: deflate the polynomial");
        }
        amax = 0.;
        i__1 = n + 1;
        for (i = 1; i <= i__1; ++i) {
            apoly[i - 1] = z_abs(poly[i - 1]);
            d__1 = amax;
            d__2 = apoly[i - 1];
            amax = Math.max(d__1, d__2);
            apolyr[i - 1] = apoly[i - 1];
        }
        i__1 = n;
        for (i = 1; i <= i__1; ++i) {
            radius[i - 1] = 0.;
            err[i - 1] = true;
        }
        start_(n, apolyr, root, radius, nzeros, theSmall, big, err);
        i__1 = n + 1;
        for (i = 1; i <= i__1; ++i) {
            apolyr[n - i + 2 - 1] = eps * apoly[i - 1] * ((n - i + 1) * (float) 3.8 + 1);
            apoly[i - 1] = eps * apoly[i - 1] * ((i - 1) * (float) 3.8 + 1);
        }
        i__1 = n;
        for (i = 1; i <= i__1; ++i) {
            err[i - 1] = radius[i - 1] != -1.;
        }

        i__1 = nitmax;
        for (iter[0] = 1; iter[0] <= i__1; ++iter[0]) {
            i__2 = n;
            for (i = 1; i <= i__2; ++i) {
                if (err[i - 1]) {
                    newton_(n, poly, apoly, apolyr, root[i - 1], theSmall,
                            radius, corr, err, i - 1);
                    if (err[i - 1]) {
                        aberth_(n, i, root, abcorr);
                        i__3 = i;
                        i__4 = i;
                        z__4.r = corr.r * abcorr.r - corr.i * abcorr.i;
                        z__4.i = corr.r * abcorr.i + corr.i * abcorr.r;
                        z__3.r = 1 - z__4.r;
                        z__3.i = -z__4.i;
                        z_div(z__2, corr, z__3);
                        z__1.r = root[i__4 - 1].r - z__2.r;
                        z__1.i = root[i__4 - 1].i - z__2.i;
                        root[i__3 - 1].r = z__1.r;
                        root[i__3 - 1].i = z__1.i;
                    } else {
                        ++nzeros[0];
                        if (nzeros[0] == n) {
                            return;
                        }
                    }
                }
            }
        }
    }

    void newton_(int n, doublecomplex[] poly, double[] apoly, double[] apolyr,
                 doublecomplex z, double theSmall, double[] radius, doublecomplex corr,
                 boolean[] again, int ik) {
        int i__1;
        double d__1;
        doublecomplex z__1 = dc(), z__2 = dc();
        double absp;
        doublecomplex ppsp = dc();
        int i;
        doublecomplex p = dc(), p1 = dc();
        double ap, az;
        doublecomplex zi = dc(), den = dc();
        double azi;

        az = z_abs(z);

        if (az <= 1.) {
            i__1 = n + 1;
            p.r = poly[i__1 - 1].r;
            p.i = poly[i__1 - 1].i;
            ap = apoly[n + 1 - 1];
            p1.r = p.r;
            p1.i = p.i;
            for (i = n; i >= 2; --i) {
                z__2.r = p.r * z.r - p.i * z.i;
                z__2.i = p.r * z.i + p.i * z.r;
                i__1 = i;
                z__1.r = z__2.r + poly[i__1 - 1].r;
                z__1.i = z__2.i + poly[i__1 - 1].i;
                p.r = z__1.r;
                p.i = z__1.i;
                z__2.r = p1.r * z.r - p1.i * z.i;
                z__2.i = p1.r * z.i + p1.i * z.r;
                z__1.r = z__2.r + p.r;
                z__1.i = z__2.i + p.i;
                p1.r = z__1.r;
                p1.i = z__1.i;
                ap = ap * az + apoly[i - 1];
            }
            z__2.r = p.r * z.r - p.i * z.i;
            z__2.i = p.r * z.i + p.i * z.r;
            z__1.r = z__2.r + poly[0].r;
            z__1.i = z__2.i + poly[0].i;
            p.r = z__1.r;
            p.i = z__1.i;
            ap = ap * az + apoly[0];
            z_div(z__1, p, p1);
            corr.r = z__1.r;
            corr.i = z__1.i;
            absp = z_abs(p);
            again[ik] = absp > theSmall + ap;
            if (!again[ik]) {
                radius[ik] = n * (absp + ap) / z_abs(p1);
            }
        } else {
            z_div(z__1, c_b35, z);
            zi.r = z__1.r;
            zi.i = z__1.i;
            azi = 1 / az;
            p.r = poly[0].r;
            p.i = poly[0].i;
            p1.r = p.r;
            p1.i = p.i;
            ap = apolyr[n + 1 - 1];
            for (i = n; i >= 2; --i) {
                z__2.r = p.r * zi.r - p.i * zi.i;
                z__2.i = p.r * zi.i + p.i * zi.r;
                i__1 = n - i + 2;
                z__1.r = z__2.r + poly[i__1 - 1].r;
                z__1.i = z__2.i + poly[i__1 - 1].i;
                p.r = z__1.r;
                p.i = z__1.i;
                z__2.r = p1.r * zi.r - p1.i * zi.i;
                z__2.i = p1.r * zi.i + p1.i * zi.r;
                z__1.r = z__2.r + p.r;
                z__1.i = z__2.i + p.i;
                p1.r = z__1.r;
                p1.i = z__1.i;
                ap = ap * azi + apolyr[i - 1];
                /* L20: */
            }
            z__2.r = p.r * zi.r - p.i * zi.i;
            z__2.i = p.r * zi.i + p.i * zi.r;
            i__1 = n + 1;
            z__1.r = z__2.r + poly[i__1 - 1].r;
            z__1.i = z__2.i + poly[i__1 - 1].i;
            p.r = z__1.r;
            p.i = z__1.i;
            ap = ap * azi + apolyr[0];
            absp = z_abs(p);
            again[ik] = absp > theSmall + ap;
            z__2.r = p.r * z.r - p.i * z.i;
            z__2.i = p.r * z.i + p.i * z.r;
            z_div(z__1, z__2, p1);
            ppsp.r = z__1.r;
            ppsp.i = z__1.i;
            d__1 = n;
            z__2.r = d__1 * ppsp.r;
            z__2.i = d__1 * ppsp.i;
            z__1.r = z__2.r - 1;
            z__1.i = z__2.i;
            den.r = z__1.r;
            den.i = z__1.i;
            z_div(z__2, ppsp, den);
            z__1.r = z.r * z__2.r - z.i * z__2.i;
            z__1.i = z.r * z__2.i + z.i * z__2.r;
            corr.r = z__1.r;
            corr.i = z__1.i;
            if (again[ik]) {
                return;
            }
            radius[ik] = z_abs(ppsp) + ap * az / z_abs(p1);
            radius[ik] = n * radius[ik] / z_abs(den);
            radius[ik] *= az;
        }
    }

    void aberth_(int n, int j, doublecomplex[] root, doublecomplex abcorr) {
        int i__1, i__2;
        doublecomplex z__1 = dc(), z__2 = dc();

        int i;
        doublecomplex z = dc(), zj = dc();

        abcorr.r = 0.;
        abcorr.i = 0.;
        i__1 = j;
        zj.r = root[i__1 - 1].r;
        zj.i = root[i__1 - 1].i;
        i__1 = j - 1;
        for (i = 1; i <= i__1; ++i) {
            i__2 = i;
            z__1.r = zj.r - root[i__2 - 1].r;
            z__1.i = zj.i - root[i__2 - 1].i;
            z.r = z__1.r;
            z.i = z__1.i;
            z_div(z__2, c_b35, z);
            z__1.r = abcorr.r + z__2.r;
            z__1.i = abcorr.i + z__2.i;
            abcorr.r = z__1.r;
            abcorr.i = z__1.i;
        }
        i__1 = n;
        for (i = j + 1; i <= i__1; ++i) {
            i__2 = i;
            z__1.r = zj.r - root[i__2 - 1].r;
            z__1.i = zj.i - root[i__2 - 1].i;
            z.r = z__1.r;
            z.i = z__1.i;
            z_div(z__2, c_b35, z);
            z__1.r = abcorr.r + z__2.r;
            z__1.i = abcorr.i + z__2.i;
            abcorr.r = z__1.r;
            abcorr.i = z__1.i;
        }
    }

    int start_(int n, double[] a, doublecomplex[] y, double[] radius,
               int[] nz, double theSmall, double big, boolean[] h) {
        int i__1, i__2, i__3;
        double d__1, d__2;
        doublecomplex z__1 = dc(), z__2 = dc(), z__3 = dc();

        int iold;
        double xbig, temp;
        int i, j;
        double r = 0.;
        int jj;
        double th, xsmall;
        int nzeros;
        double ang;

        xsmall = Math.log(theSmall);
        xbig = Math.log(big);
        nz[0] = 0;

        i__1 = n + 1;
        for (i = 1; i <= i__1; ++i) {
            if (a[i - 1] != 0.) {
                a[i - 1] = Math.log(a[i - 1]);
            } else {
                a[i - 1] = -1e30;
            }
        }
        i__1 = n + 1;
        cnvex_(i__1, a, h);

        iold = 1;
        th = 6.2831853071796 / n;
        i__1 = n + 1;
        for (i = 2; i <= i__1; ++i) {
            if (h[i - 1]) {
                nzeros = i - iold;
                temp = (a[iold - 1] - a[i - 1]) / nzeros;

                if (temp < -xbig && temp >= xsmall) {
                    nz[0] += nzeros;
                    r = 1. / big;
                }
                if (temp < xsmall) {
                    nz[0] += nzeros;
                }

                if (temp > xbig) {
                    r = big;
                    nz[0] += nzeros;
                }

                d__1 = -xbig;
                if (temp <= xbig && temp > Math.max(d__1, xsmall)) {
                    r = Math.exp(temp);
                }

                ang = 6.2831853071796 / nzeros;
                i__2 = i - 1;
                for (j = iold; j <= i__2; ++j) {
                    jj = j - iold + 1;
                    if (r <= 1. / big || r == big) {
                        radius[j - 1] = -1.;
                    }
                    i__3 = j;
                    final double a1 = ang * jj + th * i + .7;
                    d__1 = Math.cos(a1);
                    d__2 = Math.sin(a1);
                    z__3.r = d__2 * (float) 0.;
                    z__3.i = d__2 * (float) 1.;
                    z__2.r = d__1 + z__3.r;
                    z__2.i = z__3.i;
                    z__1.r = r * z__2.r;
                    z__1.i = r * z__2.i;
                    y[i__3 - 1].r = z__1.r;
                    y[i__3 - 1].i = z__1.i;
                }
                iold = i;
            }
        }
        return 0;
    }

    int cnvex_(int n, double[] a, boolean[] h) {
        int i__1, i__2, i__3;
        int i, j, k, m, jc, nj;

        i__1 = n;
        for (i = 1; i <= i__1; ++i) {
            h[i - 1] = true;
        }

        k = (int) (Math.log(n - 2.) / Math.log(2.));
        i__1 = k + 1;
        if (pow_ii(2, i__1) <= n - 2) {
            ++k;
        }

        m = 1;
        i__1 = k;
        for (i = 0; i <= i__1; ++i) {
            i__2 = 0;
            i__3 = (n - 2 - m) / (m + m);
            nj = Math.max(i__2, i__3);
            i__2 = nj;
            for (j = 0; j <= i__2; ++j) {
                jc = (j + j + 1) * m + 1;
                cmerge_(n, a, jc, m, h);
            }
            m += m;
        }
        return 0;
    }

    int left_(int n, boolean[] h, int i, int[] il) {
        for (il[0] = i - 1; il[0] >= 0; --il[0]) {
            if (h[il[0] - 1]) {
                return 0;
            }
        }
        return 0;
    }

    int right_(int n, boolean[] h, int i, int[] ir) {
        int i__1 = n;
        for (ir[0] = i + 1; ir[0] <= i__1; ++ir[0]) {
            if (h[ir[0] - 1]) {
                return 0;
            }
        }
        return 0;
    }

    int cmerge_(int n, double[] a, int i, int m, boolean[] h) {
        int i__1, i__2;

        boolean tstl, tstr;
        int[] ill = new int[1], irr = new int[1];
        int[] il = new int[1], ir = new int[1];

        left_(n, h, i, il);
        right_(n, h, i, ir);
        if (ctest_(n, a, il[0], i, ir[0])) {
            return 0;
        } else {
            h[i - 1] = false;
            while (true) {
                if (il[0] == i - m) {
                    tstl = true;
                } else {
                    left_(n, h, il[0], ill);
                    tstl = ctest_(n, a, ill[0], il[0], ir[0]);
                }
                i__1 = n;
                i__2 = i + m;
                if (ir[0] == Math.min(i__1, i__2)) {
                    tstr = true;
                } else {
                    right_(n, h, ir[0], irr);
                    tstr = ctest_(n, a, il[0], ir[0], irr[0]);
                }
                h[il[0] - 1] = tstl;
                h[ir[0] - 1] = tstr;
                if (tstl && tstr) {
                    return 0;
                }
                if (!tstl) {
                    il[0] = ill[0];
                }
                if (!tstr) {
                    ir[0] = irr[0];
                }
            }
        }
    }

    boolean ctest_(int n, double[] a, int il, int i, int ir) {
        boolean ret_val;
        double s1, s2;
        s1 = a[i - 1] - a[il - 1];
        s2 = a[ir - 1] - a[i - 1];
        s1 *= ir - i;
        s2 *= i - il;
        ret_val = s1 > s2 + .4;
        return ret_val;
    }

    static class doublecomplex {
        double r, i;

        public doublecomplex(double r, double i) {
            this.r = r;
            this.i = i;
        }

        public String toString() {
            return r + " + i*" + i;
        }
    }
}
