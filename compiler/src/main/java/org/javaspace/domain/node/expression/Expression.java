package org.javaspace.domain.node.expression;

import org.javaspace.bytecodegeneration.expression.ExpressionGenerator;
import org.javaspace.bytecodegeneration.statement.StatementGenerator;
import org.javaspace.domain.node.statement.Statement;
import org.javaspace.domain.type.Type;

public interface Expression extends Statement {
    Type getType();
    void accept(ExpressionGenerator genrator);
    @Override
    void accept(StatementGenerator generator);
}
