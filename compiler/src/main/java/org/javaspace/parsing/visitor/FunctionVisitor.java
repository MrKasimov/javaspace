package org.javaspace.parsing.visitor;

import org.javaspace.antlr.JavaSpaceBaseVisitor;
import org.javaspace.antlr.JavaSpaceParser;
import org.javaspace.antlr.JavaSpaceParser.BlockContext;
import org.javaspace.antlr.JavaSpaceParser.FunctionContext;
import org.javaspace.domain.Constructor;
import org.javaspace.domain.Function;
import org.javaspace.domain.scope.FunctionSignature;
import org.javaspace.domain.scope.LocalVariable;
import org.javaspace.domain.scope.Scope;
import org.javaspace.domain.node.statement.Statement;
import org.javaspace.parsing.visitor.statement.StatementVisitor;
import org.antlr.v4.runtime.misc.NotNull;
public class FunctionVisitor extends JavaSpaceBaseVisitor<Function> {

    private final Scope scope;

    public FunctionVisitor(Scope scope) {
        this.scope = new Scope(scope);
    }

    public Function visitFunction(@NotNull FunctionContext ctx) {
        FunctionSignature signature = ctx.functionSignature().accept(new FunctionSignatureVisitor(scope));
        scope.addLocalVariable(new LocalVariable("this",scope.getClassType()));
        addParametersAsLocalVariables(signature);
        Statement block = getBlock(ctx);
        if(signature.getName().equals(scope.getClassName())) {
            return new Constructor(signature,block);
        }
        return new Function(signature, block);
    }

    private void addParametersAsLocalVariables(FunctionSignature signature) {
        signature.getParameters().stream()
                .forEach(param -> scope.addLocalVariable(new LocalVariable(param.getName(), param.getType())));
    }

    private Statement getBlock(FunctionContext functionContext) {
        StatementVisitor statementVisitor = new StatementVisitor(scope);
        BlockContext block = functionContext.block();
        return block.accept(statementVisitor);
    }
}
