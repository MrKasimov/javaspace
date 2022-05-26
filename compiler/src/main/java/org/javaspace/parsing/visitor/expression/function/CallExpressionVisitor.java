package org.javaspace.parsing.visitor.expression.function;

import org.javaspace.antlr.JavaSpaceBaseVisitor;
import org.javaspace.antlr.JavaSpaceParser.ArgumentListContext;
import org.javaspace.antlr.JavaSpaceParser.ConstructorCallContext;
import org.javaspace.antlr.JavaSpaceParser.FunctionCallContext;
import org.javaspace.antlr.JavaSpaceParser.SupercallContext;
import org.javaspace.domain.node.expression.*;
import org.javaspace.domain.scope.FunctionSignature;
import org.javaspace.domain.scope.LocalVariable;
import org.javaspace.domain.scope.Scope;
import org.javaspace.domain.type.ClassType;
import org.javaspace.exception.FunctionNameEqualClassException;
import org.javaspace.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CallExpressionVisitor extends JavaSpaceBaseVisitor<Call> {
    private final ExpressionVisitor expressionVisitor;
    private final Scope scope;

    public CallExpressionVisitor(ExpressionVisitor expressionVisitor, Scope scope) {
        this.expressionVisitor = expressionVisitor;
        this.scope = scope;
    }

    
    public Call visitFunctionCall(@NotNull FunctionCallContext ctx) {
        String functionName = ctx.functionName().getText();
        if (functionName.equals(scope.getClassName())) {
            throw new FunctionNameEqualClassException(functionName);
        }
        List<Argument> arguments = getArgumentsForCall(ctx.argumentList());
        boolean ownerIsExplicit = ctx.owner != null;
        if (ownerIsExplicit) {
            Expression owner = ctx.owner.accept(expressionVisitor);
            FunctionSignature signature = scope.getMethodCallSignature(Optional.of(owner.getType()),functionName, arguments);
            return new FunctionCall(signature, arguments, owner);
        }
        ClassType thisType = new ClassType(scope.getClassName());
        FunctionSignature signature = scope.getMethodCallSignature(functionName, arguments);
        LocalVariable thisVariable = new LocalVariable("this",thisType);
        return new FunctionCall(signature, arguments, new LocalVariableReference(thisVariable));
    }

    
    public Call visitConstructorCall(@NotNull ConstructorCallContext ctx) {
        String className = ctx.className().getText();
        List<Argument> arguments = getArgumentsForCall(ctx.argumentList());
        return new ConstructorCall(className, arguments);
    }

    
    public Call visitSupercall(@NotNull SupercallContext ctx) {
        List<Argument> arguments = getArgumentsForCall(ctx.argumentList());
        return new SuperCall(arguments);
    }

    private List<Argument> getArgumentsForCall(ArgumentListContext argumentsListCtx) {
        if (argumentsListCtx != null) {
            ArgumentExpressionsListVisitor visitor = new ArgumentExpressionsListVisitor(expressionVisitor);
            return argumentsListCtx.accept(visitor);
        }
        return Collections.emptyList();
    }
}