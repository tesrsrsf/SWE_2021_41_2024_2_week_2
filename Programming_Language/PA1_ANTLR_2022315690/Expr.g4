grammar Expr;

// parser rules
prog : (expr ';' NEWLINE?)*;
expr : '(' expr ')'         # parensExpr
     | expr ('*'|'/') expr  # infixExpr
     | expr ('+'|'-') expr  # infixExpr
     | num                  # numberExpr
     | id '=' expr          # assignExpr
     | id                   # identifierExpr
     ;
num  : INT
     | REAL
     ;
id   : ID
     ;

// lexer rules
NEWLINE: [\r\n]+ ;
INT: [-]?[0-9]+ ;          // should handle negatives
REAL: [-+]?[0-9]+'.'[0-9]* ; // should handle signs(+/-)
ID: [a-zA-Z][a-zA-Z0-9_]* ;
WS: [ \t\r\n]+ -> skip ;
