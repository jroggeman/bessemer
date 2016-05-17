package com.ast.expressions;

import com.ast.statements.Statement;

public interface Expression extends Statement {
    // Are inputs to this particular thing required to be numeric or boolean?
    boolean areInputsNumeric();
    boolean areInputsBoolean();

    // Is the output of this particular thing numeric or boolean?
    boolean isOutputNumeric();
    boolean isOutputBoolean();
}
