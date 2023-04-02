package rocks.palaiologos.maja;

import static rocks.palaiologos.maja.Maja.*;

public class LegendreIntegral {
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
}
