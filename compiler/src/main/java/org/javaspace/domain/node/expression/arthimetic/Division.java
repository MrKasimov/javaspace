package org.javaspace.domain.node.expression.arthimetic;

import org.javaspace.bytecodegeneration.expression.ExpressionGenerator;
import org.javaspace.bytecodegeneration.statement.StatementGenerator;
import org.javaspace.domain.node.expression.Expression;

public class Division extends ArthimeticExpression {
    public Division(Expression leftExpress, Expression rightExpress) {
        super(leftExpress,rightExpress);
    }

    @Override
    public void accept(ExpressionGenerator genrator) {
        genrator.generate(this);
    }

    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }
}
