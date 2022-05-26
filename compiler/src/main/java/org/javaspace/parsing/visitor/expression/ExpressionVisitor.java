package org.javaspace.parsing.visitor.expression;

import org.javaspace.antlr.JavaSpaceBaseVisitor;
import org.javaspace.antlr.JavaSpaceParser.AddContext;
import org.javaspace.antlr.JavaSpaceParser.ConditionalExpressionContext;
import org.javaspace.antlr.JavaSpaceParser.ConstructorCallContext;
import org.javaspace.antlr.JavaSpaceParser.DivideContext;
import org.javaspace.antlr.JavaSpaceParser.FunctionCallContext;
import org.javaspace.antlr.JavaSpaceParser.MultiplyContext;
import org.javaspace.antlr.JavaSpaceParser.SubstractContext;
import org.javaspace.antlr.JavaSpaceParser.SupercallContext;
import org.javaspace.antlr.JavaSpaceParser.ValueContext;
import org.javaspace.antlr.JavaSpaceParser.VarReferenceContext;
import org.javaspace.domain.node.expression.*;
import org.javaspace.domain.scope.Scope;
import org.javaspace.parsing.visitor.expression.function.CallExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;
import org.javaspace.domain.node.expression.ConditionalExpression;
import org.javaspace.domain.node.expression.Expression;

public class ExpressionVisitor extends JavaSpaceBaseVisitor<Expression> {

    private final ArithmeticExpressionVisitor arithmeticExpressionVisitor;
    private final VariableReferenceExpressionVisitor variableReferenceExpressionVisitor;
    private final ValueExpressionVisitor valueExpressionVisitor;
    private final CallExpressionVisitor callExpressionVisitor;
    private final ConditionalExpressionVisitor conditionalExpressionVisitor;

    public ExpressionVisitor(Scope scope) {
        arithmeticExpressionVisitor = new ArithmeticExpressionVisitor(this);
        variableReferenceExpressionVisitor = new VariableReferenceExpressionVisitor(scope);
        valueExpressionVisitor = new ValueExpressionVisitor();
        callExpressionVisitor = new CallExpressionVisitor(this, scope);
        conditionalExpressionVisitor = new ConditionalExpressionVisitor(this);
    }

    
    public Expression visitVarReference(@NotNull VarReferenceContext ctx) {
        return variableReferenceExpressionVisitor.visitVarReference(ctx);
    }

    
    public Expression visitValue(@NotNull ValueContext ctx) {
        return valueExpressionVisitor.visitValue(ctx);
    }

    
    public Expression visitFunctionCall(@NotNull FunctionCallContext ctx) {
        return callExpressionVisitor.visitFunctionCall(ctx);
    }

    
    public Expression visitConstructorCall(@NotNull ConstructorCallContext ctx) {
        return callExpressionVisitor.visitConstructorCall(ctx);
    }

    
    public Expression visitSupercall(@NotNull SupercallContext ctx) {
        return callExpressionVisitor.visitSupercall(ctx);
    }

    
    public Expression visitAdd(@NotNull AddContext ctx) {

        return arithmeticExpressionVisitor.visitAdd(ctx);
    }

    
    public Expression visitMultiply(@NotNull MultiplyContext ctx) {

        return arithmeticExpressionVisitor.visitMultiply(ctx);
    }

    
    public Expression visitSubstract(@NotNull SubstractContext ctx) {

        return arithmeticExpressionVisitor.visitSubstract(ctx);
    }

    
    public Expression visitDivide(@NotNull DivideContext ctx) {

        return arithmeticExpressionVisitor.visitDivide(ctx);
    }

    
    public ConditionalExpression visitConditionalExpression(@NotNull ConditionalExpressionContext ctx) {
        return conditionalExpressionVisitor.visitConditionalExpression(ctx);
    }
}
