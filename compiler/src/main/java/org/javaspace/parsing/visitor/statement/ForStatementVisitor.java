package org.javaspace.parsing.visitor.statement;

import org.javaspace.antlr.JavaSpaceBaseVisitor;
import org.javaspace.antlr.JavaSpaceParser.ForConditionsContext;
import org.javaspace.antlr.JavaSpaceParser.ForStatementContext;
import org.javaspace.antlr.JavaSpaceParser.VariableReferenceContext;
import org.javaspace.domain.node.expression.Expression;
import org.javaspace.domain.node.statement.RangedForStatement;
import org.javaspace.domain.scope.LocalVariable;
import org.javaspace.domain.scope.Scope;
import org.javaspace.domain.node.statement.Assignment;
import org.javaspace.domain.node.statement.Statement;
import org.javaspace.domain.node.statement.VariableDeclaration;
import org.javaspace.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

public class ForStatementVisitor extends JavaSpaceBaseVisitor<RangedForStatement> {
    private final Scope scope;
    private final ExpressionVisitor expressionVisitor;

    public ForStatementVisitor(Scope scope) {
        this.scope = scope;
        expressionVisitor = new ExpressionVisitor(this.scope);
    }

    public RangedForStatement visitForStatement(@NotNull ForStatementContext ctx) {
        Scope newScope = new Scope(scope);
        ForConditionsContext forExpressionContext = ctx.forConditions();
        Expression startExpression = forExpressionContext.startExpr.accept(expressionVisitor);
        Expression endExpression = forExpressionContext.endExpr.accept(expressionVisitor);
        VariableReferenceContext iterator = forExpressionContext.iterator;
        StatementVisitor statementVisitor = new StatementVisitor(newScope);
        String varName = iterator.getText();
        if(newScope.isLocalVariableExists(varName)) {
            Statement iteratorVariable = new Assignment(varName, startExpression);
            Statement statement = ctx.statement().accept(statementVisitor);
            return new RangedForStatement(iteratorVariable, startExpression, endExpression,statement, varName, newScope);
        } else {
            newScope.addLocalVariable(new LocalVariable(varName,startExpression.getType()));
            Statement iteratorVariable = new VariableDeclaration(varName,startExpression);
            Statement statement = ctx.statement().accept(statementVisitor);
            return new RangedForStatement(iteratorVariable, startExpression, endExpression,statement, varName,newScope);
        }
    }

}
