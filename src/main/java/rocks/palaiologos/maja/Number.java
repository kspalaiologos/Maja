package rocks.palaiologos.maja;

/**
 * A class that can hold a double precision, long or complex number.
 * @author Palaiologos
 */
public class Number {
    private final Object data;

    public Number(double d) {
        data = d;
    }

    public Number(long i) {
        data = i;
    }

    public Number(Complex z) {
        data = z;
    }

    public Number(Object o) {
        if(o instanceof Double || o instanceof Long || o instanceof Complex)
            data = o;
        else if(o instanceof Boolean)
            data = (Boolean)o ? 1 : 0;
        else
            throw new IllegalArgumentException("Object must be a double, long or complex");
    }

    public Complex getComplex() {
        if(data instanceof Complex)
            return (Complex)data;
        else if(data instanceof Double)
            return new Complex((Double)data, 0);
        else
            return new Complex((Long)data, 0);
    }

    public double getDouble() {
        if(data instanceof Complex)
            throw new ArithmeticException("Cannot convert complex to double");
        else if(data instanceof Double)
            return (Double)data;
        else
            return (Long)data;
    }

    public long getLong() {
        if(data instanceof Complex)
            throw new ArithmeticException("Cannot convert complex to long");
        else if(data instanceof Double)
            throw new ArithmeticException("Cannot convert double to long");
        else
            return (Long)data;
    }

    public boolean isComplex() {
        return data instanceof Complex;
    }

    public boolean isDouble() {
        return data instanceof Double;
    }

    public boolean isLong() {
        return data instanceof Long;
    }

    @Override
    public String toString() {
        if(data instanceof Complex)
            return ((Complex)data).toString();
        else if(data instanceof Double)
            return ((Double)data).toString();
        else
            return ((Long)data).toString();
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Number n) {
            if(data instanceof Complex)
                return n.data instanceof Complex && data.equals(n.data);
            else if(data instanceof Double)
                return n.data instanceof Double && data.equals(n.data);
            else
                return n.data instanceof Long && data.equals(n.data);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }
}
