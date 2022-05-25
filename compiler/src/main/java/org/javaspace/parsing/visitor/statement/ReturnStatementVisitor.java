package org.javaspace.parsing.visitor.statement;

import org.javaspace.antlr.JavaSpaceBaseVisitor;
import org.javaspace.antlr.JavaSpaceParser;
import org.javaspace.antlr.JavaSpaceParser.ReturnVoidContext;
import org.javaspace.antlr.JavaSpaceParser.ReturnWithValueContext;
import org.javaspace.domain.node.expression.EmptyExpression;
import org.javaspace.domain.node.expression.Expression;
import org.javaspace.domain.node.statement.ReturnStatement;
import org.javaspace.domain.type.BultInType;
import org.javaspace.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

public class ReturnStatementVisitor extends JavaSpaceBaseVisitor<ReturnStatement> {
    private final ExpressionVisitor expressionVisitor;

    public ReturnStatementVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public ReturnStatement visitReturnVoid(@NotNull ReturnVoidContext ctx) {
        return new ReturnStatement(new EmptyExpression(BultInType.VOID));
    }

    @Override
    public ReturnStatement visitReturnWithValue(@NotNull ReturnWithValueContext ctx) {
        Expression expression = ctx.expression().accept(expressionVisitor);
        return new ReturnStatement(expression);
    }
}