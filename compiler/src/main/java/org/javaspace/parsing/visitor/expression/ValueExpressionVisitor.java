package org.javaspace.parsing.visitor.expression;

import org.javaspace.antlr.JavaSpaceBaseVisitor;
import org.javaspace.antlr.JavaSpaceParser;
import org.javaspace.antlr.JavaSpaceParser.ValueContext;
import org.javaspace.domain.node.expression.Value;
import org.javaspace.domain.type.Type;
import org.javaspace.util.TypeResolver;
import org.antlr.v4.runtime.misc.NotNull;

public class ValueExpressionVisitor extends JavaSpaceBaseVisitor<Value> {

    @Override
    public Value visitValue(@NotNull ValueContext ctx) {
        String value = ctx.getText();
        Type type = TypeResolver.getFromValue(ctx);
        return new Value(type, value);
    }
}