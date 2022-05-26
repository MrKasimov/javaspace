package org.javaspace.exception;

import org.javaspace.domain.node.expression.Argument;
import org.javaspace.domain.scope.Scope;

import java.util.List;

public class MethodSignatureNotFoundException extends RuntimeException {
    public MethodSignatureNotFoundException(Scope scope, String methodName, List<Argument> parameterTypes) {
        super(String.format("Signature of method %s with parameters %s wasn't found in the scope %s",
                methodName, parameterTypes, scope));
    }
}
