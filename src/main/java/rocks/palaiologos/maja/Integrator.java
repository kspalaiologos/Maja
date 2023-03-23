package rocks.palaiologos.maja;

import java.util.concurrent.ConcurrentHashMap;

class Integrator {
    // https://www.genivia.com/files/qthsh.pdf.
    // returns a two element double array of the result and the estimated error.
    double[] finiteTanhSinh(MonadicFunction f, double a, double b, int n, double eps) {
        final double tol = 10 * eps;
        double c = (a + b) / 2;
        double d = (b - a) / 2;
        double s = f.apply(c);
        double e, v, h = 2;
        int k = 0;
        if (n <= 0)
            n = 6;
        if (eps <= 0)
            eps = 1E-9;
        do {
            double p = 0, q, fp = 0, fm = 0, t, eh;
            h /= 2;
            t = eh = Math.exp(h);
            if (k > 0)
                eh *= eh;
            do {
                double u = Math.exp(1 / t - t);
                double r = 2 * u / (1 + u);
                double w = (t + 1 / t) * r / (1 + u);
                double x = d * r;
                if (a + x > a) {
                    double y = f.apply(a + x);
                    if (Double.isFinite(y))
                        fp = y;
                }
                if (b - x < b) {
                    double y = f.apply(b - x);
                    if (Double.isFinite(y))
                        fm = y;
                }
                q = w * (fp + fm);
                p += q;
                t *= eh;
            } while (Math.abs(q) > eps * Math.abs(p));
            v = s - p;
            s += p;
            ++k;
        } while (Math.abs(v) > tol * Math.abs(s) && k <= n);
        e = Math.abs(v) / (Math.abs(s) + eps);
        return new double[]{d * s * h, e};
    }

    // N = 10000
    public static double finiteSimpson(MonadicFunction f, double a, double b, int N) {
        double h = (b - a) / (N - 1);

        // 1/3 terms
        double sum = 1.0 / 3.0 * (f.apply(a) + f.apply(b));

        // 4/3 terms
        for (int i = 1; i < N - 1; i += 2) {
            double x = a + h * i;
            sum += 4.0 / 3.0 * f.apply(x);
        }

        // 2/3 terms
        for (int i = 2; i < N - 1; i += 2) {
            double x = a + h * i;
            sum += 2.0 / 3.0 * f.apply(x);
        }

        return sum * h;
    }

    private static class GaussLegendreParameters {
        private int N;

        public GaussLegendreParameters(int N) {
            this.N = N;
            this.lroots = new double[N];
            this.weight = new double[N];
            this.lcoef = new double[N + 1][N + 1];
            legeCoef();
            legeRoots();
            lcoef = null;
        }

        public double[] lroots, weight;
        public double[][] lcoef;

        private void legeCoef() {
            lcoef[0][0] = lcoef[1][1] = 1;

            for (int n = 2; n <= N; n++) {

                lcoef[n][0] = -(n - 1) * lcoef[n - 2][0] / n;

                for (int i = 1; i <= n; i++) {
                    lcoef[n][i] = ((2 * n - 1) * lcoef[n - 1][i - 1]
                            - (n - 1) * lcoef[n - 2][i]) / n;
                }
            }
        }

        private double legeEval(int n, double x) {
            double s = lcoef[n][n];
            for (int i = n; i > 0; i--)
                s = s * x + lcoef[n][i - 1];
            return s;
        }

        private double legeDiff(int n, double x) {
            return n * (x * legeEval(n, x) - legeEval(n - 1, x)) / (x * x - 1);
        }

        private void legeRoots() {
            double x, x1;
            for (int i = 1; i <= N; i++) {
                x = Math.cos(Math.PI * (i - 0.25) / (N + 0.5));
                do {
                    x1 = x;
                    x -= legeEval(N, x) / legeDiff(N, x);
                } while (x != x1);

                lroots[i - 1] = x;

                x1 = legeDiff(N, x);
                weight[i - 1] = 2 / ((1 - x * x) * x1 * x1);
            }
        }
    }

    private static final ConcurrentHashMap<Integer, GaussLegendreParameters> gaussLegendreParameters = new ConcurrentHashMap<>();

    private static GaussLegendreParameters getParameters(int n) {
        synchronized (gaussLegendreParameters) {
            if (!gaussLegendreParameters.containsKey(n)) {
                gaussLegendreParameters.put(n, new GaussLegendreParameters(n));
            }
            return gaussLegendreParameters.get(n);
        }
    }

    // N = 5
    public static double gaussLegendreIntegrate(MonadicFunction f, double a, double b, int N) {
        GaussLegendreParameters parameters = getParameters(N);
        double c1 = (b - a) / 2, c2 = (b + a) / 2, sum = 0;
        for (int i = 0; i < N; i++)
            sum += parameters.weight[i] * f.apply(c1 * parameters.lroots[i] + c2);
        return c1 * sum;
    }
}
