package com;

import com.ast.Token;

import java.util.logging.Level;
import java.util.logging.Logger;

public enum Errors {
    CALL_NON_EXISTENT_FUNCTION ("Attempting to call a non-existent function at line {0}, column {1}."),
    CALL_NON_FUNCTION ("Attempting to call a non-function identifier at line {0}, column {1}"),
    VARIABLE_USED_BEFORE_DECLARATION ("Variable not declared at line {0}, column {1}"),
    ASSIGNMENT_TYPE_DISAGREEMENT ("Variable type and expression type don''t agree at line {0}, column {1}");

    private String message;

    Errors(String message) {
        this.message = message;
    }

    public void log(Logger logger, Token element) {
        logger.log(Level.SEVERE, message, new Object[] { element.lineNumber, element.columnNumber });
    }

    public String toString() {
        return message;
    }
}
