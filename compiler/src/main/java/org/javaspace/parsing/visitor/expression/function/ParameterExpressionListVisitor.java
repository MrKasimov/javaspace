package org.javaspace.parsing.visitor.expression.function;

import com.google.common.collect.Lists;
import org.javaspace.antlr.JavaSpaceBaseVisitor;
import org.javaspace.antlr.JavaSpaceParser;
import org.javaspace.antlr.JavaSpaceParser.ParameterContext;
import org.javaspace.antlr.JavaSpaceParser.ParameterWithDefaultValueContext;
import org.javaspace.antlr.JavaSpaceParser.ParametersListContext;
import org.javaspace.domain.node.expression.Parameter;
import org.javaspace.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ParameterExpressionListVisitor extends JavaSpaceBaseVisitor<List<Parameter>> {

    private final ExpressionVisitor expressionVisitor;

    public ParameterExpressionListVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    public List<Parameter> visitParametersList(@NotNull ParametersListContext ctx) {
        List<ParameterContext> paramsCtx = ctx.parameter();
        ParameterExpressionVisitor parameterExpressionVisitor = new ParameterExpressionVisitor(expressionVisitor);
        List<Parameter> parameters = new ArrayList<>();
        if(paramsCtx != null) {
            List<Parameter> params = Lists.transform(paramsCtx, p -> p.accept(parameterExpressionVisitor));
            parameters.addAll(params);
        }
        List<ParameterWithDefaultValueContext> paramsWithDefaultValueCtx = ctx.parameterWithDefaultValue();
        if(paramsWithDefaultValueCtx != null) {
            List<Parameter> params = Lists.transform(paramsWithDefaultValueCtx, p -> p.accept(parameterExpressionVisitor));
            parameters.addAll(params);
        }
        return parameters;
    }

}

