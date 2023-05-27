package rocks.palaiologos.maja;

import static rocks.palaiologos.maja.Maja.*;

class Landau {
    // Adapted from:
    // K. S. Kolbig and B. Schorr, "A program package for the Landau distribution", Computer Phys.Comm., 31(1984), 97-111

    public static double landau(double x, double mpv, double sigma, boolean norm) {
        if (sigma <= 0) {
            return 0;
        }
        final double[] p1 = {0.4259894875, -0.1249762550, 0.03984243700, -0.006298287635, 0.001511162253};
        final double[] q1 = {1.0, -0.3388260629, 0.09594393323, -0.01608042283, 0.003778942063};

        final double[] p2 = {0.1788541609, 0.1173957403, 0.01488850518, -0.001394989411, 0.0001283617211};
        final double[] q2 = {1.0, 0.7428795082, 0.3153932961, 0.06694219548, 0.008790609714};

        final double[] p3 = {0.1788544503, 0.09359161662, 0.006325387654, 0.00006611667319, -0.000002031049101};
        final double[] q3 = {1.0, 0.6097809921, 0.2560616665, 0.04746722384, 0.006957301675};

        final double[] p4 = {0.9874054407, 118.6723273, 849.2794360, -743.7792444, 427.0262186};
        final double[] q4 = {1.0, 106.8615961, 337.6496214, 2016.712389, 1597.063511};

        final double[] p5 = {1.003675074, 167.5702434, 4789.711289, 21217.86767, -22324.94910};
        final double[] q5 = {1.0, 156.9424537, 3745.310488, 9834.698876, 66924.28357};

        final double[] p6 = {1.000827619, 664.9143136, 62972.92665, 475554.6998, -5743609.109};
        final double[] q6 = {1.0, 651.4101098, 56974.73333, 165917.4725, -2815759.939};

        final double[] a1 = {0.04166666667, -0.01996527778, 0.02709538966};
        final double[] a2 = {-1.845568670, -4.284640743};

        final double v = (x - mpv) / sigma;
        final double u;
        final double ue;
        final double us;
        final double den;
        if (v < -5.5) {
            u = exp(v + 1.0);
            if (u < 1e-10) {
                return 0.0;
            }
            ue = exp(-1 / u);
            us = sqrt(u);
            den = 0.3989422803 * (ue / us) * (1 + (a1[0] + (a1[1] + a1[2] * u) * u) * u);
        } else if (v < -1) {
            u = exp(-v - 1);
            den = exp(-u) * sqrt(u) * (p1[0] + (p1[1] + (p1[2] + (p1[3] + p1[4] * v) * v) * v) * v)
                    / (q1[0] + (q1[1] + (q1[2] + (q1[3] + q1[4] * v) * v) * v) * v);
        } else if (v < 1) {
            den = (p2[0] + (p2[1] + (p2[2] + (p2[3] + p2[4] * v) * v) * v) * v)
                    / (q2[0] + (q2[1] + (q2[2] + (q2[3] + q2[4] * v) * v) * v) * v);
        } else if (v < 5) {
            den = (p3[0] + (p3[1] + (p3[2] + (p3[3] + p3[4] * v) * v) * v) * v)
                    / (q3[0] + (q3[1] + (q3[2] + (q3[3] + q3[4] * v) * v) * v) * v);
        } else if (v < 12) {
            u = 1 / v;
            den = u * u * (p4[0] + (p4[1] + (p4[2] + (p4[3] + p4[4] * u) * u) * u) * u)
                    / (q4[0] + (q4[1] + (q4[2] + (q4[3] + q4[4] * u) * u) * u) * u);
        } else if (v < 50) {
            u = 1 / v;
            den = u * u * (p5[0] + (p5[1] + (p5[2] + (p5[3] + p5[4] * u) * u) * u) * u)
                    / (q5[0] + (q5[1] + (q5[2] + (q5[3] + q5[4] * u) * u) * u) * u);
        } else if (v < 300) {
            u = 1 / v;
            den = u * u * (p6[0] + (p6[1] + (p6[2] + (p6[3] + p6[4] * u) * u) * u) * u)
                    / (q6[0] + (q6[1] + (q6[2] + (q6[3] + q6[4] * u) * u) * u) * u);
        } else {
            u = 1 / (v - v * log(v) / (v + 1));
            den = u * u * (1 + (a2[0] + a2[1] * u) * u);
        }

        if (!norm) {
            return den;
        }

        return den / sigma;
    }

    public static double landauI(double x) {
        final double[] p1 = {0.2514091491e+0, -0.6250580444e-1, 0.1458381230e-1, -0.2108817737e-2, 0.7411247290e-3};
        final double[] q1 = {1.0, -0.5571175625e-2, 0.6225310236e-1, -0.3137378427e-2, 0.1931496439e-2};

        final double[] p2 = {0.2868328584e+0, 0.3564363231e+0, 0.1523518695e+0, 0.2251304883e-1};
        final double[] q2 = {1.0, 0.6191136137e+0, 0.1720721448e+0, 0.2278594771e-1};

        final double[] p3 = {0.2868329066e+0, 0.3003828436e+0, 0.9950951941e-1, 0.8733827185e-2};
        final double[] q3 = {1.0, 0.4237190502e+0, 0.1095631512e+0, 0.8693851567e-2};

        final double[] p4 = {0.1000351630e+1, 0.4503592498e+1, 0.1085883880e+2, 0.7536052269e+1};
        final double[] q4 = {1.0, 0.5539969678e+1, 0.1933581111e+2, 0.2721321508e+2};

        final double[] p5 = {0.1000006517e+1, 0.4909414111e+2, 0.8505544753e+2, 0.1532153455e+3};
        final double[] q5 = {1.0, 0.5009928881e+2, 0.1399819104e+3, 0.4200002909e+3};

        final double[] p6 = {0.1000000983e+1, 0.1329868456e+3, 0.9162149244e+3, -0.9605054274e+3};
        final double[] q6 = {1.0, 0.1339887843e+3, 0.1055990413e+4, 0.5532224619e+3};

        final double[] a1 = {0, -0.4583333333e+0, 0.6675347222e+0, -0.1641741416e+1};

        final double[] a2 = {0, 1.0, -0.4227843351e+0, -0.2043403138e+1};

        final double u;
        final double v;
        final double lan;
        v = x;
        if (v < -5.5) {
            u = exp(v + 1);
            lan = 0.3989422803 * exp(-1. / u) * sqrt(u) * (1 + (a1[1] + (a1[2] + a1[3] * u) * u) * u);
        } else if (v < -1) {
            u = exp(-v - 1);
            lan = exp(-u) / sqrt(u) * (p1[0] + (p1[1] + (p1[2] + (p1[3] + p1[4] * v) * v) * v) * v)
                    / (q1[0] + (q1[1] + (q1[2] + (q1[3] + q1[4] * v) * v) * v) * v);
        } else if (v < 1) {
            lan = (p2[0] + (p2[1] + (p2[2] + p2[3] * v) * v) * v) / (q2[0] + (q2[1] + (q2[2] + q2[3] * v) * v) * v);
        } else if (v < 4) {
            lan = (p3[0] + (p3[1] + (p3[2] + p3[3] * v) * v) * v) / (q3[0] + (q3[1] + (q3[2] + q3[3] * v) * v) * v);
        } else if (v < 12) {
            u = 1. / v;
            lan = (p4[0] + (p4[1] + (p4[2] + p4[3] * u) * u) * u) / (q4[0] + (q4[1] + (q4[2] + q4[3] * u) * u) * u);
        } else if (v < 50) {
            u = 1. / v;
            lan = (p5[0] + (p5[1] + (p5[2] + p5[3] * u) * u) * u) / (q5[0] + (q5[1] + (q5[2] + q5[3] * u) * u) * u);
        } else if (v < 300) {
            u = 1. / v;
            lan = (p6[0] + (p6[1] + (p6[2] + p6[3] * u) * u) * u) / (q6[0] + (q6[1] + (q6[2] + q6[3] * u) * u) * u);
        } else {
            u = 1. / (v - v * log(v) / (v + 1));
            lan = 1 - (a2[1] + (a2[2] + a2[3] * u) * u) * u;
        }
        return lan;
    }

    /**
     * Computes quantiles for standard normal distribution N(0, 1) at probability p.
     * Adapted from ALGORITHM AS241 APPL. STATIST. (1988) VOL. 37, NO. 3, 477-484.
     */
    public static double normQuantile(double p) {
        if (p <= 0 || p >= 1)
            throw new ArithmeticException("probability outside (0, 1)");

        final double a0 = 3.3871328727963666080e0;
        final double a1 = 1.3314166789178437745e+2;
        final double a2 = 1.9715909503065514427e+3;
        final double a3 = 1.3731693765509461125e+4;
        final double a4 = 4.5921953931549871457e+4;
        final double a5 = 6.7265770927008700853e+4;
        final double a6 = 3.3430575583588128105e+4;
        final double a7 = 2.5090809287301226727e+3;
        final double b1 = 4.2313330701600911252e+1;
        final double b2 = 6.8718700749205790830e+2;
        final double b3 = 5.3941960214247511077e+3;
        final double b4 = 2.1213794301586595867e+4;
        final double b5 = 3.9307895800092710610e+4;
        final double b6 = 2.8729085735721942674e+4;
        final double b7 = 5.2264952788528545610e+3;
        final double c0 = 1.42343711074968357734e0;
        final double c1 = 4.63033784615654529590e0;
        final double c2 = 5.76949722146069140550e0;
        final double c3 = 3.64784832476320460504e0;
        final double c4 = 1.27045825245236838258e0;
        final double c5 = 2.41780725177450611770e-1;
        final double c6 = 2.27238449892691845833e-2;
        final double c7 = 7.74545014278341407640e-4;
        final double d1 = 2.05319162663775882187e0;
        final double d2 = 1.67638483018380384940e0;
        final double d3 = 6.89767334985100004550e-1;
        final double d4 = 1.48103976427480074590e-1;
        final double d5 = 1.51986665636164571966e-2;
        final double d6 = 5.47593808499534494600e-4;
        final double d7 = 1.05075007164441684324e-9;
        final double e0 = 6.65790464350110377720e0;
        final double e1 = 5.46378491116411436990e0;
        final double e2 = 1.78482653991729133580e0;
        final double e3 = 2.96560571828504891230e-1;
        final double e4 = 2.65321895265761230930e-2;
        final double e5 = 1.24266094738807843860e-3;
        final double e6 = 2.71155556874348757815e-5;
        final double e7 = 2.01033439929228813265e-7;
        final double f1 = 5.99832206555887937690e-1;
        final double f2 = 1.36929880922735805310e-1;
        final double f3 = 1.48753612908506148525e-2;
        final double f4 = 7.86869131145613259100e-4;
        final double f5 = 1.84631831751005468180e-5;
        final double f6 = 1.42151175831644588870e-7;
        final double f7 = 2.04426310338993978564e-15;

        final double split1 = 0.425;
        final double split2 = 5.;
        final double konst1 = 0.180625;
        final double konst2 = 1.6;

        final double q;
        double r;
        double quantile;
        q = p - 0.5;
        if (abs(q) < split1) {
            r = konst1 - q * q;
            quantile = q * (((((((a7 * r + a6) * r + a5) * r + a4) * r + a3) * r + a2) * r + a1) * r + a0)
                    / (((((((b7 * r + b6) * r + b5) * r + b4) * r + b3) * r + b2) * r + b1) * r + 1.);
        } else {
            if (q < 0) {
                r = p;
            } else {
                r = 1 - p;
            }
            // error case
            if (r <= 0) {
                quantile = 0;
            } else {
                r = sqrt(-log(r));
                if (r <= split2) {
                    r -= konst2;
                    quantile = (((((((c7 * r + c6) * r + c5) * r + c4) * r + c3) * r + c2) * r + c1) * r + c0)
                            / (((((((d7 * r + d6) * r + d5) * r + d4) * r + d3) * r + d2) * r + d1) * r + 1);
                } else {
                    r -= split2;
                    quantile = (((((((e7 * r + e6) * r + e5) * r + e4) * r + e3) * r + e2) * r + e1) * r + e0)
                            / (((((((f7 * r + f6) * r + f5) * r + f4) * r + f3) * r + f2) * r + f1) * r + 1);
                }
                if (q < 0) {
                    quantile = -quantile;
                }
            }
        }
        return quantile;
    }


    /**
     * Evaluate the quantiles of the chi-squared probability distribution function.
     * Adapted from algorithm AS 91 Appl. Statist. (1975) Vol.24, P.35 implemented by Anna Kreshuk.
     * Incorporates the suggested changes in AS R85 (vol.40(1), pp.233-5, 1991)
     */
    public static double chisquareQuantile(double p, double ndf) {
        if (ndf <= 0) {
            return 0;
        }
        final double[] c = {0, 0.01, 0.222222, 0.32, 0.4, 1.24, 2.2, 4.67, 6.66, 6.73, 13.32, 60.0, 70.0, 84.0, 105.0, 120.0,
                127.0, 140.0, 175.0, 210.0, 252.0, 264.0, 294.0, 346.0, 420.0, 462.0, 606.0, 672.0, 707.0, 735.0, 889.0,
                932.0, 966.0, 1141.0, 1182.0, 1278.0, 1740.0, 2520.0, 5040.0};
        final double e = 5e-7;
        final double aa = 0.6931471806;
        double ch;
        double p1;
        double p2;
        double q;
        double t;
        double a;
        final double x;
        final double g = loggamma(0.5 * ndf);

        final double xx = 0.5 * ndf;
        final double cp = xx - 1;
        if (ndf >= log(p) * -c[5]) {
            // starting approximation for ndf less than or equal to 0.32
            if (ndf > c[3]) {
                x = normQuantile(p);
                // starting approximation using Wilson and Hilferty estimate
                p1 = c[2] / ndf;
                ch = ndf * pow(x * sqrt(p1) + 1 - p1, 3);
                if (ch > c[6] * ndf + 6) {
                    ch = -2 * (log(1 - p) - cp * log(0.5 * ch) + g);
                }
            } else {
                ch = c[4];
                a = log(1 - p);
                do {
                    q = ch;
                    p1 = 1 + ch * (c[7] + ch);
                    p2 = ch * (c[9] + ch * (c[8] + ch));
                    t = -0.5 + (c[7] + 2 * ch) / p1 - (c[9] + ch * (c[10] + 3 * ch)) / p2;
                    ch = ch - (1 - exp(a + g + 0.5 * ch + cp * aa) * p2 / p1) / t;
                } while (abs(q / ch - 1) > c[1]);
            }
        } else {
            ch = pow(p * xx * exp(g + xx * aa), 1. / xx);
            if (ch < e) {
                return ch;
            }
        }

        final int maxit = 20;
        double b;
        double s1;
        double s2;
        double s3;
        double s4;
        double s5;
        double s6;
        for (int i = 0; i < maxit; i++) {
            q = ch;
            p1 = 0.5 * ch;
            p2 = p - gammaP(xx, p1);

            t = p2 * exp(xx * aa + g + p1 - cp * log(ch));
            b = t / ch;
            a = 0.5 * t - b * cp;
            s1 = (c[19] + a * (c[17] + a * (c[14] + a * (c[13] + a * (c[12] + c[11] * a))))) / c[24];
            s2 = (c[24] + a * (c[29] + a * (c[32] + a * (c[33] + c[35] * a)))) / c[37];
            s3 = (c[19] + a * (c[25] + a * (c[28] + c[31] * a))) / c[37];
            s4 = (c[20] + a * (c[27] + c[34] * a) + cp * (c[22] + a * (c[30] + c[36] * a))) / c[38];
            s5 = (c[13] + c[21] * a + cp * (c[18] + c[26] * a)) / c[37];
            s6 = (c[15] + cp * (c[23] + c[16] * cp)) / c[38];
            ch = ch + t * (1 + 0.5 * t * s1 - b * cp * (s1 - b * (s2 - b * (s3 - b * (s4 - b * (s5 - b * s6))))));
            if (abs(q / ch - 1) > e) {
                break;
            }
        }
        return ch;
    }
}
