package org.javaspace.domain.node.statement;

import org.javaspace.bytecodegeneration.statement.StatementGenerator;
import org.javaspace.domain.node.expression.Expression;
import org.javaspace.domain.scope.Scope;
import org.javaspace.domain.type.Type;
import org.javaspace.util.TypeChecker;
import org.javaspace.exception.UnsupportedRangedLoopTypes;

public class RangedForStatement implements Statement {
    private final Statement iteratorVariable;
    private final Expression startExpression;
    private final Expression endExpression;
    private final Statement statement;
    private final String iteratorVarName;
    private final Scope scope;

    public RangedForStatement(Statement iteratorVariable, Expression startExpression, Expression endExpression, Statement statement, String iteratorVarName, Scope scope) {
        this.scope = scope;
        Type startExpressionType = startExpression.getType();
        Type endExpressionType = endExpression.getType();
        boolean typesAreIntegers = TypeChecker.isInt(startExpressionType) || TypeChecker.isInt(endExpressionType);
        if(!typesAreIntegers) {
            throw new UnsupportedRangedLoopTypes(startExpression,endExpression);
        }
        this.iteratorVariable = iteratorVariable;
        this.startExpression = startExpression;
        this.endExpression = endExpression;
        this.statement = statement;
        this.iteratorVarName = iteratorVarName;
    }

    public Statement getIteratorVariableStatement() {
        return iteratorVariable;
    }

    public Expression getStartExpression() {
        return startExpression;
    }

    public Expression getEndExpression() {
        return endExpression;
    }

    public Statement getStatement() {
        return statement;
    }

    public String getIteratorVarName() {
        return iteratorVarName;
    }

    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }

    public Scope getScope() {
        return scope;
    }

    public Type getType() {
        return startExpression.getType();
    }
}
