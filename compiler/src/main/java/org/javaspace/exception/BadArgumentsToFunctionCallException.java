package org.javaspace.exception;

import org.javaspace.domain.node.expression.Call;

public class BadArgumentsToFunctionCallException extends RuntimeException {
    public BadArgumentsToFunctionCallException(Call functionCall) {
        super("You called function with bad arguments " + functionCall);
    }
}
