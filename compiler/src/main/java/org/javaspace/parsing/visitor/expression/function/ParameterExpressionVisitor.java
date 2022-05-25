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

    @Override
    public Parameter visitParameter(@NotNull ParameterContext ctx) {
        String name = ctx.ID().getText();
        Type type = TypeResolver.getFromTypeContext(ctx.type());
        return new Parameter(name, type, Optional.empty());
    }

    @Override
    public Parameter visitParameterWithDefaultValue(@NotNull ParameterWithDefaultValueContext ctx) {
        String name = ctx.ID().getText();
        Type type = TypeResolver.getFromTypeContext(ctx.type());
        Expression defaultValue = ctx.defaultValue.accept(expressionVisitor);
        return new Parameter(name, type, Optional.of(defaultValue));
    }
}
