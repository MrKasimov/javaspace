package org.javaspace.exception;

import org.javaspace.domain.scope.FunctionSignature;

public class MethodWithNameAlreadyDefinedException extends RuntimeException {
    public MethodWithNameAlreadyDefinedException(FunctionSignature signature) {
        super("Method already defined in scope :" + signature);
    }
}
