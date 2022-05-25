package org.javaspace.domain.node.expression;

import org.javaspace.bytecodegeneration.expression.ExpressionGenerator;
import org.javaspace.bytecodegeneration.statement.StatementGenerator;

public interface Reference extends Expression {
    String geName();

    @Override
    void accept(ExpressionGenerator genrator);

    @Override
    void accept(StatementGenerator generator);
}
