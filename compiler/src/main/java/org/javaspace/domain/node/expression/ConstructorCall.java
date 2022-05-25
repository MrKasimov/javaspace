package org.javaspace.domain.node.expression;

import org.javaspace.bytecodegeneration.expression.ExpressionGenerator;
import org.javaspace.bytecodegeneration.statement.StatementGenerator;
import org.javaspace.domain.type.ClassType;
import org.javaspace.domain.type.Type;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@ToString
public class ConstructorCall implements Call {
    private final List<Argument> arguments;
    private final Type type;
    private final String identifier;

    public ConstructorCall(String identifier) {
        this(identifier, Collections.emptyList());
    }

    public ConstructorCall(String className, List<Argument> arguments) {
        this.type = new ClassType(className);
        this.arguments = arguments;
        this.identifier = type.getName();
    }

    @Override
    public void accept(ExpressionGenerator genrator) {
        genrator.generate(this);
    }

    @Override
    public List<Argument> getArguments() {
        return Collections.unmodifiableList(arguments);
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }
}
