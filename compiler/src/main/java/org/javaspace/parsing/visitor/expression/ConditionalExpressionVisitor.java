package org.javaspace.parsing.visitor.expression;

import org.javaspace.antlr.JavaSpaceBaseVisitor;
import org.javaspace.antlr.JavaSpaceParser;
import org.javaspace.antlr.JavaSpaceParser.ConditionalExpressionContext;
import org.javaspace.antlr.JavaSpaceParser.ExpressionContext;
import org.javaspace.domain.node.expression.ConditionalExpression;
import org.javaspace.domain.node.expression.Expression;
import org.javaspace.domain.node.expression.Value;
import org.javaspace.domain.CompareSign;
import org.javaspace.domain.type.BultInType;
import org.antlr.v4.runtime.misc.NotNull;

public class ConditionalExpressionVisitor extends JavaSpaceBaseVisitor<ConditionalExpression> {
    private final ExpressionVisitor expressionVisitor;

    public ConditionalExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public ConditionalExpression visitConditionalExpression(@NotNull ConditionalExpressionContext ctx) {
        ExpressionContext leftExpressionCtx = ctx.expression(0);
        ExpressionContext rightExpressionCtx = ctx.expression(1);
        Expression leftExpression = leftExpressionCtx.accept(expressionVisitor);
        Expression rightExpression = rightExpressionCtx != null ? rightExpressionCtx.accept(expressionVisitor) : new Value(BultInType.INT, "0");
        CompareSign cmpSign = ctx.cmp != null ? CompareSign.fromString(ctx.cmp.getText()) : CompareSign.NOT_EQUAL;
        return new ConditionalExpression(leftExpression, rightExpression, cmpSign);
    }
}