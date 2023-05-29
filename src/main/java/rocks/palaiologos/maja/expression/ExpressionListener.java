// Generated from /home/palaiologos/workspace/Maja/src/main/java/rocks/palaiologos/maja/expression/Expression.g4 by ANTLR 4.12.0

    package rocks.palaiologos.maja.expression;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExpressionParser}.
 */
public interface ExpressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#main}.
	 * @param ctx the parse tree
	 */
	void enterMain(ExpressionParser.MainContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#main}.
	 * @param ctx the parse tree
	 */
	void exitMain(ExpressionParser.MainContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#toplevel}.
	 * @param ctx the parse tree
	 */
	void enterToplevel(ExpressionParser.ToplevelContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#toplevel}.
	 * @param ctx the parse tree
	 */
	void exitToplevel(ExpressionParser.ToplevelContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(ExpressionParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(ExpressionParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SimpleAssignment}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterSimpleAssignment(ExpressionParser.SimpleAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SimpleAssignment}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitSimpleAssignment(ExpressionParser.SimpleAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MatrixAssignment}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterMatrixAssignment(ExpressionParser.MatrixAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MatrixAssignment}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitMatrixAssignment(ExpressionParser.MatrixAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SimpleFunctionDeclaration}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterSimpleFunctionDeclaration(ExpressionParser.SimpleFunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SimpleFunctionDeclaration}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitSimpleFunctionDeclaration(ExpressionParser.SimpleFunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionDeclaration}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(ExpressionParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionDeclaration}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(ExpressionParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code If}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterIf(ExpressionParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code If}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitIf(ExpressionParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SimpleIf}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterSimpleIf(ExpressionParser.SimpleIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SimpleIf}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitSimpleIf(ExpressionParser.SimpleIfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code While}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterWhile(ExpressionParser.WhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code While}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitWhile(ExpressionParser.WhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code For}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterFor(ExpressionParser.ForContext ctx);
	/**
	 * Exit a parse tree produced by the {@code For}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitFor(ExpressionParser.ForContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Return}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterReturn(ExpressionParser.ReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Return}
	 * labeled alternative in {@link ExpressionParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitReturn(ExpressionParser.ReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprGcd}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprGcd(ExpressionParser.ExprGcdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprGcd}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprGcd(ExpressionParser.ExprGcdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprIndex}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprIndex(ExpressionParser.ExprIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprIndex}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprIndex(ExpressionParser.ExprIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprNeg}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprNeg(ExpressionParser.ExprNegContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprNeg}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprNeg(ExpressionParser.ExprNegContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprNot}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprNot(ExpressionParser.ExprNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprNot}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprNot(ExpressionParser.ExprNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprPos}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprPos(ExpressionParser.ExprPosContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprPos}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprPos(ExpressionParser.ExprPosContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprDiv}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprDiv(ExpressionParser.ExprDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprDiv}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprDiv(ExpressionParser.ExprDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprOr}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprOr(ExpressionParser.ExprOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprOr}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprOr(ExpressionParser.ExprOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprSub}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprSub(ExpressionParser.ExprSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprSub}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprSub(ExpressionParser.ExprSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprMul}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprMul(ExpressionParser.ExprMulContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprMul}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprMul(ExpressionParser.ExprMulContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprGe}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprGe(ExpressionParser.ExprGeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprGe}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprGe(ExpressionParser.ExprGeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprLambda}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprLambda(ExpressionParser.ExprLambdaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprLambda}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprLambda(ExpressionParser.ExprLambdaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprMod}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprMod(ExpressionParser.ExprModContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprMod}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprMod(ExpressionParser.ExprModContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprLe}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprLe(ExpressionParser.ExprLeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprLe}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprLe(ExpressionParser.ExprLeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprParen}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprParen(ExpressionParser.ExprParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprParen}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprParen(ExpressionParser.ExprParenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprInt}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprInt(ExpressionParser.ExprIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprInt}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprInt(ExpressionParser.ExprIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprMatrix}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprMatrix(ExpressionParser.ExprMatrixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprMatrix}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprMatrix(ExpressionParser.ExprMatrixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprGt}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprGt(ExpressionParser.ExprGtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprGt}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprGt(ExpressionParser.ExprGtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprEq}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprEq(ExpressionParser.ExprEqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprEq}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprEq(ExpressionParser.ExprEqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAnd}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprAnd(ExpressionParser.ExprAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAnd}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprAnd(ExpressionParser.ExprAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprPow}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprPow(ExpressionParser.ExprPowContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprPow}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprPow(ExpressionParser.ExprPowContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprFunctionCall}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprFunctionCall(ExpressionParser.ExprFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprFunctionCall}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprFunctionCall(ExpressionParser.ExprFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprLcm}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprLcm(ExpressionParser.ExprLcmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprLcm}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprLcm(ExpressionParser.ExprLcmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprLt}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprLt(ExpressionParser.ExprLtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprLt}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprLt(ExpressionParser.ExprLtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprRem}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprRem(ExpressionParser.ExprRemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprRem}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprRem(ExpressionParser.ExprRemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprNeq}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprNeq(ExpressionParser.ExprNeqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprNeq}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprNeq(ExpressionParser.ExprNeqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprReal}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprReal(ExpressionParser.ExprRealContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprReal}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprReal(ExpressionParser.ExprRealContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprVariable}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprVariable(ExpressionParser.ExprVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprVariable}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprVariable(ExpressionParser.ExprVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAdd}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprAdd(ExpressionParser.ExprAddContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAdd}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprAdd(ExpressionParser.ExprAddContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#matrix}.
	 * @param ctx the parse tree
	 */
	void enterMatrix(ExpressionParser.MatrixContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#matrix}.
	 * @param ctx the parse tree
	 */
	void exitMatrix(ExpressionParser.MatrixContext ctx);
}