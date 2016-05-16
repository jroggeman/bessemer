package com.ast.expressions;

import com.ast.statements.Statement;

public interface Expression extends Statement {
    boolean isNumeric();
    boolean isBoolean();
}
