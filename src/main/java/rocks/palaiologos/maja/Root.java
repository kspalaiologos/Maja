package rocks.palaiologos.maja;

class Root {
    private Root() {
    }

    public static double newtonRaphson(MonadicFunction f, MonadicFunction df, double x, double eps) {
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
