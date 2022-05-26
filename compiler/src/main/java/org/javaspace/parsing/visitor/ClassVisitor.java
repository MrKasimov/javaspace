package org.javaspace.parsing.visitor;

import org.javaspace.antlr.JavaSpaceBaseVisitor;
import org.javaspace.antlr.JavaSpaceParser.ClassDeclarationContext;
import org.javaspace.antlr.JavaSpaceParser.FunctionContext;
import org.javaspace.domain.Constructor;
import org.javaspace.domain.Function;
import org.javaspace.domain.node.expression.ConstructorCall;
import org.javaspace.domain.node.expression.FunctionCall;
import org.javaspace.domain.node.expression.Parameter;
import org.javaspace.domain.ClassDeclaration;
import org.javaspace.domain.MetaData;
import org.javaspace.domain.scope.Field;
import org.javaspace.domain.scope.FunctionSignature;
import org.javaspace.domain.scope.Scope;
import org.javaspace.domain.node.statement.Block;
import org.javaspace.domain.type.BultInType;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class ClassVisitor extends JavaSpaceBaseVisitor<ClassDeclaration> {

    private Scope scope;

    public ClassDeclaration visitClassDeclaration(@NotNull ClassDeclarationContext ctx) {
        MetaData metaData = new MetaData(ctx.className().getText(), MetaData.objectClassName);

        scope = new Scope(metaData);

        List<FunctionContext> methodsCtx = ctx.classBody().function();

        // adding fields to the scope
        List<Field> fields = ctx.classBody().field().stream()
                .map(field -> field.accept(new FieldVisitor(scope)))
                .peek(scope::addField)
                .collect(toList());

        // adding methods to the scope
        methodsCtx.stream()
                .map(method -> method.functionSignature().accept(new FunctionSignatureVisitor(scope)))
                .forEach(scope::addSignature);

        boolean defaultConstructorExists = scope.isParameterLessSignatureExists(metaData.getClassName());

        List<Function> methods = methodsCtx.stream()
                .map(method -> method.accept(new FunctionVisitor(scope)))
                .collect(toList());

        // adding default constructor, if not exists
        if(!defaultConstructorExists) {
            FunctionSignature constructorSignature = new FunctionSignature(
                    metaData.getClassName(),
                    Collections.emptyList(),
                    BultInType.VOID
            );
            scope.addSignature(constructorSignature);
            methods.add(buildConstructor());
        }

        if(scope.isParameterLessSignatureExists("start")) {
            methods.add(buildMainMethod());
        }

        return new ClassDeclaration(metaData.getClassName(), fields, methods);
    }

    private Constructor buildConstructor() {
        FunctionSignature signature = scope.getMethodCallSignatureWithoutParameters(scope.getClassName());
        return new Constructor(signature, Block.empty(scope));
    }

    private Function buildMainMethod() {
        FunctionSignature mainSignature = new FunctionSignature(
                "main",
                Collections.singletonList(new Parameter("args", BultInType.STRING_ARR, Optional.empty())),
                BultInType.VOID
        );
        FunctionCall startFunctionCall = new FunctionCall(
                new FunctionSignature("start", Collections.emptyList(), BultInType.VOID),
                Collections.emptyList(),
                scope.getClassType()
        );
        return new Function(mainSignature,
                new Block(new Scope(scope), Arrays.asList(new ConstructorCall(scope.getClassName()), startFunctionCall))
        );
    }
}
