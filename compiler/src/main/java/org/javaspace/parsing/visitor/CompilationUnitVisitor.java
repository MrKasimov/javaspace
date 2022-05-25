package org.javaspace.parsing.visitor;

import org.javaspace.antlr.JavaSpaceBaseVisitor;
import org.javaspace.antlr.JavaSpaceParser;
import org.javaspace.antlr.JavaSpaceParser.ClassDeclarationContext;
import org.javaspace.antlr.JavaSpaceParser.CompilationUnitContext;
import org.javaspace.domain.CompilationUnit;
import org.javaspace.domain.ClassDeclaration;
import org.antlr.v4.runtime.misc.NotNull;

public class CompilationUnitVisitor extends JavaSpaceBaseVisitor<CompilationUnit> {

    @Override
    public CompilationUnit visitCompilationUnit(@NotNull CompilationUnitContext ctx) {
        ClassVisitor classVisitor = new ClassVisitor();
        ClassDeclarationContext classDeclarationContext = ctx.classDeclaration();
        ClassDeclaration classDeclaration = classDeclarationContext.accept(classVisitor);
        return new CompilationUnit(classDeclaration);
    }
}
