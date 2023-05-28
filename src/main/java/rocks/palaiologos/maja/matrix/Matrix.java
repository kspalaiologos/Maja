package rocks.palaiologos.maja.matrix;

import rocks.palaiologos.maja.Pair;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A class representing a two-dimensional matrix of arbitrary type.
 * It is recommended that the type is immutable.
 *
 * @param <T>
 * @author Palaiologos
 */
public class Matrix<T> {
    final List<List<T>> data;

    /**
     * Create a matrix out of a two-dimensional array.
     * The array itself is copied, but the elements are not, meaning that if the
     * elements are mutated, the change will be reflected in the matrix.
     *
     * @param data
     */
    public Matrix(T[][] data) {
        this.data = new ArrayList<>();
        for (T[] row : data) {
            ArrayList<T> newRow = new ArrayList<>();
            Collections.addAll(newRow, row);
            this.data.add(newRow);
        }
    }

    Matrix(boolean copyless, List<List<T>> data) {
        if(copyless)
            this.data = data;
        else
            this.data = new ArrayList<>(data);
    }

    /**
     * Create an empty matrix with the specified dimensions.
     *
     * @param rows
     * @param columns
     */
    public Matrix(int rows, int columns) {
        this.data = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            ArrayList<T> newRow = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                newRow.add(null);
            }
            this.data.add(newRow);
        }
    }

    /**
     * Create a matrix out of a nested list.
     * The list itself is copied, but the elements are not, meaning that if the
     * elements are mutated, the change will be reflected in the matrix.
     *
     * @param data
     */
    public Matrix(List<List<T>> data) {
        this.data = new ArrayList<>();
        for (List<T> row : data)
            this.data.add(new ArrayList<>(row));
    }

    /**
     * Create a nested list view of all rows in the matrix. The returned list is
     * a view, meaning that if the matrix is mutated, the change will be
     * reflected in the list. If the view is mutated, the change will be
     * reflected in the matrix.
     *
     * @return
     */
    public List<List<T>> columns() {
        return new AbstractList<>() {
            @Override
            public List<T> get(int index) {
                return column(index);
            }

            @Override
            public List<T> set(int index, List<T> element) {
                List<T> old = new ArrayList<>(column(index));
                for (int i = 0; i < element.size(); i++)
                    Matrix.this.set(i, index, element.get(i));
                return old;
            }

            @Override
            public int size() {
                return data.get(0).size();
            }
        };
    }

    /**
     * Create a list view of a column in the matrix. The returned list is a
     * view, meaning that if the matrix is mutated, the change will be reflected
     * in the list. If the view is mutated, the change will be reflected in the
     * matrix.
     *
     * @param i
     * @return
     */
    public List<T> column(int i) {
        return new AbstractList<T>() {
            @Override
            public T get(int index) {
                return data.get(index).get(i);
            }

            @Override
            public T set(int index, T element) {
                return data.get(index).set(i, element);
            }

            @Override
            public int size() {
                return data.size();
            }
        };
    }

    /**
     * Return the width of the matrix.
     *
     * @return
     */
    public int width() {
        return data.get(0).size();
    }

    /**
     * Return the height of the matrix.
     *
     * @return
     */
    public int height() {
        return data.size();
    }

    /**
     * Create a nested list view of all columns in the matrix. The returned list
     * is a view, meaning that if the matrix is mutated, the change will be
     * reflected in the list. If the view is mutated, the change will be
     * reflected in the matrix.
     *
     * @return
     */
    public List<List<T>> rows() {
        return data;
    }

    /**
     * Create a list view of a row in the matrix. The returned list is a view,
     * meaning that if the matrix is mutated, the change will be reflected in
     * the list. If the view is mutated, the change will be reflected in the
     * matrix.
     *
     * @param i
     * @return
     */
    public List<T> row(int i) {
        return new AbstractList<T>() {
            @Override
            public T get(int index) {
                return data.get(i).get(index);
            }

            @Override
            public T set(int index, T element) {
                return data.get(i).set(index, element);
            }

            @Override
            public int size() {
                return data.get(i).size();
            }
        };
    }

    /**
     * Pick the element at the specified position in the matrix.
     *
     * @param i The row index.
     * @param j The column index.
     * @return The element at the specified position.
     */
    public T get(int i, int j) {
        return data.get(i).get(j);
    }

    /**
     * Set the element at the specified position in the matrix.
     * The old element is returned.
     *
     * @param i       The row index.
     * @param j       The column index.
     * @param element The new element.
     * @return The old element.
     */
    public T set(int i, int j, T element) {
        return data.get(i).set(j, element);
    }

    /**
     * Swap two elements in the matrix.
     *
     * @param srcRow The row index of the first element.
     * @param srcCol The column index of the first element.
     * @param dstRow The row index of the second element.
     * @param dstCol The column index of the second element.
     */
    public void swap(int srcRow, int srcCol, int dstRow, int dstCol) {
        T tmp = get(srcRow, srcCol);
        set(srcRow, srcCol, get(dstRow, dstCol));
        set(dstRow, dstCol, tmp);
    }

    /**
     * Transpose the matrix, i.e. swap its axes.
     *
     * @return The transposed matrix.
     */
    public Matrix<T> transpose() {
        Matrix<T> result = new Matrix<>(data.get(0).size(), data.size());
        for (int i = 0; i < data.size(); i++)
            for (int j = 0; j < data.get(i).size(); j++)
                result.set(j, i, data.get(i).get(j));
        return result;
    }

    /**
     * Reduce the matrix on the last axis with a specified reductor.
     *
     * @param reductor
     * @return
     */
    public List<T> reduceLast(BiFunction<T, T, T> reductor) {
        // Reduce on last axis.
        List<T> result = new ArrayList<>();
        for (List<T> row : rows()) {
            T reduced = row.get(0);
            for (int i = 1; i < row.size(); i++)
                reduced = reductor.apply(reduced, row.get(i));
            result.add(reduced);
        }
        return result;
    }

    /**
     * Reduce the matrix on the first axis with a specified reductor.
     *
     * @param reductor
     * @return
     */
    public List<T> reduceFirst(BiFunction<T, T, T> reductor) {
        // Reduce on first axis.
        List<T> result = new ArrayList<>();
        for (List<T> column : columns()) {
            T reduced = column.get(0);
            for (int i = 1; i < column.size(); i++)
                reduced = reductor.apply(reduced, column.get(i));
            result.add(reduced);
        }
        return result;
    }

    /**
     * Map each cell of the matrix with a specified mapper.
     *
     * @param mapper
     * @return
     */
    public Matrix<T> map(Function<T, T> mapper) {
        Matrix<T> result = new Matrix<>(data.size(), data.get(0).size());
        for (int i = 0; i < data.size(); i++)
            for (int j = 0; j < data.get(i).size(); j++)
                result.set(i, j, mapper.apply(data.get(i).get(j)));
        return result;
    }

    /**
     * Zip two matrices together to produce a new matrix, using a specified
     * zipper function.
     *
     * @param other
     * @param zipper
     * @return
     */
    public Matrix<T> zipWith(Matrix<T> other, BiFunction<T, T, T> zipper) {
        Matrix<T> result = new Matrix<>(data.size(), data.get(0).size());
        for (int i = 0; i < data.size(); i++)
            for (int j = 0; j < data.get(i).size(); j++)
                result.set(i, j, zipper.apply(data.get(i).get(j), other.get(i, j)));
        return result;
    }

    /**
     * Reverse the matrix on the first axis.
     *
     * @return
     */
    public Matrix<T> reverseFirst() {
        Matrix<T> result = new Matrix<>(data.size(), data.get(0).size());
        for (int i = 0; i < data.size(); i++)
            for (int j = 0; j < data.get(i).size(); j++)
                result.set(i, j, data.get(data.size() - i - 1).get(j));
        return result;
    }

    /**
     * Reverse the matrix on the last axis.
     *
     * @return
     */
    public Matrix<T> reverseLast() {
        Matrix<T> result = new Matrix<>(data.size(), data.get(0).size());
        for (int i = 0; i < data.size(); i++)
            for (int j = 0; j < data.get(i).size(); j++)
                result.set(i, j, data.get(i).get(data.get(i).size() - j - 1));
        return result;
    }

    /**
     * Map each cell of the matrix with a specified mapper, which takes the
     * index of the cell as an additional argument.
     *
     * @param mapper
     * @return
     */
    public Matrix<T> mapIdx(BiFunction<Pair<Integer, Integer>, T, T> mapper) {
        Matrix<T> result = new Matrix<>(data.size(), data.get(0).size());
        for (int i = 0; i < data.size(); i++)
            for (int j = 0; j < data.get(i).size(); j++)
                result.set(i, j, mapper.apply(new Pair<>(i, j), data.get(i).get(j)));
        return result;
    }

    /**
     * Compute the ravel of the matrix, i.e. a list of all elements in it.
     * The ravel follows a row-major order.
     *
     * @return
     */
    public List<T> ravel() {
        List<T> result = new ArrayList<>();
        for (List<T> row : data)
            result.addAll(row);
        return result;
    }

    /**
     * Determine whether the matrix contains a specified element.
     *
     * @param element
     * @return
     */
    public boolean has(T element) {
        for (List<T> row : data)
            if (row.contains(element))
                return true;
        return false;
    }

    /**
     * Compute the generalised dot product of the matrix with another matrix.
     * The generalised dot product is defined as follows:
     * <pre>
     *     (A o B) = sum_{i,j} (A_{i,j} o B_{i,j})
     * </pre>
     * <p>
     * Where o is the *scalar* product and sum is the *vector* sum. For example, to multiply two matrices, one would use:
     * <pre>
     *     dot(A, B, (a, b) -> a * b, (a, b) -> a + b)
     * </pre>
     *
     * @param another The other matrix.
     * @param scalar  The scalar product.
     * @param vector  The vector sum.
     * @return The generalised dot product.
     */
    public Matrix<T> dot(Matrix<T> another, BiFunction<T, T, T> scalar, BiFunction<T, T, T> vector) {
        var a = rows();
        var b = another.columns();
        if (a.size() != b.size())
            throw new IllegalArgumentException("Matrices are not aligned.");
        Matrix<T> result = new Matrix<>(a.size(), b.size());
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                List<T> row = a.get(i);
                List<T> column = b.get(j);
                T reduced = scalar.apply(row.get(0), column.get(0));
                for (int k = 1; k < row.size(); k++)
                    reduced = vector.apply(reduced, scalar.apply(row.get(k), column.get(k)));
                result.set(i, j, reduced);
            }
        }
        return result;
    }

    /**
     * Copy the matrix.
     *
     * @return
     */
    public Matrix<T> copy() {
        return new Matrix<>(data);
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Matrix)
            return data.equals(((Matrix<?>) obj).data);
        return false;
    }

    @Override
    public String toString() {
        List<List<String>> stringData = new ArrayList<>();
        for (List<T> row : data) {
            List<String> stringRow = new ArrayList<>();
            for (T element : row)
                stringRow.add(element.toString());
            stringData.add(stringRow);
        }

        List<List<Pair<Integer, Integer>>> cellSizes = new ArrayList<>();
        for (List<String> row : stringData) {
            List<Pair<Integer, Integer>> rowSizes = new ArrayList<>();
            for (String element : row) {
                int longestLine = 0;
                int linefeeds = 0;
                for (String line : element.split("\n")) {
                    longestLine = Math.max(longestLine, line.length());
                    linefeeds++;
                }
                rowSizes.add(new Pair<>(longestLine, linefeeds));
            }
            cellSizes.add(rowSizes);
        }

        int[] columnWidths = new int[stringData.get(0).size()];
        for (int i = 0; i < stringData.get(0).size(); i++) {
            int columnWidth = 0;
            for (int j = 0; j < stringData.size(); j++)
                columnWidth = Math.max(columnWidth, cellSizes.get(j).get(i).first());
            columnWidths[i] = columnWidth;
        }

        int[] rowHeights = new int[stringData.size()];
        for (int i = 0; i < stringData.size(); i++) {
            int rowHeight = 0;
            for (int j = 0; j < stringData.get(i).size(); j++)
                rowHeight = Math.max(rowHeight, cellSizes.get(i).get(j).second());
            rowHeights[i] = rowHeight;
        }

        for (int i = 0; i < stringData.size(); i++) {
            for (int j = 0; j < stringData.get(i).size(); j++) {
                String element = stringData.get(i).get(j);
                Pair<Integer, Integer> cellSize = cellSizes.get(i).get(j);
                int columnWidth = columnWidths[j];
                int rowHeight = rowHeights[i];
                int linefeeds = cellSize.second();
                StringBuilder sb = new StringBuilder();
                for (String line : element.split("\n")) {
                    sb.append(line);
                    sb.append(" ".repeat(Math.max(0, columnWidth - line.length())));
                    sb.append('\n');
                }
                String emptyLine = " ".repeat(columnWidth) + '\n';
                sb.append(emptyLine.repeat(Math.max(0, rowHeight - linefeeds)));
                stringData.get(i).set(j, sb.toString());
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("+");
        for (int columnWidth : columnWidths) sb.append("-".repeat(columnWidth + 2)).append("+");
        sb.append("\n");
        for (int i = 0; i < stringData.size(); i++) {
            for (int line = 0; line < rowHeights[i]; line++) {
                sb.append("|");
                for (int j = 0; j < stringData.get(i).size(); j++) {
                    String element = stringData.get(i).get(j);
                    String[] lines = element.split("\n");
                    if (line < lines.length)
                        sb.append(" ").append(lines[line]).append(" |");
                    else
                        sb.append(" ".repeat(columnWidths[j] + 2)).append("|");
                }
                sb.append("\n");
            }
            sb.append("+");
            for (int j = 0; j < stringData.get(i).size(); j++)
                sb.append("-".repeat(columnWidths[j] + 2)).append("+");
            sb.append("\n");
        }
        return sb.toString();
    }
}
