package org.javaspace.parsing.visitor;

import org.javaspace.antlr.JavaSpaceBaseVisitor;
import org.javaspace.antlr.JavaSpaceParser;
import org.javaspace.domain.scope.Field;
import org.javaspace.domain.scope.Scope;
import org.javaspace.domain.type.Type;
import org.javaspace.util.TypeResolver;
import org.antlr.v4.runtime.misc.NotNull;

public class FieldVisitor extends JavaSpaceBaseVisitor<Field> {

    private final Scope scope;

    public FieldVisitor(Scope scope) {
        this.scope = scope;
    }

    public Field visitField(@NotNull JavaSpaceParser.FieldContext ctx) {
        return new Field(ctx.name().getText(), scope.getClassType(), TypeResolver.getFromTypeContext(ctx.type()));
    }
}
