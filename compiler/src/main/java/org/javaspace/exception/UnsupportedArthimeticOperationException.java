package org.javaspace.exception;

import org.javaspace.domain.node.expression.arthimetic.ArthimeticExpression;
import org.javaspace.domain.node.expression.Expression;

public class UnsupportedArthimeticOperationException extends RuntimeException {
    public UnsupportedArthimeticOperationException(ArthimeticExpression expression) {
        super(prepareMesage(expression));
    }

    private static String prepareMesage(ArthimeticExpression expression) {
        Expression leftExpression = expression.getLeftExpression();
        Expression rightExpression = expression.getRightExpression();
        return "Unsupported arthimetic operation between " + leftExpression +" and "+rightExpression;
    }
}
