package org.javaspace.parsing.visitor.expression.function;

import org.javaspace.antlr.JavaSpaceBaseVisitor;
import org.javaspace.antlr.JavaSpaceParser.ParameterContext;
import org.javaspace.antlr.JavaSpaceParser.ParameterWithDefaultValueContext;
import org.javaspace.domain.node.expression.Expression;
import org.javaspace.domain.node.expression.Parameter;
import org.javaspace.domain.type.Type;
import org.javaspace.util.TypeResolver;
import org.javaspace.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Optional;

public class ParameterExpressionVisitor extends JavaSpaceBaseVisitor<Parameter> {

    private final ExpressionVisitor expressionVisitor;

    public ParameterExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    
    public Parameter visitParameter(@NotNull ParameterContext ctx) {
        return new Parameter(
                ctx.ID().getText(),
                TypeResolver.getFromTypeContext(ctx.type()),
                Optional.empty()
        );
    }

    
    public Parameter visitParameterWithDefaultValue(@NotNull ParameterWithDefaultValueContext ctx) {
        Expression defaultValue = ctx.defaultValue.accept(expressionVisitor);
        return new Parameter(ctx.ID().getText(), TypeResolver.getFromTypeContext(ctx.type()), Optional.of(defaultValue));
    }
}
