package rocks.palaiologos.maja;

import java.util.ArrayList;

class ExpressionFunctions {
    private static void assertArity(ArrayList<Number> args, int arity) {
        if (args.size() != arity)
            throw new ArithmeticException("Expected " + arity + " arguments, got: " + args.size());
    }


    public static Number call(String name, ArrayList<Number> args) {
        return new Number(switch (name) {
            case "sin" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.sin(args.get(0).getComplex());
                else
                    yield Maja.sin(args.get(0).getDouble());
            }
            case "cos" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.cos(args.get(0).getComplex());
                else
                    yield Maja.cos(args.get(0).getDouble());
            }
            case "tan" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.tan(args.get(0).getComplex());
                else
                    yield Maja.tan(args.get(0).getDouble());
            }
            case "asin" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.asin(args.get(0).getComplex());
                else
                    yield Maja.asin(args.get(0).getDouble());
            }
            case "acos" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.acos(args.get(0).getComplex());
                else
                    yield Maja.acos(args.get(0).getDouble());
            }
            case "atan" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.atan(args.get(0).getComplex());
                else
                    yield Maja.atan(args.get(0).getDouble());
            }
            case "sinh" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.sinh(args.get(0).getComplex());
                else
                    yield Maja.sinh(args.get(0).getDouble());
            }
            case "cosh" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.cosh(args.get(0).getComplex());
                else
                    yield Maja.cosh(args.get(0).getDouble());
            }
            case "tanh" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.tanh(args.get(0).getComplex());
                else
                    yield Maja.tanh(args.get(0).getDouble());
            }
            case "cot" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.cot(args.get(0).getComplex());
                else
                    yield Maja.cot(args.get(0).getDouble());
            }
            case "sec" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.sec(args.get(0).getComplex());
                else
                    yield Maja.sec(args.get(0).getDouble());
            }
            case "csc" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.csc(args.get(0).getComplex());
                else
                    yield Maja.csc(args.get(0).getDouble());
            }
            case "acot" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.acot(args.get(0).getComplex());
                else
                    yield Maja.acot(args.get(0).getDouble());
            }
            case "asec" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.asec(args.get(0).getComplex());
                else
                    yield Maja.asec(args.get(0).getDouble());
            }
            case "acsc" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.acsc(args.get(0).getComplex());
                else
                    yield Maja.acsc(args.get(0).getDouble());
            }
            case "coth" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.coth(args.get(0).getComplex());
                else
                    yield Maja.coth(args.get(0).getDouble());
            }
            case "sech" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.sech(args.get(0).getComplex());
                else
                    yield Maja.sech(args.get(0).getDouble());
            }
            case "csch" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.csch(args.get(0).getComplex());
                else
                    yield Maja.csch(args.get(0).getDouble());
            }
            case "asinh" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.asinh(args.get(0).getComplex());
                else
                    yield Maja.asinh(args.get(0).getDouble());
            }
            case "acosh" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.acosh(args.get(0).getComplex());
                else
                    yield Maja.acosh(args.get(0).getDouble());
            }
            case "atanh" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.atanh(args.get(0).getComplex());
                else
                    yield Maja.atanh(args.get(0).getDouble());
            }
            case "acoth" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.acoth(args.get(0).getComplex());
                else
                    yield Maja.acoth(args.get(0).getDouble());
            }
            case "eq" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    yield Maja.eq(args.get(0).getComplex(), args.get(1).getComplex());
                else if (args.get(0).isDouble() || args.get(1).isDouble())
                    yield Maja.eq(args.get(0).getDouble(), args.get(1).getDouble());
                else
                    yield args.get(0).getLong() == args.get(1).getLong();
            }
            case "ne" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    yield Maja.ne(args.get(0).getComplex(), args.get(1).getComplex());
                else if (args.get(0).isDouble() || args.get(1).isDouble())
                    yield Maja.ne(args.get(0).getDouble(), args.get(1).getDouble());
                else
                    yield args.get(0).getLong() != args.get(1).getLong();
            }
            case "lt" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    throw new ArithmeticException("Cannot compare complex numbers");
                else if (args.get(0).isDouble() || args.get(1).isDouble())
                    yield Maja.lt(args.get(0).getDouble(), args.get(1).getDouble());
                else
                    yield args.get(0).getLong() < args.get(1).getLong();
            }
            case "le" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    throw new ArithmeticException("Cannot compare complex numbers");
                else if (args.get(0).isDouble() || args.get(1).isDouble())
                    yield Maja.le(args.get(0).getDouble(), args.get(1).getDouble());
                else
                    yield args.get(0).getLong() <= args.get(1).getLong();
            }
            case "gt" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    throw new ArithmeticException("Cannot compare complex numbers");
                else if (args.get(0).isDouble() || args.get(1).isDouble())
                    yield Maja.gt(args.get(0).getDouble(), args.get(1).getDouble());
                else
                    yield args.get(0).getLong() > args.get(1).getLong();
            }
            case "ge" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    throw new ArithmeticException("Cannot compare complex numbers");
                else if (args.get(0).isDouble() || args.get(1).isDouble())
                    yield Maja.ge(args.get(0).getDouble(), args.get(1).getDouble());
                else
                    yield args.get(0).getLong() >= args.get(1).getLong();
            }
            case "min" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    throw new ArithmeticException("Cannot compare complex numbers");
                else if (args.get(0).isDouble() || args.get(1).isDouble())
                    yield Maja.min(args.get(0).getDouble(), args.get(1).getDouble());
                else
                    yield Math.min(args.get(0).getLong(), args.get(1).getLong());
            }
            case "max" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    throw new ArithmeticException("Cannot compare complex numbers");
                else if (args.get(0).isDouble() || args.get(1).isDouble())
                    yield Maja.max(args.get(0).getDouble(), args.get(1).getDouble());
                else
                    yield Math.max(args.get(0).getLong(), args.get(1).getLong());
            }
            case "abs" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.abs(args.get(0).getComplex());
                else if (args.get(0).isDouble())
                    yield Maja.abs(args.get(0).getDouble());
                else
                    yield Math.abs(args.get(0).getLong());
            }
            case "signum" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    throw new ArithmeticException("Cannot compute sign of complex number");
                else if (args.get(0).isDouble())
                    yield Maja.signum(args.get(0).getDouble());
                else
                    yield Maja.signum(args.get(0).getLong());
            }
            case "pow" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    yield Maja.pow(args.get(0).getComplex(), args.get(1).getComplex());
                else if (args.get(0).isDouble() || args.get(1).isDouble())
                    yield Maja.pow(args.get(0).getDouble(), args.get(1).getDouble());
                else
                    yield Maja.pow(args.get(0).getLong(), args.get(1).getLong());
            }
            case "exp" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.exp(args.get(0).getComplex());
                else if (args.get(0).isDouble())
                    yield Maja.exp(args.get(0).getDouble());
                else
                    yield Maja.exp(args.get(0).getLong());
            }
            case "log" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.log(args.get(0).getComplex());
                else if (args.get(0).isDouble())
                    yield Maja.log(args.get(0).getDouble());
                else
                    yield Maja.log(args.get(0).getLong());
            }
            case "log10" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    throw new ArithmeticException("Cannot compute log10 of complex number");
                else if (args.get(0).isDouble())
                    yield Maja.log10(args.get(0).getDouble());
                else
                    yield Maja.log10(args.get(0).getLong());
            }
            case "log2" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    throw new ArithmeticException("Cannot compute log2 of complex number");
                else if (args.get(0).isDouble())
                    yield Maja.log2(args.get(0).getDouble());
                else
                    yield Maja.log2(args.get(0).getLong());
            }
            case "log1p" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    throw new ArithmeticException("Cannot compute log1p of complex number");
                else if (args.get(0).isDouble())
                    yield Maja.log1p(args.get(0).getDouble());
                else
                    yield Maja.log1p(args.get(0).getLong());
            }
            case "expm1" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.sub(Maja.exp(args.get(0).getComplex()), 1);
                else if (args.get(0).isDouble())
                    yield Maja.expm1(args.get(0).getDouble());
                else
                    yield Maja.expm1(args.get(0).getLong());
            }
            case "sqrt" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.sqrt(args.get(0).getComplex());
                else if (args.get(0).isDouble())
                    yield Maja.sqrt(args.get(0).getDouble());
                else
                    yield Maja.sqrt(args.get(0).getLong());
            }
            case "cbrt" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.cbrt(args.get(0).getComplex());
                else if (args.get(0).isDouble())
                    yield Maja.cbrt(args.get(0).getDouble());
                else
                    yield Maja.cbrt(args.get(0).getLong());
            }
            case "hypot" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    throw new ArithmeticException("Cannot compute hypot of complex numbers");
                else if (args.get(0).isDouble() || args.get(1).isDouble())
                    yield Maja.hypot(args.get(0).getDouble(), args.get(1).getDouble());
                else
                    yield Maja.hypot(args.get(0).getLong(), args.get(1).getLong());
            }
            case "ceil" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.ceil(args.get(0).getComplex());
                else if (args.get(0).isDouble())
                    yield Maja.ceil(args.get(0).getDouble());
                else
                    yield Maja.ceil(args.get(0).getLong());
            }
            case "floor" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.floor(args.get(0).getComplex());
                else if (args.get(0).isDouble())
                    yield Maja.floor(args.get(0).getDouble());
                else
                    yield Maja.floor(args.get(0).getLong());
            }
            case "round" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.round(args.get(0).getComplex());
                else if (args.get(0).isDouble())
                    yield Maja.round(args.get(0).getDouble());
                else
                    yield Maja.round(args.get(0).getLong());
            }
            case "atan2" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    throw new ArithmeticException("Cannot compute atan2 of complex numbers");
                else if (args.get(0).isDouble() || args.get(1).isDouble())
                    yield Maja.atan2(args.get(0).getDouble(), args.get(1).getDouble());
                else
                    yield Maja.atan2(args.get(0).getLong(), args.get(1).getLong());
            }
            case "sinc" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.sinc(args.get(0).getComplex());
                else if (args.get(0).isDouble())
                    yield Maja.sinc(args.get(0).getDouble());
                else
                    yield Maja.sinc(args.get(0).getLong());
            }
            case "rad" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    throw new ArithmeticException("Cannot compute rad of complex number");
                else if (args.get(0).isDouble())
                    yield Maja.toRadians(args.get(0).getDouble());
                else
                    yield Maja.toRadians(args.get(0).getLong());
            }
            case "deg" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    throw new ArithmeticException("Cannot compute deg of complex number");
                else if (args.get(0).isDouble())
                    yield Maja.toDegrees(args.get(0).getDouble());
                else
                    yield Maja.toDegrees(args.get(0).getLong());
            }
            case "fma" -> {
                assertArity(args, 3);
                if (args.get(0).isComplex() || args.get(1).isComplex() || args.get(2).isComplex())
                    throw new ArithmeticException("Cannot compute fma of complex numbers");
                else if (args.get(0).isDouble() || args.get(1).isDouble() || args.get(2).isDouble())
                    yield Maja.fma(args.get(0).getDouble(), args.get(1).getDouble(), args.get(2).getDouble());
                else
                    yield Maja.fma(args.get(0).getLong(), args.get(1).getLong(), args.get(2).getLong());
            }
            case "nextafter" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    throw new ArithmeticException("Cannot compute nextafter of complex numbers");
                else if (args.get(0).isDouble() || args.get(1).isDouble())
                    yield Maja.nextAfter(args.get(0).getDouble(), args.get(1).getDouble());
                else
                    throw new ArithmeticException("Cannot compute nextafter of integers");
            }
            case "nextup" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    throw new ArithmeticException("Cannot compute nextup of complex number");
                else if (args.get(0).isDouble())
                    yield Maja.nextUp(args.get(0).getDouble());
                else
                    throw new ArithmeticException("Cannot compute nextup of integer");
            }
            case "nextdown" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    throw new ArithmeticException("Cannot compute nextdown of complex number");
                else if (args.get(0).isDouble())
                    yield Maja.nextDown(args.get(0).getDouble());
                else
                    throw new ArithmeticException("Cannot compute nextdown of integer");
            }
            case "ulp" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    throw new ArithmeticException("Cannot compute ulp of complex number");
                else if (args.get(0).isDouble())
                    yield Maja.ulp(args.get(0).getDouble());
                else
                    throw new ArithmeticException("Cannot compute ulp of integer");
            }
            case "scalb" -> {
                assertArity(args, 2);
                if (!args.get(0).isDouble() || !args.get(1).isLong())
                    throw new ArithmeticException("scalb() requires a double and an integer");
                yield Maja.scalb(args.get(0).getDouble(), (int) args.get(1).getLong());
            }
            case "copysign" -> {
                assertArity(args, 2);
                if (args.get(0).isDouble() || args.get(1).isDouble())
                    yield Maja.copySign(args.get(0).getDouble(), args.get(1).getDouble());
                else
                    yield Maja.copySign(args.get(0).getLong(), args.get(1).getLong());
            }
            case "getexp" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    throw new ArithmeticException("Cannot compute getexp of complex number");
                else if (args.get(0).isDouble())
                    yield Maja.getExponent(args.get(0).getDouble());
                else
                    throw new ArithmeticException("Cannot compute getexp of integer");
            }
            case "randrange" -> {
                if (args.size() == 1) {
                    if (args.get(0).isLong())
                        yield Maja.random(args.get(0).getLong());
                    else if (args.get(0).isDouble())
                        yield Maja.random(args.get(0).getDouble());
                    else
                        throw new ArithmeticException("randrange() requires an integer or a double");
                } else if (args.size() == 2) {
                    assertArity(args, 2);
                    if (args.get(0).isDouble() || args.get(1).isDouble())
                        yield Maja.random(args.get(0).getDouble(), args.get(1).getDouble());
                    else if (args.get(0).isLong() || args.get(1).isLong())
                        yield Maja.random(args.get(0).getLong(), args.get(1).getLong());
                    else
                        throw new ArithmeticException("randrange() requires integers or doubles.");
                } else {
                    throw new ArithmeticException("randrange() takes 1 or 2 arguments (" + args.size() + " given)");
                }
            }
            case "compare" -> {
                assertArity(args, 2);
                if (args.get(0).isDouble() || args.get(1).isDouble())
                    yield Maja.compare(args.get(0).getDouble(), args.get(1).getDouble());
                else if (args.get(0).isLong() || args.get(1).isLong())
                    yield Maja.compare(args.get(0).getLong(), args.get(1).getLong());
                else
                    throw new ArithmeticException("Cannot compare complex numbers");
            }
            case "approxeq" -> {
                assertArity(args, 3);
                if (!args.get(2).isDouble())
                    throw new ArithmeticException("approxeq() requires a double as third argument");
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    yield Maja.eq(args.get(0).getComplex(), args.get(1).getComplex(), args.get(2).getDouble());
                else if (args.get(0).isDouble() || args.get(1).isDouble())
                    yield Maja.eq(args.get(0).getDouble(), args.get(1).getDouble(), args.get(2).getDouble());
                else
                    throw new ArithmeticException("Cannot approximately compare integers");
            }
            case "isperfectsquare" -> {
                assertArity(args, 1);
                if (args.get(0).isLong())
                    yield Maja.isPerfectSquare(args.get(0).getLong());
                else
                    throw new ArithmeticException("isperfectsquare() requires an integer");
            }
            case "linearmap" -> {
                assertArity(args, 5);
                yield Maja.linearMap(args.get(0).getDouble(), args.get(1).getDouble(), args.get(2).getDouble(), args.get(3).getDouble(), args.get(4).getDouble());
            }
            case "linearnorm" -> {
                assertArity(args, 3);
                yield Maja.linearNorm(args.get(0).getDouble(), args.get(1).getDouble(), args.get(2).getDouble());
            }
            case "linearinterpolate" -> {
                assertArity(args, 3);
                yield Maja.linearInterpolate(args.get(0).getDouble(), args.get(1).getDouble(), args.get(2).getDouble());
            }
            case "clamp" -> {
                assertArity(args, 3);
                if (args.get(0).isDouble() || args.get(1).isDouble() || args.get(2).isDouble())
                    yield Maja.clamp(args.get(0).getDouble(), args.get(1).getDouble(), args.get(2).getDouble());
                else
                    yield Maja.clamp(args.get(0).getLong(), args.get(1).getLong(), args.get(2).getLong());
            }
            case "ispowerof2" -> {
                assertArity(args, 1);
                yield Maja.isPowerOfTwo(args.get(0).getLong());
            }
            case "nextpowerof2" -> {
                assertArity(args, 1);
                yield Maja.nextPowerOfTwo(args.get(0).getLong());
            }
            case "fastsin" -> {
                assertArity(args, 1);
                yield Maja.fastSin((float) args.get(0).getDouble());
            }
            case "fastcos" -> {
                assertArity(args, 1);
                yield Maja.fastCos((float) args.get(0).getDouble());
            }
            case "icbrt" -> {
                assertArity(args, 1);
                yield Maja.icbrt(args.get(0).getLong());
            }
            case "isqrt" -> {
                assertArity(args, 1);
                yield Maja.isqrt(args.get(0).getLong());
            }
            case "ilog10" -> {
                assertArity(args, 1);
                yield Maja.ilog10((int) args.get(0).getLong());
            }
            case "iexp" -> {
                assertArity(args, 2);
                yield Maja.ipow((int) args.get(0).getLong(), (int) args.get(1).getLong());
            }
            case "dipow" -> {
                assertArity(args, 2);
                yield Maja.pow(args.get(0).getDouble(), (int) args.get(1).getLong());
            }
            case "airyAi" -> {
                assertArity(args, 1);
                if (args.get(0).isDouble())
                    yield Maja.airyAi(args.get(0).getDouble());
                else if (args.get(0).isComplex())
                    yield Maja.airyAi(args.get(0).getComplex());
                else
                    yield Maja.airyAi(args.get(0).getDouble());
            }
            case "airyAi'" -> {
                assertArity(args, 1);
                if (args.get(0).isDouble())
                    yield Maja.airyAip(args.get(0).getDouble());
                else if (args.get(0).isComplex())
                    yield Maja.airyAip(args.get(0).getComplex());
                else
                    yield Maja.airyAip(args.get(0).getDouble());
            }
            case "airyBi" -> {
                assertArity(args, 1);
                if (args.get(0).isDouble())
                    yield Maja.airyBi(args.get(0).getDouble());
                else if (args.get(0).isComplex())
                    yield Maja.airyBi(args.get(0).getComplex());
                else
                    yield Maja.airyBi(args.get(0).getDouble());
            }
            case "airyBi'" -> {
                assertArity(args, 1);
                if (args.get(0).isDouble())
                    yield Maja.airyBip(args.get(0).getDouble());
                else if (args.get(0).isComplex())
                    yield Maja.airyBip(args.get(0).getComplex());
                else
                    yield Maja.airyBip(args.get(0).getDouble());
            }
            case "gamma" -> {
                assertArity(args, 1);
                if (args.get(0).isDouble())
                    yield Maja.gamma(args.get(0).getDouble());
                else if (args.get(0).isComplex())
                    yield Maja.gamma(args.get(0).getComplex());
                else
                    yield Maja.gamma(args.get(0).getDouble());
            }
            case "loggamma" -> {
                assertArity(args, 1);
                if (args.get(0).isDouble())
                    yield Maja.loggamma(args.get(0).getDouble());
                else if (args.get(0).isComplex())
                    yield Maja.loggamma(args.get(0).getComplex());
                else
                    yield Maja.loggamma(args.get(0).getDouble());
            }
            case "digamma" -> {
                assertArity(args, 1);
                if (args.get(0).isDouble())
                    yield Maja.digamma(args.get(0).getDouble());
                else if (args.get(0).isComplex())
                    yield Maja.digamma(args.get(0).getComplex());
                else
                    yield Maja.digamma(args.get(0).getDouble());
            }
            case "trigamma" -> {
                assertArity(args, 1);
                if (args.get(0).isDouble())
                    yield Maja.trigamma(args.get(0).getDouble());
                else if (args.get(0).isComplex())
                    yield Maja.trigamma(args.get(0).getComplex());
                else
                    yield Maja.trigamma(args.get(0).getDouble());
            }
            case "uigamma" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    yield Maja.uiGamma(args.get(0).getComplex(), args.get(1).getComplex());
                else if (args.get(0).isDouble() || args.get(1).isDouble())
                    yield Maja.uiGamma(args.get(0).getDouble(), args.get(1).getDouble());
                else
                    yield Maja.uiGamma(args.get(0).getDouble(), args.get(1).getDouble());
            }
            case "ligamma" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    yield Maja.liGamma(args.get(0).getComplex(), args.get(1).getComplex());
                else if (args.get(0).isDouble() || args.get(1).isDouble())
                    yield Maja.liGamma(args.get(0).getDouble(), args.get(1).getDouble());
                else
                    yield Maja.liGamma(args.get(0).getDouble(), args.get(1).getDouble());
            }
            case "pochhammer" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    yield Maja.pochhammer(args.get(0).getComplex(), args.get(1).getComplex());
                else if (args.get(0).isDouble() || args.get(1).isDouble())
                    yield Maja.pochhammer(args.get(0).getDouble(), args.get(1).getDouble());
                else
                    yield Maja.pochhammer(args.get(0).getDouble(), args.get(1).getDouble());
            }
            case "Ei" -> {
                assertArity(args, 1);
                if (args.get(0).isDouble())
                    yield Maja.Ei(args.get(0).getDouble());
                else if (args.get(0).isComplex())
                    yield Maja.Ei(args.get(0).getComplex());
                else
                    yield Maja.Ei(args.get(0).getDouble());
            }
            case "E1" -> {
                assertArity(args, 1);
                yield Maja.e1(args.get(0).getComplex());
            }
            case "En" -> {
                assertArity(args, 2);
                yield Maja.en(args.get(0).getComplex(), args.get(1).getComplex());
            }
            case "zeta" -> {
                assertArity(args, 1);
                if (args.get(0).isDouble() || args.get(0).isLong())
                    yield Maja.zeta(args.get(0).getDouble());
                else
                    yield Maja.zeta(args.get(0).getComplex());
            }
            case "hurwitzzeta" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    yield Maja.hurwitzZeta(args.get(0).getComplex(), args.get(1).getComplex());
                else
                    yield Maja.hurwitzZeta(args.get(0).getDouble(), args.get(1).getDouble());
            }
            case "lerchphi" -> {
                assertArity(args, 3);
                if (args.get(0).isComplex() || args.get(1).isComplex() || args.get(2).isComplex())
                    yield Maja.lerchPhi(args.get(0).getComplex(), args.get(1).getComplex(), args.get(2).getComplex());
                else
                    yield Maja.lerchPhi(args.get(0).getDouble(), args.get(1).getDouble(), args.get(2).getDouble());
            }
            case "polygamma" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    yield Maja.polygamma(args.get(0).getComplex(), args.get(1).getComplex());
                else
                    yield Maja.polygamma(args.get(0).getDouble(), args.get(1).getDouble());
            }
            case "beta" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    yield Maja.beta(args.get(0).getComplex(), args.get(1).getComplex());
                else
                    yield Maja.beta(args.get(0).getDouble(), args.get(1).getDouble());
            }
            case "logbeta" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    yield Maja.logbeta(args.get(0).getComplex(), args.get(1).getComplex());
                else
                    yield Maja.logbeta(args.get(0).getDouble(), args.get(1).getDouble());
            }
            case "dilog" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.dilog(args.get(0).getComplex());
                else
                    yield Maja.dilog(args.get(0).getDouble());
            }
            case "spence" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.spence(args.get(0).getComplex());
                else
                    yield Maja.spence(args.get(0).getDouble());
            }
            case "polylog" -> {
                assertArity(args, 2);
                if (args.get(0).isLong() && args.get(1).isDouble())
                    yield Maja.polylog((int) args.get(0).getLong(), args.get(1).getDouble());
                else
                    yield Maja.polylog(args.get(0).getComplex(), args.get(1).getComplex());
            }
            case "lambertw0" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    throw new IllegalArgumentException("lambertw0 not yet implemented for complex arguments.");
                else
                    yield Maja.lambertW0(args.get(0).getDouble());
            }
            case "lambertwm1" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    throw new IllegalArgumentException("lambertwm1 not yet implemented for complex arguments.");
                else
                    yield Maja.lambertWm1(args.get(0).getDouble());
            }
            case "dawsonp" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.dawsonPlus(args.get(0).getComplex());
                else
                    yield Maja.dawsonPlus(args.get(0).getDouble());
            }
            case "dawsonm" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.dawsonMinus(args.get(0).getComplex());
                else
                    yield Maja.dawsonMinus(args.get(0).getDouble());
            }
            case "erf" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.erf(args.get(0).getComplex());
                else
                    yield Maja.erf(args.get(0).getDouble());
            }
            case "erfc" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.erfc(args.get(0).getComplex());
                else
                    yield Maja.erfc(args.get(0).getDouble());
            }
            case "erfi" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.erfi(args.get(0).getComplex());
                else
                    yield Maja.erfi(args.get(0).getDouble());
            }
            case "stretch" -> {
                assertArity(args, 1);
                yield Maja.stretch(args.get(0).getDouble());
            }
            case "squash" -> {
                assertArity(args, 1);
                yield Maja.squash(args.get(0).getDouble());
            }
            case "Si" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.Si(args.get(0).getComplex());
                else
                    yield Maja.Si(args.get(0).getDouble());
            }
            case "Ci" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.Ci(args.get(0).getComplex());
                else
                    yield Maja.Ci(args.get(0).getDouble());
            }
            case "si" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.si(args.get(0).getComplex());
                else
                    yield Maja.si(args.get(0).getDouble());
            }
            case "Cin" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.Cin(args.get(0).getComplex());
                else
                    yield Maja.Cin(args.get(0).getDouble());
            }
            case "Shi" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.Shi(args.get(0).getComplex());
                else
                    yield Maja.Shi(args.get(0).getDouble());
            }
            case "Chi" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.Chi(args.get(0).getComplex());
                else
                    yield Maja.Chi(args.get(0).getDouble());
            }
            case "fresnelC" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.fresnelC(args.get(0).getComplex());
                else
                    yield Maja.fresnelC(args.get(0).getDouble());
            }
            case "fresnelS" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    yield Maja.fresnelS(args.get(0).getComplex());
                else
                    yield Maja.fresnelS(args.get(0).getDouble());
            }
            case "y0" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    throw new IllegalArgumentException("y0 not yet implemented for complex arguments.");
                else
                    yield Maja.besselY0(args.get(0).getDouble());
            }
            case "y1" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    throw new IllegalArgumentException("y1 not yet implemented for complex arguments.");
                else
                    yield Maja.besselY1(args.get(0).getDouble());
            }
            case "yn" -> {
                assertArity(args, 2);
                if (args.get(0).isLong() && args.get(1).isDouble())
                    yield Maja.besselYn((int) args.get(0).getLong(), args.get(1).getDouble());
                else
                    throw new IllegalArgumentException("yn not yet implemented for complex arguments.");
            }
            case "j0" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    throw new IllegalArgumentException("j0 not yet implemented for complex arguments.");
                else
                    yield Maja.besselJ0(args.get(0).getDouble());
            }
            case "j1" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    throw new IllegalArgumentException("j1 not yet implemented for complex arguments.");
                else
                    yield Maja.besselJ1(args.get(0).getDouble());
            }
            case "jn" -> {
                assertArity(args, 2);
                if (args.get(0).isLong() && args.get(1).isDouble())
                    yield Maja.besselJn((int) args.get(0).getLong(), args.get(1).getDouble());
                else
                    throw new IllegalArgumentException("yn not yet implemented for complex arguments.");
            }
            case "i0" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    throw new IllegalArgumentException("i0 not yet implemented for complex arguments.");
                else
                    yield Maja.besselI0(args.get(0).getDouble());
            }
            case "i1" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    throw new IllegalArgumentException("i1 not yet implemented for complex arguments.");
                else
                    yield Maja.besselI1(args.get(0).getDouble());
            }
            case "k0" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    throw new IllegalArgumentException("k0 not yet implemented for complex arguments.");
                else
                    yield Maja.besselK0(args.get(0).getDouble());
            }
            case "k1" -> {
                assertArity(args, 1);
                if (args.get(0).isComplex())
                    throw new IllegalArgumentException("k1 not yet implemented for complex arguments.");
                else
                    yield Maja.besselK1(args.get(0).getDouble());
            }
            case "kn" -> {
                assertArity(args, 2);
                if (args.get(0).isLong() && args.get(1).isDouble())
                    yield Maja.besselKn((int) args.get(0).getLong(), args.get(1).getDouble());
                else
                    throw new IllegalArgumentException("yn not yet implemented for complex arguments.");
            }
            case "fib" -> {
                assertArity(args, 1);
                yield Maja.fib((int) args.get(0).getLong());
            }
            case "hypergeo2F1" -> {
                assertArity(args, 4);
                for (var arg : args)
                    if (arg.isComplex())
                        throw new IllegalArgumentException("hypergeo2F1 not yet implemented for complex arguments.");
                yield Maja.hypergeo2F1(args.get(0).getDouble(), args.get(1).getDouble(), args.get(2).getDouble(), args.get(3).getDouble());
            }
            case "hypergeo1F1" -> {
                assertArity(args, 3);
                for (var arg : args)
                    if (arg.isComplex())
                        throw new IllegalArgumentException("hypergeo1F1 not yet implemented for complex arguments.");
                yield Maja.hypergeo1F1(args.get(0).getDouble(), args.get(1).getDouble(), args.get(2).getDouble());
            }
            case "hypergeo3F0" -> {
                assertArity(args, 4);
                for (var arg : args)
                    if (arg.isComplex())
                        throw new IllegalArgumentException("hypergeo3F0 not yet implemented for complex arguments.");
                yield Maja.hypergeo3F0(args.get(0).getDouble(), args.get(1).getDouble(), args.get(2).getDouble(), args.get(3).getDouble());
            }
            case "hypergeo1F2" -> {
                assertArity(args, 4);
                for (var arg : args)
                    if (arg.isComplex())
                        throw new IllegalArgumentException("hypergeo1F2 not yet implemented for complex arguments.");
                yield Maja.hypergeo1F2(args.get(0).getDouble(), args.get(1).getDouble(), args.get(2).getDouble(), args.get(3).getDouble());
            }
            case "struve" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    throw new IllegalArgumentException("struve not yet implemented for complex arguments.");
                else
                    yield Maja.struve(args.get(0).getDouble(), args.get(1).getDouble());
            }
            case "jv" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    throw new IllegalArgumentException("jv not yet implemented for complex arguments.");
                else
                    yield Maja.besselJv(args.get(0).getDouble(), args.get(1).getDouble());
            }
            case "yv" -> {
                assertArity(args, 2);
                if (args.get(0).isComplex() || args.get(1).isComplex())
                    throw new IllegalArgumentException("yv not yet implemented for complex arguments.");
                else
                    yield Maja.besselYv(args.get(0).getDouble(), args.get(1).getDouble());
            }
            case "binomial" -> {
                assertArity(args, 2);
                yield Maja.binomial((int) args.get(0).getLong(), (int) args.get(1).getLong());
            }
            default -> throw new ArithmeticException("Unknown function: " + name);
        });
    }

    public static Number constant(String name) {
        return new Number(switch (name) {
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
            case "i" -> Maja.I;
            default -> Double.NaN;
        });
    }
}