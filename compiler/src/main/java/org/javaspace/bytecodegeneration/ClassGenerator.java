package org.javaspace.bytecodegeneration;

import org.javaspace.domain.Function;
import org.javaspace.domain.ClassDeclaration;
import org.javaspace.domain.scope.Field;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.util.Collection;
import java.util.List;

public class ClassGenerator {

    private static final int CLASS_VERSION = 52;
    private final ClassWriter classWriter;

    public ClassGenerator() {
        classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES + ClassWriter.COMPUTE_MAXS);
    }

    public ClassWriter generate(ClassDeclaration classDeclaration) {
        String name = classDeclaration.getName();
        classWriter.visit(CLASS_VERSION, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER,name,null,"java/lang/Object",null);

        List<Function> methods = classDeclaration.getMethods();
        Collection<Field> fields = classDeclaration.getFields();

        fields.forEach(f -> f.accept(new FieldGenerator(classWriter)));
        methods.forEach(f -> f.accept(new MethodGenerator(classWriter)));

        classWriter.visitEnd();
        return classWriter;
    }


}
