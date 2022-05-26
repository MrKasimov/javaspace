package org.javaspace.parsing.visitor.statement;

import org.javaspace.antlr.JavaSpaceBaseVisitor;
import org.javaspace.antlr.JavaSpaceParser;
import org.javaspace.domain.node.expression.Expression;
import org.javaspace.domain.node.statement.Assignment;
import org.javaspace.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

public class AssignmentStatementVisitor extends JavaSpaceBaseVisitor<Assignment> {
    private final ExpressionVisitor expressionVisitor;

    public AssignmentStatementVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    
    public Assignment visitAssignment(@NotNull JavaSpaceParser.AssignmentContext ctx) {
        JavaSpaceParser.ExpressionContext expressionCtx = ctx.expression();
        Expression expression = expressionCtx.accept(expressionVisitor);
        String varName = ctx.name().getText();
        return new Assignment(varName, expression);
    }
}