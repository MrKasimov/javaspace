package org.javaspace.exception;

import org.javaspace.domain.node.expression.Parameter;

import java.util.List;

public class ParameterForNameNotFoundException extends RuntimeException {
    public ParameterForNameNotFoundException(String name, List<Parameter> parameters) {
    }
}
