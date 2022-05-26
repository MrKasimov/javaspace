grammar JavaSpace;

compilationUnit : classDeclaration EOF;

importDirective  : 'import' FILE_PATH;

className        : qualifiedName ;
classDeclaration : 'class' className '{' classBody '}';
classBody        : field* function*;

field : type name;

functionName      : ID;
function          : functionSignature block;
functionSignature : (type)? functionName '('? parametersList? ')'?;

parameter                 : type ID;
parameterWithDefaultValue : type ID '=' defaultValue=expression;
parametersList            : parameter (',' parameter)*
                          | parameter (',' parameterWithDefaultValue)*
                          | parameterWithDefaultValue (',' parameterWithDefaultValue)*
                          ;

type          : primitiveType
              | classType
              ;

primitiveType : 'boolean' ('[' ']')*
              | 'string'  ('[' ']')*
              | 'char'    ('[' ']')*
              | 'byte'    ('[' ']')*
              | 'short'   ('[' ']')*
              | 'int'     ('[' ']')*
              | 'long'    ('[' ']')*
              | 'float'   ('[' ']')*
              | 'double'  ('[' ']')*
              | 'void'    ('[' ']')*
              ;

classType: qualifiedName ('[' ']')*;

block     : '{' statement* '}';
statement : block
          | variableDeclaration
          | assignment
          | printStatement
          | forStatement
          | returnStatement
          | ifStatement
          | expression
          ;


// control sequences and statements
printStatement      : PRINT expression;
variableDeclaration : VARIABLE name EQUALS expression;
assignment          : name EQUALS expression;
returnStatement     : 'return' expression #ReturnWithValue | 'return' #ReturnVoid;
ifStatement         : 'if'  ('(')? expression (')')? trueStatement=statement ('else' falseStatement=statement)?;
forStatement        : 'for' ('(')? forConditions (')')? statement;
forConditions       : iterator=variableReference  'from' startExpr=expression range='to' endExpr=expression;
name                : ID;

argument      : expression;
namedArgument : name '->' expression;
argumentList  : argument? (',' a=argument)* #UnnamedArgumentsList
              | namedArgument? (',' namedArgument)* #NamedArgumentsList
              ;

expression : variableReference                                      #VarReference
           | owner=expression '.' functionName '(' argumentList ')' #FunctionCall
           | functionName '(' argumentList ')'                      #FunctionCall
           | superCall='super' '('argumentList ')'                  #Supercall
           | newCall='new' className '('argumentList ')'            #ConstructorCall
           | value                                                  #ValueExpr
           |  '('expression '*' expression')'                       #Multiply
           | expression '*' expression                              #Multiply
           | '(' expression '/' expression ')'                      #Divide
           | expression '/' expression                              #Divide
           | '(' expression '+' expression ')'                      #Add
           | expression '+' expression                              #Add
           | '(' expression '-' expression ')'                      #Substract
           | expression '-' expression                              #Substract
           | expression cmp='>' expression                          #ConditionalExpression
           | expression cmp='<' expression                          #ConditionalExpression
           | expression cmp='==' expression                         #ConditionalExpression
           | expression cmp='!=' expression                         #ConditionalExpression
           | expression cmp='>=' expression                         #ConditionalExpression
           | expression cmp='<=' expression                         #ConditionalExpression
           ;

variableReference : ID;

value : NUMBER
      | BOOL
      | STRING
      ;

qualifiedName : ID ('.' ID)*;

VARIABLE  : 'var';
PRINT     : 'print';
EQUALS    : '=';
NUMBER    : '-'?[0-9.]+;
BOOL      : 'true' | 'false';
STRING    : '"'~('\r' | '\n' | '"')*'"';
ID        : [a-zA-Z0-9]+;
WS        : [ \t\n\r]+ -> skip;
COMMENT   : '#' ~[\r\n]* '\r'? '\n' -> skip ;
FILE_PATH : [a-zA-Z0-9/]+;