package org.javaspace.domain.node.statement;

import org.javaspace.bytecodegeneration.statement.StatementGenerator;
import org.javaspace.domain.node.expression.Expression;

public class ReturnStatement implements Statement {

    private final Expression expression;

    public ReturnStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }

    public Expression getExpression() {
        return expression;
    }
}
