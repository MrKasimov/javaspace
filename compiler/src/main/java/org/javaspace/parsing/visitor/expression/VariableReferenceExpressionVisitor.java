package org.javaspace.parsing.visitor.expression;

import org.javaspace.antlr.JavaSpaceBaseVisitor;
import org.javaspace.antlr.JavaSpaceParser.VarReferenceContext;
import org.javaspace.domain.node.expression.FieldReference;
import org.javaspace.domain.node.expression.LocalVariableReference;
import org.javaspace.domain.node.expression.Reference;
import org.javaspace.domain.scope.Field;
import org.javaspace.domain.scope.LocalVariable;
import org.javaspace.domain.scope.Scope;
import org.antlr.v4.runtime.misc.NotNull;

public class VariableReferenceExpressionVisitor extends JavaSpaceBaseVisitor<Reference> {
    private final Scope scope;

    public VariableReferenceExpressionVisitor(Scope scope) {
        this.scope = scope;
    }

    public Reference visitVarReference(@NotNull VarReferenceContext ctx) {
        String varName = ctx.getText();
        if(scope.isFieldExists(varName)) {
            Field field = scope.getField(varName);
            return new FieldReference(field);
        }
        LocalVariable variable = scope.getLocalVariable(varName);
        return new LocalVariableReference(variable);
    }
}