package org.javaspace.domain.scope;

import org.javaspace.bytecodegeneration.FieldGenerator;
import org.javaspace.domain.type.Type;

public class Field implements Variable {

    private final String name;
    private final Type owner;
    private final Type type;

    public Field(String name, Type owner, Type type) {
        this.name = name;
        this.type = type;
        this.owner = owner;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getOwnerInternalName() {
        return owner.getInternalName();
    }

    public void accept(FieldGenerator generator) {
        generator.generate(this);
    }
}
