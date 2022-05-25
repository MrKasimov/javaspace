package org.javaspace.bytecodegeneration.statement;

import org.javaspace.bytecodegeneration.expression.ExpressionGenerator;
import org.javaspace.domain.node.expression.Expression;
import org.javaspace.domain.scope.Field;
import org.javaspace.domain.scope.LocalVariable;
import org.javaspace.domain.scope.Scope;
import org.javaspace.domain.node.statement.Assignment;
import org.javaspace.domain.type.Type;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AssignmentStatementGenerator {
    private final MethodVisitor methodVisitor;
    private final ExpressionGenerator expressionGenerator;
    private final Scope scope;

    public AssignmentStatementGenerator(MethodVisitor methodVisitor, ExpressionGenerator expressionGenerator, Scope scope) {
        this.methodVisitor = methodVisitor;
        this.expressionGenerator = expressionGenerator;
        this.scope = scope;
    }

    public void generate(Assignment assignment) {
        String varName = assignment.getVarName();
        Expression expression = assignment.getExpression();
        Type type = expression.getType();
        if(scope.isLocalVariableExists(varName)) {
            int index = scope.getLocalVariableIndex(varName);
            LocalVariable localVariable = scope.getLocalVariable(varName);
            Type localVariableType = localVariable.getType();
            castIfNecessary(type, localVariableType);
            methodVisitor.visitVarInsn(type.getStoreVariableOpcode(), index);
            return;
        }
        Field field = scope.getField(varName);
        String descriptor = field.getType().getDescriptor();
        methodVisitor.visitVarInsn(Opcodes.ALOAD,0);
        expression.accept(expressionGenerator);
        castIfNecessary(type, field.getType());
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD,field.getOwnerInternalName(),field.getName(),descriptor);
    }

    private void castIfNecessary(Type expressionType, Type variableType) {
        if(!expressionType.equals(variableType)) {
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST,variableType.getInternalName());
        }
    }
}