package org.javaspace.exception;

import org.javaspace.domain.node.expression.Call;

public class BadArgumentsToFunctionCallException extends RuntimeException {
    public BadArgumentsToFunctionCallException(Call functionCall) {
        super(String.format("Called a function %s with incorrect number of parameters", functionCall));
    }
}
