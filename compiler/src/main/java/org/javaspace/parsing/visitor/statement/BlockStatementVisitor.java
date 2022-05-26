package org.javaspace.parsing.visitor.statement;

import org.javaspace.antlr.JavaSpaceBaseVisitor;
import org.javaspace.antlr.JavaSpaceParser;
import org.javaspace.antlr.JavaSpaceParser.BlockContext;
import org.javaspace.antlr.JavaSpaceParser.StatementContext;
import org.javaspace.domain.scope.Scope;
import org.javaspace.domain.node.statement.Block;
import org.javaspace.domain.node.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class BlockStatementVisitor extends JavaSpaceBaseVisitor<Block>{
    private final Scope scope;

    public BlockStatementVisitor(Scope scope) {
        this.scope = scope;
    }

    public Block visitBlock(@NotNull BlockContext ctx) {
        List<StatementContext> blockStatementsCtx = ctx.statement();
        Scope newScope = new Scope(scope);
        StatementVisitor statementVisitor = new StatementVisitor(newScope);
        List<Statement> statements = blockStatementsCtx.stream().map(smtt -> smtt.accept(statementVisitor)).collect(Collectors.toList());
        return new Block(newScope, statements);
    }
}