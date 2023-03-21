package rocks.palaiologos.maja;

class Ei {
    private static double __expint_E1_series(double __x) {
        double __term = 1.0;
        double __esum = 0.0;
        double __osum = 0.0;
        final int __max_iter = 1000;
        for (int __i = 1; __i < __max_iter; ++__i) {
            __term *= -__x / __i;
            if (Math.abs(__term) < Maja.EPSILON)
                break;
            if (__term >= 0.0)
                __esum += __term / __i;
            else
                __osum += __term / __i;
        }
        return -__esum - __osum
                - Maja.EULER_GAMMA - Math.log(__x);
    }

    private static double __expint_E1_asymp(double __x) {
        double __term = 1;
        double __esum = 1;
        double __osum = 0;
        final int __max_iter = 1000;
        for (int __i = 1; __i < __max_iter; ++__i) {
            double __prev = __term;
            __term *= -__i / __x;
            if (Math.abs(__term) > Math.abs(__prev))
                break;
            if (__term >= 0)
                __esum += __term;
            else
                __osum += __term;
        }
        return Math.exp(-__x) * (__esum + __osum) / __x;
    }

    private static double __expint_En_cont_frac(double __x) {
        final int __max_iter = 1000;
        double __b = __x + 1;
        double __c = Double.MAX_VALUE;
        double __d = 1 / __b;
        double __h = __d;
        for (int __i = 1; __i <= __max_iter; ++__i) {
            double __a = -__i * (__i);
            __b += 2;
            __d = 1 / (__a * __d + __b);
            __c = __b + __a / __c;
            final double __del = __c * __d;
            __h *= __del;
            if (Math.abs(__del - 1) < Maja.EPSILON) {
                return __h * Math.exp(-__x);
            }
        }
        throw new RuntimeException("Continued fraction failed in __expint_En_cont_frac.");
    }

    private static double __expint_Ei_series(double __x) {
        double __term = 1;
        double __sum = 0;
        final int __max_iter = 1000;
        for (int __i = 1; __i < __max_iter; ++__i) {
            __term *= __x / __i;
            __sum += __term / __i;
            if (__term < Maja.EPSILON * __sum)
                break;
        }
        return Maja.EULER_GAMMA + __sum + Math.log(__x);
    }

    private static double __expint_Ei_asymp(double __x) {
        double __term = 1;
        double __sum = 1;
        final int __max_iter = 1000;
        for (int __i = 1; __i < __max_iter; ++__i) {
            double __prev = __term;
            __term *= __i / __x;
            if (__term < Maja.EPSILON)
                break;
            if (__term >= __prev)
                break;
            __sum += __term;
        }
        return Math.exp(__x) * __sum / __x;
    }

    private static double __expint_Ei(double __x) {
        if (__x < 0)
            return -__expint_E1(-__x);
        else if (__x < -Math.log(Maja.EPSILON))
            return __expint_Ei_series(__x);
        else
            return __expint_Ei_asymp(__x);
    }

    private static double __expint_E1(double __x) {
        if (__x < 0)
            return -__expint_Ei(-__x);
        else if (__x < 1)
            return __expint_E1_series(__x);
        else if (__x < 100)  //  TODO: Find a good asymptotic switch point.
            return __expint_En_cont_frac(__x);
        else
            return __expint_E1_asymp(__x);
    }

    public static double expint(double x) {
        if (Double.isNaN(x)) {
            return Double.NaN;
        } else {
            return __expint_Ei(x);
        }
    }
}
