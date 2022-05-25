package org.javaspace.domain.node.statement;

import org.javaspace.domain.node.Node;
import org.javaspace.bytecodegeneration.statement.StatementGenerator;

@FunctionalInterface
public interface Statement extends Node {
    void accept(StatementGenerator generator);
}
