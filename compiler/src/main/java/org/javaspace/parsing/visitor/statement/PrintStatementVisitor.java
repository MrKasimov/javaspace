package org.javaspace.parsing.visitor.statement;

import org.javaspace.antlr.JavaSpaceBaseVisitor;
import org.javaspace.antlr.JavaSpaceParser;
import org.javaspace.antlr.JavaSpaceParser.ExpressionContext;
import org.javaspace.antlr.JavaSpaceParser.PrintStatementContext;
import org.javaspace.domain.node.expression.Expression;
import org.javaspace.domain.node.statement.PrintStatement;
import org.javaspace.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

public class PrintStatementVisitor extends JavaSpaceBaseVisitor<PrintStatement> {
    private final ExpressionVisitor expressionVisitor;

    public PrintStatementVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    public PrintStatement visitPrintStatement(@NotNull PrintStatementContext ctx) {
        ExpressionContext expressionCtx = ctx.expression();
        Expression expression = expressionCtx.accept(expressionVisitor);
        return new PrintStatement(expression);
    }
}