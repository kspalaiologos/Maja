package rocks.palaiologos.maja.expression;

import java.util.List;

interface ExpressionFunction {
    List<String> params();
    Object eval();
}
