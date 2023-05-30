package rocks.palaiologos.maja.expression;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

public final class Evaluator {
    static class ThrowingErrorListener extends BaseErrorListener {
        private final int lineNumberOffset;

        public ThrowingErrorListener(int lineNumberOffset) {
            this.lineNumberOffset = lineNumberOffset;
        }

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)
                throws ParseCancellationException {
            throw new ParseCancellationException("line " + lineNumberOffset + line + ":" + charPositionInLine + " " + msg);
        }
    }

    public static Object evaluate(String input, Environment env) {
        ExpressionLexer lex = new ExpressionLexer(CharStreams.fromString(input)) {
            @Override
            public void skip() {
                setChannel(HIDDEN);
            }
        };
        lex.removeErrorListeners();
        lex.addErrorListener(new ThrowingErrorListener(0));
        CommonTokenStream tokens = new CommonTokenStream(lex);
        ExpressionParser parser = new ExpressionParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new ThrowingErrorListener(0));
        ParseTree tree = parser.main();
        DefaultExpressionVisitor visitor = new DefaultExpressionVisitor(env);
        return visitor.visit(tree);
    }
}
