package org.javaspace.parsing;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.NotNull;
import org.javaspace.antlr.JavaSpaceBaseVisitor;
import org.javaspace.antlr.JavaSpaceLexer;
import org.javaspace.antlr.JavaSpaceParser;
import org.javaspace.domain.CompilationUnit;
import org.javaspace.parsing.visitor.ClassVisitor;

import java.io.IOException;

public class Parser {
    public CompilationUnit getCompilationUnit(String fileAbsolutePath) throws IOException {
        JavaSpaceParser parser = new JavaSpaceParser(
                new CommonTokenStream(
                        new JavaSpaceLexer(
                                new ANTLRFileStream(fileAbsolutePath)
                        )
                )
        );

        parser.addErrorListener(new JavaSpaceTreeWalkErrorListener());

        return parser.compilationUnit().accept(new JavaSpaceBaseVisitor<CompilationUnit>() {
            @Override
            public CompilationUnit visitCompilationUnit(@NotNull JavaSpaceParser.CompilationUnitContext ctx) {
                return new CompilationUnit(ctx.classDeclaration().accept(new ClassVisitor()));
            }
        });
    }
}
