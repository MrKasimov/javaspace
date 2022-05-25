package org.javaspace.exception;

import org.javaspace.domain.node.expression.Argument;
import org.javaspace.domain.scope.Scope;

import java.util.List;

public class MethodSignatureNotFoundException extends RuntimeException {
    public MethodSignatureNotFoundException(Scope scope, String methodName, List<Argument> parameterTypes) {
        super("There is no method '" + methodName + "' with parameters " + parameterTypes);
    }
}
