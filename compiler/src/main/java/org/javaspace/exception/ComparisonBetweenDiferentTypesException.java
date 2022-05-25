package org.javaspace.exception;

import org.javaspace.domain.node.expression.Expression;

public class ComparisonBetweenDiferentTypesException extends RuntimeException {
    public ComparisonBetweenDiferentTypesException(Expression leftExpression, Expression rightExpression) {
        super("Comparison between types " + leftExpression.getType() + " and " + rightExpression.getType() + " not yet supported");
    }
}
