package rocks.palaiologos.maja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Expression {
    private static final String[] operators = {
            "+", "-", "*", "/", "rem", "mod", "!", "lcm", "gcd", "**", "|"
    };

    enum TokenType {
        NUM, OP, LPAREN, RPAREN, COMMA, NAME, QUOTING, EOF
    }

    static class Token {
        TokenType type;
        String value;

        Token(TokenType type, String value) {
            this.type = type;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("%s(%s)", type, value);
        }
    }

    static class Tokenizer {
        private String input;
        private int pos;

        Tokenizer(String input) {
            this.input = input;
            this.pos = 0;
        }

        private Token name() {
            int begin = pos;
            while (pos < input.length() && Character.isLetterOrDigit(input.charAt(pos)) || input.charAt(pos) == '\'')
                pos++;
            return new Token(TokenType.NAME, input.substring(begin, pos));
        }

        private Token digit() {
            // Parse numbers like 1.623e-5.
            int begin = pos;
            while (pos < input.length() && Character.isDigit(input.charAt(pos)))
                pos++;
            if (pos < input.length() && input.charAt(pos) == '.') {
                pos++;
                while (pos < input.length() && Character.isDigit(input.charAt(pos)))
                    pos++;
            }
            if (pos < input.length() && (input.charAt(pos) == 'e' || input.charAt(pos) == 'E')) {
                pos++;
                if (pos < input.length() && (input.charAt(pos) == '+' || input.charAt(pos) == '-'))
                    pos++;
                while (pos < input.length() && Character.isDigit(input.charAt(pos)))
                    pos++;
            }
            return new Token(TokenType.NUM, input.substring(begin, pos));
        }

        Token nextToken() {
            if (pos >= input.length()) {
                return new Token(TokenType.EOF, "");
            }

            char c = input.charAt(pos);
            if (Character.isDigit(c)) {
                return digit();
            } else if (c == '(') {
                pos++;
                return new Token(TokenType.LPAREN, "(");
            } else if (c == ')') {
                pos++;
                return new Token(TokenType.RPAREN, ")");
            } else if (c == ',') {
                pos++;
                return new Token(TokenType.COMMA, ",");
            } else if (c == ' ' || c == '\t' || c == '\r' || c == '\n') {
                pos++;
                return nextToken();
            } else if (c == '-') {
                pos++;
                return new Token(TokenType.OP, "-");
            } else if (c == '+') {
                pos++;
                return new Token(TokenType.OP, "+");
            } else if (c == '*') {
                if (pos + 1 < input.length() && input.charAt(pos + 1) == '*') {
                    pos += 2;
                    return new Token(TokenType.OP, "**");
                } else {
                    pos++;
                    return new Token(TokenType.OP, "*");
                }
            } else if (c == '/') {
                pos++;
                return new Token(TokenType.OP, "/");
            } else if (c == '|') {
                pos++;
                return new Token(TokenType.OP, "|");
            } else if (c == '!') {
                pos++;
                return new Token(TokenType.OP, "!");
            } else if (c == 'r') {
                if (pos + 2 < input.length() && input.charAt(pos + 1) == 'e' && input.charAt(pos + 2) == 'm') {
                    pos += 3;
                    return new Token(TokenType.OP, "rem");
                } else {
                    return name();
                }
            } else if (c == 'm') {
                if (pos + 2 < input.length() && input.charAt(pos + 1) == 'o' && input.charAt(pos + 2) == 'd') {
                    pos += 3;
                    return new Token(TokenType.OP, "mod");
                } else {
                    return name();
                }
            } else if (Character.isLetter(c)) {
                return name();
            } else if (c == '[') {
                int brackets = 1;
                int begin = pos;
                pos++;
                while (pos < input.length() && brackets > 0) {
                    if (input.charAt(pos) == '[')
                        brackets++;
                    else if (input.charAt(pos) == ']')
                        brackets--;
                    pos++;
                }
                if (brackets > 0)
                    throw new ArithmeticException("Missing closing bracket");
                String s = input.substring(begin, pos);
                if(s.length() < 3)
                    throw new ArithmeticException("Empty quoting");
                return new Token(TokenType.QUOTING, s.substring(1, s.length() - 1));
            } else {
                throw new ArithmeticException("Unexpected character: " + c);
            }
        }
    }

    static class Evaluator {
        private Tokenizer tokenizer;
        private Token currentToken;
        private Map<String, Double> variables;

        Evaluator(String input, Map<String, Double> variables) {
            tokenizer = new Tokenizer(input);
            this.variables = variables;
            currentToken = tokenizer.nextToken();
        }

        private long mustLong(double d) {
            long l = (long) d;
            if (l != d)
                throw new ArithmeticException("Expected an exact integer value, got: " + d + ". Consider rounding?");
            return l;
        }

        private int mustInt(double d) {
            int i = (int) d;
            if (i != d)
                throw new ArithmeticException("Expected an exact integer value, got: " + d + ". Consider rounding?");
            return i;
        }

        private void eat(TokenType type) {
            if (currentToken.type == type) {
                currentToken = tokenizer.nextToken();
            } else {
                throw new ArithmeticException("Unexpected token: " + currentToken);
            }
        }

        /* Evaluate math expressions like: lerchPhi(23 lcm 1, !3e-1) */
        private double expr() {
            double result = term();
            while (currentToken.type == TokenType.OP && (currentToken.value.equals("+") || currentToken.value.equals("-"))) {
                Token op = currentToken;
                eat(TokenType.OP);
                if (op.value.equals("+")) {
                    result += term();
                } else if (op.value.equals("-")) {
                    result -= term();
                }
            }
            return result;
        }

        private double term() {
            double result = factor();
            while (currentToken.type != TokenType.OP || (currentToken.value.equals("*") || currentToken.value.equals("/"))) {
                if(currentToken.type == TokenType.EOF || currentToken.type == TokenType.RPAREN
                || currentToken.type == TokenType.COMMA || currentToken.type == TokenType.QUOTING)
                    return result;
                if(currentToken.type == TokenType.OP) {
                    Token op = currentToken;
                    eat(TokenType.OP);
                    if (op.value.equals("*")) {
                        result *= factor();
                    } else if (op.value.equals("/")) {
                        result /= factor();
                    }
                } else {
                    // Implicit product:
                    result *= factor();
                }
            }
            return result;
        }

        // "!", "|"
        private double factor() {
            double result = unexp();
            while (currentToken.type == TokenType.OP && currentToken.value.equals("**") || currentToken.value.equals("rem") || currentToken.value.equals("mod") || currentToken.value.equals("lcm") || currentToken.value.equals("gcd")) {
                Token op = currentToken;
                eat(TokenType.OP);
                if (op.value.equals("**")) {
                    result = Maja.pow(result, unexp());
                } else if (op.value.equals("rem")) {
                    result = Maja.rem(result, unexp());
                } else if (op.value.equals("mod")) {
                    result = Maja.mod(result, unexp());
                } else if (op.value.equals("lcm")) {
                    result = Maja.lcm(mustLong(result), mustLong(unexp()));
                } else if (op.value.equals("gcd")) {
                    result = Maja.gcd(mustLong(result), mustLong(unexp()));
                }
            }
            return result;
        }

        private double unexp() {
            if (currentToken.type == TokenType.OP && currentToken.value.equals("-")) {
                eat(TokenType.OP);
                return -unexp();
            } else if (currentToken.type == TokenType.OP && currentToken.value.equals("+")) {
                eat(TokenType.OP);
                return unexp();
            } else if (currentToken.type == TokenType.OP && currentToken.value.equals("!")) {
                eat(TokenType.OP);
                return Maja.factorial(mustInt(unexp()));
            } else if (currentToken.type == TokenType.OP && currentToken.value.equals("|")) {
                eat(TokenType.OP);
                return Maja.abs(unexp());
            } else {
                return atom();
            }
        }

        private MonadicFunction wrap(String text, String arg) {
            if(!arg.startsWith("d"))
                throw new ArithmeticException("Expected the infinitesimal variable name starting with 'd', got: " + arg);
            String var = arg.substring(1);
            if(var.isEmpty())
                throw new ArithmeticException("Expected the infinitesimal variable name, got: " + arg);
            return x -> new Evaluator(text, Map.of(var, x)).expr();
        }

        private double atom() {
            if (currentToken.type == TokenType.NUM) {
                double result = Double.parseDouble(currentToken.value);
                eat(TokenType.NUM);
                return result;
            } else if (currentToken.type == TokenType.NAME) {
                // Can be either a function call, constant or variable.
                // Check if it's a function call.
                String name = currentToken.value;
                eat(TokenType.NAME);
                if (currentToken.type == TokenType.LPAREN) {
                    eat(TokenType.LPAREN);
                    switch(name) {
                        case "pick": {
                            int n = mustInt(expr());
                            if(n < 0)
                                throw new ArithmeticException("pick(n, ...) requires n >= 0");
                            for(int i = 0; i < n; i++) {
                                if(currentToken.type == TokenType.RPAREN)
                                    throw new ArithmeticException("pick(n, ...) requires n <= number of arguments");
                                eat(TokenType.COMMA);
                                expr();
                            }
                            eat(TokenType.COMMA);
                            double result = expr();
                            while(currentToken.type == TokenType.COMMA) {
                                eat(TokenType.COMMA);
                                expr();
                            }
                            eat(TokenType.RPAREN);
                            return result;
                        }
                        case "gausslegendre": {
                            // gausslegendre([x ** 2], -1, 1, dx, 10)
                            String f = currentToken.value;
                            eat(TokenType.COMMA);
                            double a = expr();
                            eat(TokenType.COMMA);
                            double b = expr();
                            eat(TokenType.COMMA);
                            String d = currentToken.value;
                            eat(TokenType.COMMA);
                            int n = mustInt(expr());
                            eat(TokenType.RPAREN);
                            return Maja.integrateGaussLegendre(wrap(f, d), a, b, n);
                        }
                        case "tanhsinh": {
                            // tanhsinh([x ** 2], -1, 1, dx, 10, 1e-7)
                            String f = currentToken.value;
                            eat(TokenType.COMMA);
                            double a = expr();
                            eat(TokenType.COMMA);
                            double b = expr();
                            eat(TokenType.COMMA);
                            String d = currentToken.value;
                            eat(TokenType.COMMA);
                            int n = mustInt(expr());
                            eat(TokenType.COMMA);
                            double eps = expr();
                            eat(TokenType.RPAREN);
                            return Maja.integrateTanhSinh(wrap(f, d), a, b, n, eps)[0];
                        }
                        case "simpson": {
                            // simpson([x ** 2], -1, 1, dx, 10)
                            String f = currentToken.value;
                            eat(TokenType.QUOTING);
                            eat(TokenType.COMMA);
                            double a = expr();
                            eat(TokenType.COMMA);
                            double b = expr();
                            eat(TokenType.COMMA);
                            String d = currentToken.value;
                            eat(TokenType.NAME);
                            eat(TokenType.COMMA);
                            int n = mustInt(expr());
                            eat(TokenType.RPAREN);
                            return Maja.integrateSimpson(wrap(f, d), a, b, n);
                        }
                        default:
                            ArrayList<Double> args = new ArrayList<>();
                            if (currentToken.type != TokenType.RPAREN) {
                                args.add(expr());
                                while (currentToken.type == TokenType.COMMA) {
                                    eat(TokenType.COMMA);
                                    args.add(expr());
                                }
                            }
                            eat(TokenType.RPAREN);
                            return call(name, args);
                    }
                } else {
                    // Check if it's a constant.
                    double constant = constant(name);
                    if (!Double.isNaN(constant)) {
                        return constant;
                    } else {
                        // Check if it's a variable.
                        Double variable = variables.get(name);
                        if (variable != null) {
                            return variable;
                        } else {
                            throw new ArithmeticException("Unknown variable/constant name: " + name);
                        }
                    }
                }
            } else if (currentToken.type == TokenType.LPAREN) {
                eat(TokenType.LPAREN);
                double result = expr();
                eat(TokenType.RPAREN);
                return result;
            } else {
                throw new ArithmeticException("Unexpected token: " + currentToken);
            }
        }

        private void assertArity(ArrayList<Double> args, int arity) {
            if (args.size() != arity)
                throw new ArithmeticException("Expected " + arity + " arguments, got: " + args.size());
        }

        private double evalMonadic(MonadicFunction f, ArrayList<Double> args) {
            assertArity(args, 1);
            return f.apply(args.get(0));
        }

        private double evalDyadic(DyadicFunction f, ArrayList<Double> args) {
            assertArity(args, 2);
            return f.apply(args.get(0), args.get(1));
        }

        private double evalTriadic(TriadicFunction f, ArrayList<Double> args) {
            assertArity(args, 3);
            return f.apply(args.get(0), args.get(1), args.get(2));
        }

        private double evalCmp(BiFunction<Double, Double, Boolean> f, ArrayList<Double> args) {
            assertArity(args, 2);
            return f.apply(args.get(0), args.get(1)) ? 1 : 0;
        }

        private double call(String name, ArrayList<Double> args) {
            return switch (name) {
                case "sin" -> evalMonadic(Maja::sin, args);
                case "cos" -> evalMonadic(Maja::cos, args);
                case "tan" -> evalMonadic(Maja::tan, args);
                case "asin" -> evalMonadic(Maja::asin, args);
                case "acos" -> evalMonadic(Maja::acos, args);
                case "atan" -> evalMonadic(Maja::atan, args);
                case "sinh" -> evalMonadic(Maja::sinh, args);
                case "cosh" -> evalMonadic(Maja::cosh, args);
                case "tanh" -> evalMonadic(Maja::tanh, args);
                case "cot" -> evalMonadic(Maja::cot, args);
                case "sec" -> evalMonadic(Maja::sec, args);
                case "csc" -> evalMonadic(Maja::csc, args);
                case "acot" -> evalMonadic(Maja::acot, args);
                case "asec" -> evalMonadic(Maja::asec, args);
                case "acsc" -> evalMonadic(Maja::acsc, args);
                case "coth" -> evalMonadic(Maja::coth, args);
                case "sech" -> evalMonadic(Maja::sech, args);
                case "csch" -> evalMonadic(Maja::csch, args);
                case "asinh" -> evalMonadic(Maja::asinh, args);
                case "acosh" -> evalMonadic(Maja::acosh, args);
                case "atanh" -> evalMonadic(Maja::atanh, args);
                case "acoth" -> evalMonadic(Maja::acoth, args);
                case "eq" -> evalCmp(Maja::eq, args);
                case "ne" -> evalCmp(Maja::ne, args);
                case "lt" -> evalCmp(Maja::lt, args);
                case "le" -> evalCmp(Maja::le, args);
                case "gt" -> evalCmp(Maja::gt, args);
                case "ge" -> evalCmp(Maja::ge, args);
                case "min" -> evalDyadic(Maja::min, args);
                case "max" -> evalDyadic(Maja::max, args);
                case "pow" -> evalDyadic(Maja::pow, args);
                case "exp" -> evalMonadic(Maja::exp, args);
                case "log" -> evalMonadic(Maja::log, args);
                case "log10" -> evalMonadic(Maja::log10, args);
                case "log2" -> evalMonadic(Maja::log2, args);
                case "log1p" -> evalMonadic(Maja::log1p, args);
                case "expm1" -> evalMonadic(Maja::expm1, args);
                case "sqrt" -> evalMonadic(Maja::sqrt, args);
                case "cbrt" -> evalMonadic(Maja::cbrt, args);
                case "hypot" -> evalDyadic(Maja::hypot, args);
                case "ceil" -> evalMonadic(Maja::ceil, args);
                case "floor" -> evalMonadic(Maja::floor, args);
                case "round" -> evalMonadic(Maja::round, args);
                case "atan2" -> evalDyadic(Maja::atan2, args);
                case "signum" -> evalMonadic(Maja::signum, args);
                case "sinc" -> evalMonadic(Maja::sinc, args);
                case "rad" -> evalMonadic(Maja::toRadians, args);
                case "deg" -> evalMonadic(Maja::toDegrees, args);
                case "fma" -> evalTriadic(Maja::fma, args);
                case "nextafter" -> evalDyadic(Maja::nextAfter, args);
                case "nextup" -> evalMonadic(Maja::nextUp, args);
                case "nextdown" -> evalMonadic(Maja::nextDown, args);
                case "ulp" -> evalMonadic(Maja::ulp, args);
                case "scalb" -> {
                    assertArity(args, 2);
                    yield Maja.scalb(args.get(0), args.get(1).intValue());
                }
                case "copysign" -> evalDyadic(Maja::copySign, args);
                case "getexp" -> evalMonadic(Maja::getExponent, args);
                case "randrange" -> {
                    if(args.size() == 1) {
                        yield Maja.random(args.get(0));
                    } else if(args.size() == 2) {
                        assertArity(args, 2);
                        yield Maja.random(args.get(0), args.get(1));
                    } else {
                        throw new ArithmeticException("randrange() takes 1 or 2 arguments (" + args.size() + " given)");
                    }
                }
                case "compare" -> {
                    assertArity(args, 2);
                    yield Maja.compare(args.get(0), args.get(1));
                }
                case "approxeq" -> {
                    assertArity(args, 3);
                    yield Maja.eq(args.get(0), args.get(1), args.get(2)) ? 1 : 0;
                }
                case "isperfectsquare" -> {
                    assertArity(args, 1);
                    yield Maja.isPerfectSquare(mustLong(args.get(0))) ? 1 : 0;
                }
                case "linearmap" -> {
                    assertArity(args, 5);
                    yield Maja.linearMap(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4));
                }
                case "linearnorm" -> evalTriadic(Maja::linearNorm, args);
                case "linearinterpolate" -> evalTriadic(Maja::linearInterpolate, args);
                case "clamp" -> evalTriadic(Maja::clamp, args);
                case "ispowerof2" -> {
                    assertArity(args, 1);
                    yield Maja.isPowerOfTwo(mustLong(args.get(0))) ? 1 : 0;
                }
                case "nextpowerof2" -> {
                    assertArity(args, 1);
                    yield Maja.nextPowerOfTwo(mustLong(args.get(0)));
                }
                case "fastsin" -> {
                    assertArity(args, 1);
                    yield Maja.fastSin(args.get(0).floatValue());
                }
                case "fastcos" -> {
                    assertArity(args, 1);
                    yield Maja.fastCos(args.get(0).floatValue());
                }
                case "icbrt" -> {
                    assertArity(args, 1);
                    yield Maja.icbrt(mustLong(args.get(0)));
                }
                case "isqrt" -> {
                    assertArity(args, 1);
                    yield Maja.isqrt(mustLong(args.get(0)));
                }
                case "ilog10" -> {
                    assertArity(args, 1);
                    yield Maja.ilog10(mustInt(args.get(0)));
                }
                case "iexp" -> {
                    assertArity(args, 2);
                    yield Maja.ipow(mustInt(args.get(0)), mustInt(args.get(1)));
                }
                case "dipow" -> {
                    assertArity(args, 2);
                    yield Maja.pow(args.get(0), mustInt(args.get(1)));
                }
                case "airy" -> evalMonadic(Maja::airy, args);
                case "airy'" -> evalMonadic(Maja::airyDerivative, args);
                case "gamma" -> evalMonadic(Maja::gamma, args);
                case "loggamma" -> evalMonadic(Maja::loggamma, args);
                case "digamma" -> evalMonadic(Maja::digamma, args);
                case "trigamma" -> evalMonadic(Maja::trigamma, args);
                case "uigamma" -> evalDyadic(Maja::uiGamma, args);
                case "ligamma" -> evalDyadic(Maja::liGamma, args);
                case "pochhammer" -> evalDyadic(Maja::pochhammer, args);
                case "expint" -> evalMonadic(Maja::expint, args);
                case "zeta" -> evalMonadic(Maja::zeta, args);
                case "hurwitzzeta" -> evalDyadic(Maja::hurwitzZeta, args);
                case "lerchphi" -> evalTriadic(Maja::lerchPhi, args);
                case "polygamma" -> evalDyadic(Maja::polygamma, args);
                case "beta" -> evalDyadic(Maja::beta, args);
                case "logbeta" -> evalDyadic(Maja::logbeta, args);
                case "dilog" -> evalMonadic(Maja::dilog, args);
                case "spence" -> evalMonadic(Maja::spence, args);
                case "polylog" -> {
                    assertArity(args, 2);
                    yield Maja.polylog(mustInt(args.get(0)), args.get(1));
                }
                case "lambertw0" -> evalMonadic(Maja::lambertW0, args);
                case "lambertwm1" -> evalMonadic(Maja::lambertWm1, args);
                case "erfinv" -> evalMonadic(Maja::erfInv, args);
                case "erfcinv" -> evalMonadic(Maja::erfcInv, args);
                case "dawsonp" -> evalMonadic(Maja::dawsonPlus, args);
                case "dawsonm" -> evalMonadic(Maja::dawsonMinus, args);
                case "erf" -> evalMonadic(Maja::erf, args);
                case "erfc" -> evalMonadic(Maja::erfc, args);
                case "erfi" -> evalMonadic(Maja::erfi, args);
                case "stretch" -> evalMonadic(Maja::stretch, args);
                case "squash" -> evalMonadic(Maja::squash, args);
                case "Si" -> evalMonadic(Maja::Si, args);
                case "Ci" -> evalMonadic(Maja::Ci, args);
                case "si" -> evalMonadic(Maja::si, args);
                case "Cin" -> evalMonadic(Maja::Cin, args);
                case "Shi" -> evalMonadic(Maja::Shi, args);
                case "Chi" -> evalMonadic(Maja::Chi, args);
                case "fresnelC" -> evalMonadic(Maja::fresnelC, args);
                case "fresnelS" -> evalMonadic(Maja::fresnelS, args);
                case "y0" -> evalMonadic(Maja::besselY0, args);
                case "y1" -> evalMonadic(Maja::besselY1, args);
                case "yn" -> {
                    assertArity(args, 2);
                    yield Maja.besselYn(mustInt(args.get(0)), args.get(1));
                }
                case "j0" -> evalMonadic(Maja::besselJ0, args);
                case "j1" -> evalMonadic(Maja::besselJ1, args);
                case "jn" -> {
                    assertArity(args, 2);
                    yield Maja.besselJn(mustInt(args.get(0)), args.get(1));
                }
                case "i0" -> evalMonadic(Maja::besselI0, args);
                case "i1" -> evalMonadic(Maja::besselI1, args);
                case "k0" -> evalMonadic(Maja::besselK0, args);
                case "k1" -> evalMonadic(Maja::besselK1, args);
                case "kn" -> {
                    assertArity(args, 2);
                    yield Maja.besselKn(mustInt(args.get(0)), args.get(1));
                }
                case "binetfib" -> {
                    assertArity(args, 1);
                    yield Maja.fib(mustInt(args.get(0)));
                }
                case "hypergeo2F1" -> {
                    assertArity(args, 4);
                    yield Maja.hypergeo2F1(args.get(0), args.get(1), args.get(2), args.get(3));
                }
                case "hypergeo1F1" -> evalTriadic(Maja::hypergeo1F1, args);
                case "jv" -> evalDyadic(Maja::besselJv, args);
                case "yv" -> evalDyadic(Maja::besselYv, args);
                case "binomial" -> {
                    assertArity(args, 2);
                    yield Maja.binomial(mustInt(args.get(0)), mustInt(args.get(1)));
                }
                default -> throw new ArithmeticException("Unknown function: " + name);
            };
        }

        private double constant(String name) {
            return switch (name) {
                case "e" -> Maja.E;
                case "pi" -> Maja.PI;
                case "glaisher" -> Maja.GLAISHER_CONSTANT;
                case "apery" -> Maja.APERY_CONSTANT;
                case "pi2" -> Maja.PI_2;
                case "pi4" -> Maja.PI_4;
                case "tau" -> Maja.TWO_PI;
                case "invpi" -> Maja.ONE_OVER_PI;
                case "inve" -> Maja.ONE_OVER_E;
                case "epsilon" -> Maja.EPSILON;
                case "eulergamma" -> Maja.EULER_GAMMA;
                case "catalan" -> Maja.CATALAN_CONSTANT;
                case "khinchin" -> Maja.KHINCHIN_CONSTANT;
                case "ln2" -> Maja.LN2;
                case "feigenbaum" -> Maja.FEIGENBAUM_CONSTANT;
                case "ln10" -> Maja.LN10;
                case "log2e" -> Maja.LOG2E;
                case "mills" -> Maja.MILLS_CONSTANT;
                case "golombdickman" -> Maja.GOLOMB_DICKMAN_CONSTANT;
                case "phi" -> Maja.GOLDEN_RATIO;
                case "deci" -> Maja.DECI;
                case "centi" -> Maja.CENTI;
                case "milli" -> Maja.MILLI;
                case "micro" -> Maja.MICRO;
                case "nano" -> Maja.NANO;
                case "pico" -> Maja.PICO;
                case "femto" -> Maja.FEMTO;
                case "atto" -> Maja.ATTO;
                case "zepto" -> Maja.ZEPTO;
                case "yocto" -> Maja.YOCTO;
                case "deca" -> Maja.DECA;
                case "hecto" -> Maja.HECTO;
                case "kilo" -> Maja.KILO;
                case "mega" -> Maja.MEGA;
                case "giga" -> Maja.GIGA;
                case "tera" -> Maja.TERA;
                case "peta" -> Maja.PETA;
                case "exa" -> Maja.EXA;
                case "zetta" -> Maja.ZETTA;
                case "yotta" -> Maja.YOTTA;
                case "random" -> Maja.random();
                case "randomsign" -> Maja.randomSign();
                default -> Double.NaN;
            };
        }
    }

    public static double evalExpression(String expr, Map<String, Double> variables) {
        return new Evaluator(expr, variables).expr();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("> ");
            String expr = scanner.nextLine();
            if(expr.isEmpty()) break;
            System.out.println("Ans = " + evalExpression(expr, Map.of()));
        }
    }
}
