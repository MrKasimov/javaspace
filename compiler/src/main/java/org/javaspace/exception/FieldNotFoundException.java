package org.javaspace.exception;

import org.javaspace.domain.scope.Scope;

public class FieldNotFoundException extends RuntimeException {
    public FieldNotFoundException(Scope scope, String field) {
        super(String.format("Field %s wasn't found in the scope %s", field, scope));
    }
}