package org.javaspace.domain.node.statement;

import org.javaspace.bytecodegeneration.statement.StatementGenerator;
import org.javaspace.domain.node.expression.Expression;

public class PrintStatement implements Statement {

    private final Expression expression;

    public PrintStatement(Expression expression) {

        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }


    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }
}
