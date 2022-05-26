package org.javaspace.exception;

import org.javaspace.domain.type.Type;

public class MixedComparisonNotAllowedException extends RuntimeException {
    public MixedComparisonNotAllowedException(Type lhs, Type rhs) {
        super(String.format("An attempt to compare a primitive and an object: %s and %s", lhs, rhs));
    }
}
