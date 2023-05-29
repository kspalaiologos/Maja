// Generated from /home/palaiologos/workspace/Maja/src/main/java/rocks/palaiologos/maja/expression/Expression.g4 by ANTLR 4.12.0

    package rocks.palaiologos.maja.expression;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExpressionParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExpressionVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#main}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain(ExpressionParser.MainContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#toplevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToplevel(ExpressionParser.ToplevelContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(ExpressionParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SimpleAssignment}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleAssignment(ExpressionParser.SimpleAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MatrixAssignment}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatrixAssignment(ExpressionParser.MatrixAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SimpleFunctionDeclaration}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleFunctionDeclaration(ExpressionParser.SimpleFunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionDeclaration}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(ExpressionParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code If}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(ExpressionParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SimpleIf}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleIf(ExpressionParser.SimpleIfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code While}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile(ExpressionParser.WhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code For}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor(ExpressionParser.ForContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Return}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(ExpressionParser.ReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprGcd}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprGcd(ExpressionParser.ExprGcdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprIndex}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprIndex(ExpressionParser.ExprIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprNeg}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNeg(ExpressionParser.ExprNegContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprNot}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNot(ExpressionParser.ExprNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprPos}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPos(ExpressionParser.ExprPosContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprDiv}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprDiv(ExpressionParser.ExprDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprOr}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprOr(ExpressionParser.ExprOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprSub}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSub(ExpressionParser.ExprSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMul}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMul(ExpressionParser.ExprMulContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprGe}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprGe(ExpressionParser.ExprGeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprLambda}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLambda(ExpressionParser.ExprLambdaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMod}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMod(ExpressionParser.ExprModContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprLe}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLe(ExpressionParser.ExprLeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprParen}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParen(ExpressionParser.ExprParenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprInt}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprInt(ExpressionParser.ExprIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMatrix}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMatrix(ExpressionParser.ExprMatrixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprGt}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprGt(ExpressionParser.ExprGtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprEq}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprEq(ExpressionParser.ExprEqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAnd}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAnd(ExpressionParser.ExprAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprPow}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPow(ExpressionParser.ExprPowContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprFunctionCall}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprFunctionCall(ExpressionParser.ExprFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprLcm}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLcm(ExpressionParser.ExprLcmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprLt}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLt(ExpressionParser.ExprLtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprRem}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprRem(ExpressionParser.ExprRemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprNeq}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNeq(ExpressionParser.ExprNeqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprReal}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprReal(ExpressionParser.ExprRealContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprVariable}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprVariable(ExpressionParser.ExprVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAdd}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAdd(ExpressionParser.ExprAddContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#matrix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatrix(ExpressionParser.MatrixContext ctx);
}