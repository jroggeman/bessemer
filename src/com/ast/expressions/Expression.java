package com.ast.expressions;

import com.ast.statements.Statement;

public abstract class Expression extends Statement {
    public Expression(int lineNumber, int columnNumber) {
        super(lineNumber, columnNumber);
    }

    // Are inputs to this particular thing required to be numeric or boolean?
    public abstract boolean areInputsNumeric();
    public abstract boolean areInputsBoolean();

    // is the output of this particular thing numeric or boolean?
    public abstract boolean isOutputNumeric();
    public abstract boolean isOutputBoolean();
}
