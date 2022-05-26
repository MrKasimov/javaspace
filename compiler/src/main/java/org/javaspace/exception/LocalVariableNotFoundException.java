package org.javaspace.exception;

import org.javaspace.domain.scope.Scope;

public class LocalVariableNotFoundException extends RuntimeException {
    public LocalVariableNotFoundException(Scope scope, String variableName) {
        super(String.format("No local variable found for name %s in scope %s", variableName, scope));
    }
}
