package rocks.palaiologos.maja.matrix;

import rocks.palaiologos.maja.Complex;
import rocks.palaiologos.maja.Pair;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A class representing a two-dimensional matrix of double precision complex numbers.
 *
 * @author Palaiologos
 */
public class ComplexMatrix extends Matrix<Complex> {
    public ComplexMatrix(Complex[][] data) {
        super(data);
    }

    public ComplexMatrix(int rows, int columns) {
        super(rows, columns);
    }

    public ComplexMatrix(List<List<Complex>> data) {
        super(data);
    }

    private ComplexMatrix(Matrix<Complex> m) {
        super(m.data);
    }

    public static ComplexMatrix into(Matrix<Complex> mat) {
        if(mat instanceof ComplexMatrix)
            return (ComplexMatrix) mat;
        return new ComplexMatrix(mat);
    }

    @Override
    public ComplexMatrix copy() {
        return new ComplexMatrix(data);
    }

    @Override
    public ComplexMatrix transpose() {
        ComplexMatrix result = new ComplexMatrix(data.get(0).size(), data.size());
        for (int i = 0; i < data.size(); i++)
            for (int j = 0; j < data.get(i).size(); j++)
                result.set(j, i, data.get(i).get(j));
        return result;
    }

    @Override
    public ComplexMatrix map(Function<Complex, Complex> mapper) {
        ComplexMatrix result = new ComplexMatrix(data.size(), data.get(0).size());
        for (int i = 0; i < data.size(); i++)
            for (int j = 0; j < data.get(i).size(); j++)
                result.set(i, j, mapper.apply(data.get(i).get(j)));
        return result;
    }

    @Override
    public ComplexMatrix zipWith(Matrix<Complex> other, BiFunction<Complex, Complex, Complex> zipper) {
        ComplexMatrix result = new ComplexMatrix(data.size(), data.get(0).size());
        for (int i = 0; i < data.size(); i++)
            for (int j = 0; j < data.get(i).size(); j++)
                result.set(i, j, zipper.apply(data.get(i).get(j), other.get(i, j)));
        return result;
    }

    @Override
    public ComplexMatrix reverseFirst() {
        ComplexMatrix result = new ComplexMatrix(data.size(), data.get(0).size());
        for (int i = 0; i < data.size(); i++)
            for (int j = 0; j < data.get(i).size(); j++)
                result.set(i, j, data.get(data.size() - i - 1).get(j));
        return result;
    }

    @Override
    public ComplexMatrix reverseLast() {
        ComplexMatrix result = new ComplexMatrix(data.size(), data.get(0).size());
        for (int i = 0; i < data.size(); i++)
            for (int j = 0; j < data.get(i).size(); j++)
                result.set(i, j, data.get(i).get(data.get(i).size() - j - 1));
        return result;
    }

    @Override
    public ComplexMatrix mapIdx(BiFunction<Pair<Integer, Integer>, Complex, Complex> mapper) {
        ComplexMatrix result = new ComplexMatrix(data.size(), data.get(0).size());
        for (int i = 0; i < data.size(); i++)
            for (int j = 0; j < data.get(i).size(); j++)
                result.set(i, j, mapper.apply(new Pair<>(i, j), data.get(i).get(j)));
        return result;
    }

    @Override
    public ComplexMatrix dot(Matrix<Complex> another, BiFunction<Complex, Complex, Complex> scalar, BiFunction<Complex, Complex, Complex> vector) {
        var a = rows();
        var b = another.columns();
        if (a.size() != b.size())
            throw new IllegalArgumentException("Matrices are not aligned.");
        ComplexMatrix result = new ComplexMatrix(a.size(), b.size());
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                List<Complex> row = a.get(i);
                List<Complex> column = b.get(j);
                Complex reduced = scalar.apply(row.get(0), column.get(0));
                for (int k = 1; k < row.size(); k++)
                    reduced = vector.apply(reduced, scalar.apply(row.get(k), column.get(k)));
                result.set(i, j, reduced);
            }
        }
        return result;
    }
}
