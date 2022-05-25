package org.javaspace.bytecodegeneration;

import org.javaspace.domain.ClassDeclaration;
import org.javaspace.domain.CompilationUnit;

public class BytecodeGenerator {
    public byte[] generate(CompilationUnit compilationUnit) {
        ClassDeclaration classDeclaration = compilationUnit.getClassDeclaration();
        ClassGenerator classGenerator = new ClassGenerator();
        return classGenerator.generate(classDeclaration).toByteArray();
    }
}
