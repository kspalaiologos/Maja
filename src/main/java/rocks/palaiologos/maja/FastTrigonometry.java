package rocks.palaiologos.maja;

/**
 * Loosely based on Raven's implementations of sin/cos.
 */
class FastTrigonometry {
    static public final float PI = (float) Math.PI;
    static public final float PI2 = PI * 2;
    static private final float radFull = PI2;
    static public final float HALF_PI = PI / 2;
    static private final int SIN_BITS = 14;
    static private final int SIN_MASK = ~(-1 << SIN_BITS);
    static private final int SIN_COUNT = SIN_MASK + 1;
    static private final float radToIndex = SIN_COUNT / radFull;
    static private final float degToIndex = SIN_COUNT / 360f;
    static final float[] sintable = new float[SIN_COUNT];

    static {
        for (int i = 0; i < SIN_COUNT; i++)
            sintable[i] = (float) Math.sin((i + 0.5f) / SIN_COUNT * radFull);
        // The four right angles get extra-precise values, because they are
        // the most likely to need to be correct.
        sintable[0] = 0f;
        sintable[(int) (90 * degToIndex) & SIN_MASK] = 1f;
        sintable[(int) (180 * degToIndex) & SIN_MASK] = 0f;
        sintable[(int) (270 * degToIndex) & SIN_MASK] = -1f;
    }

    private FastTrigonometry() {
    }

    // sin(x) for x between -2*pi and 2*pi
    public static float sin(float radians) {
        return sintable[(int) (radians * radToIndex) & SIN_MASK];
    }

    public static float cos(float radians) {
        return sintable[(int) ((radians + HALF_PI) * radToIndex) & SIN_MASK];
    }
}
