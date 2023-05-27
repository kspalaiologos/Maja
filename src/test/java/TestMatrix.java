
import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Complex;
import rocks.palaiologos.maja.Matrix;
import rocks.palaiologos.maja.Maja;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestMatrix {
    @Test
    public void testBasicToString() {
        Matrix<Complex> m = new Matrix<>(new Complex[][] {
            { new Complex(1, 2), new Complex(3, 4) },
            { new Complex(5, 6), new Complex(7, 8) }
        });
        assertThat(m.toString()).isEqualTo("""
                +------------+------------+
                | 1.0 + 2.0i | 3.0 + 4.0i |
                +------------+------------+
                | 5.0 + 6.0i | 7.0 + 8.0i |
                +------------+------------+
                """);
    }

    @Test
    public void testMultilineToString() {
        Matrix<String> m = new Matrix<>(new String[][] {
            { "Hello", "World" },
            { "Foo", "Bar\nBaz" }
        });
        assertThat(m.toString()).isEqualTo("""
                +-------+-------+
                | Hello | World |
                +-------+-------+
                | Foo   | Bar   |
                |       | Baz   |
                +-------+-------+
                """);
    }

    @Test
    public void testNestedToString() {
        Matrix[][] data = new Matrix[][] {
            {
                new Matrix<>(new Complex[][] {
                    { new Complex(1, 2), new Complex(3, 4) },
                    { new Complex(5, 6), new Complex(7, 8) }
                }),
                new Matrix<>(new Complex[][] {
                    { new Complex(9, 10), new Complex(11, 12) },
                    { new Complex(13, 14), new Complex(15, 16) }
                })
            },
            {
                new Matrix<>(new Complex[][] {
                    { new Complex(17, 18), new Complex(19, 20) },
                    { new Complex(21, 22), new Complex(23, 24) }
                }),
                new Matrix<>(new Complex[][] {
                    { new Complex(25, 26), new Complex(27, 28) },
                    { new Complex(29, 30), new Complex(31, 32) }
                })
            }
        };
        Matrix<Matrix<Complex>> mat = new Matrix<>(data);

        assertThat(mat.toString()).isEqualTo("""
               +---------------------------------+---------------------------------+
               | +------------+------------+     | +--------------+--------------+ |
               | | 1.0 + 2.0i | 3.0 + 4.0i |     | | 9.0 + 10.0i  | 11.0 + 12.0i | |
               | +------------+------------+     | +--------------+--------------+ |
               | | 5.0 + 6.0i | 7.0 + 8.0i |     | | 13.0 + 14.0i | 15.0 + 16.0i | |
               | +------------+------------+     | +--------------+--------------+ |
               +---------------------------------+---------------------------------+
               | +--------------+--------------+ | +--------------+--------------+ |
               | | 17.0 + 18.0i | 19.0 + 20.0i | | | 25.0 + 26.0i | 27.0 + 28.0i | |
               | +--------------+--------------+ | +--------------+--------------+ |
               | | 21.0 + 22.0i | 23.0 + 24.0i | | | 29.0 + 30.0i | 31.0 + 32.0i | |
               | +--------------+--------------+ | +--------------+--------------+ |
               +---------------------------------+---------------------------------+
               """);
    }

    @Test
    public void testDot() {
        Matrix<Complex> a = new Matrix<>(new Complex[][] {
            { new Complex(1, 2), new Complex(3, 4) },
            { new Complex(5, 6), new Complex(7, 8) }
        });

        Matrix<Complex> b = new Matrix<>(new Complex[][] {
            { new Complex(9, 10), new Complex(11, 12) },
            { new Complex(13, 14), new Complex(15, 16) }
        });

        Matrix<Complex> c = a.dot(b, Maja::mul, Maja::add);

        assertThat(c).isEqualTo(new Matrix<>(new Complex[][] {
            { new Complex(-28, 122), new Complex(-32, 142) },
            { new Complex(-36, 306), new Complex(-40, 358) }
        }));
    }

    @Test
    public void testEq() {
        Matrix<Complex> a = new Matrix<>(new Complex[][] {
                { new Complex(1, 2), new Complex(3, 4) },
                { new Complex(5, 6), new Complex(7, 8) }
        });

        Matrix<Complex> b = new Matrix<>(new Complex[][] {
                { new Complex(9, 10), new Complex(11, 12) },
                { new Complex(13, 14), new Complex(15, 16) }
        });

        Matrix<Complex> c = new Matrix<>(new Complex[][] {
                { new Complex(1, 2), new Complex(3, 4) },
                { new Complex(5, 6), new Complex(7, 8) }
        });

        assertThat(a.equals(c)).isTrue();
        assertThat(a.equals(b)).isFalse();
    }

    @Test
    public void testHas() {
        Matrix<Complex> a = new Matrix<>(new Complex[][] {
                { new Complex(1, 2), new Complex(3, 4) },
                { new Complex(5, 6), new Complex(7, 8) }
        });

        Matrix<Complex> b = new Matrix<>(new Complex[][] {
                { new Complex(9, 10), new Complex(11, 12) },
                { new Complex(13, 14), new Complex(15, 16) }
        });

        assertThat(a.has(new Complex(1, 2))).isTrue();
        assertThat(a.has(new Complex(9, 10))).isFalse();

        assertThat(b.has(new Complex(9, 10))).isTrue();
        assertThat(b.has(new Complex(1, 2))).isFalse();
    }

    @Test
    public void testRavel() {
        Matrix<Complex> a = new Matrix<>(new Complex[][] {
                { new Complex(1, 2), new Complex(3, 4) },
                { new Complex(5, 6), new Complex(7, 8) }
        });

        List<Complex> expectedRavel = List.of(
                new Complex(1, 2),
                new Complex(3, 4),
                new Complex(5, 6),
                new Complex(7, 8)
        );

        assertThat(a.ravel()).isEqualTo(expectedRavel);
    }

    @Test
    public void testRandomAccess() {
        Matrix<Complex> a = new Matrix<>(new Complex[][] {
                { new Complex(1, 2), new Complex(3, 4) },
                { new Complex(5, 6), new Complex(7, 8) }
        });

        assertThat(a.get(0, 0)).isEqualTo(new Complex(1, 2));
        assertThat(a.get(0, 1)).isEqualTo(new Complex(3, 4));
    }

    @Test
    public void testTranspose() {
        Matrix<Complex> a = new Matrix<>(new Complex[][] {
                { new Complex(1, 2), new Complex(3, 4) },
                { new Complex(5, 6), new Complex(7, 8) }
        });

        Matrix<Complex> expected = new Matrix<>(new Complex[][] {
                { new Complex(1, 2), new Complex(5, 6) },
                { new Complex(3, 4), new Complex(7, 8) }
        });

        assertThat(a.transpose()).isEqualTo(expected);
    }
}
