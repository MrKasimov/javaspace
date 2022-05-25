package org.javaspace.parsing;

import org.javaspace.antlr.JavaSpaceLexer;
import org.javaspace.antlr.JavaSpaceParser;
import org.javaspace.domain.CompilationUnit;
import org.javaspace.parsing.visitor.CompilationUnitVisitor;
import org.antlr.v4.runtime.*;

import java.io.IOException;

public class Parser {
    public CompilationUnit getCompilationUnit(String fileAbsolutePath) throws IOException {
        CharStream charStream = new ANTLRFileStream(fileAbsolutePath);
        JavaSpaceLexer lexer = new JavaSpaceLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        JavaSpaceParser parser = new JavaSpaceParser(tokenStream);

        ANTLRErrorListener errorListener = new JavaSpaceTreeWalkErrorListener();
        parser.addErrorListener(errorListener);

        CompilationUnitVisitor compilationUnitVisitor = new CompilationUnitVisitor();
        return parser.compilationUnit().accept(compilationUnitVisitor);
    }
}
