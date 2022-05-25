package org.javaspace.exception;

import org.javaspace.antlr.JavaSpaceParser;
import org.javaspace.antlr.JavaSpaceParser.ExpressionContext;
import org.javaspace.domain.scope.FunctionSignature;

import java.util.List;

public class BadArgumentsSize extends RuntimeException {
    public BadArgumentsSize(FunctionSignature function, List<ExpressionContext> calledParameters) {
        super("Bad arguments amount");
    }
}
