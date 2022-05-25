package org.javaspace.util;

import org.javaspace.domain.type.BultInType;
import org.javaspace.domain.type.Type;

public final class TypeChecker {
    public static boolean isInt(Type type) {
        return type == BultInType.INT;
    }

    public static boolean isBool(Type type) {
        return type == BultInType.BOOLEAN;
    }

    public static boolean isFloat(Type type) {
        return type == BultInType.FLOAT;
    }

    public static boolean isDouble(Type type) {
        return type == BultInType.DOUBLE;
    }
}
