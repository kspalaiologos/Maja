package rocks.palaiologos.maja;

import static rocks.palaiologos.maja.Maja.*;

class Airy {
    private final static double[] APGN = {
        -3.55615429033082288335E-2,  -6.37311518129435504426E-1,
        -1.70856738884312371053E0,   -1.50221872117316635393E0,
        -5.63606665822102676611E-1,  -1.02101031120216891789E-1,
        -9.48396695961445269093E-3,  -4.60325307486780994357E-4,
        -1.14300836484517375919E-5,  -1.33415518685547420648E-7,
        -5.63803833958893494476E-10
    };

    private final static double[] APGD = {
        9.85865801696130355144E0,  2.16401867356585941885E1,
        1.73130776389749389525E1,  6.17872175280828766327E0,
        1.08848694396321495475E0,  9.95005543440888479402E-2,
        4.78468199683886610842E-3, 1.18159633322838625562E-4,
        1.37480673554219441465E-6, 5.79912514929147598821E-9
    };

    private final static double[] AN = {
        3.46538101525629032477E-1, 1.20075952739645805542E1,
        7.62796053615234516538E1,  1.68089224934630576269E2,
        1.59756391350164413639E2,  7.05360906840444183113E1,
        1.40264691163389668864E1,  9.99999999999999995305E-1,
    };

    private final static double[] AD = {
        5.67594532638770212846E-1, 1.47562562584847203173E1,
        8.45138970141474626562E1,  1.77318088145400459522E2,
        1.64234692871529701831E2,  7.14778400825575695274E1,
        1.40959135607834029598E1,  1.00000000000000000470E0,
    };

    private final static double[] AFN = {
        -1.31696323418331795333E-1, -6.26456544431912369773E-1,
        -6.93158036036933542233E-1, -2.79779981545119124951E-1,
        -4.91900132609500318020E-2, -4.06265923594885404393E-3,
        -1.59276496239262096340E-4, -2.77649108155232920844E-6,
        -1.67787698489114633780E-8
    };

    private final static double[] AFD = {
        1.33560420706553243746E1,  3.26825032795224613948E1,
        2.67367040941499554804E1,  9.18707402907259625840E0,
        1.47529146771666414581E0,  1.15687173795188044134E-1,
        4.40291641615211203805E-3, 7.54720348287414296618E-5,
        4.51850092970580378464E-7
    };

    private final static double[] AGN = {
        1.97339932091685679179E-2,  3.91103029615688277255E-1,
        1.06579897599595591108E0,   9.39169229816650230044E-1,
        3.51465656105547619242E-1,  6.33888919628925490927E-2,
        5.85804113048388458567E-3,  2.82851600836737019778E-4,
        6.98793669997260967291E-6,  8.11789239554389293311E-8,
        3.41551784765923618484E-10
    };
    private final static double[] AGD = {
        9.30892908077441974853E0,  1.98352928718312140417E1,
        1.55646628932864612953E1,  5.47686069422975497931E0,
        9.54293611618961883998E-1, 8.64580826352392193095E-2,
        4.12656523824222607191E-3, 1.01259085116509135510E-4,
        1.17166733214413521882E-6, 4.91834570062930015649E-9
    };

    private final static double[] APFN = {
        1.85365624022535566142E-1, 8.86712188052584095637E-1,
        9.87391981747398547272E-1, 4.01241082318003734092E-1,
        7.10304926289631174579E-2, 5.90618657995661810071E-3,
        2.33051409401776799569E-4, 4.08718778289035454598E-6,
        2.48379932900442457853E-8
    };

    private final static double[] APFD = {
        1.47345854687502542552E1,  3.75423933435489594466E1,
        3.14657751203046424330E1,  1.09969125207298778536E1,
        1.78885054766999417817E0,  1.41733275753662636873E-1,
        5.44066067017226003627E-3, 9.39421290654511171663E-5,
        5.65978713036027009243E-7
    };

    private final static double[] APN = {
        6.13759184814035759225E-1, 1.47454670787755323881E1,
        8.20584123476060982430E1,  1.71184781360976385540E2,
        1.59317847137141783523E2,  6.99778599330103016170E1,
        1.39470856980481566958E1,  1.00000000000000000550E0
    };

    private final static double[] APD = {
        3.34203677749736953049E-1, 1.11810297306158156705E1,
        7.11727352147859965283E1,  1.58778084372838313640E2,
        1.53206427475809220834E2,  6.86752304592780337944E1,
        1.38498634758259442477E1,  9.99999999999999994502E-1
    };

    private final static double[] BN16 = {
        -2.53240795869364152689E-1, 5.75285167332467384228E-1,
        -3.29907036873225371650E-1, 6.44404068948199951727E-2,
        -3.82519546641336734394E-3
    };

    private final static double[] BD16 = {
        -7.15685095054035237902E0,  1.06039580715664694291E1,
        -5.23246636471251500874E0,  9.57395864378383833152E-1,
        -5.50828147163549611107E-2
    };

    private final static double[] BPPN = {
        4.65461162774651610328E-1, -1.08992173800493920734E0,
        6.38800117371827987759E-1, -1.26844349553102907034E-1,
        7.62487844342109852105E-3,
    };

    private final static double[] BPPD = {
        -8.70622787633159124240E0,  1.38993162704553213172E1,
        -7.14116144616431159572E0,  1.34008595960680518666E0,
        -7.84273211323341930448E-2,
    };

    public static double[] airy(double x) {
        if(Double.isNaN(x))
            return new double[] { Double.NaN, Double.NaN, Double.NaN, Double.NaN };
        if(x == Double.POSITIVE_INFINITY)
            return new double[] { 0, 0, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY };
        if(x == Double.NEGATIVE_INFINITY)
            return new double[] { 0, Double.NaN, 0, Double.NaN };

        double z, zz, t, f, g, uf, ug, k, zeta, theta;
        int domflg;
        double ai = Double.NaN, aip = Double.NaN, bi = Double.NaN, bip = Double.NaN;

        domflg = 0;
        if (x > 103.892) {
            return new double[] { 0, 0, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY };
        }

        if (x < -2.09) {
            domflg = 15;
            t = sqrt(-x);
            zeta = -2.0 * x * t / 3.0;
            t = sqrt(t);
            k = 5.64189583547756286948E-1 / t;
            z = 1.0 / zeta;
            zz = z * z;
            uf = 1.0 + zz * Spence.polevl(zz, AFN, 8) / Spence.p1evl(zz, AFD, 9);
            ug = z * Spence.polevl(zz, AGN, 10) / Spence.p1evl(zz, AGD, 10);
            theta = zeta + 0.25 * PI;
            f = sin(theta);
            g = cos(theta);
            ai = k * (f * uf - g * ug);
            bi = k * (g * uf + f * ug);
            uf = 1.0 + zz * Spence.polevl(zz, APFN, 8) / Spence.p1evl(zz, APFD, 9);
            ug = z * Spence.polevl(zz, APGN, 10) / Spence.p1evl(zz, APGD, 10);
            k = 5.64189583547756286948E-1 * t;
            aip = -k * (g * uf + f * ug);
            bip = k * (f * uf - g * ug);
            return new double[] { ai, aip, bi, bip };
        }

        if (x >= 2.09) {
            domflg = 5;
            t = sqrt(x);
            zeta = 2.0 * x * t / 3.0;
            g = exp(zeta);
            t = sqrt(t);
            k = 2.0 * t * g;
            z = 1.0 / zeta;
            f = Spence.polevl(z, AN, 7) / Spence.polevl(z, AD, 7);
            ai = 5.64189583547756286948E-1 * f / k;
            k = -0.5 * 5.64189583547756286948E-1 * t / g;
            f = Spence.polevl(z, APN, 7) / Spence.polevl(z, APD, 7);
            aip = f * k;

            if (x > 8.3203353) /* zeta > 16 */
            {
                f = z * Spence.polevl(z, BN16, 4) / Spence.p1evl(z, BD16, 5);
                k = 5.64189583547756286948E-1 * g;
                bi = k * (1.0 + f) / t;
                f = z * Spence.polevl(z, BPPN, 4) / Spence.p1evl(z, BPPD, 5);
                bip = k * t * (1.0 + f);
                return new double[] { ai, aip, bi, bip };
            }
        }

        f = 1.0;
        g = x;
        t = 1.0;
        uf = 1.0;
        ug = x;
        k = 1.0;
        z = x * x * x;
        while (t > EPSILON) {
            uf *= z;
            k += 1.0;
            uf /= k;
            ug *= z;
            k += 1.0;
            ug /= k;
            uf /= k;
            f += uf;
            k += 1.0;
            ug /= k;
            g += ug;
            t = abs(uf / f);
        }
        uf = 0.35502805388781723926 * f;
        ug = 0.258819403792806798405 * g;
        if ((domflg & 1) == 0)
            ai = uf - ug;
        if ((domflg & 2) == 0)
            bi = 1.732050807568877293527 * (uf + ug);

        /* the deriviative of ai */
        k = 4.0;
        uf = x * x / 2.0;
        ug = z / 3.0;
        f = uf;
        g = 1.0 + ug;
        uf /= 3.0;
        t = 1.0;

        while (t > EPSILON) {
            uf *= z;
            ug /= k;
            k += 1.0;
            ug *= z;
            uf /= k;
            f += uf;
            k += 1.0;
            ug /= k;
            uf /= k;
            g += ug;
            k += 1.0;
            t = abs(ug / g);
        }

        uf = 0.35502805388781723926 * f;
        ug = 0.258819403792806798405 * g;
        if ((domflg & 4) == 0)
            aip = uf - ug;
        if ((domflg & 8) == 0)
            bip = 1.732050807568877293527 * (uf + ug);
        return new double[] { ai, aip, bi, bip };
    }

    public static Complex[] airy(Complex x) {
        if(Complex.isNaN(x))
            return new Complex[] { Complex.NaN, Complex.NaN, Complex.NaN, Complex.NaN };
        if(Complex.isInfinite(x))
            return new Complex[] { Complex.ZERO, Complex.ZERO, Complex.COMPLEX_INFINITY, Complex.COMPLEX_INFINITY };

        Complex z, zz, t, f, g, uf, ug, k, zeta, theta;
        int domflg;
        Complex ai = Complex.NaN, aip = Complex.NaN, bi = Complex.NaN, bip = Complex.NaN;

        domflg = 0;
        if (x.re() > 103.892) {
            return new Complex[] { Complex.ZERO, Complex.ZERO, Complex.COMPLEX_INFINITY, Complex.COMPLEX_INFINITY };
        }

        if (x.re() < -2.09) {
            domflg = 15;
            t = sqrt(negate(x));
            zeta = div(mul(mul(-2.0, x), t), 3.0);
            t = sqrt(t);
            k = div(5.64189583547756286948E-1, t);
            z = div(1.0, zeta);
            zz = mul(z, z);
            uf = div(add(1, mul(zz, Spence.polevl(zz, AFN, 8))), Spence.p1evl(zz, AFD, 9));
            ug = div(mul(z, Spence.polevl(zz, AGN, 10)), Spence.p1evl(zz, AGD, 10));
            theta = add(zeta, PI_4);
            f = sin(theta);
            g = cos(theta);
            ai = mul(k, sub(mul(f, uf), mul(g, ug)));
            bi = mul(k, add(mul(g, uf), mul(f, ug)));
            uf = div(add(1, mul(zz, Spence.polevl(zz, APFN, 8))), Spence.p1evl(zz, APFD, 9));
            ug = div(mul(z, Spence.polevl(zz, APGN, 10)), Spence.p1evl(zz, APGD, 10));
            k = mul(5.64189583547756286948E-1, t);
            aip = mul(negate(k), add(mul(g, uf), mul(f, ug)));
            bip = mul(k, sub(mul(f, uf), mul(g, ug)));
            return new Complex[] { ai, aip, bi, bip };
        }

        if (x.re() >= 2.09) {
            domflg = 5;
            t = sqrt(x);
            zeta = div(mul(mul(2.0, x), t), 3.0);
            g = exp(zeta);
            t = sqrt(t);
            k = mul(mul(2, t), g);
            z = div(1, zeta);
            f = div(Spence.polevl(z, AN, 7), Spence.polevl(z, AD, 7));
            ai = div(mul(5.64189583547756286948E-1, f), k);
            k = div(mul(-0.282094791773878143474, t), g);
            f = div(Spence.polevl(z, APN, 7), Spence.polevl(z, APD, 7));
            aip = mul(f, k);

            if (x.re() > 8.3203353) /* zeta > 16 */
            {
                f = div(mul(z, Spence.polevl(z, BN16, 4)), Spence.p1evl(z, BD16, 5));
                k = mul(5.64189583547756286948E-1, g);
                bi = div(mul(k, add(1, f)), t);
                f = div(mul(z, Spence.polevl(z, BPPN, 4)), Spence.p1evl(z, BPPD, 5));
                bip = mul(mul(k, t), add(1, f));
                return new Complex[] { ai, aip, bi, bip };
            }
        }

        f = Complex.ONE;
        g = x;
        t = Complex.ONE;
        uf = Complex.ONE;
        ug = x;
        k = Complex.ONE;
        z = mul(x, mul(x, x));
        while (t.re() > EPSILON) {
            uf = mul(uf, z);
            k = add(k, 1);
            uf = div(uf, k);
            ug = mul(ug, z);
            k = add(1, k);
            ug = div(ug, k);
            uf = div(uf, k);
            f = add(f, uf);
            k = add(1, k);
            ug = div(ug, k);
            g = add(g, ug);
            t = new Complex(abs(div(uf, f)));
        }
        uf = mul(0.35502805388781723926, f);
        ug = mul(0.258819403792806798405, g);
        if ((domflg & 1) == 0)
            ai = sub(uf, ug);
        if ((domflg & 2) == 0)
            bi = mul(1.732050807568877293527, add(uf, ug));

        /* the deriviative of ai */
        k = new Complex(4);
        uf = div(mul(x, x), 2.0);
        ug = div(z, 3.0);
        f = uf;
        g = add(1.0, ug);
        uf = div(uf, 3.0);
        t = Complex.ONE;

        while (t.re() > EPSILON) {
            uf = mul(uf, z);
            ug = div(ug, k);
            k = add(k, 1);
            ug = mul(ug, z);
            uf = div(uf, k);
            f = add(f, uf);
            k = add(k, 1);
            ug = div(ug, k);
            uf = div(uf, k);
            g = add(g, ug);
            k = add(k, 1);
            t = new Complex(abs(div(ug, g)));
        }

        uf = mul(0.35502805388781723926, f);
        ug = mul(0.258819403792806798405, g);
        if ((domflg & 4) == 0)
            aip = sub(uf, ug);
        if ((domflg & 8) == 0)
            bip = mul(1.732050807568877293527, add(uf, ug));
        return new Complex[] { ai, aip, bi, bip };
    }
}
