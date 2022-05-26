package org.javaspace.exception;

public class FunctionNameEqualClassException extends RuntimeException {
    public FunctionNameEqualClassException(String functionName) {
        super(String.format("Function %s has the same name as a class", functionName));
    }
}
