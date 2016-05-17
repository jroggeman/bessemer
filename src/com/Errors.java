package com;

import java.util.logging.Logger;

public enum Errors {
    CALL_NON_EXISTENT_FUNCTION ("Attempting to call a non-existent function at line {0}, column {1}."),
    CALL_NON_FUNCTION ("Attempting to call a non-function identifier at line {0}, column {1}"),
    VARIABLE_USED_BEFORE_DECLARATION ("Variable not declared at line {0}, column {1}");

    private String message;

    Errors(String message) {
        this.message = message;
    }

    public static void logError(Logger logger, Errors error) {

    }

    public String toString() {
        return message;
    }
}
