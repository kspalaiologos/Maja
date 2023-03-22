package rocks.palaiologos.maja;

class Integrator {
    // https://www.genivia.com/files/qthsh.pdf
    double[] finiteTanhSinh(MonadicFunction f, double a, double b, int n, double eps) {
        final double tol = 10*eps;
        double c = (a+b)/2;
        double d = (b-a)/2;
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
                double u = Math.exp(1/t-t);
                double r = 2*u/(1+u);
                double w = (t+1/t)*r/(1+u);
                double x = d*r;
                if (a+x > a) {
                    double y = f.apply(a+x);
                    if (Double.isFinite(y))
                        fp = y;
                }
                if (b-x < b) {
                    double y = f.apply(b-x);
                    if (Double.isFinite(y))
                        fm = y;
                }
                q = w*(fp+fm);
                p += q;
                t *= eh;
            } while (Math.abs(q) > eps*Math.abs(p));
            v = s-p;
            s += p;
            ++k;
        } while (Math.abs(v) > tol*Math.abs(s) && k <= n);
        e = Math.abs(v)/(Math.abs(s)+eps);
        return new double[] { d*s*h, e };
    }
}
