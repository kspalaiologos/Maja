package rocks.palaiologos.maja.expression;

import org.antlr.v4.runtime.tree.*;

import java.util.List;

public class DefaultExpressionVisitor extends AbstractParseTreeVisitor<Object> implements ExpressionVisitor<Object> {
    private Environment env;

    @Override
    public List<Object> visitMain(ExpressionParser.MainContext ctx) {
        return ctx.toplevel().stream().map(this::visit).toList();
    }

    @Override
    public Object visitToplevel(ExpressionParser.ToplevelContext ctx) {
        return visit(ctx.getChild(0));
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
            public Object eval(Environment env) {
                return visit(ctx.expression());
            }
        };
        env.set(ctx.ID(0).getText(), f);
        return f;
    }

    @Override
    public Object visitFunctionDeclaration(ExpressionParser.FunctionDeclarationContext ctx) {
        return null;
    }

    @Override
    public Object visitIf(ExpressionParser.IfContext ctx) {
        return null;
    }

    @Override
    public Object visitSimpleIf(ExpressionParser.SimpleIfContext ctx) {
        return null;
    }

    @Override
    public Object visitWhile(ExpressionParser.WhileContext ctx) {
        return null;
    }

    @Override
    public Object visitFor(ExpressionParser.ForContext ctx) {
        return null;
    }

    @Override
    public Object visitExprGcd(ExpressionParser.ExprGcdContext ctx) {
        return null;
    }

    @Override
    public Object visitExprNeg(ExpressionParser.ExprNegContext ctx) {
        return null;
    }

    @Override
    public Object visitExprNot(ExpressionParser.ExprNotContext ctx) {
        return null;
    }

    @Override
    public Object visitExprPos(ExpressionParser.ExprPosContext ctx) {
        return null;
    }

    @Override
    public Object visitExprDiv(ExpressionParser.ExprDivContext ctx) {
        return null;
    }

    @Override
    public Object visitExprOr(ExpressionParser.ExprOrContext ctx) {
        return null;
    }

    @Override
    public Object visitExprSub(ExpressionParser.ExprSubContext ctx) {
        return null;
    }

    @Override
    public Object visitExprMul(ExpressionParser.ExprMulContext ctx) {
        return null;
    }

    @Override
    public Object visitExprGe(ExpressionParser.ExprGeContext ctx) {
        return null;
    }

    @Override
    public Object visitExprMod(ExpressionParser.ExprModContext ctx) {
        return null;
    }

    @Override
    public Object visitExprLe(ExpressionParser.ExprLeContext ctx) {
        return null;
    }

    @Override
    public Object visitExprParen(ExpressionParser.ExprParenContext ctx) {
        return null;
    }

    @Override
    public Object visitExprInt(ExpressionParser.ExprIntContext ctx) {
        return null;
    }

    @Override
    public Object visitExprMatrix(ExpressionParser.ExprMatrixContext ctx) {
        return null;
    }

    @Override
    public Object visitExprGt(ExpressionParser.ExprGtContext ctx) {
        return null;
    }

    @Override
    public Object visitExprBlock(ExpressionParser.ExprBlockContext ctx) {
        return null;
    }

    @Override
    public Object visitExprEq(ExpressionParser.ExprEqContext ctx) {
        return null;
    }

    @Override
    public Object visitExprAnd(ExpressionParser.ExprAndContext ctx) {
        return null;
    }

    @Override
    public Object visitExprPow(ExpressionParser.ExprPowContext ctx) {
        return null;
    }

    @Override
    public Object visitExprFunctionCall(ExpressionParser.ExprFunctionCallContext ctx) {
        return null;
    }

    @Override
    public Object visitExprLcm(ExpressionParser.ExprLcmContext ctx) {
        return null;
    }

    @Override
    public Object visitExprLt(ExpressionParser.ExprLtContext ctx) {
        return null;
    }

    @Override
    public Object visitExprRem(ExpressionParser.ExprRemContext ctx) {
        return null;
    }

    @Override
    public Object visitExprNeq(ExpressionParser.ExprNeqContext ctx) {
        return null;
    }

    @Override
    public Object visitExprReal(ExpressionParser.ExprRealContext ctx) {
        return null;
    }

    @Override
    public Object visitExprVariable(ExpressionParser.ExprVariableContext ctx) {
        return null;
    }

    @Override
    public Object visitExprAdd(ExpressionParser.ExprAddContext ctx) {
        return null;
    }
}
