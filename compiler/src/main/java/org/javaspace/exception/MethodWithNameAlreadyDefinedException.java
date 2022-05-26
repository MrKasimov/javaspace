package org.javaspace.exception;

import org.javaspace.domain.scope.FunctionSignature;

public class MethodWithNameAlreadyDefinedException extends RuntimeException {
    public MethodWithNameAlreadyDefinedException(FunctionSignature signature) {
        super(String.format("Method %s is already defined in this scope", signature));
    }
}
