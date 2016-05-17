package com.ast.expressions;

import com.ast.statements.Statement;
import com.ast.types.Type;

public abstract class Expression extends Statement {
    public Expression(int lineNumber, int columnNumber) {
        super(lineNumber, columnNumber);
    }
    public abstract Type getType();
}
