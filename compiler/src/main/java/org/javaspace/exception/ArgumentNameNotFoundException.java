package org.javaspace.exception;

import org.javaspace.domain.node.expression.Argument;
import org.javaspace.domain.node.expression.Parameter;

import java.util.List;

public class ArgumentNameNotFoundException extends RuntimeException {
    public ArgumentNameNotFoundException(Argument argument, List<Parameter> parameters) {
        super(String.format("Argument %s wasn't found among parameters %s", argument.getParameterName().get(), parameters));
    }
}
