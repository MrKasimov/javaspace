package org.javaspace.parsing.visitor.statement;

import org.javaspace.antlr.JavaSpaceBaseVisitor;
import org.javaspace.antlr.JavaSpaceParser;
import org.javaspace.antlr.JavaSpaceParser.AddContext;
import org.javaspace.antlr.JavaSpaceParser.BlockContext;
import org.javaspace.antlr.JavaSpaceParser.ConditionalExpressionContext;
import org.javaspace.antlr.JavaSpaceParser.ConstructorCallContext;
import org.javaspace.antlr.JavaSpaceParser.DivideContext;
import org.javaspace.antlr.JavaSpaceParser.FunctionCallContext;
import org.javaspace.antlr.JavaSpaceParser.IfStatementContext;
import org.javaspace.antlr.JavaSpaceParser.MultiplyContext;
import org.javaspace.antlr.JavaSpaceParser.PrintStatementContext;
import org.javaspace.antlr.JavaSpaceParser.ReturnVoidContext;
import org.javaspace.antlr.JavaSpaceParser.ReturnWithValueContext;
import org.javaspace.antlr.JavaSpaceParser.SubstractContext;
import org.javaspace.antlr.JavaSpaceParser.SupercallContext;
import org.javaspace.antlr.JavaSpaceParser.ValueContext;
import org.javaspace.antlr.JavaSpaceParser.VarReferenceContext;
import org.javaspace.antlr.JavaSpaceParser.VariableDeclarationContext;
import org.javaspace.domain.node.expression.ConditionalExpression;
import org.javaspace.domain.node.expression.Expression;
import org.javaspace.domain.scope.Scope;
import org.javaspace.domain.node.statement.*;
import org.javaspace.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;
import org.javaspace.domain.node.statement.Statement;

public class StatementVisitor extends JavaSpaceBaseVisitor<Statement> {

    private final ExpressionVisitor expressionVisitor;
    private final PrintStatementVisitor printStatementVisitor;
    private final VariableDeclarationStatementVisitor variableDeclarationStatementVisitor;
    private final ReturnStatementVisitor returnStatementVisitor;
    private final BlockStatementVisitor blockStatementVisitor;
    private final IfStatementVisitor ifStatementVisitor;
    private final ForStatementVisitor forStatementVisitor;
    private final AssignmentStatementVisitor assignmentStatementVisitor;

    public StatementVisitor(Scope scope) {
        expressionVisitor = new ExpressionVisitor(scope);
        printStatementVisitor = new PrintStatementVisitor(expressionVisitor);
        variableDeclarationStatementVisitor = new VariableDeclarationStatementVisitor(expressionVisitor,scope);
        returnStatementVisitor = new ReturnStatementVisitor(expressionVisitor);
        blockStatementVisitor = new BlockStatementVisitor(scope);
        ifStatementVisitor = new IfStatementVisitor(this,expressionVisitor);
        forStatementVisitor = new ForStatementVisitor(scope);
        assignmentStatementVisitor = new AssignmentStatementVisitor(expressionVisitor);
    }

    
    public Statement visitPrintStatement(@NotNull PrintStatementContext ctx) {
        return printStatementVisitor.visitPrintStatement(ctx);
    }

    
    public Statement visitVariableDeclaration(@NotNull VariableDeclarationContext ctx) {
        return variableDeclarationStatementVisitor.visitVariableDeclaration(ctx);
    }

    
    public Statement visitReturnVoid(@NotNull ReturnVoidContext ctx) {
        return returnStatementVisitor.visitReturnVoid(ctx);
    }

    
    public Statement visitReturnWithValue(@NotNull ReturnWithValueContext ctx) {
        return returnStatementVisitor.visitReturnWithValue(ctx);
    }

    
    public Statement visitBlock(@NotNull BlockContext ctx) {
        return blockStatementVisitor.visitBlock(ctx);
    }

    
    public Statement visitIfStatement(@NotNull IfStatementContext ctx) {
        return ifStatementVisitor.visitIfStatement(ctx);
    }

    
    public Expression visitVarReference(@NotNull VarReferenceContext ctx) {
        return expressionVisitor.visitVarReference(ctx);
    }

    
    public Expression visitValue(@NotNull ValueContext ctx) {
        return expressionVisitor.visitValue(ctx);
    }

    
    public Expression visitFunctionCall(@NotNull FunctionCallContext ctx) {
        return expressionVisitor.visitFunctionCall(ctx);
    }

    
    public Expression visitConstructorCall(@NotNull ConstructorCallContext ctx) {
        return expressionVisitor.visitConstructorCall(ctx);
    }

    
    public Expression visitSupercall(@NotNull SupercallContext ctx) {
        return expressionVisitor.visitSupercall(ctx);
    }

    
    public Expression visitAdd(@NotNull AddContext ctx) {
        return expressionVisitor.visitAdd(ctx);
    }

    
    public Expression visitMultiply(@NotNull MultiplyContext ctx) {
        return expressionVisitor.visitMultiply(ctx);
    }

    
    public Expression visitSubstract(@NotNull SubstractContext ctx) {
        return expressionVisitor.visitSubstract(ctx);
    }

    
    public Expression visitDivide(@NotNull DivideContext ctx) {
        return expressionVisitor.visitDivide(ctx);
    }

    
    public ConditionalExpression visitConditionalExpression(@NotNull ConditionalExpressionContext ctx) {
        return expressionVisitor.visitConditionalExpression(ctx);
    }

    
    public Statement visitForStatement(@NotNull JavaSpaceParser.ForStatementContext ctx) {
        return forStatementVisitor.visitForStatement(ctx);
    }

    
    public Statement visitAssignment(@NotNull JavaSpaceParser.AssignmentContext ctx) {
        return assignmentStatementVisitor.visitAssignment(ctx);
    }
}
