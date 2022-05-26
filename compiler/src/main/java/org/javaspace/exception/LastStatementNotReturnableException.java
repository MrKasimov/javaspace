package org.javaspace.exception;

import org.javaspace.domain.node.statement.Statement;

public class LastStatementNotReturnableException extends RuntimeException {
    public LastStatementNotReturnableException(Statement lastStatement) {
        super(String.format("The last statement %s in the function is not an expression", lastStatement));
    }
}
