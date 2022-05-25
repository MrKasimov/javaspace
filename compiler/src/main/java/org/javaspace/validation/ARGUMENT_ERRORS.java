package org.javaspace.validation;

public enum ARGUMENT_ERRORS {
    NONE (""),
    NO_FILE ("No file for compilation supplied"),
    BAD_FILE_EXTENSION ("File has to have .javaspace extension");

    private final String message;

    ARGUMENT_ERRORS(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
