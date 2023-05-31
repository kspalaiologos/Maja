import org.junit.jupiter.api.Test;
import rocks.palaiologos.maja.Complex;
import rocks.palaiologos.maja.Maja;
import rocks.palaiologos.maja.matrix.DoubleMatrix;
import rocks.palaiologos.maja.matrix.Matrix;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestMatrix {
    @Test
    public void testBasicToString() {
        Matrix<Complex> m = new Matrix<>(new Complex[][]{
                {new Complex(1, 2), new Complex(3, 4)},
                {new Complex(5, 6), new Complex(7, 8)}
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
        Matrix<String> m = new Matrix<>(new String[][]{
                {"Hello", "World"},
                {"Foo", "Bar\nBaz"}
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
        Matrix[][] data = new Matrix[][]{
                {
                        new Matrix<>(new Complex[][]{
                                {new Complex(1, 2), new Complex(3, 4)},
                                {new Complex(5, 6), new Complex(7, 8)}
                        }),
                        new Matrix<>(new Complex[][]{
                                {new Complex(9, 10), new Complex(11, 12)},
                                {new Complex(13, 14), new Complex(15, 16)}
                        })
                },
                {
                        new Matrix<>(new Complex[][]{
                                {new Complex(17, 18), new Complex(19, 20)},
                                {new Complex(21, 22), new Complex(23, 24)}
                        }),
                        new Matrix<>(new Complex[][]{
                                {new Complex(25, 26), new Complex(27, 28)},
                                {new Complex(29, 30), new Complex(31, 32)}
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
        Matrix<Complex> a = new Matrix<>(new Complex[][]{
                {new Complex(1, 2), new Complex(3, 4)},
                {new Complex(5, 6), new Complex(7, 8)}
        });

        Matrix<Complex> b = new Matrix<>(new Complex[][]{
                {new Complex(9, 10), new Complex(11, 12)},
                {new Complex(13, 14), new Complex(15, 16)}
        });

        Matrix<Complex> c = a.dot(b, Maja::mul, Maja::add);

        assertThat(c).isEqualTo(new Matrix<>(new Complex[][]{
                {new Complex(-28, 122), new Complex(-32, 142)},
                {new Complex(-36, 306), new Complex(-40, 358)}
        }));
    }

    @Test
    public void testEq() {
        Matrix<Complex> a = new Matrix<>(new Complex[][]{
                {new Complex(1, 2), new Complex(3, 4)},
                {new Complex(5, 6), new Complex(7, 8)}
        });

        Matrix<Complex> b = new Matrix<>(new Complex[][]{
                {new Complex(9, 10), new Complex(11, 12)},
                {new Complex(13, 14), new Complex(15, 16)}
        });

        Matrix<Complex> c = new Matrix<>(new Complex[][]{
                {new Complex(1, 2), new Complex(3, 4)},
                {new Complex(5, 6), new Complex(7, 8)}
        });

        assertThat(a.equals(c)).isTrue();
        assertThat(a.equals(b)).isFalse();
    }

    @Test
    public void testHas() {
        Matrix<Complex> a = new Matrix<>(new Complex[][]{
                {new Complex(1, 2), new Complex(3, 4)},
                {new Complex(5, 6), new Complex(7, 8)}
        });

        Matrix<Complex> b = new Matrix<>(new Complex[][]{
                {new Complex(9, 10), new Complex(11, 12)},
                {new Complex(13, 14), new Complex(15, 16)}
        });

        assertThat(a.has(new Complex(1, 2))).isTrue();
        assertThat(a.has(new Complex(9, 10))).isFalse();

        assertThat(b.has(new Complex(9, 10))).isTrue();
        assertThat(b.has(new Complex(1, 2))).isFalse();
    }

    @Test
    public void testRavel() {
        Matrix<Complex> a = new Matrix<>(new Complex[][]{
                {new Complex(1, 2), new Complex(3, 4)},
                {new Complex(5, 6), new Complex(7, 8)}
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
        Matrix<Complex> a = new Matrix<>(new Complex[][]{
                {new Complex(1, 2), new Complex(3, 4)},
                {new Complex(5, 6), new Complex(7, 8)}
        });

        assertThat(a.get(0, 0)).isEqualTo(new Complex(1, 2));
        assertThat(a.get(0, 1)).isEqualTo(new Complex(3, 4));
    }

    @Test
    public void testTranspose() {
        Matrix<Complex> a = new Matrix<>(new Complex[][]{
                {new Complex(1, 2), new Complex(3, 4)},
                {new Complex(5, 6), new Complex(7, 8)}
        });

        Matrix<Complex> expected = new Matrix<>(new Complex[][]{
                {new Complex(1, 2), new Complex(5, 6)},
                {new Complex(3, 4), new Complex(7, 8)}
        });

        assertThat(a.transpose()).isEqualTo(expected);
    }

    @Test
    public void testSum() {
        DoubleMatrix a = new DoubleMatrix(new double[][]{
                {1, 2},
                {3, 4}
        });

        DoubleMatrix b = new DoubleMatrix(new double[][]{
                {5, 6},
                {7, 8}
        });

        DoubleMatrix expected = new DoubleMatrix(new double[][]{
                {6, 8},
                {10, 12}
        });

        assertThat(a.zipWith(b, Maja::add)).isEqualTo(expected);
    }

    @Test
    public void testDet() {
        DoubleMatrix a = new DoubleMatrix(new double[][]{
                {1, 2},
                {3, 4}
        });

        assertThat(a.det()).isEqualTo(-2);

        DoubleMatrix b = new DoubleMatrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });

        assertThat(b.det()).isEqualTo(0);

        DoubleMatrix c = new DoubleMatrix(new double[][]{
                {1, 2, 33, 4},
                {5, 6, 7, 8},
                {12, 0, 1, 2},
                {3, 4, 5, 6}
        });

        assertThat(c.det()).isEqualTo(-1560);

        DoubleMatrix d = new DoubleMatrix(new double[][]{
                {1, 2, 33, 4, 5},
                {5, 6, 7, 8, 9},
                {12, 0, 1, 2, 3},
                {3, 44, 5, 6, 7},
                {1, 2, 3, 4, 5}
        });

        assertThat(d.det()).isEqualTo(62400);
    }

    @Test
    public void testPerm() {
        DoubleMatrix a = new DoubleMatrix(new double[][]{
                {1, 2},
                {3, 4}
        });

        assertThat(a.perm()).isEqualTo(10);

        DoubleMatrix b = new DoubleMatrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });

        assertThat(b.perm()).isEqualTo(450);

        DoubleMatrix c = new DoubleMatrix(new double[][]{
                {1, 2, 33, 4},
                {5, 6, 7, 8},
                {12, 0, 1, 2},
                {3, 4, 5, 6}
        });

        assertThat(c.perm()).isEqualTo(34816);

        DoubleMatrix d = new DoubleMatrix(new double[][]{
                {1, 2, 33, 4, 5},
                {5, 6, 7, 8, 9},
                {12, 0, 1, 2, 3},
                {3, 44, 5, 6, 7},
                {1, 2, 3, 4, 5}
        });

        assertThat(d.perm()).isEqualTo(2212552);
    }

    @Test
    public void testAlt() {
        DoubleMatrix a = new DoubleMatrix(new double[][]{
                {1, 2},
                {3, 4}
        });

        assertThat(a.alt(Maja::sub, Maja::sub)).isEqualTo(-4);

        DoubleMatrix b = new DoubleMatrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });

        assertThat(b.alt(Maja::sub, Maja::sub)).isEqualTo(4);
    }

    @Test
    public void testInverse() {
        // 2x2
        DoubleMatrix a = new DoubleMatrix(new double[][]{
                {1, 2},
                {3, 4}
        });

        DoubleMatrix expected = new DoubleMatrix(new double[][]{
                {-2, 1},
                {1.5, -0.5}
        });

        assertThat(a.invert()).isEqualTo(expected);

        // 3x3
        DoubleMatrix b = new DoubleMatrix(new double[][]{
                {1, 2, 3},
                {0, 1, 4},
                {5, 6, 0}
        });

        DoubleMatrix expected2 = new DoubleMatrix(new double[][]{
                {-24, 18, 5},
                {20, -15, -4},
                {-5, 4, 1}
        });

        assertThat(b.invert()).isEqualTo(expected2);

        // 4x4
        DoubleMatrix c = new DoubleMatrix(new double[][]{
                {1, 2, 3, 4},
                {0, 1, 4, 5},
                {6, 7, 0, 8},
                {9, 0, 1, 2}
        });

        DoubleMatrix expected3 = new DoubleMatrix(new double[][]{
                {0.09602649006622517, -0.09933774834437085, -0.013245033112582781, 0.10927152317880795},
                {0.8940397350993378, -0.6490066225165563, -0.019867549668874173, -0.08609271523178808},
                {0.8443708609271523, -0.39072847682119205, -0.18543046357615894, 0.029801324503311258},
                {-0.8543046357615894, 0.6423841059602649, 0.152317880794702, -0.006622516556291391}
        });

        assertThat(c.invert()).isEqualTo(expected3);

        // 5x5
        DoubleMatrix d = new DoubleMatrix(new double[][]{
                {1, 2, 3, 4, 5},
                {0, 1, 4, 5, 6},
                {7, 8, 0, 9, 10},
                {11, 12, 13, 0, 14},
                {15, 16, 17, 18, 0}
        });

        DoubleMatrix expected4 = new DoubleMatrix(new double[][]{
                { -3.6879999999999984, 2.143999999999999, 0.36533333333333334, 0.13733333333333325, 0.041333333333333264 },
                { 3.933333333333332, -2.4666666666666655, -0.32592592592592595, -0.11481481481481473, -0.025925925925925852 },
                { -0.16266666666666674, 0.18133333333333335, -0.0708148148148148, 0.030962962962962956, 0.021185185185185182 },
                { -0.26933333333333304, 0.23466666666666647, 0.05214814814814813, -0.041629629629629634, 0.02414814814814814 },
                { -0.3226666666666666, 0.26133333333333325, 0.058074074074074084, 0.03318518518518518, -0.02992592592592593 }
        });

        assertThat(d.invert()).isEqualTo(expected4);

        // 1x1
        DoubleMatrix e = new DoubleMatrix(new double[][]{
                {1}
        });

        DoubleMatrix expected5 = new DoubleMatrix(new double[][]{
                {1}
        });

        assertThat(e.invert()).isEqualTo(expected5);
    }

    @Test
    public void testEigenvalues() {
        DoubleMatrix a = new DoubleMatrix(new double[][]{
                {1, 2},
                {3, 4}
        });

        Complex[] expected = new Complex[]{
                new Complex(-0.3722813232690143, 0),
                new Complex(5.372281323269014, 0)
        };

        assertThat(a.eigen().e()).isEqualTo(expected);

        DoubleMatrix b = new DoubleMatrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });

        Complex[] expected2 = new Complex[]{
                new Complex(16.116843969807057, 0),
                new Complex(-1.1168439698070447, 0),
                new Complex(-8.046297179356069E-16, 0) // slightly above machep. should be 0.
        };

        assertThat(b.eigen().e()).isEqualTo(expected2);
    }
}
