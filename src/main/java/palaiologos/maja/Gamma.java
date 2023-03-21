package palaiologos.maja;

class Gamma {
    // It's more efficient to simply tabulate all factorials.
    // '1,',1↓∊',',⍪⍕¨!¨⍳170
    private static final double[] factorialTab = {
            1.0, 1.0, 2.0, 6.0, 24.0, 120.0, 720.0, 5040.0, 40320.0, 362880.0, 3628800.0, 39916800.0, 479001600.0, 6227020800.0,
            8.71782912E10, 1.307674368E12, 2.092278989E13, 3.556874281E14, 6.402373706E15,
            1.216451004E17, 2.432902008E18, 5.109094217E19, 1.124000728E21,
            2.585201674E22, 6.204484017E23, 1.551121004E25, 4.032914611E26,
            1.088886945E28, 3.048883446E29, 8.841761994E30, 2.652528598E32,
            8.222838654E33, 2.631308369E35, 8.683317619E36, 2.95232799E38, 1.033314797E40,
            3.719933268E41, 1.376375309E43, 5.230226175E44, 2.039788208E46,
            8.159152832E47, 3.345252661E49, 1.405006118E51, 6.041526306E52,
            2.658271575E54, 1.196222209E56, 5.50262216E57, 2.586232415E59, 1.241391559E61,
            6.08281864E62, 3.04140932E64, 1.551118753E66, 8.065817517E67, 4.274883284E69,
            2.308436973E71, 1.269640335E73, 7.109985878E74, 4.05269195E76, 2.350561331E78,
            1.386831185E80, 8.320987113E81, 5.075802139E83, 3.146997326E85,
            1.982608315E87, 1.268869322E89, 8.247650592E90, 5.443449391E92,
            3.647111092E94, 2.480035542E96, 1.711224524E98, 1.197857167E100,
            8.504785886E101, 6.123445838E103, 4.470115462E105, 3.307885442E107,
            2.480914081E109, 1.885494702E111, 1.45183092E113, 1.132428118E115,
            8.946182131E116, 7.156945705E118, 5.797126021E120, 4.753643337E122,
            3.94552397E124, 3.314240135E126, 2.817104114E128, 2.422709538E130,
            2.107757298E132, 1.854826423E134, 1.650795516E136, 1.485715964E138,
            1.352001528E140, 1.243841405E142, 1.156772507E144, 1.087366157E146,
            1.032997849E148, 9.916779349E149, 9.619275968E151, 9.426890449E153,
            9.332621544E155, 9.332621544E157, 9.42594776E159, 9.614466715E161,
            9.902900716E163, 1.029901675E166, 1.081396758E168, 1.146280564E170,
            1.226520203E172, 1.324641819E174, 1.443859583E176, 1.588245542E178,
            1.762952551E180, 1.974506857E182, 2.231192749E184, 2.543559733E186,
            2.925093693E188, 3.393108684E190, 3.969937161E192, 4.68452585E194,
            5.574585761E196, 6.689502913E198, 8.094298525E200, 9.875044201E202,
            1.214630437E205, 1.506141742E207, 1.882677177E209, 2.372173243E211,
            3.012660018E213, 3.856204824E215, 4.974504222E217, 6.466855489E219,
            8.471580691E221, 1.118248651E224, 1.487270706E226, 1.992942746E228,
            2.690472707E230, 3.659042882E232, 5.012888748E234, 6.917786473E236,
            9.615723197E238, 1.346201248E241, 1.898143759E243, 2.695364138E245,
            3.854370717E247, 5.550293833E249, 8.047926057E251, 1.174997204E254,
            1.72724589E256, 2.556323918E258, 3.808922638E260, 5.713383956E262,
            8.627209774E264, 1.311335886E267, 2.006343905E269, 3.089769614E271,
            4.789142901E273, 7.471062926E275, 1.172956879E278, 1.853271869E280,
            2.946702272E282, 4.714723636E284, 7.590705054E286, 1.229694219E289,
            2.004401577E291, 3.287218586E293, 5.423910666E295, 9.003691706E297,
            1.503616515E300, 2.526075745E302, 4.269068009E304, 7.257415615E306
    };

    public static double loggamma(double xx) {
        double x, y, tmp, ser;

        final double[] cof = {76.18009172947146, -86.50532032941677,
                24.01409824083091, -1.231739572450155, 0.1208650973866179e-2,
                -0.5395239384953e-5};
        int j;
        y = x = xx;
        tmp = x + 5.5;
        tmp -= (x + 0.5) * Math.log(tmp);
        ser = 1.000000000190015;
        for (j = 0; j <= 5; j++)
            ser += cof[j] / ++y;

        return -tmp + Math.log(2.5066282746310005 * ser / x);
    }

    public static double gamma(double x) {
        double sgngam, q, z, y, p1, q1;
        int ip, p;
        double[] pp = {1.60119522476751861407E-4, 1.19135147006586384913E-3, 1.04213797561761569935E-2, 4.76367800457137231464E-2, 2.07448227648435975150E-1, 4.94214826801497100753E-1, 9.99999999999999996796E-1};
        double[] qq = {-2.31581873324120129819E-5, 5.39605580493303397842E-4, -4.45641913851797240494E-3, 1.18139785222060435552E-2, 3.58236398605498653373E-2, -2.34591795718243348568E-1, 7.14304917030273074085E-2, 1.00000000000000000320};
        sgngam = 1;
        q = Math.abs(x);
        if (q > 33.0) {
            if (x < 0.0) {
                p = (int) Math.floor(q);
                ip = Math.round(p);
                if (ip % 2 == 0) {
                    sgngam = -1;
                }
                z = q - p;
                if (z > 0.5) {
                    p = p + 1;
                    z = q - p;
                }
                z = q * Math.sin(Math.PI * z);
                z = Math.abs(z);
                z = Math.PI / (z * gammastirf(q));
            } else {
                z = gammastirf(x);
            }
            y = sgngam * z;
            return y;
        }
        z = 1;
        while (x >= 3) {
            x = x - 1;
            z = z * x;
        }
        while (x < 0) {
            if (x > -0.000000001) {
                y = z / ((1 + 0.5772156649015329 * x) * x);
                return y;
            }
            z = z / x;
            x = x + 1;
        }
        while (x < 2) {
            if (x < 0.000000001) {
                y = z / ((1 + 0.5772156649015329 * x) * x);
                return y;
            }
            z = z / x;
            x = x + 1.0;
        }
        if (x == 2) {
            y = z;
            return y;
        }
        x = x - 2.0;
        p1 = pp[0];
        for (int i = 1; i < 7; i++) {
            p1 = pp[i] + p1 * x;
        }
        q1 = qq[0];
        for (int i = 1; i < 8; i++) {
            q1 = qq[i] + q1 * x;
        }
        return z * p1 / q1;
    }

    private static double gammastirf(double x) {
        double p1, w, y, v;
        w = 1 / x;
        double[] pp = {7.87311395793093628397E-4, -2.29549961613378126380E-4, -2.68132617805781232825E-3, 3.47222221605458667310E-3, 8.33333333333482257126E-2};
        p1 = pp[0];
        for (int i = 1; i < 5; i++) {
            p1 = pp[i] + p1 * x;
        }
        w = 1 + w * p1;
        y = Math.exp(x);
        if (x > 143.01608) {
            v = Math.pow(x, 0.5 * x - 0.25);
            y = v * (v / y);
        } else {
            y = Math.pow(x, x - 0.5) / y;
        }
        return 2.50662827463100050242 * y * w;
    }

    public static double digamma(double x) {
        final double GAMMA = 0.577215664901532860606512090082;
        final double C_LIMIT = 49;
        final double S_LIMIT = 1e-5;
        final double F_M1_12 = -1d / 12;
        final double F_1_120 = 1d / 120;
        final double F_M1_252 = -1d / 252;

        if (!Double.isFinite(x)) {
            return x;
        }

        double digamma = 0;
        if (x < 0) {
            digamma -= Math.PI / Math.tan(Math.PI * x);
            x = 1 - x;
        }

        if (x > 0 && x <= S_LIMIT) {
            return digamma - GAMMA - 1 / x;
        }

        while (x < C_LIMIT) {
            digamma -= 1 / x;
            x += 1;
        }

        final double inv = 1 / (x * x);
        digamma += Math.log(x) - 0.5 / x + inv * (F_M1_12 + inv * (F_1_120 + F_M1_252 * inv));

        return digamma;
    }

    public static double trigamma(double x) {
        final double C_LIMIT = 49;
        final double S_LIMIT = 1e-5;
        final double F_1_6 = 1d / 6;
        final double F_1_30 = 1d / 30;
        final double F_1_42 = 1d / 42;

        if (!Double.isFinite(x)) {
            return x;
        }

        if (x > 0 && x <= S_LIMIT) {
            return 1 / (x * x);
        }

        double trigamma = 0;
        while (x < C_LIMIT) {
            trigamma += 1 / (x * x);
            x += 1;
        }

        final double inv = 1 / (x * x);
        trigamma += 1 / x + inv / 2 + inv / x * (F_1_6 - inv * (F_1_30 + F_1_42 * inv));

        return trigamma;
    }

    public static double lowerIncomplete(double a, double x) {
        if (a == 0)
            return -Ei.expint(-x);
        return regularizedGammaP(a, x) * gamma(a);
    }

    public static double upperIncomplete(double a, double x) {
        return regularizedGammaQ(a, x) * gamma(a);
    }

    private static double regularizedGammaQ(double a, double x) {
        if (a <= 0.0)
            throw new IllegalArgumentException("Invalid arguments in routine gammq");
        return 1.0 - regularizedGammaP(a, x);
    }

    private static double regularizedGammaP(double a, double x) {
        if (a <= 0.0)
            throw new IllegalArgumentException("Invalid arguments in routine gammp");
        if (x < (a + 1.0)) {
            return gser(a, x);
        } else {
            return 1.0 - gcf(a, x);
        }
    }

    private static double gser(double a, double x) {
        double gln = loggamma(a);
        if (x <= 0.0) {
            if (x < 0.0)
                throw new IllegalArgumentException("x < 0 in routine gser");
            return 0.0;
        } else {
            double ap = a;
            double del, sum;
            del = sum = 1.0 / a;
            for (int n = 1; n <= 100; n++) {
                ++ap;
                del *= x / ap;
                sum += del;
                if (Math.abs(del) < Math.abs(sum) * 3.0e-7) {
                    return sum * Math.exp(-x + a * Math.log(x) - gln);
                }
            }
            throw new IllegalArgumentException("a too large, ITMAX too small in routine gser");
        }
    }

    private static double gcf(double a, double x) {
        double an, del;
        int i;
        double b = x + 1.0 - a;
        double c = Double.MAX_VALUE;
        double d = 1.0 / b;
        double h = d;
        for (i = 1; i <= 100; i++) {
            an = -i * (i - a);
            b += 2.0;
            d = an * d + b;
            if (Math.abs(d) < Double.MIN_VALUE)
                d = Double.MIN_VALUE;
            c = b + an / c;
            if (Math.abs(c) < Double.MIN_VALUE)
                c = Double.MIN_VALUE;
            d = 1.0 / d;
            del = d * c;
            h *= del;
            if (Math.abs(del - 1.0) < 3.0e-7)
                break;
        }
        if (i > 100)
            throw new IllegalArgumentException("a too large, ITMAX too small in gcf");
        double gln = loggamma(a);
        return Math.exp(-x + a * Math.log(x) - gln) * h;
    }

    public static double factorial(int n) {
        if (n < 0) {
            throw new ArithmeticException("Domain error");
        }
        if (n > 170) {
            return Double.POSITIVE_INFINITY;
        }
        return factorialTab[n];
    }
}
