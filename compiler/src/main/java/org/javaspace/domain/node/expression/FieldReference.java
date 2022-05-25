package org.javaspace.domain.node.expression;

import org.javaspace.bytecodegeneration.expression.ExpressionGenerator;
import org.javaspace.bytecodegeneration.statement.StatementGenerator;
import org.javaspace.domain.scope.Field;
import org.javaspace.domain.type.Type;

public class FieldReference implements Reference {
    private Field field;

    public FieldReference(Field field) {
        this.field = field;
    }


    @Override
    public String geName() {
        return field.getName();
    }

    @Override
    public void accept(ExpressionGenerator generator) {
        generator.generate(this);
    }

    @Override
    public Type getType() {
        return field.getType();
    }

    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }

    public String getOwnerInternalName() {
        return field.getOwnerInternalName();
    }
}
