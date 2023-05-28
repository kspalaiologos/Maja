
grammar Expression;

@header {
    package rocks.palaiologos.maja.expression;
}

main : toplevel* EOF;

toplevel
    : declaration
    | expression
    ;

declaration
    : ID '=' expression ';' # SimpleAssignment
    | ID '(' (ID (',' ID)*)? ')' '=' expression ';' # SimpleFunctionDeclaration
    | ID '(' (ID (',' ID)*)? ')' '{' toplevel* '}' # FunctionDeclaration
    | 'if' expression '{' toplevel* '}' ('else' '{' toplevel* '}')? # If
    | 'if' expression 'then' expression 'else' expression # SimpleIf
    | 'while' expression '{' toplevel* '}' # While
    | 'for' ID '=' expression 'to' expression '{' toplevel* '}' # For
    ;

expression
    : expression '**' expression # ExprPow
    | expression 'rem' expression # ExprRem
    | expression 'mod' expression # ExprMod
    | expression 'gcd' expression # ExprGcd
    | expression 'lcm' expression # ExprLcm
    | expression '*' expression # ExprMul
    | expression '/' expression # ExprDiv
    | expression '+' expression # ExprAdd
    | expression '-' expression # ExprSub
    | expression '==' expression # ExprEq
    | expression '!=' expression # ExprNeq
    | expression '<' expression # ExprLt
    | expression '<=' expression # ExprLe
    | expression '>' expression # ExprGt
    | expression '>=' expression # ExprGe
    | expression '&&' expression # ExprAnd
    | expression '||' expression # ExprOr
    | '+' expression # ExprPos
    | '-' expression # ExprNeg
    | '!' expression # ExprNot
    | '(' expression ')' # ExprParen
    | '{' expression '}' # ExprBlock
    | '[' expression ']' # ExprMatrix
    | ID '(' (expression (',' expression)*)? ')' # ExprFunctionCall
    | ID # ExprVariable
    | INT # ExprInt
    | REAL # ExprReal
    ;

ID : [A-Za-z_][A-Za-z0-9_]* ;

fragment SIGN : ('+' | '-') ;
fragment EXP : ('e' | 'E') SIGN? INUM+ ;
fragment INUM : ('0' .. '9') ;
REAL : INUM+ ('.' INUM* EXP?)? ;
INT : INUM+ EXP? ;

TRASH : [ \t\r\n]+ -> channel(HIDDEN) ;

