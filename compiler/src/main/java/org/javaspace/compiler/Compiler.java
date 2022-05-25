package org.javaspace.compiler;

import org.javaspace.domain.CompilationUnit;
import org.javaspace.bytecodegeneration.BytecodeGenerator;
import org.javaspace.parsing.Parser;
import org.javaspace.validation.ARGUMENT_ERRORS;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Compiler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Compiler.class);

    public static void main(String[] args) throws Exception {
        try {
            new Compiler().compile(args);
        } catch (IOException exception) {
            LOGGER.error("ERROR: " + exception.getMessage());
        }
    }

    public void compile(String[] args) throws Exception {
        ARGUMENT_ERRORS argumentsErrors = getArgumentValidationErrors(args);
        if (argumentsErrors != ARGUMENT_ERRORS.NONE) {
            String errorMessage = argumentsErrors.getMessage();
            LOGGER.error(errorMessage);
            return;
        }
        File sourceFile = new File(args[0]);
        String fileAbsolutePath = sourceFile.getAbsolutePath();
        LOGGER.info("Parsing '{}' begins", sourceFile.getAbsolutePath());
        CompilationUnit compilationUnit = new Parser().getCompilationUnit(fileAbsolutePath);
        LOGGER.info("Parsing finished. Code generation begins.");
        saveBytecodeToClassFile(compilationUnit);
    }

    private ARGUMENT_ERRORS getArgumentValidationErrors(String[] args) {
        if (args.length != 1) {
            return ARGUMENT_ERRORS.NO_FILE;
        }
        String filePath = args[0];
        if (!filePath.endsWith(".javaspace")) {
            return ARGUMENT_ERRORS.BAD_FILE_EXTENSION;
        }
        return ARGUMENT_ERRORS.NONE;
    }

    private void saveBytecodeToClassFile(CompilationUnit compilationUnit) throws IOException {
        BytecodeGenerator bytecodeGenerator = new BytecodeGenerator();
        byte[] bytecode = bytecodeGenerator.generate(compilationUnit);
        String className = compilationUnit.getClassName();
        String fileName = className + ".class";
        Path path = Paths.get(fileName);
        LOGGER.info("Compiled file saved to '{}'.", path.toAbsolutePath());
        OutputStream os = Files.newOutputStream(path);
        IOUtils.write(bytecode, os);
        LOGGER.info("Execute via 'java {}' in project directory",className);
    }
}
