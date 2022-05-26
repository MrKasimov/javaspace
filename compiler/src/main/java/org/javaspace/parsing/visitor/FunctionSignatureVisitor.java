package org.javaspace.parsing.visitor;

import org.javaspace.antlr.JavaSpaceBaseVisitor;
import org.javaspace.antlr.JavaSpaceParser.FunctionSignatureContext;
import org.javaspace.antlr.JavaSpaceParser.ParametersListContext;
import org.javaspace.domain.node.expression.Parameter;
import org.javaspace.domain.scope.Scope;
import org.javaspace.domain.type.Type;
import org.javaspace.domain.scope.FunctionSignature;
import org.javaspace.util.TypeResolver;
import org.javaspace.parsing.visitor.expression.ExpressionVisitor;
import org.javaspace.parsing.visitor.expression.function.ParameterExpressionListVisitor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Collections;
import java.util.List;

public class FunctionSignatureVisitor extends JavaSpaceBaseVisitor<FunctionSignature> {

    private final ExpressionVisitor expressionVisitor;

    public FunctionSignatureVisitor(Scope scope) {
        this.expressionVisitor = new ExpressionVisitor(scope);
    }

    public FunctionSignature visitFunctionSignature(@NotNull FunctionSignatureContext ctx) {
        String name = ctx.functionName().getText();
        Type returnType = TypeResolver.getFromTypeContext(ctx.type());
        ParametersListContext parametersCtx = ctx.parametersList();
        if(parametersCtx == null) {
            return new FunctionSignature(name, Collections.emptyList(), returnType);
        }
        return new FunctionSignature(
                name,
                parametersCtx.accept(new ParameterExpressionListVisitor(expressionVisitor)),
                returnType
        );
    }
}
