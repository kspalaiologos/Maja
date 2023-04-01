package rocks.palaiologos.maja;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;

import static rocks.palaiologos.maja.Maja.*;

class Expression {
    private static final String[] operators = {
            "+", "-", "*", "/", "rem", "mod", "!", "lcm", "gcd", "**", "|"
    };

    public static Number evalExpression(String expr, Map<String, Number> variables) {
        return new Evaluator(expr, variables).expr();
    }

    enum TokenType {
        NUM, OP, LPAREN, RPAREN, COMMA, NAME, QUOTING, EOF
    }

    static class Token {
        final TokenType type;
        final String value;

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
        private final String input;
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
                if (s.length() < 3)
                    throw new ArithmeticException("Empty quoting");
                return new Token(TokenType.QUOTING, s.substring(1, s.length() - 1));
            } else {
                throw new ArithmeticException("Unexpected character: " + c);
            }
        }
    }

    static class Evaluator {
        private final Tokenizer tokenizer;
        private final Map<String, Number> variables;
        private Token currentToken;

        Evaluator(String input, Map<String, Number> variables) {
            tokenizer = new Tokenizer(input);
            this.variables = variables;
            currentToken = tokenizer.nextToken();
        }

        private void eat(TokenType type) {
            if (currentToken.type == type) {
                currentToken = tokenizer.nextToken();
            } else {
                throw new ArithmeticException("Unexpected token: " + currentToken);
            }
        }

        /* Evaluate math expressions like: lerchPhi(23 lcm 1, !3e-1) */
        private Number expr() {
            Number result = term();
            while (currentToken.type == TokenType.OP && (currentToken.value.equals("+") || currentToken.value.equals("-"))) {
                Token op = currentToken;
                eat(TokenType.OP);
                if (op.value.equals("+")) {
                    result = add(result, term());
                } else {
                    result = sub(result, term());
                }
            }
            return result;
        }

        private Number term() {
            Number result = factor();
            while (currentToken.type != TokenType.OP || currentToken.value.equals("*") || currentToken.value.equals("/")) {
                if (currentToken.type == TokenType.EOF || currentToken.type == TokenType.RPAREN
                        || currentToken.type == TokenType.COMMA || currentToken.type == TokenType.QUOTING)
                    return result;
                if (currentToken.type == TokenType.OP) {
                    Token op = currentToken;
                    eat(TokenType.OP);
                    if (op.value.equals("*")) {
                        result = mul(result, factor());
                    } else {
                        result = div(result, factor());
                    }
                } else {
                    // Implicit product:
                    result = mul(result, factor());
                }
            }
            return result;
        }

        private Number factor() {
            Number result = unexp();
            while (currentToken.type == TokenType.OP && currentToken.value.equals("**") || currentToken.value.equals("rem") || currentToken.value.equals("mod") || currentToken.value.equals("lcm") || currentToken.value.equals("gcd")) {
                Token op = currentToken;
                eat(TokenType.OP);
                switch (op.value) {
                    case "**" -> {
                        Number exp = unexp();
                        // Special cases:
                        if (result.isComplex() && exp.isComplex()) {
                            result = new Number(pow(result.getComplex(), exp.getComplex()));
                        } else if (result.isComplex() && exp.isLong()) {
                            result = new Number(pow(result.getComplex(), exp.getLong()));
                        } else if (result.isDouble() && exp.isDouble()) {
                            result = new Number(pow(result.getDouble(), exp.getDouble()));
                        } else if (result.isLong() && exp.isLong()) {
                            result = new Number(pow(result.getLong(), exp.getLong()));
                        } else if (result.isComplex() || exp.isComplex()) {
                            // Promote to complex:
                            result = new Number(pow(result.getComplex(), exp.getComplex()));
                        } else if (result.isDouble() || exp.isDouble()) {
                            // Promote to double:
                            result = new Number(pow(result.getDouble(), exp.getDouble()));
                        }
                    }
                    case "rem" -> {
                        Number exp = unexp();
                        // Special cases:
                        if (result.isComplex() && exp.isComplex()) {
                            result = new Number(rem(result.getComplex(), exp.getComplex()));
                        } else if (result.isDouble() && exp.isDouble()) {
                            result = new Number(rem(result.getDouble(), exp.getDouble()));
                        } else if (result.isLong() && exp.isLong()) {
                            result = new Number(rem(result.getLong(), exp.getLong()));
                        } else if (result.isComplex() || exp.isComplex()) {
                            // Promote to complex:
                            result = new Number(rem(result.getComplex(), exp.getComplex()));
                        } else if (result.isDouble() || exp.isDouble()) {
                            // Promote to double:
                            result = new Number(rem(result.getDouble(), exp.getDouble()));
                        }
                    }
                    case "mod" -> {
                        Number exp = unexp();
                        if (result.isComplex() || exp.isComplex()) {
                            throw new ArithmeticException("mod is not defined for complex numbers");
                        } else if (result.isDouble() || exp.isDouble()) {
                            // Promote to double:
                            result = new Number(mod(result.getDouble(), exp.getDouble()));
                        } else {
                            result = new Number(mod(result.getLong(), exp.getLong()));
                        }
                    }
                    case "lcm" -> {
                        Number exp = unexp();
                        if (result.isComplex() || exp.isComplex()) {
                            // Promote to complex:
                            result = new Number(lcm(result.getComplex(), exp.getComplex()));
                        } else if (result.isDouble() || exp.isDouble()) {
                            // Promote to double:
                            result = new Number(lcm(result.getDouble(), exp.getDouble()));
                        } else {
                            result = new Number(lcm(result.getLong(), exp.getLong()));
                        }
                    }
                    case "gcd" -> {
                        Number exp = unexp();
                        if (result.isComplex() || exp.isComplex()) {
                            // Promote to complex:
                            result = new Number(gcd(result.getComplex(), exp.getComplex()));
                        } else if (result.isDouble() || exp.isDouble()) {
                            // Promote to double:
                            result = new Number(gcd(result.getDouble(), exp.getDouble()));
                        } else {
                            result = new Number(gcd(result.getLong(), exp.getLong()));
                        }
                    }
                }
            }
            return result;
        }

        private Number unexp() {
            if (currentToken.type == TokenType.OP && currentToken.value.equals("-")) {
                eat(TokenType.OP);
                Number exp = unexp();
                if (exp.isComplex())
                    return new Number(negate(exp.getComplex()));
                else if (exp.isDouble())
                    return new Number(-exp.getDouble());
                else
                    return new Number(-exp.getLong());
            } else if (currentToken.type == TokenType.OP && currentToken.value.equals("+")) {
                eat(TokenType.OP);
                return unexp();
            } else if (currentToken.type == TokenType.OP && currentToken.value.equals("!")) {
                eat(TokenType.OP);
                Number exp = unexp();
                if (exp.isLong())
                    return new Number(Maja.factorial(exp.getLong()));
                else
                    throw new ArithmeticException("Factorial is not defined for non-integer numbers");
            } else if (currentToken.type == TokenType.OP && currentToken.value.equals("|")) {
                eat(TokenType.OP);
                Number exp = unexp();
                if (exp.isComplex())
                    return new Number(abs(exp.getComplex()));
                else if (exp.isDouble())
                    return new Number(abs(exp.getDouble()));
                else
                    return new Number(abs(exp.getLong()));
            } else {
                return atom();
            }
        }

        private Function<Double, Complex> wrap(String text, String arg) {
            if (!arg.startsWith("d"))
                throw new ArithmeticException("Expected the infinitesimal variable name starting with 'd', got: " + arg);
            String var = arg.substring(1);
            if (var.isEmpty())
                throw new ArithmeticException("Expected the infinitesimal variable name, got: " + arg);
            return x -> new Evaluator(text, Map.of(var, new Number(x))).expr().getComplex();
        }

        private Number atom() {
            if (currentToken.type == TokenType.NUM) {
                if (currentToken.value.matches("-?[0-9]+([eE][0-9]+)?")) {
                    // long value
                    long result = Long.parseLong(currentToken.value);
                    eat(TokenType.NUM);
                    return new Number(result);
                } else {
                    // double value
                    double result = Double.parseDouble(currentToken.value);
                    eat(TokenType.NUM);
                    return new Number(result);
                }
            } else if (currentToken.type == TokenType.NAME) {
                // Can be either a function call, constant or variable.
                // Check if it's a function call.
                String name = currentToken.value;
                eat(TokenType.NAME);
                if (currentToken.type == TokenType.LPAREN) {
                    eat(TokenType.LPAREN);
                    switch (name) {
                        case "pick": {
                            long n = expr().getLong();
                            if (n < 0)
                                throw new ArithmeticException("pick(n, ...) requires n >= 0");
                            for (int i = 0; i < n; i++) {
                                if (currentToken.type == TokenType.RPAREN)
                                    throw new ArithmeticException("pick(n, ...) requires n <= number of arguments");
                                eat(TokenType.COMMA);
                                expr();
                            }
                            eat(TokenType.COMMA);
                            Number result = expr();
                            while (currentToken.type == TokenType.COMMA) {
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
                            double a = expr().getDouble();
                            eat(TokenType.COMMA);
                            double b = expr().getDouble();
                            eat(TokenType.COMMA);
                            String d = currentToken.value;
                            eat(TokenType.COMMA);
                            int n = (int) expr().getLong();
                            eat(TokenType.RPAREN);
                            Complex result = Maja.integrateGaussLegendre(wrap(f, d), a, b, n);
                            if (result.im() != 0)
                                return new Number(result);
                            else return new Number(result.re());
                        }
                        case "tanhsinh": {
                            // tanhsinh([x ** 2], -1, 1, dx, 10, 1e-7)
                            String f = currentToken.value;
                            eat(TokenType.COMMA);
                            double a = expr().getDouble();
                            eat(TokenType.COMMA);
                            double b = expr().getDouble();
                            eat(TokenType.COMMA);
                            String d = currentToken.value;
                            eat(TokenType.COMMA);
                            int n = (int) expr().getLong();
                            eat(TokenType.COMMA);
                            double eps = expr().getDouble();
                            eat(TokenType.RPAREN);
                            Complex result = Maja.integrateTanhSinh(wrap(f, d), a, b, n, eps)[0];
                            if (result.im() != 0)
                                return new Number(result);
                            else return new Number(result.re());
                        }
                        case "simpson": {
                            // simpson([x ** 2], -1, 1, dx, 10)
                            String f = currentToken.value;
                            eat(TokenType.QUOTING);
                            eat(TokenType.COMMA);
                            double a = expr().getDouble();
                            eat(TokenType.COMMA);
                            double b = expr().getDouble();
                            eat(TokenType.COMMA);
                            String d = currentToken.value;
                            eat(TokenType.NAME);
                            eat(TokenType.COMMA);
                            int n = (int) expr().getLong();
                            eat(TokenType.RPAREN);
                            Complex result = Maja.integrateSimpson(wrap(f, d), a, b, n);
                            if (result.im() != 0)
                                return new Number(result);
                            else return new Number(result.re());
                        }
                        default:
                            ArrayList<Number> args = new ArrayList<>();
                            if (currentToken.type != TokenType.RPAREN) {
                                args.add(expr());
                                while (currentToken.type == TokenType.COMMA) {
                                    eat(TokenType.COMMA);
                                    args.add(expr());
                                }
                            }
                            eat(TokenType.RPAREN);
                            return ExpressionFunctions.call(name, args);
                    }
                } else {
                    // Check if it's a constant.
                    Number constant = ExpressionFunctions.constant(name);
                    if (!(constant.isDouble() && Double.isNaN(constant.getDouble()))) {
                        return constant;
                    } else {
                        // Check if it's a variable.
                        Number variable = variables.get(name);
                        if (variable != null) {
                            return variable;
                        } else {
                            throw new ArithmeticException("Unknown variable/constant name: " + name);
                        }
                    }
                }
            } else if (currentToken.type == TokenType.LPAREN) {
                eat(TokenType.LPAREN);
                Number result = expr();
                eat(TokenType.RPAREN);
                return result;
            } else {
                throw new ArithmeticException("Unexpected token: " + currentToken);
            }
        }
    }
}
