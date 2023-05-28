package rocks.palaiologos.maja.expression;

import java.util.HashMap;
import java.util.List;

public interface ExpressionFunction {
    List<String> params();
    Object eval(Environment env);
}
