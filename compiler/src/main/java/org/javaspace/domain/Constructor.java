package org.javaspace.domain;

import org.javaspace.bytecodegeneration.MethodGenerator;
import org.javaspace.domain.scope.FunctionSignature;
import org.javaspace.domain.node.statement.Statement;
import org.javaspace.domain.type.BultInType;
import org.javaspace.domain.type.Type;

public class Constructor extends Function {
    public Constructor(FunctionSignature signature, Statement block) {
        super(signature, block);
    }

    @Override
    public Type getReturnType() {
        return BultInType.VOID;
    }

    @Override
    public void accept(MethodGenerator generator) {
        generator.generate(this);
    }
}
