package rocks.palaiologos.maja.expression;

import org.antlr.v4.runtime.tree.*;
import rocks.palaiologos.maja.Complex;
import rocks.palaiologos.maja.Maja;
import rocks.palaiologos.maja.matrix.ComplexMatrix;
import rocks.palaiologos.maja.matrix.DoubleMatrix;
import rocks.palaiologos.maja.matrix.Matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DefaultExpressionVisitor extends AbstractParseTreeVisitor<Object> implements ExpressionVisitor<Object> {
    private Environment env;

    public DefaultExpressionVisitor(Environment env) {
        this.env = env;

        this.env.set("i", Maja.I);

        this.env.set("sin", new ExpressionFunction() {
            @Override
            public List<String> params() {
                return List.of("x");
            }

            @Override
            public Object eval() {
                Object x = env.get("x");
                if (x instanceof Complex c) {
                    return Maja.sin(c);
                } else if (x instanceof Double d) {
                    return Maja.sin(d);
                } else if (x instanceof Long l) {
                    return Maja.sin(l);
                }

                throw new RuntimeException("Invalid argument type for sin(x).");
            }
        });
    }

    @Override
    public List<Object> visitMain(ExpressionParser.MainContext ctx) {
        return ctx.toplevel().stream().map(this::visit).toList();
    }

    @Override
    public Object visitToplevel(ExpressionParser.ToplevelContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public Object visitBlock(ExpressionParser.BlockContext ctx) {
        Environment old = env; env = env.createChild();
        Object last = null;
        for (int i = 0; i < ctx.toplevel().size(); i++) {
            if (ctx.toplevel(i).expression() != null) {
                return visit(ctx.toplevel(i).expression());
            } else {
                last = visit(ctx.toplevel(i));
            }
        }
        env = old;
        return last;
    }

    @Override
    public Object visitSimpleAssignment(ExpressionParser.SimpleAssignmentContext ctx) {
        Object value = visit(ctx.expression());
        env.set(ctx.ID().getText(), value);
        return value;
    }

    @Override
    public Object visitSimpleFunctionDeclaration(ExpressionParser.SimpleFunctionDeclarationContext ctx) {
        List<String> args = ctx.ID().stream().skip(1).map(ParseTree::getText).toList();
        var f = new ExpressionFunction() {
            @Override
            public List<String> params() {
                return args;
            }

            @Override
            public Object eval() {
                return visit(ctx.expression());
            }
        };
        env.set(ctx.ID(0).getText(), f);
        return f;
    }

    @Override
    public Object visitFunctionDeclaration(ExpressionParser.FunctionDeclarationContext ctx) {
        List<String> args = ctx.ID().stream().skip(1).map(ParseTree::getText).toList();
        var f = new ExpressionFunction() {
            @Override
            public List<String> params() {
                return args;
            }

            @Override
            public Object eval() {
                try {
                    Object res = visit(ctx.block());
                    if (res == null)
                        throw new RuntimeException("Function " + ctx.ID(0).getText() + " did not return a value.");
                    return res;
                } catch (ReturnError err) {
                    return err.value;
                }
            }
        };
        env.set(ctx.ID(0).getText(), f);
        return f;
    }

    private boolean truthy(Object cond) {
        boolean b;
        if (cond instanceof Long l) {
            b = l != 0;
        } else if (cond instanceof Double d) {
            b = d != 0;
        } else if (cond instanceof Complex c) {
            b = Maja.ne(c, Complex.ZERO);
        } else {
            throw new RuntimeException("Invalid condition type.");
        }
        return b;
    }

    @Override
    public Object visitIf(ExpressionParser.IfContext ctx) {
        Object cond = visit(ctx.expression());
        if (truthy(cond)) {
            return visit(ctx.block(0));
        } else if (ctx.block().size() > 1) {
            return visit(ctx.block(1));
        }
        return null;
    }

    @Override
    public Object visitSimpleIf(ExpressionParser.SimpleIfContext ctx) {
        Object cond = visit(ctx.expression(0));
        if (truthy(cond)) {
            return visit(ctx.expression(1));
        } else if (ctx.expression().size() > 2) {
            return visit(ctx.expression(2));
        }
        return null;
    }

    @Override
    public Object visitWhile(ExpressionParser.WhileContext ctx) {
        Object r = null;
        while(truthy(visit(ctx.expression())))
            r = visit(ctx.block());
        return r;
    }

    private static Object determineStep(Object begin, Object end) {
        if (begin instanceof Long l1 && end instanceof Long l2) {
            return l1 < l2 ? 1L : -1L;
        } else if (begin instanceof Double d1 && end instanceof Double d2) {
            return d1 < d2 ? 1.0 : -1.0;
        } else if (begin instanceof Long l1 && end instanceof Double d2) {
            return l1 < d2 ? 1.0 : -1.0;
        } else if (begin instanceof Double d1 && end instanceof Long l2) {
            return d1 < l2 ? 1.0 : -1.0;
        } else {
            throw new RuntimeException("Invalid type for for loop.");
        }
    }

    private static boolean mayFor(Object cur, Object end) {
        if (cur instanceof Long l1 && end instanceof Long l2) {
            return l1 < l2;
        } else if (cur instanceof Double d1 && end instanceof Double d2) {
            return d1 < d2;
        } else if (cur instanceof Long l1 && end instanceof Double d2) {
            return l1 < d2;
        } else if (cur instanceof Double d1 && end instanceof Long l2) {
            return d1 < l2;
        } else {
            throw new RuntimeException("Invalid type for for loop.");
        }
    }

    private static Object addFor(Object cur, Object step) {
        if (cur instanceof Long l1 && step instanceof Long l2) {
            return l1 + l2;
        } else if (cur instanceof Double d1 && step instanceof Double d2) {
            return d1 + d2;
        } else if (cur instanceof Long l1 && step instanceof Double d2) {
            return l1 + d2;
        } else if (cur instanceof Double d1 && step instanceof Long l2) {
            return d1 + l2;
        } else {
            throw new RuntimeException("Invalid type for for loop.");
        }
    }

    @Override
    public Object visitFor(ExpressionParser.ForContext ctx) {
        String objname = ctx.ID().getText();
        Object initial = visit(ctx.expression(0));
        Object end = visit(ctx.expression(1));
        Object step = ctx.expression().size() > 2 ? visit(ctx.expression(2)) : (determineStep(initial, end));
        Environment old = env; env = env.createChild();
        env.set(objname, initial);
        Object r = null;
        while(mayFor(env.get(objname), end)) {
            r = visit(ctx.block());
            env.set(objname, addFor(env.get(objname), step));
        }
        env = old;
        return r;
    }

    @Override
    public Object visitReturn(ExpressionParser.ReturnContext ctx) {
        throw new ReturnError(visit(ctx.expression()));
    }

    @Override
    public Object visitExprGcd(ExpressionParser.ExprGcdContext ctx) {
        Object a = visit(ctx.expression(0)), b = visit(ctx.expression(1));
        if (a instanceof Long l1 && b instanceof Long l2) {
            return Maja.gcd(l1, l2);
        } else if (a instanceof Double d1 && b instanceof Double d2) {
            return Maja.gcd(d1, d2);
        } else if (a instanceof Complex c1 && b instanceof Complex c2) {
            return Maja.gcd(c1, c2);
        } else if (a instanceof Long l1 && b instanceof Double d2) {
            return Maja.gcd(l1, d2);
        } else if (a instanceof Double d1 && b instanceof Long l2) {
            return Maja.gcd(d1, l2);
        } else if (a instanceof Long l1 && b instanceof Complex c2) {
            return Maja.gcd(new Complex(l1), c2);
        } else if (a instanceof Complex c1 && b instanceof Long l2) {
            return Maja.gcd(c1, new Complex(l2));
        } else if (a instanceof Double d1 && b instanceof Complex c2) {
            return Maja.gcd(new Complex(d1), c2);
        } else if (a instanceof Complex c1 && b instanceof Double d2) {
            return Maja.gcd(c1, new Complex(d2));
        } else if (a instanceof DoubleMatrix dm1 && b instanceof Long l2) {
            return dm1.map(d -> Maja.gcd(d, l2));
        } else if (a instanceof Long l1 && b instanceof DoubleMatrix dm2) {
            return dm2.map(d -> Maja.gcd(l1, d));
        } else if (a instanceof DoubleMatrix dm1 && b instanceof DoubleMatrix dm2) {
            return dm1.zipWith(dm2, Maja::gcd);
        } else if (a instanceof DoubleMatrix dm1 && b instanceof Double d2) {
            return dm1.map(d -> Maja.gcd(d, d2));
        } else if (a instanceof Double d1 && b instanceof DoubleMatrix dm2) {
            return dm2.map(d -> Maja.gcd(d1, d));
        } else if (a instanceof DoubleMatrix dm1 && b instanceof Complex c2) {
            return ComplexMatrix.into(dm1.retype(Complex::new).map(c -> Maja.gcd(c, c2)));
        } else if (a instanceof Complex c1 && b instanceof DoubleMatrix dm2) {
            return ComplexMatrix.into(dm2.retype(Complex::new).map(c -> Maja.gcd(c1, c)));
        } else if (a instanceof ComplexMatrix dm1 && b instanceof Long l2) {
            Complex cl2 = new Complex(l2);
            return dm1.map(d -> Maja.gcd(d, cl2));
        } else if (a instanceof Long l1 && b instanceof ComplexMatrix dm2) {
            Complex cl1 = new Complex(l1);
            return dm2.map(d -> Maja.gcd(cl1, d));
        } else if (a instanceof ComplexMatrix dm1 && b instanceof ComplexMatrix dm2) {
            return dm1.zipWith(dm2, Maja::gcd);
        } else if (a instanceof ComplexMatrix dm1 && b instanceof Double d2) {
            Complex cd2 = new Complex(d2);
            return dm1.map(d -> Maja.gcd(d, cd2));
        } else if (a instanceof Double d1 && b instanceof ComplexMatrix dm2) {
            Complex cd1 = new Complex(d1);
            return dm2.map(d -> Maja.gcd(cd1, d));
        } else if (a instanceof ComplexMatrix dm1 && b instanceof Complex c2) {
            return dm1.map(d -> Maja.gcd(d, c2));
        } else if (a instanceof Complex c1 && b instanceof ComplexMatrix dm2) {
            return dm2.map(d -> Maja.gcd(c1, d));
        } else {
            throw new RuntimeException("Invalid type for gcd.");
        }
    }

    @Override
    public Object visitExprIndex(ExpressionParser.ExprIndexContext ctx) {
        throw new RuntimeException("TOOD");
    }

    @Override
    public Object visitExprNeg(ExpressionParser.ExprNegContext ctx) {
        Object a = visit(ctx.expression());
        if (a instanceof Long l) {
            return -l;
        } else if (a instanceof Double d) {
            return -d;
        } else if (a instanceof Complex c) {
            return Maja.negate(c);
        } else {
            throw new RuntimeException("Invalid type for -.");
        }
    }

    @Override
    public Object visitExprNot(ExpressionParser.ExprNotContext ctx) {
        Object a = visit(ctx.expression());
        if (a instanceof Long l) {
            return (l == 0) ? 1 : 0;
        } else if (a instanceof Double d) {
            return (d == 0) ? 1 : 0;
        } else if (a instanceof Complex c) {
            return Maja.eq(c, Complex.ZERO) ? 1 : 0;
        } else {
            throw new RuntimeException("Invalid type for ~.");
        }
    }

    @Override
    public Object visitExprPos(ExpressionParser.ExprPosContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Object visitExprDiv(ExpressionParser.ExprDivContext ctx) {
        Object a = visit(ctx.expression(0)), b = visit(ctx.expression(1));
        if (a instanceof Long l1 && b instanceof Long l2) {
            return Maja.div(l1, l2);
        } else if (a instanceof Double d1 && b instanceof Double d2) {
            return Maja.div(d1, d2);
        } else if (a instanceof Complex c1 && b instanceof Complex c2) {
            return Maja.div(c1, c2);
        } else if (a instanceof Long l1 && b instanceof Double d2) {
            return Maja.div(l1, d2);
        } else if (a instanceof Double d1 && b instanceof Long l2) {
            return Maja.div(d1, l2);
        } else if (a instanceof Long l1 && b instanceof Complex c2) {
            return Maja.div(new Complex(l1), c2);
        } else if (a instanceof Complex c1 && b instanceof Long l2) {
            return Maja.div(c1, new Complex(l2));
        } else if (a instanceof Double d1 && b instanceof Complex c2) {
            return Maja.div(new Complex(d1), c2);
        } else if (a instanceof Complex c1 && b instanceof Double d2) {
            return Maja.div(c1, new Complex(d2));
        } else if (a instanceof DoubleMatrix dm1 && b instanceof Long l2) {
            return dm1.map(d -> Maja.div(d, l2));
        } else if (a instanceof Long l1 && b instanceof DoubleMatrix dm2) {
            return dm2.map(d -> Maja.div(l1, d));
        } else if (a instanceof DoubleMatrix dm1 && b instanceof DoubleMatrix dm2) {
            return dm1.zipWith(dm2, Maja::div);
        } else if (a instanceof DoubleMatrix dm1 && b instanceof Double d2) {
            return dm1.map(d -> Maja.div(d, d2));
        } else if (a instanceof Double d1 && b instanceof DoubleMatrix dm2) {
            return dm2.map(d -> Maja.div(d1, d));
        } else if (a instanceof DoubleMatrix dm1 && b instanceof Complex c2) {
            return ComplexMatrix.into(dm1.retype(Complex::new).map(c -> Maja.div(c, c2)));
        } else if (a instanceof Complex c1 && b instanceof DoubleMatrix dm2) {
            return ComplexMatrix.into(dm2.retype(Complex::new).map(c -> Maja.div(c1, c)));
        } else if (a instanceof ComplexMatrix dm1 && b instanceof Long l2) {
            Complex cl2 = new Complex(l2);
            return dm1.map(d -> Maja.div(d, cl2));
        } else if (a instanceof Long l1 && b instanceof ComplexMatrix dm2) {
            Complex cl1 = new Complex(l1);
            return dm2.map(d -> Maja.div(cl1, d));
        } else if (a instanceof ComplexMatrix dm1 && b instanceof ComplexMatrix dm2) {
            return dm1.zipWith(dm2, Maja::div);
        } else if (a instanceof ComplexMatrix dm1 && b instanceof Double d2) {
            Complex cd2 = new Complex(d2);
            return dm1.map(d -> Maja.div(d, cd2));
        } else if (a instanceof Double d1 && b instanceof ComplexMatrix dm2) {
            Complex cd1 = new Complex(d1);
            return dm2.map(d -> Maja.div(cd1, d));
        } else if (a instanceof ComplexMatrix dm1 && b instanceof Complex c2) {
            return dm1.map(d -> Maja.div(d, c2));
        } else if (a instanceof Complex c1 && b instanceof ComplexMatrix dm2) {
            return dm2.map(d -> Maja.div(c1, d));
        } else {
            throw new RuntimeException("Invalid type for /.");
        }
    }

    @Override
    public Object visitExprOr(ExpressionParser.ExprOrContext ctx) {
        Object a = visit(ctx.expression(0)), b = visit(ctx.expression(1));
        if (a instanceof Long l1 && b instanceof Long l2) {
            return (l1 != 0 || l2 != 0) ? 1 : 0;
        } else if (a instanceof Double d1 && b instanceof Double d2) {
            return (d1 != 0 || d2 != 0) ? 1 : 0;
        } else if (a instanceof Complex c1 && b instanceof Complex c2) {
            return (Maja.ne(c1, Complex.ZERO) || Maja.ne(c2, Complex.ZERO)) ? 1 : 0;
        } else if (a instanceof Long l1 && b instanceof Double d2) {
            return (l1 != 0 || d2 != 0) ? 1 : 0;
        } else if (a instanceof Double d1 && b instanceof Long l2) {
            return (d1 != 0 || l2 != 0) ? 1 : 0;
        } else if (a instanceof Long l1 && b instanceof Complex c2) {
            return (Maja.ne(new Complex(l1), Complex.ZERO) || Maja.ne(c2, Complex.ZERO)) ? 1 : 0;
        } else if (a instanceof Complex c1 && b instanceof Long l2) {
            return (Maja.ne(c1, Complex.ZERO) || Maja.ne(new Complex(l2), Complex.ZERO)) ? 1 : 0;
        } else if (a instanceof Double d1 && b instanceof Complex c2) {
            return (Maja.ne(new Complex(d1), Complex.ZERO) || Maja.ne(c2, Complex.ZERO)) ? 1 : 0;
        } else if (a instanceof Complex c1 && b instanceof Double d2) {
            return (Maja.ne(c1, Complex.ZERO) || Maja.ne(new Complex(d2), Complex.ZERO)) ? 1 : 0;
        } else if (a instanceof DoubleMatrix dm1 && b instanceof Long l2) {
            return dm1.map(d -> (d != 0 || l2 != 0) ? 1.0 : 0.0);
        } else if (a instanceof Long l1 && b instanceof DoubleMatrix dm2) {
            return dm2.map(d -> (l1 != 0 || d != 0) ? 1.0 : 0.0);
        } else if (a instanceof DoubleMatrix dm1 && b instanceof DoubleMatrix dm2) {
            return dm1.zipWith(dm2, (x, y) -> (x != 0 || y != 0) ? 1.0 : 0.0);
        } else if (a instanceof DoubleMatrix dm1 && b instanceof Double d2) {
            return dm1.map(d -> (d != 0 || d2 != 0) ? 1.0 : 0.0);
        } else if (a instanceof Double d1 && b instanceof DoubleMatrix dm2) {
            return dm2.map(d -> (d1 != 0 || d != 0) ? 1.0 : 0.0);
        } else if (a instanceof DoubleMatrix dm1 && b instanceof Complex c2) {
            return dm1.map(d -> (Maja.ne(c2, Complex.ZERO) || d != 0) ? 1.0 : 0.0);
        } else if (a instanceof Complex c1 && b instanceof DoubleMatrix dm2) {
            return dm2.map(d -> (Maja.ne(c1, Complex.ZERO) || d != 0) ? 1.0 : 0.0);
        } else if (a instanceof ComplexMatrix dm1 && b instanceof Long l2) {
            return dm1.map(d -> (Maja.ne(d, Complex.ZERO) || l2 != 0) ? Complex.ONE : Complex.ZERO);
        } else if (a instanceof Long l1 && b instanceof ComplexMatrix dm2) {
            return dm2.map(d -> (Maja.ne(d, Complex.ZERO) || l1 != 0) ? Complex.ONE : Complex.ZERO);
        } else if (a instanceof ComplexMatrix dm1 && b instanceof ComplexMatrix dm2) {
            return dm1.zipWith(dm2, (x, y) -> (Maja.ne(x, Complex.ZERO) || Maja.ne(y, Complex.ZERO)) ? Complex.ONE : Complex.ZERO);
        } else if (a instanceof ComplexMatrix dm1 && b instanceof Double d2) {
            return dm1.map(d -> (Maja.ne(d, Complex.ZERO) || d2 != 0) ? Complex.ONE : Complex.ZERO);
        } else if (a instanceof Double d1 && b instanceof ComplexMatrix dm2) {
            return dm2.map(d -> (Maja.ne(d, Complex.ZERO) || d1 != 0) ? Complex.ONE : Complex.ZERO);
        } else if (a instanceof ComplexMatrix dm1 && b instanceof Complex c2) {
            return dm1.map(d -> (Maja.ne(d, Complex.ZERO) || Maja.ne(c2, Complex.ZERO)) ? Complex.ONE : Complex.ZERO);
        } else if (a instanceof Complex c1 && b instanceof ComplexMatrix dm2) {
            return dm2.map(d -> (Maja.ne(d, Complex.ZERO) || Maja.ne(c1, Complex.ZERO)) ? Complex.ONE : Complex.ZERO);
        } else {
            throw new RuntimeException("Invalid type for ||.");
        }
    }

    @Override
    public Object visitExprSub(ExpressionParser.ExprSubContext ctx) {
        Object a = visit(ctx.expression(0)), b = visit(ctx.expression(1));
        if (a instanceof Long l1 && b instanceof Long l2) {
            return Maja.sub(l1, l2);
        } else if (a instanceof Double d1 && b instanceof Double d2) {
            return Maja.sub(d1, d2);
        } else if (a instanceof Complex c1 && b instanceof Complex c2) {
            return Maja.sub(c1, c2);
        } else if (a instanceof Long l1 && b instanceof Double d2) {
            return Maja.sub(l1, d2);
        } else if (a instanceof Double d1 && b instanceof Long l2) {
            return Maja.sub(d1, l2);
        } else if (a instanceof Long l1 && b instanceof Complex c2) {
            return Maja.sub(new Complex(l1), c2);
        } else if (a instanceof Complex c1 && b instanceof Long l2) {
            return Maja.sub(c1, new Complex(l2));
        } else if (a instanceof Double d1 && b instanceof Complex c2) {
            return Maja.sub(new Complex(d1), c2);
        } else if (a instanceof Complex c1 && b instanceof Double d2) {
            return Maja.sub(c1, new Complex(d2));
        } else if (a instanceof DoubleMatrix dm1 && b instanceof Long l2) {
            return dm1.map(d -> Maja.sub(d, l2));
        } else if (a instanceof Long l1 && b instanceof DoubleMatrix dm2) {
            return dm2.map(d -> Maja.sub(l1, d));
        } else if (a instanceof DoubleMatrix dm1 && b instanceof DoubleMatrix dm2) {
            return dm1.zipWith(dm2, Maja::sub);
        } else if (a instanceof DoubleMatrix dm1 && b instanceof Double d2) {
            return dm1.map(d -> Maja.sub(d, d2));
        } else if (a instanceof Double d1 && b instanceof DoubleMatrix dm2) {
            return dm2.map(d -> Maja.sub(d1, d));
        } else if (a instanceof DoubleMatrix dm1 && b instanceof Complex c2) {
            return ComplexMatrix.into(dm1.retype(Complex::new).map(c -> Maja.sub(c, c2)));
        } else if (a instanceof Complex c1 && b instanceof DoubleMatrix dm2) {
            return ComplexMatrix.into(dm2.retype(Complex::new).map(c -> Maja.sub(c1, c)));
        } else if (a instanceof ComplexMatrix dm1 && b instanceof Long l2) {
            Complex cl2 = new Complex(l2);
            return dm1.map(d -> Maja.sub(d, cl2));
        } else if (a instanceof Long l1 && b instanceof ComplexMatrix dm2) {
            Complex cl1 = new Complex(l1);
            return dm2.map(d -> Maja.sub(cl1, d));
        } else if (a instanceof ComplexMatrix dm1 && b instanceof ComplexMatrix dm2) {
            return dm1.zipWith(dm2, Maja::sub);
        } else if (a instanceof ComplexMatrix dm1 && b instanceof Double d2) {
            Complex cd2 = new Complex(d2);
            return dm1.map(d -> Maja.sub(d, cd2));
        } else if (a instanceof Double d1 && b instanceof ComplexMatrix dm2) {
            Complex cd1 = new Complex(d1);
            return dm2.map(d -> Maja.sub(cd1, d));
        } else if (a instanceof ComplexMatrix dm1 && b instanceof Complex c2) {
            return dm1.map(d -> Maja.sub(d, c2));
        } else if (a instanceof Complex c1 && b instanceof ComplexMatrix dm2) {
            return dm2.map(d -> Maja.sub(c1, d));
        } else {
            throw new RuntimeException("Invalid type for -.");
        }
    }

    @Override
    public Object visitExprMul(ExpressionParser.ExprMulContext ctx) {
        Object a = visit(ctx.expression(0)), b = visit(ctx.expression(1));
        if (a instanceof Long l1 && b instanceof Long l2) {
            return Maja.mul(l1, l2);
        } else if (a instanceof Double d1 && b instanceof Double d2) {
            return Maja.mul(d1, d2);
        } else if (a instanceof Complex c1 && b instanceof Complex c2) {
            return Maja.mul(c1, c2);
        } else if (a instanceof Long l1 && b instanceof Double d2) {
            return Maja.mul(l1, d2);
        } else if (a instanceof Double d1 && b instanceof Long l2) {
            return Maja.mul(d1, l2);
        } else if (a instanceof Long l1 && b instanceof Complex c2) {
            return Maja.mul(new Complex(l1), c2);
        } else if (a instanceof Complex c1 && b instanceof Long l2) {
            return Maja.mul(c1, new Complex(l2));
        } else if (a instanceof Double d1 && b instanceof Complex c2) {
            return Maja.mul(new Complex(d1), c2);
        } else if (a instanceof Complex c1 && b instanceof Double d2) {
            return Maja.mul(c1, new Complex(d2));
        } else if (a instanceof DoubleMatrix dm1 && b instanceof Long l2) {
            return dm1.map(d -> Maja.mul(d, l2));
        } else if (a instanceof Long l1 && b instanceof DoubleMatrix dm2) {
            return dm2.map(d -> Maja.mul(l1, d));
        } else if (a instanceof DoubleMatrix dm1 && b instanceof DoubleMatrix dm2) {
            return dm1.zipWith(dm2, Maja::mul);
        } else if (a instanceof DoubleMatrix dm1 && b instanceof Double d2) {
            return dm1.map(d -> Maja.mul(d, d2));
        } else if (a instanceof Double d1 && b instanceof DoubleMatrix dm2) {
            return dm2.map(d -> Maja.mul(d1, d));
        } else if (a instanceof DoubleMatrix dm1 && b instanceof Complex c2) {
            return ComplexMatrix.into(dm1.retype(Complex::new).map(c -> Maja.mul(c, c2)));
        } else if (a instanceof Complex c1 && b instanceof DoubleMatrix dm2) {
            return ComplexMatrix.into(dm2.retype(Complex::new).map(c -> Maja.mul(c1, c)));
        } else if (a instanceof ComplexMatrix dm1 && b instanceof Long l2) {
            Complex cl2 = new Complex(l2);
            return dm1.map(d -> Maja.mul(d, cl2));
        } else if (a instanceof Long l1 && b instanceof ComplexMatrix dm2) {
            Complex cl1 = new Complex(l1);
            return dm2.map(d -> Maja.mul(cl1, d));
        } else if (a instanceof ComplexMatrix dm1 && b instanceof ComplexMatrix dm2) {
            return dm1.zipWith(dm2, Maja::mul);
        } else if (a instanceof ComplexMatrix dm1 && b instanceof Double d2) {
            Complex cd2 = new Complex(d2);
            return dm1.map(d -> Maja.mul(d, cd2));
        } else if (a instanceof Double d1 && b instanceof ComplexMatrix dm2) {
            Complex cd1 = new Complex(d1);
            return dm2.map(d -> Maja.mul(cd1, d));
        } else if (a instanceof ComplexMatrix dm1 && b instanceof Complex c2) {
            return dm1.map(d -> Maja.mul(d, c2));
        } else if (a instanceof Complex c1 && b instanceof ComplexMatrix dm2) {
            return dm2.map(d -> Maja.mul(c1, d));
        } else {
            throw new RuntimeException("Invalid type for *.");
        }
    }

    @Override
    public Object visitExprGe(ExpressionParser.ExprGeContext ctx) {
        Object a = visit(ctx.expression(0)), b = visit(ctx.expression(1));
        if (a instanceof Long l1 && b instanceof Long l2) {
            return Maja.ge(l1, l2) ? 1 : 0;
        } else if (a instanceof Double d1 && b instanceof Double d2) {
            return Maja.ge(d1, d2) ? 1 : 0;
        } else if (a instanceof Long l1 && b instanceof Double d2) {
            return Maja.ge(l1, d2) ? 1 : 0;
        } else if (a instanceof Double d1 && b instanceof Long l2) {
            return Maja.ge(d1, l2) ? 1 : 0;
        } else {
            throw new RuntimeException("Invalid type for >=.");
        }
    }

    @Override
    public Object visitExprMod(ExpressionParser.ExprModContext ctx) {
        Object a = visit(ctx.expression(0)), b = visit(ctx.expression(1));
        if (a instanceof Long l1 && b instanceof Long l2) {
            return Maja.mod(l1, l2);
        } else if (a instanceof Double d1 && b instanceof Double d2) {
            return Maja.mod(d1, d2);
        } else if (a instanceof Long l1 && b instanceof Double d2) {
            return Maja.mod(l1, d2);
        } else if (a instanceof Double d1 && b instanceof Long l2) {
            return Maja.mod(d1, l2);
        } else {
            throw new RuntimeException("Invalid type for mod.");
        }
    }

    @Override
    public Object visitExprLe(ExpressionParser.ExprLeContext ctx) {
        Object a = visit(ctx.expression(0)), b = visit(ctx.expression(1));
        if (a instanceof Long l1 && b instanceof Long l2) {
            return Maja.le(l1, l2) ? 1 : 0;
        } else if (a instanceof Double d1 && b instanceof Double d2) {
            return Maja.le(d1, d2) ? 1 : 0;
        } else if (a instanceof Long l1 && b instanceof Double d2) {
            return Maja.le(l1, d2) ? 1 : 0;
        } else if (a instanceof Double d1 && b instanceof Long l2) {
            return Maja.le(d1, l2) ? 1 : 0;
        } else {
            throw new RuntimeException("Invalid type for <=.");
        }
    }

    @Override
    public Object visitExprParen(ExpressionParser.ExprParenContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Object visitExprInt(ExpressionParser.ExprIntContext ctx) {
        return Long.parseLong(ctx.INT().getText());
    }

    @Override
    public Object visitMatrix(ExpressionParser.MatrixContext ctx) {
        return ctx.expression().stream().map(this::visit).collect(Collectors.toList());
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Object visitExprMatrix(ExpressionParser.ExprMatrixContext ctx) {
        List constructor = ctx.matrix().stream().map(this::visit).map(List.class::cast).collect(Collectors.toList());
        if (constructor.stream().allMatch(l -> ((List) l).stream().allMatch(o -> o instanceof Double))) {
            return new DoubleMatrix((List<List<Double>>) constructor);
        } else if (constructor.stream().allMatch(l -> ((List) l).stream().allMatch(o -> o instanceof Complex))) {
            return new ComplexMatrix((List<List<Complex>>) constructor);
        } else {
            return new Matrix(constructor);
        }
    }

    @Override
    public Object visitExprGt(ExpressionParser.ExprGtContext ctx) {
        Object a = visit(ctx.expression(0)), b = visit(ctx.expression(1));
        if (a instanceof Long l1 && b instanceof Long l2) {
            return Maja.gt(l1, l2) ? 1 : 0;
        } else if (a instanceof Double d1 && b instanceof Double d2) {
            return Maja.gt(d1, d2) ? 1 : 0;
        } else if (a instanceof Long l1 && b instanceof Double d2) {
            return Maja.gt(l1, d2) ? 1 : 0;
        } else if (a instanceof Double d1 && b instanceof Long l2) {
            return Maja.gt(d1, l2) ? 1 : 0;
        } else {
            throw new RuntimeException("Invalid type for >.");
        }
    }

    @Override
    public Object visitExprEq(ExpressionParser.ExprEqContext ctx) {
        Object a = visit(ctx.expression(0)), b = visit(ctx.expression(1));
        if (a instanceof Long l1 && b instanceof Long l2) {
            return Maja.eq(l1, l2) ? 1 : 0;
        } else if (a instanceof Double d1 && b instanceof Double d2) {
            return Maja.eq(d1, d2) ? 1 : 0;
        } else if (a instanceof Complex c1 && b instanceof Complex c2) {
            return Maja.eq(c1, c2) ? 1 : 0;
        } else if (a instanceof Long l1 && b instanceof Double d2) {
            return Maja.eq(l1, d2) ? 1 : 0;
        } else if (a instanceof Double d1 && b instanceof Long l2) {
            return Maja.eq(d1, l2) ? 1 : 0;
        } else if (a instanceof Long l1 && b instanceof Complex c2) {
            return Maja.eq(new Complex(l1), c2) ? 1 : 0;
        } else if (a instanceof Complex c1 && b instanceof Long l2) {
            return Maja.eq(c1, new Complex(l2)) ? 1 : 0;
        } else if (a instanceof Double d1 && b instanceof Complex c2) {
            return Maja.eq(new Complex(d1), c2) ? 1 : 0;
        } else if (a instanceof Complex c1 && b instanceof Double d2) {
            return Maja.eq(c1, new Complex(d2)) ? 1 : 0;
        } else {
            throw new RuntimeException("Invalid type for ==.");
        }
    }

    @Override
    public Object visitExprAnd(ExpressionParser.ExprAndContext ctx) {
        Object a = visit(ctx.expression(0)), b = visit(ctx.expression(1));
        if (a instanceof Long l1 && b instanceof Long l2) {
            return (l1 != 0 && l2 != 0) ? 1 : 0;
        } else if (a instanceof Double d1 && b instanceof Double d2) {
            return (d1 != 0 && d2 != 0) ? 1 : 0;
        } else if (a instanceof Complex c1 && b instanceof Complex c2) {
            return (Maja.ne(c1, Complex.ZERO) && Maja.ne(c2, Complex.ZERO)) ? 1 : 0;
        } else if (a instanceof Long l1 && b instanceof Double d2) {
            return (l1 != 0 && d2 != 0) ? 1 : 0;
        } else if (a instanceof Double d1 && b instanceof Long l2) {
            return (d1 != 0 && l2 != 0) ? 1 : 0;
        } else if (a instanceof Long l1 && b instanceof Complex c2) {
            return (Maja.ne(new Complex(l1), Complex.ZERO) && Maja.ne(c2, Complex.ZERO)) ? 1 : 0;
        } else if (a instanceof Complex c1 && b instanceof Long l2) {
            return (Maja.ne(c1, Complex.ZERO) && Maja.ne(new Complex(l2), Complex.ZERO)) ? 1 : 0;
        } else if (a instanceof Double d1 && b instanceof Complex c2) {
            return (Maja.ne(new Complex(d1), Complex.ZERO) && Maja.ne(c2, Complex.ZERO)) ? 1 : 0;
        } else if (a instanceof Complex c1 && b instanceof Double d2) {
            return (Maja.ne(c1, Complex.ZERO) && Maja.ne(new Complex(d2), Complex.ZERO)) ? 1 : 0;
        } else {
            throw new RuntimeException("Invalid type for &&.");
        }
    }

    @Override
    public Object visitExprPow(ExpressionParser.ExprPowContext ctx) {
        Object a = visit(ctx.expression(0)), b = visit(ctx.expression(1));
        if (a instanceof Long l1 && b instanceof Long l2) {
            return Maja.pow(l1, l2);
        } else if (a instanceof Double d1 && b instanceof Double d2) {
            return Maja.pow(d1, d2);
        } else if (a instanceof Complex c1 && b instanceof Complex c2) {
            return Maja.pow(c1, c2);
        } else if (a instanceof Long l1 && b instanceof Double d2) {
            return Maja.pow(l1, d2);
        } else if (a instanceof Double d1 && b instanceof Long l2) {
            return Maja.pow(d1, l2);
        } else if (a instanceof Long l1 && b instanceof Complex c2) {
            return Maja.pow(new Complex(l1), c2);
        } else if (a instanceof Complex c1 && b instanceof Long l2) {
            return Maja.pow(c1, new Complex(l2));
        } else if (a instanceof Double d1 && b instanceof Complex c2) {
            return Maja.pow(new Complex(d1), c2);
        } else if (a instanceof Complex c1 && b instanceof Double d2) {
            return Maja.pow(c1, new Complex(d2));
        } else {
            throw new RuntimeException("Invalid type for **.");
        }
    }

    @Override
    public Object visitExprFunctionCall(ExpressionParser.ExprFunctionCallContext ctx) {
        String function = ctx.ID().getText();
        Object callable = env.get(function);
        if (callable instanceof ExpressionFunction e) {
            if(e.params().size() != ctx.expression().size())
                throw new RuntimeException("Invalid number of arguments for function " + function + ".");
            Environment old = env;
            env = env.createChild();
            for(int i = 0; i < e.params().size(); i++)
                env.set(e.params().get(i), visit(ctx.expression(i)));
            Object result = e.eval();
            env = old;
            return result;
        } else {
            throw new RuntimeException("Invalid function call.");
        }
    }

    @Override
    public Object visitExprLcm(ExpressionParser.ExprLcmContext ctx) {
        Object a = visit(ctx.expression(0)), b = visit(ctx.expression(1));
        if (a instanceof Long l1 && b instanceof Long l2) {
            return Maja.lcm(l1, l2);
        } else if (a instanceof Double d1 && b instanceof Double d2) {
            return Maja.lcm(d1, d2);
        } else if (a instanceof Complex c1 && b instanceof Complex c2) {
            return Maja.lcm(c1, c2);
        } else if (a instanceof Long l1 && b instanceof Double d2) {
            return Maja.lcm(l1, d2);
        } else if (a instanceof Double d1 && b instanceof Long l2) {
            return Maja.lcm(d1, l2);
        } else if (a instanceof Long l1 && b instanceof Complex c2) {
            return Maja.lcm(new Complex(l1), c2);
        } else if (a instanceof Complex c1 && b instanceof Long l2) {
            return Maja.lcm(c1, new Complex(l2));
        } else if (a instanceof Double d1 && b instanceof Complex c2) {
            return Maja.lcm(new Complex(d1), c2);
        } else if (a instanceof Complex c1 && b instanceof Double d2) {
            return Maja.lcm(c1, new Complex(d2));
        } else {
            throw new RuntimeException("Invalid type for lcm.");
        }
    }

    @Override
    public Object visitExprLt(ExpressionParser.ExprLtContext ctx) {
        Object a = visit(ctx.expression(0)), b = visit(ctx.expression(1));
        if (a instanceof Long l1 && b instanceof Long l2) {
            return Maja.lt(l1, l2) ? 1 : 0;
        } else if (a instanceof Double d1 && b instanceof Double d2) {
            return Maja.lt(d1, d2) ? 1 : 0;
        } else if (a instanceof Long l1 && b instanceof Double d2) {
            return Maja.lt(l1, d2) ? 1 : 0;
        } else if (a instanceof Double d1 && b instanceof Long l2) {
            return Maja.lt(d1, l2) ? 1 : 0;
        } else {
            throw new RuntimeException("Invalid type for <.");
        }
    }

    @Override
    public Object visitExprRem(ExpressionParser.ExprRemContext ctx) {
        Object a = visit(ctx.expression(0)), b = visit(ctx.expression(1));
        if (a instanceof Long l1 && b instanceof Long l2) {
            return Maja.rem(l1, l2);
        } else if (a instanceof Double d1 && b instanceof Double d2) {
            return Maja.rem(d1, d2);
        } else if (a instanceof Complex c1 && b instanceof Complex c2) {
            return Maja.rem(c1, c2);
        } else if (a instanceof Long l1 && b instanceof Double d2) {
            return Maja.rem(l1, d2);
        } else if (a instanceof Double d1 && b instanceof Long l2) {
            return Maja.rem(d1, l2);
        } else if (a instanceof Long l1 && b instanceof Complex c2) {
            return Maja.rem(new Complex(l1), c2);
        } else if (a instanceof Complex c1 && b instanceof Long l2) {
            return Maja.rem(c1, new Complex(l2));
        } else if (a instanceof Double d1 && b instanceof Complex c2) {
            return Maja.rem(new Complex(d1), c2);
        } else if (a instanceof Complex c1 && b instanceof Double d2) {
            return Maja.rem(c1, new Complex(d2));
        } else {
            throw new RuntimeException("Invalid type for rem.");
        }
    }

    @Override
    public Object visitExprNeq(ExpressionParser.ExprNeqContext ctx) {
        Object a = visit(ctx.expression(0)), b = visit(ctx.expression(1));
        if (a instanceof Long l1 && b instanceof Long l2) {
            return Maja.ne(l1, l2) ? 1 : 0;
        } else if (a instanceof Double d1 && b instanceof Double d2) {
            return Maja.ne(d1, d2) ? 1 : 0;
        } else if (a instanceof Complex c1 && b instanceof Complex c2) {
            return Maja.ne(c1, c2) ? 1 : 0;
        } else if (a instanceof Long l1 && b instanceof Double d2) {
            return Maja.ne(l1, d2) ? 1 : 0;
        } else if (a instanceof Double d1 && b instanceof Long l2) {
            return Maja.ne(d1, l2) ? 1 : 0;
        } else if (a instanceof Long l1 && b instanceof Complex c2) {
            return Maja.ne(new Complex(l1), c2) ? 1 : 0;
        } else if (a instanceof Complex c1 && b instanceof Long l2) {
            return Maja.ne(c1, new Complex(l2)) ? 1 : 0;
        } else if (a instanceof Double d1 && b instanceof Complex c2) {
            return Maja.ne(new Complex(d1), c2) ? 1 : 0;
        } else if (a instanceof Complex c1 && b instanceof Double d2) {
            return Maja.ne(c1, new Complex(d2)) ? 1 : 0;
        } else {
            throw new RuntimeException("Invalid type for !=.");
        }
    }

    @Override
    public Object visitExprReal(ExpressionParser.ExprRealContext ctx) {
        return Double.parseDouble(ctx.REAL().getText());
    }

    @Override
    public Object visitExprVariable(ExpressionParser.ExprVariableContext ctx) {
        return env.get(ctx.ID().getText());
    }

    @Override
    public Object visitExprAdd(ExpressionParser.ExprAddContext ctx) {
        Object a = visit(ctx.expression(0)), b = visit(ctx.expression(1));
        if (a instanceof Long l1 && b instanceof Long l2) {
            return Maja.add(l1, l2);
        } else if (a instanceof Double d1 && b instanceof Double d2) {
            return Maja.add(d1, d2);
        } else if (a instanceof Complex c1 && b instanceof Complex c2) {
            return Maja.add(c1, c2);
        } else if (a instanceof Long l1 && b instanceof Double d2) {
            return Maja.add(l1, d2);
        } else if (a instanceof Double d1 && b instanceof Long l2) {
            return Maja.add(d1, l2);
        } else if (a instanceof Long l1 && b instanceof Complex c2) {
            return Maja.add(new Complex(l1), c2);
        } else if (a instanceof Complex c1 && b instanceof Long l2) {
            return Maja.add(c1, new Complex(l2));
        } else if (a instanceof Double d1 && b instanceof Complex c2) {
            return Maja.add(new Complex(d1), c2);
        } else if (a instanceof Complex c1 && b instanceof Double d2) {
            return Maja.add(c1, new Complex(d2));
        } else {
            throw new RuntimeException("Invalid type for +.");
        }
    }
}
