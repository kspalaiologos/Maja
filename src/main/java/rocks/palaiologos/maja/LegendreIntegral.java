package rocks.palaiologos.maja;

import static rocks.palaiologos.maja.Maja.*;

class LegendreIntegral {
    // The relative error in this approximation tends to be about 4.98535*10^-10%.
    public static double legendreF(double phi, double k) {
        return integrateTanhSinh((MonadicFunction) t ->
                1 / (sqrt(1 - t * t) * sqrt(1 - k * k * t * t)), 0, sin(phi), 6, 1e-10)[0];
    }

    public static double legendreE(double phi, double k) {
        return integrateTanhSinh((MonadicFunction) t ->
                sqrt(1 - k * k * t * t) / sqrt(1 - t * t), 0, sin(phi), 6, 1e-10)[0];
    }

    public static double legendreD(double phi, double k) {
        return (legendreF(phi, k) - legendreE(phi, k)) / (phi * phi);
    }

    public static double legendrePi(double phi, double alpha, double k) {
        return integrateTanhSinh((MonadicFunction) t ->
                1 / (sqrt(1 - t * t) * sqrt(1 - k * k * t * t) * (1 - alpha * t * t)), 0, sin(phi), 6, 1e-10)[0];
    }

    public static Complex legendreF(Complex phi, Complex k) {
        return integrateTanhSinh(t -> div(1, mul(sqrt(sub(1, mul(t, t))), sqrt(sub(1, mul(mul(k, k), mul(t, t)))))), Complex.ZERO, sin(phi), 6, 1e-10)[0];
    }

    public static Complex legendreE(Complex phi, Complex k) {
        return integrateTanhSinh(t -> div(sqrt(sub(1, mul(mul(k, k), mul(t, t)))), sqrt(sub(1, mul(t, t)))), Complex.ZERO, sin(phi), 6, 1e-10)[0];
    }

    public static Complex legendreD(Complex phi, Complex k) {
        return div(sub(legendreF(phi, k), legendreE(phi, k)), mul(phi, phi));
    }

    public static Complex legendrePi(Complex phi, Complex alpha, Complex k) {
        return integrateTanhSinh(t -> div(1, mul(sqrt(sub(1, mul(t, t))), mul(sqrt(sub(1, mul(mul(k, k), mul(t, t)))), sub(1, mul(alpha, mul(t, t)))))), Complex.ZERO, sin(phi), 6, 1e-10)[0];
    }
}
