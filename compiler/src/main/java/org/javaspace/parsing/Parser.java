package org.javaspace.parsing;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.NotNull;
import org.javaspace.antlr.JavaSpaceBaseVisitor;
import org.javaspace.antlr.JavaSpaceLexer;
import org.javaspace.antlr.JavaSpaceParser;
import org.javaspace.domain.ClassDeclaration;
import org.javaspace.domain.CompilationUnit;
import org.javaspace.parsing.visitor.ClassVisitor;
import org.javaspace.parsing.visitor.statement.StatementVisitor;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Parser {
//    public ClassDeclaration importFile(String relativePath) throws IOException {
//        JavaSpaceParser parser = this.createParser(Paths.get(relativePath).toAbsolutePath().toString());
//
//    }

    public JavaSpaceParser createParser(String absolutePath) throws IOException {
        return new JavaSpaceParser(
                new CommonTokenStream(
                        new JavaSpaceLexer(
                                new ANTLRFileStream(absolutePath)
                        )
                )
        );
    }

    public CompilationUnit getCompilationUnit(String fileAbsolutePath) throws IOException {
        JavaSpaceParser parser = this.createParser(fileAbsolutePath);

        parser.addErrorListener(new JavaSpaceTreeWalkErrorListener());

        return parser.compilationUnit().accept(new JavaSpaceBaseVisitor<CompilationUnit>() {
            public CompilationUnit visitCompilationUnit(@NotNull JavaSpaceParser.CompilationUnitContext ctx) {
//                List<JavaSpaceParser.ImportDirectiveContext> importStatementsCtx = ctx.importDirective();
//                importStatementsCtx.forEach(importStatementCtx -> importStatementCtx.accept(new JavaSpaceBaseVisitor<Void>() {
//                    @Override
//                    public Void visitImportDirective(@NotNull JavaSpaceParser.ImportDirectiveContext ctx) {
//                        ctx.getTokens(JavaSpaceParser.FILE_PATH).forEach(node -> System.out.println("import path: " + node.getText()));
//                        return null;
//                    }
//                }));
                return new CompilationUnit(ctx.classDeclaration().accept(new ClassVisitor()));
            }
        });
    }
}
