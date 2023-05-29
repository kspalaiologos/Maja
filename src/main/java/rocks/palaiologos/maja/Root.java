package rocks.palaiologos.maja;

import java.util.function.Function;

class Root {
    private Root() {
    }

    public static double newtonRaphson(Function<Double, Double> f, Function<Double, Double> df, double x, double eps) {
        double h = f.apply(x) / df.apply(x);
        int maxIter = 100;
        x -= h;
        while (Math.abs(h) > eps && maxIter-- > 0) {
            h = f.apply(x) / df.apply(x);
            x -= h;
        }
        return x;
    }
}
