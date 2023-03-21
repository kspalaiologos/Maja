package palaiologos.maja;

import java.util.Random;

public class Maja {
    /**
     * The value of ln(2).
     */
    public static final double LN2 = 0.6931471805599453094172321214581765680755001343602552541206800094;
    /**
     * The value of ln(10).
     */
    public static final double LN10 = 2.3025850929940456840179914546843642076011014886287729760333279009;
    /**
     * The value of log2(e).
     */
    public static final double LOG2E = 1.4426950408889634073599246810018921374266459541529859341354494069;
    /**
     * The value of pi.
     */
    public static final double PI = 3.1415926535897932384626433832795028841971693993751058209749445923;
    /**
     * The value of pi/2.
     */
    public static final double PI_2 = 1.5707963267948966192313216916397514420985846996875529104874722961;
    /**
     * The value of pi/4.
     */
    public static final double PI_4 = 0.7853981633974483096156608458198757210492923498437764552437361480;
    /**
     * The value of 2*PI.
     */
    public static final double TWO_PI = 6.2831853071795864769252867665590057683943387987502116419498891846;
    /**
     * The value of 1/pi.
     */
    public static final double ONE_OVER_PI = 0.3183098861837906715377675267450287240689192914809128974953346881;
    /**
     * The value of e.
     */
    public static final double E = 2.7182818284590452353602874713526624977572470936999595749669676277;
    /**
     * The value of 1/e.
     */
    public static final double ONE_OVER_E = 0.3678794411714423215955237701614608674458111310317678345078368016;
    /**
     * The epsilon value for double precision numbers.
     */
    public static final double EPSILON = Math.ulp(1.0d);
    /**
     * The Euler-Mascheroni constant.
     */
    public static final double EULER_GAMMA = 0.5772156649015328606065120900824024310421593359399235988057672348;
    /**
     * The golden ratio.
     */
    public static final double GOLDEN_RATIO = 1.6180339887498948482045868343656381177203091798057628621354486227;
    /**
     * Apery's constant - zeta(3).
     */
    public static final double APERY_CONSTANT = 1.2020569031595942853997381615114499907649862923404988817922715553;
    /**
     * Glaisher-Kinkelin constant.
     */
    public static final double GLAISHER_CONSTANT = 1.2824271291006226368753425688697917277676889273250011920637400217;
    /**
     * Catalan's constant.
     */
    public static final double CATALAN_CONSTANT = 0.9159655941772190150546035149323841107741493742816721342664981196;
    /**
     * Golomb-Dickman constant.
     */
    public static final double GOLOMB_DICKMAN_CONSTANT = 0.6243299885435508709929363831008372441796426201805292869735519024;
    /**
     * Mills' constant.
     */
    public static final double MILLS_CONSTANT = 1.3063778838630806904686144926026057129167845851567136443680537599;
    /**
     * Feigenbaum constant.
     */
    public static final double FEIGENBAUM_CONSTANT = 4.6692016091029906718532038204662016172581855774757686327456513430;
    /**
     * Khinchin's constant.
     */
    public static final double KHINCHIN_CONSTANT = 2.6854520010653064453097148354817956938203822939944629530511523455;

    /**
     * Adds two double precision numbers together.
     *
     * @param x
     * @param y
     * @return x + y
     */
    public static double add(double x, double y) {
        return x + y;
    }

    /**
     * Subtracts two double precision numbers.
     *
     * @param x
     * @param y
     * @return x - y
     */
    public static double sub(double x, double y) {
        return x - y;
    }

    /**
     * Multiplies two double precision numbers.
     *
     * @param x
     * @param y
     * @return x * y
     */
    public static double mul(double x, double y) {
        return x * y;
    }

    /**
     * Divides two double precision numbers.
     *
     * @param x
     * @param y
     * @return x / y
     */
    public static double div(double x, double y) {
        return x / y;
    }

    /**
     * Returns the modulus of two double precision numbers.
     *
     * @param x
     * @param y
     * @return x mod y
     */
    public static double mod(double x, double y) {
        final double r = x % y;
        if (r != 0 && Math.signum(x) != Math.signum(y))
            return r + y;
        return r;
    }

    /**
     * Returns the remainder that results from dividing two double precision numbers.
     *
     * @param x
     * @param y
     * @return x % y
     */
    public static double rem(double x, double y) {
        return x % y;
    }

    /**
     * Returns the absolute value of a double precision number.
     *
     * @param x
     * @return |x|
     * @see java.lang.Math#abs(double)
     */
    public static double abs(double x) {
        return Math.abs(x);
    }

    /**
     * Returns the value of the inverse cosine of a double precision number.
     *
     * @param x
     * @return cos^-1(x)
     * @see java.lang.Math#acos(double)
     */
    public static double acos(double x) {
        return Math.acos(x);
    }

    /**
     * Returns the value of the inverse sine of a double precision number.
     *
     * @param x
     * @return sin^-1(x)
     * @see java.lang.Math#asin(double)
     */
    public static double asin(double x) {
        return Math.asin(x);
    }

    /**
     * Returns the value of the inverse tangent of a double precision number.
     *
     * @param x
     * @return tan^-1(x)
     * @see java.lang.Math#atan(double)
     */
    public static double atan(double x) {
        return Math.atan(x);
    }

    /**
     * Returns the value of the inverse tangent of two double precision numbers.
     *
     * @param y
     * @param x
     * @return atan2(y, x)
     * @see java.lang.Math#atan2(double, double)
     */
    public static double atan2(double y, double x) {
        return Math.atan2(y, x);
    }

    /**
     * Returns the value of the cube root of a double precision number.
     *
     * @param x
     * @return x^(1/3)
     * @see java.lang.Math#cbrt(double)
     */
    public static double cbrt(double x) {
        return Math.cbrt(x);
    }

    /**
     * Returns the value of the smallest integer greater than or equal to a double precision number.
     *
     * @param x
     * @return ceil(x)
     * @see java.lang.Math#ceil(double)
     */
    public static double ceil(double x) {
        return Math.ceil(x);
    }

    /**
     * Returns the value of the smallest integer greater than or equal to a double precision number.
     *
     * @param x
     * @return ceil(x)
     * @see java.lang.Math#ceil(double)
     */
    public static double cos(double x) {
        return Math.cos(x);
    }

    /**
     * Returns the hyperbolic cosine of a double precision number.
     *
     * @param x
     * @return cosh(x)
     * @see java.lang.Math#cosh(double)
     */
    public static double cosh(double x) {
        return Math.cosh(x);
    }

    /**
     * Returns the value of the exponential function of a double precision number.
     *
     * @param x
     * @return e^x
     * @see java.lang.Math#exp(double)
     */
    public static double exp(double x) {
        return Math.exp(x);
    }

    /**
     * Returns the value of the exponential function of a double precision number minus one.
     *
     * @param x
     * @return e^x - 1
     * @see java.lang.Math#expm1(double)
     */
    public static double expm1(double x) {
        return Math.expm1(x);
    }

    /**
     * Returns the value of the largest integer less than or equal to a double precision number.
     *
     * @param x
     * @return floor(x)
     * @see java.lang.Math#floor(double)
     */
    public static double floor(double x) {
        return Math.floor(x);
    }

    /**
     * Returns the length of the hypotenuse of a right triangle with sides of length x and y.
     *
     * @param x
     * @param y
     * @return sqrt(x ^ 2 + y ^ 2)
     * @see java.lang.Math#hypot(double, double)
     */
    public static double hypot(double x, double y) {
        return Math.hypot(x, y);
    }

    /**
     * Returns the value of the natural logarithm of a double precision number.
     *
     * @param x
     * @return ln(x)
     * @see java.lang.Math#log(double)
     */
    public static double log(double x) {
        return Math.log(x);
    }

    /**
     * Returns the value of the base 10 logarithm of a double precision number.
     *
     * @param x
     * @return log10(x)
     * @see java.lang.Math#log10(double)
     */
    public static double log10(double x) {
        return Math.log10(x);
    }

    /**
     * Returns the value of the natural logarithm of a double precision number plus one.
     *
     * @param x
     * @return ln(x + 1)
     * @see java.lang.Math#log1p(double)
     */
    public static double log1p(double x) {
        return Math.log1p(x);
    }

    /**
     * Returns the value of the base 2 logarithm of a double precision number.
     *
     * @param x
     * @return log2(x)
     */
    public static double log2(double x) {
        return Math.log(x) / LN2;
    }

    /**
     * Returns the larger of two numbers (maximum).
     *
     * @param x
     * @param y
     * @return max(x, y)
     * @see java.lang.Math#max(double, double)
     */
    public static double max(double x, double y) {
        return Math.max(x, y);
    }

    /**
     * Returns the smaller of two numbers (minimum).
     *
     * @param x
     * @param y
     * @return min(x, y)
     * @see java.lang.Math#min(double, double)
     */
    public static double min(double x, double y) {
        return Math.min(x, y);
    }

    /**
     * Returns the value of the first argument raised to the power of the second argument.
     *
     * @param x
     * @param y
     * @return x^y
     * @see java.lang.Math#pow(double, double)
     */
    public static double pow(double x, double y) {
        return Math.pow(x, y);
    }

    /**
     * Returns the sign of a double precision number.
     *
     * @param x
     * @return -1 if x < 0, 0 if x == 0, 1 if x > 0
     * @see java.lang.Math#signum(double)
     */
    public static double signum(double x) {
        return Math.signum(x);
    }

    /**
     * Returns the value of the sine of a double precision number.
     *
     * @param x
     * @return sin(x)
     * @see java.lang.Math#sin(double)
     */
    public static double sin(double x) {
        return Math.sin(x);
    }

    /**
     * Returns the hyperbolic sine of a double precision number.
     *
     * @param x
     * @return sinh(x)
     * @see java.lang.Math#sinh(double)
     */
    public static double sinh(double x) {
        return Math.sinh(x);
    }

    /**
     * Returns the square root of a double precision number.
     *
     * @param x
     * @return x^(1/2)
     * @see java.lang.Math#sqrt(double)
     */
    public static double sqrt(double x) {
        return Math.sqrt(x);
    }

    /**
     * Returns the tangent of a double precision number.
     *
     * @param x
     * @return tan(x)
     * @see java.lang.Math#tan(double)
     */
    public static double tan(double x) {
        return Math.tan(x);
    }

    /**
     * Returns the hyperbolic tangent of a double precision number.
     *
     * @param x
     * @return tanh(x)
     * @see java.lang.Math#tanh(double)
     */
    public static double tanh(double x) {
        return Math.tanh(x);
    }

    /**
     * Converts the value to degrees from radians.
     *
     * @param x
     * @return x * 180 / PI
     * @see java.lang.Math#toDegrees(double)
     */
    public static double toDegrees(double x) {
        return Math.toDegrees(x);
    }

    /**
     * Converts the value to radians from degrees.
     *
     * @param x
     * @return x * PI / 180
     * @see java.lang.Math#toRadians(double)
     */
    public static double toRadians(double x) {
        return Math.toRadians(x);
    }

    /**
     * Returns the value of the <i>unit in last place</i> (ULP) of a double precision number.
     *
     * @param x
     * @return ULP(x)
     * @see java.lang.Math#ulp(double)
     */
    public static double ulp(double x) {
        return Math.ulp(x);
    }

    /**
     * Returns the value of the fused multiply-add operation.
     *
     * @param x
     * @param y
     * @param z
     * @return x * y + z
     * @see java.lang.Math#fma(double, double, double)
     */
    public static double fma(double x, double y, double z) {
        return Math.fma(x, y, z);
    }

    /**
     * Returns the value of the double precision number adjacent to the first argument in the direction of the second argument.
     *
     * @param x
     * @param y
     * @return nextAfter(x, y)
     * @see java.lang.Math#nextAfter(double, double)
     */
    public static double nextAfter(double x, double y) {
        return Math.nextAfter(x, y);
    }

    /**
     * Returns the value of the double precision number adjacent to the first argument in the direction of positive infinity.
     *
     * @param x
     * @return nextUp(x)
     * @see java.lang.Math#nextUp(double)
     */
    public static double nextUp(double x) {
        return Math.nextUp(x);
    }

    /**
     * Returns the value of the double precision number adjacent to the first argument in the direction of negative infinity.
     *
     * @param x
     * @return nextDown(x)
     * @see java.lang.Math#nextDown(double)
     */
    public static double nextDown(double x) {
        return Math.nextDown(x);
    }

    /**
     * Returns the correctly rounded value of x*2^n.
     *
     * @param x
     * @param n
     * @return scalb(x, n)
     * @see java.lang.Math#scalb(double, int)
     */
    public static double scalb(double x, int n) {
        return Math.scalb(x, n);
    }

    /**
     * Copy the sign of the second argument to the first argument.
     *
     * @param x
     * @param y
     * @return copySign(x, y)
     * @see java.lang.Math#copySign(double, double)
     */
    public static double copySign(double x, double y) {
        return Math.copySign(x, y);
    }

    /**
     * Returns the exponent of a double precision number.
     *
     * @param x
     * @return exponent(x)
     * @see java.lang.Math#getExponent(double)
     */
    public static double getExponent(double x) {
        return Math.getExponent(x);
    }

    /**
     * Rounds a double precision number to the nearest integer.
     *
     * @param x
     * @return round(x)
     * @see java.lang.Math#round(double)
     */
    public static long round(double x) {
        return Math.round(x);
    }

    /**
     * The random number generator used by this class.
     */
    private static final Random random = new Random();

    /**
     * Returns a random double precision number in range [0, 1).
     *
     * @return random()
     */
    public static double random() {
        return Math.random();
    }

    /**
     * Returns a random double precision number in range [min, max).
     *
     * @param min
     * @param max
     * @return random() * (max - min) + min
     */
    public static double random(double min, double max) {
        return min + (max - min) * Math.random();
    }

    /**
     * Returns a random double precision number in range [0, max).
     *
     * @param max
     * @return random() * max
     */
    public static double random(double max) {
        return max * Math.random();
    }

    /**
     * Return true if x < y.
     *
     * @param x
     * @param y
     * @return x < y
     */
    public static boolean lt(double x, double y) {
        return x < y;
    }

    /**
     * Return true if x <= y.
     *
     * @param x
     * @param y
     * @return x <= y
     */
    public static boolean le(double x, double y) {
        return x <= y;
    }

    /**
     * Return true if x > y.
     *
     * @param x
     * @param y
     * @return x > y
     */
    public static boolean gt(double x, double y) {
        return x > y;
    }

    /**
     * Return true if x >= y.
     *
     * @param x
     * @param y
     * @return x >= y
     */
    public static boolean ge(double x, double y) {
        return x >= y;
    }

    /**
     * Return true if x == y.
     *
     * @param x
     * @param y
     * @return x == y
     */
    public static boolean eq(double x, double y) {
        return x == y;
    }

    /**
     * Return true if x != y.
     *
     * @param x
     * @param y
     * @return
     */
    public static boolean ne(double x, double y) {
        return x != y;
    }

    /**
     * Return true if x is approximately equal to y.
     *
     * @param x
     * @param y
     * @param tol
     * @return abs(x - y) <= tol
     */
    public static boolean eq(double x, double y, double tol) {
        return Math.abs(x - y) <= tol;
    }


    /**
     * Determine whether a number is a perfect square.
     *
     * @param x
     * @return true if x is a perfect square, false otherwise
     */
    public static boolean isPerfectSquare(long x) {
        return IsSquare.isSquare(x);
    }

    /**
     * Linearly map a value from one range to another. Input range must not be empty.
     *
     * @param inRangeStart  Input range start
     * @param inRangeEnd    Input range end
     * @param outRangeStart Output range start
     * @param outRangeEnd   Output range end
     * @param value         Value to map
     * @return Mapped value. Values outside the input range are not clamped to output range
     */
    public static double linearMap(double inRangeStart, double inRangeEnd, double outRangeStart, double outRangeEnd, double value) {
        return outRangeStart + (value - inRangeStart) * (outRangeEnd - outRangeStart) / (inRangeEnd - inRangeStart);
    }

    /**
     * Linearly normalise value from a range. Range must not be empty.
     *
     * @param rangeStart Range start normalized to 0
     * @param rangeEnd   Range end normalized to 1
     * @param value      Value to normalize
     * @return Normalized value. Values outside the range are not clamped to 0 and 1
     */
    public static double linearNorm(double rangeStart, double rangeEnd, double value) {
        return (value - rangeStart) / (rangeEnd - rangeStart);
    }

    /**
     * Linearly interpolate between two values.
     *
     * @param fromValue
     * @param toValue
     * @param progress
     * @return fromValue + (toValue - fromValue) * progress
     */
    public static double linearInterpolate(double fromValue, double toValue, double progress) {
        return fromValue + (toValue - fromValue) * progress;
    }

    /**
     * Clamp a value in the range [min, max].
     *
     * @param value
     * @param min
     * @param max
     * @return min if value < min, max if value > max, value otherwise
     */
    public static double clamp(double value, double min, double max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    /**
     * Determine if a value is a power of two.
     *
     * @param value
     * @return true if value is a power of two, false otherwise
     */
    public static boolean isPowerOfTwo(long value) {
        return value != 0 && (value & value - 1) == 0;
    }

    /**
     * Returns the next power of two.
     * If the number is already a power of two, this function acts as an identity function.
     *
     * @param value
     * @return the next power of two
     */
    public static long nextPowerOfTwo(long value) {
        if (value == 0) return 1;
        value--;
        value |= value >> 1;
        value |= value >> 2;
        value |= value >> 4;
        value |= value >> 8;
        value |= value >> 16;
        return value + 1;
    }

    /**
     * Return a random sign (-1 or 1).
     *
     * @return -1 or 1
     */
    public static long randomSign() {
        return random.nextBoolean() ? 1 : -1;
    }

    /**
     * Return a cached value of sin(x) using Raven's method. The cache is 65KB large.
     *
     * @param x angle in radians, between -2*pi and 2*pi inclusive.
     * @return sin(x)
     */
    public static float fastSin(float x) {
        return FastTrigonometry.sin(x);
    }

    /**
     * Return a cached value of cos(x) using Raven's method. The cache is 65KB large.
     *
     * @param x angle in radians, between -2*pi and 2*pi inclusive.
     * @return cos(x)
     */
    public static float fastCos(float x) {
        return FastTrigonometry.cos(x);
    }

    /**
     * Return the integer cube root of a number.
     * If x < 0, -cbrt(-x) is returned.
     *
     * @param x
     * @return floor(cbrt(x))
     */
    public static int icbrt(int x) {
        long s, y = 0, b, y2 = 0;

        if (x < 0) return -icbrt(-x);

        for (s = 30; s >= 0; s = s - 3) {
            y2 = 4 * y2;
            y = 2 * y;
            b = (3 * (y2 + y) + 1) << s;
            if (x >= b) {
                x = (int) (x - b);
                y2 = y2 + 2 * y + 1;
                y = y + 1;
            }
        }

        return (int) y;
    }

    /**
     * Return the long integer cube root of a number.
     * If x < 0, -cbrt(-x) is returned.
     *
     * @param x
     * @return floor(cbrt(x))
     */
    public static long icbrt(long x) {
        long s, y = 0, b, y2 = 0;

        if (x < 0) return -icbrt(-x);

        for (s = 60; s >= 0; s = s - 3) {
            y2 = 4 * y2;
            y = 2 * y;
            b = (3 * (y2 + y) + 1) << s;
            if (x >= b) {
                x = x - b;
                y2 = y2 + 2 * y + 1;
                y = y + 1;
            }
        }

        return y;
    }

    /**
     * Return the short integer cube root of a number.
     * If a < 0, -cbrt(-a) is returned.
     *
     * @param a
     * @return floor(cbrt(a))
     */
    public static short icbrt(short a) {
        long s, y = 0, b, y2 = 0, x = a;

        if (x < 0) return (short) -icbrt(-x);

        for (s = 15; s >= 0; s = s - 3) {
            y2 = 4 * y2;
            y = 2 * y;
            b = (3 * (y2 + y) + 1) << s;
            if (x >= b) {
                x = x - b;
                y2 = y2 + 2 * y + 1;
                y = y + 1;
            }
        }

        return (short) y;
    }

    /**
     * Compute the integer square root of a number.
     * If x < 0, -isqrt(-x) is returned.
     *
     * @param x
     * @return floor(sqrt(x))
     */
    public static int isqrt(int x) {
        long m = 0x40000000, y = 0, b, t;

        if (x < 0) return -isqrt(-x);

        while (m != 0) {
            b = y | m;
            y = y >> 1;
            t = (int) (x | ~(x - b)) >> 31;
            x = (int) (x - (b & t));
            y = y | (m & t);
            m = m >> 2;
        }

        return (int) y;
    }

    /**
     * Compute the integer square root of a number.
     * If x < 0, -isqrt(-x) is returned.
     *
     * @param x
     * @return floor(sqrt(x))
     */
    public static int isqrt(long x) {
        long m = 0x4000000000000000L, y = 0, b, t;

        if (x < 0) return -isqrt(-x);

        while (m != 0) {
            b = y | m;
            y = y >> 1;
            t = (int) (x | ~(x - b)) >> 31;
            x = (int) (x - (b & t));
            y = y | (m & t);
            m = m >> 2;
        }

        return (int) y;
    }

    /**
     * Compute the integer square root of a number.
     * If x < 0, -isqrt(-x) is returned.
     *
     * @param x
     * @return floor(sqrt(x))
     */
    public static int isqrt(short x) {
        long m = 0x4000, y = 0, b, t;

        if (x < 0) return -isqrt(-x);

        while (m != 0) {
            b = y | m;
            y = y >> 1;
            t = (int) (x | ~(x - b)) >> 31;
            x = (short) (x - (b & t));
            y = y | (m & t);
            m = m >> 2;
        }

        return (int) y;
    }

    /**
     * Compute the value of the integer logarithm in base 10 of a number
     * @param x
     * @return floor(log10(x))
     */
    public static int ilog10(int x) {
        int y;
        final int[] table = {0, 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, 0xFFFFFFFF};
        y = (19 * (31 - Integer.numberOfLeadingZeros(x))) >> 6;
        y = y + ((table[y + 1] - x) >>> 31);
        return y;
    }

    /**
     * Compute the value of integer x^n.
     * @param x
     * @param n
     * @return x^n.
     */
    public static int iexp(int x, int n) {
        int p = x, y = 1;
        while(true) {
            if ((n & 1) != 0)
                y = p * y;
            n = n >> 1;
            if (n == 0)
                return y;
            p = p * p;
        }
    }

    /**
     * Compute the value of integer x^n.
     * @param x
     * @param n
     * @return x^n.
     */
    public static long iexp(long x, long n) {
        long p = x, y = 1;
        while(true) {
            if ((n & 1) != 0)
                y = p * y;
            n = n >> 1;
            if (n == 0)
                return y;
            p = p * p;
        }
    }

    /**
     * Compute the value of integer x^n.
     * @param x
     * @param n
     * @return x^n.
     */
    public static short iexp(short x, short n) {
        int p = x, y = 1;
        while(true) {
            if ((n & 1) != 0)
                y = p * y;
            n >>= 1;
            if (n == 0)
                return (short) y;
            p = p * p;
        }
    }

    /**
     * Compute the value of x^z where x is a double precision
     * floating point number and z is an integer.
     * @param x
     * @param z
     * @return x^z.
     */
    public static double pow(double x, int z) {
        int n, e, sign, asign, lx;
        double w, y, s;
        if (x == 0.0) {
            if (z == 0)
                return (1.0);
            else if (z < 0)
                return Double.POSITIVE_INFINITY;
            else
                return (0.0);
        }
        if (z == 0)
            return (1.0);
        if (x < 0.0) {
            asign = -1;
            x = -x;
        } else
            asign = 0;
        if (z < 0) {
            sign = -1;
            n = -z;
        } else {
            sign = 1;
            n = z;
        }

        if (Double.isNaN(x) || x + x == x || Double.isInfinite(x)) {
            lx = 0;
            s = x;
        } else {
            long bits = Double.doubleToLongBits(x);
            boolean neg = (bits < 0);
            int exponent = (int) ((bits >> 52) & 0x7ffL);
            long mantissa = bits & 0xfffffffffffffL;

            if (exponent == 0) {
                exponent++;
            } else {
                mantissa = mantissa | (1L << 52);
            }

            exponent -= 1075;
            double realMant = mantissa;

            while (realMant > 1.0) {
                mantissa >>= 1;
                realMant /= 2.;
                exponent++;
            }

            if (neg) {
                realMant = realMant * -1;
            }

            lx = exponent;
            s = realMant;
        }

        e = (lx - 1) * n;
        if ((e == 0) || (e > 64) || (e < -64)) {
            s = (s - 7.0710678118654752e-1) / (s + 7.0710678118654752e-1);
            s = (2.9142135623730950 * s - 0.5 + lx) * z * 1.4426950408889634073599;
        } else {
            s = 1.4426950408889634073599 * e;
        }

        if (s > 7.09782712893383996843E2) {
            throw new ArithmeticException("pow: overflow");
        }
        if (s < -7.09782712893383996843E2)
            return (0.0);
        if ((n & 1) != 0)
            y = x;
        else {
            y = 1.0;
            asign = 0;
        }
        w = x;
        n >>= 1;
        while (n != 0) {
            w = w * w;
            if ((n & 1) != 0)
                y *= w;
            n >>= 1;
        }
        if (asign != 0)
            y = -y;
        if (sign < 0)
            y = 1.0 / y;
        return (y);
    }

    /**
     * Map the specified monadic function over an array.
     * @param f
     * @param tab
     * @return the result of the mapping
     */
    public static double[] map(MonadicFunction f, double[] tab) {
        double[] res = new double[tab.length];
        for (int i = 0; i < tab.length; i++) {
            res[i] = f.apply(tab[i]);
        }
        return res;
    }

    /**
     * Map the specified monadic function over an array, specifying the output array,
     * which may be the same as the input to perform an in-place operation.
     * @param f
     * @param tab
     * @param res
     * @return the result of the mapping
     */
    public static double[] map(MonadicFunction f, double[] tab, double[] res) {
        for (int i = 0; i < tab.length; i++) {
            res[i] = f.apply(tab[i]);
        }
        return res;
    }

    /**
     * Reduce the specified array using the specified dyadic function.
     * The array must have at least one element.
     * @param f
     * @param tab
     * @return the result of the reduction
     */
    public static double reduce(DyadicFunction f, double[] tab) {
        double res = tab[0];
        for (int i = 1; i < tab.length; i++) {
            res = f.apply(res, tab[i]);
        }
        return res;
    }

    /**
     * Reduce the specified array using the specified dyadic function.
     * @param f
     * @param id identity element
     * @param tab
     * @return the result of the reduction
     */
    public static double reduce(DyadicFunction f, double id, double[] tab) {
        double res = id;
        for (double v : tab) {
            res = f.apply(res, v);
        }
        return res;
    }

    /**
     * Map the specified dyadic function over two arrays.
     * If the arrays are of differing lengths, the result length is the same as
     * of the shroter array.
     * @param f
     * @param tab
     * @return the result of the mapping
     */
    public static double[] map(DyadicFunction f, double[] tab, double[] tab1) {
        int len = Math.min(tab.length, tab1.length);
        double[] res = new double[len];
        for (int i = 0; i < len; i++) {
            res[i] = f.apply(tab[i], tab1[i]);
        }
        return res;
    }

    /**
     * Map the specified dyadic function over two arrays specifying the output array,
     * which may be the same as the input to perform an in-place operation.
     * If the arrays are of differing lengths, the resultant array is filled up to
     * the length of the shorter array.
     * @param f
     * @param tab
     * @param res
     * @return the result of the mapping
     */
    public static double[] map(DyadicFunction f, double[] tab, double[] tab1, double[] res) {
        int len = Math.min(tab.length, tab1.length);
        for (int i = 0; i < len; i++) {
            res[i] = f.apply(tab[i], tab1[i]);
        }
        return res;
    }

    /**
     * Compute the value of the Airy Ai function at the specified point.
     * @param x
     * @return Ai(x)
     */
    public static double airy(double x) {
        return Airy.airy(x);
    }

    /**
     * Compute the value of the Airy Ai function's first derivative at the specified point.
     * @param x
     * @return Ai'(x)
     */
    public static double airyDerivative(double x) {
        return Airy.airyDerivative(x);
    }

    /**
     * Compute the gamma function of x.
     * @param x
     * @return gamma(x)
     */
    public static double gamma(double x) {
        return Gamma.gamma(x);
    }

    /**
     * Compute the logarithm of the gamma function of x.
     * @param x
     * @return log(gamma(x))
     */
    public static double loggamma(double x) {
        return Gamma.loggamma(x);
    }

    /**
     * Compute the digamma function of x.
     * @param x
     * @return digamma(x)
     */
    public static double digamma(double x) {
        return Gamma.digamma(x);
    }

    /**
     * Compute the trigamma function of x.
     * @param x
     * @return trigamma(x)
     */
    public static double trigamma(double x) {
        return Gamma.trigamma(x);
    }

    /**
     * Compute the value of the upper incomplete gamma function.
     * @param a
     * @param x
     * @return gamma_u(a, x)
     */
    public static double uiGamma(double a, double x) {
        return Gamma.upperIncomplete(a, x);
    }

    /**
     * Compute the value of the lower incomplete gamma function.
     * @param a
     * @param x
     * @return gamma_l(a, x)
     */
    public static double liGamma(double a, double x) {
        return Gamma.lowerIncomplete(a, x);
    }

    /**
     * Compute the Pochhammer symbol (x)_n.
     * @param x
     * @param n
     * @return (x)_n
     */
    public static double pochhammer(double x, double n) {
        return Gamma.gamma(x + n) / Gamma.gamma(x);
    }

    /**
     * Compute the value of the exponential integral at x.
     * @param x
     * @return Ei(x)
     */
    public static double expint(double x) {
        return Ei.expint(x);
    }

    /**
     * Compute the value of the Riemann zeta function at x.
     * @param x
     * @return zeta(x)
     */
    public static double zeta(double x) {
        return Zeta.riemann_zeta(x);
    }

    /**
     * Compute the value of the Hurwitz zeta function at x.
     * @param x
     * @param a
     * @return zeta(x, a)
     */
    public static double hurwitzZeta(double x, double a) {
        return Zeta.hurwitz_zeta(x, a);
    }

    /**
     * Compute the value of the n-th polygamma function at x.
     * @param n
     * @param x
     * @return polygamma(n, x)
     */
    public static double polygamma(double n, double x) {
        // Polygamma[n, x] = (-1)^(n+1) * Gamma[n + 1] * HurwitzZeta[n + 1, x]
        return Math.pow(-1, n + 1) * gamma(n + 1) * Zeta.hurwitz_zeta(n + 1, x);
    }

    /**
     * Compute the beta function of two values.
     * @param x
     * @param y
     * @return beta(x, y)
     */
    public static double beta(double x, double y) {
        return Gamma.gamma(x) * Gamma.gamma(y) / Gamma.gamma(x + y);
    }

    /**
     * Return the factorial of n. n must be positive.
     * Faster than using the gamma function.
     * @param n
     * @return n!
     */
    public static double factorial(int n) {
        return Gamma.factorial(n);
    }

    /**
     * Compute the dilogarithm (the value of the Spence function at 1-x) of x.
     * @param n
     * @return dilog(x)
     */
    public static double dilog(double n) {
        return Spence.dilog(n);
    }

    /**
     * Compute the Spence function of x.
     * @param n
     * @return Spence(x)
     */
    public static double spence(double n) {
        return Spence.spence(n);
    }

    /**
     * Compute the polylogarithm of x.
     * @param n
     * @param x
     * @return Li_n(x)
     */
    public static double polylog(int n, double x) {
        return Spence.polylog(n, x);
    }
}
