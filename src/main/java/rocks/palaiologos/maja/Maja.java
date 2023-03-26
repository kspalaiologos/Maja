package rocks.palaiologos.maja;

import java.util.Map;
import java.util.Random;

/**
 * A slick numerics-oriented Mathematical library for Java.
 *
 * @author Palaiologos
 */
public class Maja {
    /**
     * SI prefix for 10^1.
     */
    public static final double DECA = 10.0e1;
    /**
     * SI prefix for 10^2.
     */
    public static final double HECTO = 10.0e2;
    /**
     * SI prefix for 10^3.
     */
    public static final double KILO = 10.0e3;
    /**
     * SI prefix for 10^6.
     */
    public static final double MEGA = 10.0e6;
    /**
     * SI prefix for 10^9.
     */
    public static final double GIGA = 10.0e9;
    /**
     * SI prefix for 10^12.
     */
    public static final double TERA = 10.0e12;
    /**
     * SI prefix for 10^15.
     */
    public static final double PETA = 10.0e15;
    /**
     * SI prefix for 10^18.
     */
    public static final double EXA = 10.0e18;
    /**
     * SI prefix for 10^21.
     */
    public static final double ZETTA = 10.0e21;
    /**
     * SI prefix for 10^24.
     */
    public static final double YOTTA = 10.0e24;
    /**
     * SI prefix for 10^-1.
     */
    public static final double DECI = 10.0e-1;
    /**
     * SI prefix for 10^-2.
     */
    public static final double CENTI = 10.0e-2;
    /**
     * SI prefix for 10^-3.
     */
    public static final double MILLI = 10.0e-3;
    /**
     * SI prefix for 10^-6.
     */
    public static final double MICRO = 10.0e-6;
    /**
     * SI prefix for 10^-9.
     */
    public static final double NANO = 10.0e-9;
    /**
     * SI prefix for 10^-12.
     */
    public static final double PICO = 10.0e-12;
    /**
     * SI prefix for 10^-15.
     */
    public static final double FEMTO = 10.0e-15;
    /**
     * SI prefix for 10^-18.
     */
    public static final double ATTO = 10.0e-18;
    /**
     * SI prefix for 10^-21.
     */
    public static final double ZEPTO = 10.0e-21;
    /**
     * SI prefix for 10^-24.
     */
    public static final double YOCTO = 10.0e-24;

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
     * The imaginary unit.
     */
    public static final Complex I = new Complex(0.0, 1.0);
    /**
     * The random number generator used by this class.
     */
    private static final Random random = new Random();

    private Maja() {
    }

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
     * @return -1 if x &lt; 0, 0 if x == 0, 1 if x &gt; 0
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
     * Returns the sinc function of a double precision number, defined
     * as sin(x) / x, except for x = 0, where sinc(x) = 1.
     *
     * @param x
     * @return sinc(x)
     */
    public static double sinc(double x) {
        if (x == 0)
            return 1;
        return Math.sin(x) / x;
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
     * Return true if x &lt; y.
     *
     * @param x
     * @param y
     * @return x &lt; y
     */
    public static boolean lt(double x, double y) {
        return x < y;
    }

    /**
     * Return true if x &lt;= y.
     *
     * @param x
     * @param y
     * @return x &lt;= y
     */
    public static boolean le(double x, double y) {
        return x <= y;
    }

    /**
     * Return true if x &gt; y.
     *
     * @param x
     * @param y
     * @return x &gt; y
     */
    public static boolean gt(double x, double y) {
        return x > y;
    }

    /**
     * Return true if x &gt;= y.
     *
     * @param x
     * @param y
     * @return x &gt;= y
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
     * @return x != y
     */
    public static boolean ne(double x, double y) {
        return x != y;
    }

    /**
     * Compare two double precision numbers.
     *
     * @param x
     * @param y
     * @return 1 if x &gt; y, 0 if x == y, -1 if x &lt; y
     */
    public static int compare(double x, double y) {
        if (x == y) {
            return 0;
        } else if (x < y) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Return true if x is approximately equal to y.
     *
     * @param x
     * @param y
     * @param tol
     * @return abs(x - y) &lt;= tol
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
     * @return min if value &lt; min, max if value &gt; max, value otherwise
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
     * If x &lt; 0, -cbrt(-x) is returned.
     *
     * @param x
     * @return floor(cbrt ( x))
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
     * If x &lt; 0, -cbrt(-x) is returned.
     *
     * @param x
     * @return floor(cbrt ( x))
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
     * If a &lt; 0, -cbrt(-a) is returned.
     *
     * @param a
     * @return floor(cbrt ( a))
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
     * If x &lt; 0, -isqrt(-x) is returned.
     *
     * @param x
     * @return floor(sqrt ( x))
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
     * If x &lt; 0, -isqrt(-x) is returned.
     *
     * @param x
     * @return floor(sqrt ( x))
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
     * If x &lt; 0, -isqrt(-x) is returned.
     *
     * @param x
     * @return floor(sqrt ( x))
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
     *
     * @param x
     * @return floor(log10 ( x))
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
     *
     * @param x
     * @param n
     * @return x^n.
     */
    public static int ipow(int x, int n) {
        int p = x, y = 1;
        while (true) {
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
     *
     * @param x
     * @param n
     * @return x^n.
     */
    public static long ipow(long x, long n) {
        long p = x, y = 1;
        while (true) {
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
     *
     * @param x
     * @param n
     * @return x^n.
     */
    public static short ipow(short x, short n) {
        int p = x, y = 1;
        while (true) {
            if ((n & 1) != 0)
                y = p * y;
            n >>= 1;
            if (n == 0)
                return (short) y;
            p = p * p;
        }
    }

    /**
     * Break floating-point number down into exponent and mantissa
     *
     * @param value
     * @return A pair of the exponent and the mantissa.
     */
    public static Pair<Integer, Double> frexp(double value) {
        if (value == 0.0 || value == -0.0) {
            return new Pair<>(0, 0.0);
        }

        if (Double.isNaN(value)) {
            return new Pair<>(-1, Double.NaN);
        }

        if (Double.isInfinite(value)) {
            return new Pair<>(-1, value);
        }

        double mantissa = value;
        int exponent = 0;
        int sign = 1;

        if (mantissa < 0f) {
            sign = -1;
            mantissa = -(mantissa);
        }
        while (mantissa < 0.5f) {
            mantissa *= 2.0f;
            exponent -= 1;
        }
        while (mantissa >= 1.0f) {
            mantissa *= 0.5f;
            exponent++;
        }
        mantissa *= sign;
        return new Pair<>(exponent, mantissa);
    }

    /**
     * Compute the value of x^z where x is a double precision
     * floating point number and z is an integer.
     *
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

        Pair<Integer, Double> ep = frexp(x);
        lx = ep.first();
        s = ep.second();

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
     *
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
     *
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
     *
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
     *
     * @param f
     * @param id  identity element
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
     *
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
     *
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
     *
     * @param x
     * @return Ai(x)
     */
    public static double airy(double x) {
        return Airy.airy(x);
    }

    /**
     * Compute the value of the Airy Ai function's first derivative at the specified point.
     *
     * @param x
     * @return Ai'(x)
     */
    public static double airyDerivative(double x) {
        return Airy.airyDerivative(x);
    }

    /**
     * Compute the gamma function of x.
     *
     * @param x
     * @return gamma(x)
     */
    public static double gamma(double x) {
        return Gamma.gamma(x);
    }

    /**
     * Compute the logarithm of the gamma function of x.
     *
     * @param x
     * @return log(gamma ( x))
     */
    public static double loggamma(double x) {
        return Gamma.loggamma(x);
    }

    /**
     * Compute the digamma function of x.
     *
     * @param x
     * @return digamma(x)
     */
    public static double digamma(double x) {
        return Gamma.digamma(x);
    }

    /**
     * Compute the trigamma function of x.
     *
     * @param x
     * @return trigamma(x)
     */
    public static double trigamma(double x) {
        return Gamma.trigamma(x);
    }

    /**
     * Compute the value of the upper incomplete gamma function.
     *
     * @param a
     * @param x
     * @return gamma_u(a, x)
     */
    public static double uiGamma(double a, double x) {
        return Gamma.upperIncomplete(a, x);
    }

    /**
     * Compute the value of the lower incomplete gamma function.
     *
     * @param a
     * @param x
     * @return gamma_l(a, x)
     */
    public static double liGamma(double a, double x) {
        return Gamma.lowerIncomplete(a, x);
    }

    /**
     * Compute the Pochhammer symbol (x)_n.
     *
     * @param x
     * @param n
     * @return (x)_n
     */
    public static double pochhammer(double x, double n) {
        return Gamma.gamma(x + n) / Gamma.gamma(x);
    }

    /**
     * Compute the value of the exponential integral at x.
     *
     * @param x
     * @return Ei(x)
     */
    public static double expint(double x) {
        return Ei.expint(x);
    }

    /**
     * Compute the value of the Riemann zeta function at x.
     *
     * @param x
     * @return zeta(x)
     */
    public static double zeta(double x) {
        return Zeta.riemann_zeta(x);
    }

    /**
     * Compute the value of the Hurwitz zeta function at x.
     *
     * @param x
     * @param a
     * @return zeta(x, a)
     */
    public static double hurwitzZeta(double x, double a) {
        return Zeta.hurwitz_zeta(x, a);
    }

    /**
     * Compute the value of the n-th polygamma function at x.
     *
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
     *
     * @param x
     * @param y
     * @return beta(x, y)
     */
    public static double beta(double x, double y) {
        return Gamma.gamma(x) * Gamma.gamma(y) / Gamma.gamma(x + y);
    }

    /**
     * Compute the logarihtm of the beta function of two values.
     * Uses the identity that log(beta(x, y)) = log(gamma(x)) + log(gamma(y)) - log(gamma(x + y)).
     *
     * @param x
     * @param y
     * @return log(beta(x, y))
     */
    public static double logbeta(double x, double y) {
        return Gamma.loggamma(x) + Gamma.loggamma(y) - Gamma.loggamma(x + y);
    }

    /**
     * Return the factorial of n. n must be positive.
     * Faster than using the gamma function.
     *
     * @param n
     * @return n!
     */
    public static double factorial(int n) {
        return Gamma.factorial(n);
    }

    /**
     * Compute the dilogarithm (the value of the Spence function at 1-x) of x.
     *
     * @param n
     * @return dilog(x)
     */
    public static double dilog(double n) {
        return Spence.dilog(n);
    }

    /**
     * Compute the Spence function of x.
     *
     * @param n
     * @return Spence(x)
     */
    public static double spence(double n) {
        return Spence.spence(n);
    }

    /**
     * Compute the polylogarithm of x.
     *
     * @param n
     * @param x
     * @return Li_n(x)
     */
    public static double polylog(int n, double x) {
        return Spence.polylog(n, x);
    }

    /**
     * Compute the value of the secant (1 / cos(x)) function at x.
     *
     * @param x
     * @return sec(x)
     */
    public static double sec(double x) {
        return 1.0 / Math.cos(x);
    }

    /**
     * Compute the value of the cosecant (1 / sin(x)) function at x.
     *
     * @param x
     * @return csc(x)
     */
    public static double csc(double x) {
        return 1.0 / Math.sin(x);
    }

    /**
     * Compute the value of the cotangent (1 / tan(x)) function at x.
     *
     * @param x
     * @return cot(x)
     */
    public static double cot(double x) {
        return 1.0 / Math.tan(x);
    }

    /**
     * Compute the value of the hyperbolic secant (1 / cosh(x)) function at x.
     *
     * @param x
     * @return sech(x)
     */
    public static double sech(double x) {
        return 1.0 / Math.cosh(x);
    }

    /**
     * Compute the value of the hyperbolic cosecant (1 / sinh(x)) function at x.
     *
     * @param x
     * @return csch(x)
     */
    public static double csch(double x) {
        return 1.0 / Math.sinh(x);
    }

    /**
     * Compute the value of the hyperbolic cotangent (1 / tanh(x)) function at x.
     *
     * @param x
     * @return coth(x)
     */
    public static double coth(double x) {
        return 1.0 / Math.tanh(x);
    }

    /**
     * Compute the value of the inverse secant (1 / acos(x)) function at x.
     *
     * @param x
     * @return asec(x)
     */
    public static double asec(double x) {
        return Math.acos(1.0 / x);
    }

    /**
     * Compute the value of the inverse cosecant (1 / asin(x)) function at x.
     *
     * @param x
     * @return acsc(x)
     */
    public static double acsc(double x) {
        return Math.asin(1.0 / x);
    }

    /**
     * Compute the value of the inverse cotangent (1 / atan(x)) function at x.
     *
     * @param x
     * @return acot(x)
     */
    public static double acot(double x) {
        return Math.atan(1.0 / x);
    }

    /**
     * Compute the value of the inverse hyperbolic sine function at x.
     *
     * @param a
     * @return asinh(a)
     */
    public static double asinh(double a) {
        final double sign;

        if (Double.doubleToRawLongBits(a) < 0) {
            a = Math.abs(a);
            sign = -1.0d;
        } else {
            sign = 1.0d;
        }

        return sign * Math.log(Math.sqrt(a * a + 1.0d) + a);
    }

    private static double safeLog(double x) {
        if (x == 0.0D) {
            return 0.0D;
        } else {
            return Math.log(x);
        }
    }

    /**
     * Compute the value of the inverse hyperbolic cosine function at x.
     *
     * @param x
     * @return acosh(x)
     */
    public static double acosh(double x) {
        double ans;

        if (Double.isNaN(x) || (x < 1)) {
            ans = Double.NaN;
        } else if (x < 94906265.62) {
            ans = safeLog(x + Math.sqrt(x * x - 1.0D));
        } else {
            ans = 0.69314718055994530941723212145818D + safeLog(x);
        }

        return ans;
    }

    /**
     * Compute the value of the inverse hyperbolic tangent function at x.
     *
     * @param a
     * @return atanh(a)
     */
    public static double atanh(double a) {
        final double mult;

        if (Double.doubleToRawLongBits(a) < 0) {
            a = Math.abs(a);
            mult = -0.5d;
        } else {
            mult = 0.5d;
        }
        return mult * Math.log((1.0d + a) / (1.0d - a));
    }

    /**
     * Compute the value of the inverse hyperbolic secant (1 / acosh(x)) function at x.
     *
     * @param x
     * @return asech(x)
     */
    public static double asech(double x) {
        return acosh(1.0 / x);
    }

    /**
     * Compute the value of the inverse hyperbolic cosecant (1 / asinh(x)) function at x.
     *
     * @param x
     * @return acsch(x)
     */
    public static double acsch(double x) {
        return asinh(1.0 / x);
    }

    /**
     * Compute the value of the inverse hyperbolic cotangent (1 / atanh(x)) function at x.
     *
     * @param x
     * @return acoth(x)
     */
    public static double acoth(double x) {
        return atanh(1.0 / x);
    }

    /**
     * Compute Lambert W_0 (x).
     *
     * @param x
     * @return W_0(x)
     */
    public static double lambertW0(double x) {
        return Lambert.lambert0(x);
    }

    /**
     * Compute Lambert W_(-1) (x).
     *
     * @param x
     * @return W_(- 1) (x)
     */
    public static double lambertWm1(double x) {
        return Lambert.lambertn1(x);
    }

    /**
     * Compute the value of the Lerch transcendent function at z, s, a.
     *
     * @param z
     * @param s
     * @param a
     * @return Lerch(z, s, a)
     */
    public static double lerchPhi(double z, double s, double a) {
        return Zeta.lerch_phi(z, s, a);
    }

    /**
     * Return the value of the inverse error function at x.
     *
     * @param x
     * @return erf^(-1) (x)
     */
    public static double erfInv(double x) {
        return Erf.inverfc(1.0 - x);
    }

    /**
     * Return the value of the inverse complementary error function at x.
     *
     * @param x
     * @return erfc^(-1) (x)
     */
    public static double erfcInv(double x) {
        return Erf.inverfc(x);
    }

    /**
     * Compute the value of the Dawson function (D+) at x.
     *
     * @param x
     * @return D+(x)
     */
    public static double dawsonPlus(double x) {
        return Erf.dawson(x);
    }

    /**
     * Compute the value of the Dawson function (D-) at x.
     *
     * @param x
     * @return D-(x)
     */
    public static double dawsonMinus(double x) {
        return Erf.dawsonm(x);
    }

    /**
     * Compute the value of the error function at x.
     *
     * @param x
     * @return erf(x)
     */
    public static double erf(double x) {
        return Erf.erf(x);
    }

    /**
     * Compute the value of the complementary error function at x.
     *
     * @param x
     * @return erfc(x)
     */
    public static double erfc(double x) {
        return Erf.erfc(x);
    }

    /**
     * Compute the value of the imaginary error function at x.
     *
     * @param x
     * @return erfi(x)
     */
    public static double erfi(double x) {
        return Erf.erfi(x);
    }

    /**
     * Compute the value of the inverse of the logistic sigmoid "squash" function at x.
     *
     * @param x
     * @return stretch(x)
     */
    public static double stretch(double x) {
        return Math.log(x / (1.0 - x));
    }

    /**
     * Compute the value of the logistic sigmoid "squash" function at x.
     *
     * @param x
     * @return squash(x)
     */
    public static double squash(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    /**
     * Compute the value of the sine integral function at x.
     *
     * @param x
     * @return Si(x)
     */
    public static double Si(double x) {
        return TrigonometricIntegral.Si(x);
    }

    /**
     * Compute the value of the cosine integral function at x.
     *
     * @param x
     * @return Ci(x)
     */
    public static double Ci(double x) {
        return TrigonometricIntegral.Ci(x);
    }

    /**
     * Compute the value of the hyperbolic sine integral function at x.
     *
     * @param x
     * @return
     */
    public static double Shi(double x) {
        return TrigonometricIntegral.Shi(x);
    }

    /**
     * Compute the value of the hyperbolic cosine integral function at x.
     *
     * @param x
     * @return Chi(x)
     */
    public static double Chi(double x) {
        return TrigonometricIntegral.Chi(x);
    }

    /**
     * Compute the value of the sine integral function at x.
     *
     * @param x
     * @return si(x)
     */
    public static double si(double x) {
        return TrigonometricIntegral.si(x);
    }

    /**
     * Compute the value of the cosine integral function at x.
     *
     * @param x
     * @return Cin(x)
     */
    public static double Cin(double x) {
        return TrigonometricIntegral.Cin(x);
    }

    /**
     * Compute both the hyperbolic sine and cosine integral at x.
     * Return the results in a two-element double precision array.
     *
     * @param x
     * @return {Shi(x), Chi(x)}
     */
    public static double[] ShiChi(double x) {
        return TrigonometricIntegral.ShiChi(x);
    }

    /**
     * Compute the Fresnel integral C(x).
     *
     * @param x
     * @return C(x)
     */
    public static double fresnelC(double x) {
        return Fresnel.fresnelC(x);
    }

    /**
     * Compute the Fresnel integral S(x).
     *
     * @param x
     * @return S(x)
     */
    public static double fresnelS(double x) {
        return Fresnel.fresnelS(x);
    }

    /**
     * Compute the bessel Y0 function at x.
     *
     * @param x
     * @return Y0(x)
     */
    public static double besselY0(double x) {
        return Bessel.y0(x);
    }

    /**
     * Compute the bessel Y1 function at x.
     *
     * @param x
     * @return Y1(x)
     */
    public static double besselY1(double x) {
        return Bessel.y1(x);
    }

    /**
     * Compute the bessel Yn function at x.
     *
     * @param n
     * @param x
     * @return Yn(x)
     */
    public static double besselYn(int n, double x) {
        return Bessel.yn(n, x);
    }

    /**
     * Compute the bessel J0 function at x.
     *
     * @param x
     * @return J0(x)
     */
    public static double besselJ0(double x) {
        return Bessel.bessel0(x);
    }

    /**
     * Compute the bessel J1 function at x.
     *
     * @param x
     * @return J1(x)
     */
    public static double besselJ1(double x) {
        return Bessel.bessel1(x);
    }

    /**
     * Compute the bessel Jn function at x.
     *
     * @param n
     * @param x
     * @return Jn(x)
     */
    public static double besselJn(int n, double x) {
        return Bessel.bessel(n, x);
    }

    /**
     * Compute the bessel I0 function at x.
     *
     * @param x
     * @return I0(x)
     */
    public static double besselI0(double x) {
        return Bessel.i0(x);
    }

    /**
     * Compute the bessel I1 function at x.
     *
     * @param x
     * @return I1(x)
     */
    public static double besselI1(double x) {
        return Bessel.i1(x);
    }

    /**
     * Compute the bessel K0 function at x.
     *
     * @param x
     * @return K0(x)
     */
    public static double besselK0(double x) {
        return Bessel.k0(x);
    }

    /**
     * Compute the bessel K1 function at x.
     *
     * @param x
     * @return K1(x)
     */
    public static double besselK1(double x) {
        return Bessel.k1(x);
    }

    /**
     * Compute the bessel Kn function at x.
     *
     * @param n
     * @param x
     * @return Kn(x)
     */
    public static double besselKn(int n, double x) {
        return Bessel.kn(n, x);
    }

    /**
     * Compute the greatest common divisor of two integers.
     *
     * @param a
     * @param b
     * @return gcd(a, b)
     */
    public static long gcd(long a, long b) {
        long result;
        if (a == 0) {
            result = b;
        } else if (b == 0) {
            result = a;
        } else {
            long r = a % b;
            while (r != 0) {
                a = b;
                b = r;
                r = a % b;
            }
            result = b;
        }
        return result;
    }

    /**
     * Compute the least common multiple of two integers.
     *
     * @param a
     * @param b
     * @return lcm(a, b)
     */
    public static long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return a * b / gcd(a, b);
    }

    /**
     * Compute the n-th Fibonacci number using Binet's formula.
     * Due to floating point precision issues, this method is only
     * accurate for n &le;= 75.
     *
     * @param a
     * @return fib(a)
     */
    public static long fib(int a) {
        double phi = 1.6180339887498948482045868343656381177203091798057628621354486227;
        double psi = -0.618033988749894848204586834365638117720309179805762862135448622;
        double sqrt5 = 2.2360679774997896964091736687312762354406183596115257242708972454;
        return Math.round((pow(phi, a) - pow(psi, a)) / sqrt5);
    }

    /**
     * Computes the Gaussian hypergeometric function (2F1) of four arguments.
     *
     * @param a
     * @param b
     * @param c
     * @param x
     * @return (2F1)(a, b, c, x)
     */
    public static double hypergeo2F1(double a, double b, double c, double x) {
        return Hypergeometric.hyp2f1(a, b, c, x);
    }

    /**
     * Computes the Confluent hypergeometric function (1F1) of three arguments.
     *
     * @param a
     * @param b
     * @param x
     * @return (1F1)(a, b, x)
     */
    public static double hypergeo1F1(double a, double b, double x) {
        return Hypergeometric.hyperg(a, b, x);
    }

    /**
     * Computes the 1F2 case of the generalised hypergeometric function.
     *
     * @param a
     * @param b
     * @param c
     * @param x
     * @return a double array the approximation and the estimated error of (1F2)(a, b, c x)
     */
    public static double hypergeo1F2(double a, double b, double c, double x) {
        Hypergeometric.DoublePtr result = new Hypergeometric.DoublePtr();
        return Hypergeometric.hypergeo1f2(a, b, c, x, result);
    }

    /**
     * Computes the 3F0 case of the generalised hypergeometric function.
     *
     * @param a
     * @param b
     * @param c
     * @param x
     * @return a double array the approximation and the estimated error of (1F2)(a, b, c x)
     */
    public static double hypergeo3F0(double a, double b, double c, double x) {
        Hypergeometric.DoublePtr result = new Hypergeometric.DoublePtr();
        return Hypergeometric.hypergeo3f0(a, b, c, x, result);
    }

    /**
     * Computes the value of the Struve function of order v at x.
     * @param v order of the Struve function
     * @param x argument of the Struve function
     * @return Struve(v, x)
     */
    public static double struve(double v, double x) {
        return Hypergeometric.struve(v, x);
    }

    /**
     * Computes the logarithm of the absolute value of the gamma function of x.
     *
     * @param x
     * @return { log(|Gamma(x)|), sign(Gamma(x)) }
     * @see Maja#loggamma(double)
     */
    public static double[] logabsgamma(double x) {
        return Gamma.lgam(x);
    }

    /**
     * Compute fractional order bessel function of n and x.
     *
     * @param n
     * @param x
     * @return J_n(x)
     */
    public static double besselJv(double n, double x) {
        return Bessel.jv(n, x);
    }

    /**
     * Compute the fractional order bessel Y function of v and x.
     *
     * @param v
     * @param x
     * @return Y_n(x)
     */
    public static double besselYv(double v, double x) {
        return Bessel.yv(v, x);
    }

    /**
     * Integrate a monadic function over a finite interval [a,b] using the
     * Simpson rule. The number of intervals is given by N and the precision
     * of the final result greatly depends on this parameter.
     * @param f function to integrate
     * @param a lower bound
     * @param b upper bound
     * @param N number of intervals, N=10000 tends to give a good approximation in most scenarios.
     * @return integral of f over [a,b]
     */
    public static double integrateSimpson(MonadicFunction f, double a, double b, int N) {
        // Properly handle the configurations of a and b.
        if(a < b)
            return Integrator.finiteSimpson(f, a, b, N);
        else if(a == b)
            return 0.0;
        else
            return -Integrator.finiteSimpson(f, b, a, N);
    }

    /**
     * Integrate a monadic function over a finite interval [a,b] using the
     * Gauss-Legendre quadrature. The number of intervals is given by N and the precision
     * of the final result greatly depends on this parameter.
     * The computation of an integral using the Gauss-Legendre quadrature involves caching the
     * coefficients required to perform the integration depending on the value of the N parameter.
     * This means that the first call to this method will be slower than subsequent calls with the
     * same value of N. The coefficients are internally cached inside a ConcurrentHashMap.
     * @param f function to integrate
     * @param a lower bound
     * @param b upper bound
     * @param N number of intervals, N=6 tends to give a good approximation in most scenarios.
     *          N must be between 1 and 30.
     * @return integral of f over [a,b]
     */
    public static double integrateGaussLegendre(MonadicFunction f, double a, double b, int N) {
        if(a < b)
            return Integrator.gaussLegendreIntegrate(f, a, b, N);
        else if(a == b)
            return 0.0;
        else
            return -Integrator.gaussLegendreIntegrate(f, b, a, N);
    }

    /**
     * Integrate a monadic function over a finite interval [a,b] using the
     * Tanh-Sinh quadrature, especially useful when singularities or infinite
     * derivatives exist at one or both endpoints. The Tanh-Sinh quadrature is
     * not as efficient as Gaussian quadrature for smooth integrands.
     * @param f function to integrate
     * @param a lower bound
     * @param b upper bound
     * @param N the degree of the quadrature, usually N=6 is sufficient
     * @param eps desired precision of the result (usually 1.0e-9 is sufficient)
     * @return an array of double values, first of which is the integral of f over [a,b],
     *         while the second is the estimated error.
     */
    public static double[] integrateTanhSinh(MonadicFunction f, double a, double b, int N, double eps) {
        if(a < b)
            return Integrator.finiteTanhSinh(f, a, b, N, eps);
        else if(a == b)
            return new double[]{0.0, 0.0};
        else
            return Integrator.finiteTanhSinh(f, b, a, N, eps);
    }

    /**
     * Compute the binomial coefficient "n choose k".
     * @param n the number of elements, n &gt; 0.
     * @param k the number of elements to choose, 0 &lt; k &lt;= n.
     * @return n! / (k! * (n-k)!)
     */
    public static long binomial(int n, int k) {
        if(n <= 0 || k < 0 || k > n)
            throw new IllegalArgumentException("Invalid arguments: n = " + n + ", k = " + k);
        // Naive method.
        if (k > n - k)
            k = n - k;
        long b = 1;
        for (int i = 1, m = n; i <= k; i++, m--)
            b = b * m / i;
        return b;
    }

    /**
     * Find a root of a monadic function using the Newton-Raphson method.
     * @param f the function to find a root for
     * @param df the derivative of the function
     * @param x0 the initial guess
     * @param eps the desired precision of the result
     * @return a root of the function f within the desired precision
     *         unless the iteration limit is exceeded.
     */
    public static double newtonRaphson(MonadicFunction f, MonadicFunction df, double x0, double eps) {
        return Root.newtonRaphson(f, df, x0, eps);
    }

    /**
     * Evaluate an expression stored inside a string.
     *
     * @param expression the expression to evaluate
     * @param variables a map containing the variables and their values
     * @return the value of the expression
     */
    public static double eval(String expression, Map<String, Double> variables) {
        return Expression.evalExpression(expression, variables);
    }

    /**
     * Evaluate an expression stored inside a string.
     *
     * @param expression the expression to evaluate
     * @return the value of the expression
     */
    public static double eval(String expression) {
        return Expression.evalExpression(expression, Map.of());
    }

    /**
     * Add two complex numbers together.
     * @param a
     * @param b
     * @return a + b
     */
    public static Complex add(Complex a, Complex b) {
        return new Complex(a.re() + b.re(), a.im() + b.im());
    }

    /**
     * Subtract two complex numbers from each other.
     * @param a
     * @param b
     * @return a - b
     */
    public static Complex sub(Complex a, Complex b) {
        return new Complex(a.re() - b.re(), a.im() - b.im());
    }

    /**
     * Multiply two complex numbers.
     * @param a
     * @param b
     * @return a * b
     */
    public static Complex mul(Complex a, Complex b) {
        return new Complex(a.re() * b.re() - a.im() * b.im(), a.re() * b.im() + a.im() * b.re());
    }

    /**
     * Divide two complex numbers.
     * @param a
     * @param b
     * @return a / b
     */
    public static Complex div(Complex a, Complex b) {
        double d = b.re() * b.re() + b.im() * b.im();
        return new Complex((a.re() * b.re() + a.im() * b.im()) / d, (a.im() * b.re() - a.re() * b.im()) / d);
    }

    /**
     * Compute the complex conjugate of a complex number.
     * @param a
     * @return conj(a)
     */
    public static Complex conj(Complex a) {
        return new Complex(a.re(), -a.im());
    }

    /**
     * Compute the absolute value of a complex number.
     * @param a
     * @return |a|
     */
    public static double abs(Complex a) {
        return Math.sqrt(a.re() * a.re() + a.im() * a.im());
    }

    /**
     * Compute the square root of a complex number.
     * @param x
     * @return sqrt(x)
     */
    public static Complex sqrt(Complex x) {
        if(x.im() == 0)
            return new Complex(Math.sqrt(x.re()), 0);
        else {
            double r = abs(x);
            double t = Math.sqrt(0.5 * (r + x.re()));
            double u = Math.sqrt(0.5 * (r - x.re()));
            if(x.im() > 0)
                return new Complex(t, u);
            else
                return new Complex(t, -u);
        }
    }

    /**
     * Compute the value of the exponential function of a complex number.
     * @param x
     * @return exp(x)
     */
    public static Complex exp(Complex x) {
        double r = Math.exp(x.re());
        return new Complex(r * Math.cos(x.im()), r * Math.sin(x.im()));
    }

    /**
     * Compute the natural logarithm of a complex number.
     * @param x
     * @return ln(x)
     */
    public static Complex log(Complex x) {
        // Log(z) = ln(r) + i*theta = ln(|z|) + i*arg(z) = ln(|z|) + i*atan2(im(z), re(z))
        return new Complex(Math.log(abs(x)), Math.atan2(x.im(), x.re()));
    }

    /**
     * Compare two complex numbers for equality.
     * @param a
     * @param b
     * @return true if a == b, false otherwise
     */
    public static boolean eq(Complex a, Complex b) {
        return a.re() == b.re() && a.im() == b.im();
    }

    /**
     * Compare two complex numbers for inequality.
     * @param a
     * @param b
     * @return true if a != b, false otherwise
     */
    public static boolean ne(Complex a, Complex b) {
        return a.re() != b.re() || a.im() != b.im();
    }

    /**
     * Compare two complex numbers for equality.
     * @param a
     * @param b
     * @param tol
     * @return true if a ~= b, false otherwise
     */
    public static boolean eq(Complex a, Complex b, double tol) {
        return Math.abs(a.re() - b.re()) < tol && Math.abs(a.im() - b.im()) < tol;
    }

    /**
     * Compute the n-th root of a complex number.
     * @param x
     * @param deg
     * @return x^(1/deg)
     */
    public static double root(double x, int deg) {
        if(deg < 0)
            return Double.NaN;
        return Math.pow(x, 1.0 / deg);
    }

    /**
     * Find all n-th roots of a complex number.
     * @param x
     * @param n
     * @return x^(1/n)
     */
    public static Complex[] root(Complex x, int n) {
        if(n < 0)
            return null;
        double magnitude = abs(x);
        double phase = Math.atan2(x.im(), x.re());
        double nthRootOfMagnitude = root(magnitude, n);
        Complex[] roots = new Complex[n];
        for(int i = 0; i < n; i++) {
            double theta = (phase + 2 * Math.PI * i) / n;
            roots[i] = new Complex(nthRootOfMagnitude * Math.cos(theta), nthRootOfMagnitude * Math.sin(theta));
        }
        return roots;
    }

    /**
     * Compute the principal cube root of a complex number.
     * @param x
     * @return cbrt(x)
     */
    public static Complex cbrt(Complex x) {
        double magnitude = abs(x);
        double phase = Math.atan2(x.im(), x.re());
        double nthRootOfMagnitude = root(magnitude, 3);
        double theta = (phase + 2 * Math.PI) / 3;
        return new Complex(nthRootOfMagnitude * Math.cos(theta), nthRootOfMagnitude * Math.sin(theta));
    }
}
