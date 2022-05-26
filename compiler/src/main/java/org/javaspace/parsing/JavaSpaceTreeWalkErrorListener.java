package org.javaspace.parsing;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaSpaceTreeWalkErrorListener extends BaseErrorListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaSpaceTreeWalkErrorListener.class);

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        LOGGER.error(String.format("Unexpected token at line %d, character %d: %s", line, charPositionInLine, msg));
    }
}
