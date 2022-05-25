package org.javaspace.bytecodegeneration.statement;

import org.javaspace.bytecodegeneration.expression.ExpressionGenerator;
import org.javaspace.domain.node.expression.Expression;
import org.javaspace.domain.node.statement.Assignment;
import org.javaspace.domain.node.statement.VariableDeclaration;

public class VariableDeclarationStatementGenerator {
    private final StatementGenerator statementGenerator;
    private final ExpressionGenerator expressionGenerator;

    public VariableDeclarationStatementGenerator(StatementGenerator statementGenerator, ExpressionGenerator expressionGenerator) {
        this.statementGenerator = statementGenerator;
        this.expressionGenerator = expressionGenerator;
    }

    public void generate(VariableDeclaration variableDeclaration) {
        Expression expression = variableDeclaration.getExpression();
        expression.accept(expressionGenerator);
        Assignment assignment = new Assignment(variableDeclaration);
        assignment.accept(statementGenerator);
    }
}