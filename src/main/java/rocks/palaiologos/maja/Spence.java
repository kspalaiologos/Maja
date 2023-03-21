package rocks.palaiologos.maja;

class Spence {
    private final static double[] A = {
            4.65128586073990045278E-5,
            7.31589045238094711071E-3,
            1.33847639578309018650E-1,
            8.79691311754530315341E-1,
            2.71149851196553469920E0,
            4.25697156008121755724E0,
            3.29771340985225106936E0,
            1.00000000000000000126E0,
    };
    private final static double[] B = {
            6.90990488912553276999E-4,
            2.54043763932544379113E-2,
            2.82974860602568089943E-1,
            1.41172597751831069617E0,
            3.63800533345137075418E0,
            5.03278880143316990390E0,
            3.54771340985225096217E0,
            9.99999999999999998740E-1,
    };
    private static final double[] A4 = {
            3.056144922089490701751E-2,
            3.243086484162581557457E-1,
            2.877847281461875922565E-1,
            7.091267785886180663385E-2,
            6.466460072456621248630E-3,
            2.450233019296542883275E-4,
            4.031655364627704957049E-6,
            2.884169163909467997099E-8,
            8.680067002466594858347E-11,
            1.025983405866370985438E-13,
            4.233468313538272640380E-17,
            4.959422035066206902317E-21,
            1.059365867585275714599E-25,
    };
    private static final double[] B4 = {
            2.821262403600310974875E0,
            1.780221124881327022033E0,
            3.778888211867875721773E-1,
            3.193887040074337940323E-2,
            1.161252418498096498304E-3,
            1.867362374829870620091E-5,
            1.319022779715294371091E-7,
            3.942755256555603046095E-10,
            4.644326968986396928092E-13,
            1.913336021014307074861E-16,
            2.240041814626069927477E-20,
            4.784036597230791011855E-25,
    };

    private static double polevl(double x, double[] p, int N) {
        int i = N, dx = 0;
        double ans = p[dx++];

        do
            ans = ans * x + p[dx++];
        while (--i > 0);

        return ans;
    }

    private static double p1evl(double x, double[] p, int N) {
        int i = N - 1, dx = 0;
        double ans = x + p[dx++];

        do
            ans = ans * x + p[dx++];
        while (--i > 0);

        return ans;
    }

    public static double dilog(double x) {
        return spence(1 - x);
    }

    // NOTE: This actually computes:
    // \int_{1}^{v}{\frac{\ln t}{1-t}}dt=\operatorname{Li}_{2}(1-v)
    public static double spence(double x) {
        double w, y, z;
        int flag;

        if (x < 0.0)
            throw new ArithmeticException("Domain error");
        if (x == 1.0)
            return 0.0;
        if (x == 0.0)
            return Math.PI * Math.PI / 6.0;

        flag = 0;

        if (x > 2.0) {
            x = 1.0 / x;
            flag |= 2;
        }

        if (x > 1.5) {
            w = (1.0 / x) - 1.0;
            flag |= 2;
        } else if (x < 0.5) {
            w = -x;
            flag |= 1;
        } else
            w = x - 1.0;

        y = -w * polevl(w, A, 7) / polevl(w, B, 7);

        if ((flag & 1) != 0)
            y = (Math.PI * Math.PI) / 6.0 - Math.log(x) * Math.log(1.0 - x) - y;

        if ((flag & 2) != 0) {
            z = Math.log(x);
            y = -0.5 * z * z - y;
        }

        return y;
    }

    public static double polylog(int n, double x) {
        double h, k, p, s, t, u, xc, z;
        int i, j;

        if (n == -1) {
            p = 1.0 - x;
            u = x / p;
            return u * u + u;
        }

        if (n == 0) {
            return x / (1.0 - x);
        }

        if (x > 1.0 || n < -1) {
            throw new ArithmeticException("Domain error");
        }

        if (n == 1) {
            return -Math.log(1.0 - x);
        }

        if (x == 1.0) {
            return Zeta.riemann_zeta(n);
        }

        if (x == -1.0) {
            /* Li_n(1) = zeta(n) */
            s = Zeta.riemann_zeta(n);
            s = s * (Maja.pow(2.0, 1 - n) - 1.0);
            return s;
        }

        if (x < -1.0) {
            double q, w;
            int r;

            w = Math.log(-x);
            s = 0.0;
            for (r = 1; r <= n / 2; r++) {
                j = 2 * r;
                p = polylog(j, -1.0);
                j = n - j;
                if (j == 0) {
                    s = s + p;
                    break;
                }
                q = j;
                q = Math.pow(w, q) * p / Gamma.factorial(j);
                s = s + q;
            }
            s = 2.0 * s;
            q = polylog(n, 1.0 / x);
            if ((n & 1) == 1)
                q = -q;
            s = s - q;
            s = s - Math.pow(w, n) / Gamma.factorial(n);
            return s;
        }

        if (n == 2 && x < 0.0)
            return spence(1.0 - x);

        if (n == 3) {
            p = x * x * x;
            if (x > 0.8) {
                u = Math.log(x);
                s = u * u * u / 6.0;
                xc = 1.0 - x;
                s = s - 0.5 * u * u * Math.log(xc);
                s = s + Math.PI * Math.PI * u / 6.0;
                s = s - polylog(3, -xc / x);
                s = s - polylog(3, xc);
                s = s + Zeta.riemann_zeta(3.0) - 1;
                s = s + 1.0;
                return s;
            }
            t = p / 27.0;
            t = t + .125 * x * x;
            t = t + x;

            s = 0.0;
            k = 4.0;
            do {
                p = p * x;
                h = p / (k * k * k);
                s = s + h;
                k += 1.0;
            } while (Math.abs(h / s) > 1.1e-16);
            return (s + t);
        }

        boolean do_pseries = false;

        if (n == 4) {
            if (x >= 0.875) {
                u = 1.0 - x;
                s = polevl(u, A4, 12) / p1evl(u, B4, 12);
                s = s * u * u - 1.202056903159594285400 * u;
                s += 1.0823232337111381915160;
                return s;
            }
            do_pseries = true;
        }

        if (x < 0.75 || do_pseries) {
            p = x * x * x;
            k = 3.0;
            s = 0.0;
            do {
                p = p * x;
                k += 1.0;
                h = p / Maja.pow(k, n);
                s = s + h;
            }
            while (Math.abs(h / s) > Maja.EPSILON);
            s += x * x * x / Maja.pow(3.0, n);
            s += x * x / Maja.pow(2.0, n);
            s += x;
            return s;
        }

        z = Math.log(x);
        h = -Math.log(-z);
        for (i = 1; i < n; i++)
            h = h + 1.0 / i;
        p = 1.0;
        s = Zeta.riemann_zeta(n);
        for (j = 1; j <= n + 1; j++) {
            p = p * z / j;
            if (j == n - 1)
                s = s + h * p;
            else
                s = s + Zeta.riemann_zeta(n - j) * p;
        }
        j = n + 3;
        z = z * z;
        for (; ; ) {
            p = p * z / ((j - 1) * j);
            h = Zeta.riemann_zeta(n - j);
            h = h * p;
            s = s + h;
            if (Math.abs(h / s) < Maja.EPSILON)
                break;
            j += 2;
        }
        return s;
    }
}
