package com.ast.statements;

import com.ast.Token;

public abstract class Statement extends Token {
    public Statement(int lineNumber, int columnNumber) {
        super(lineNumber, columnNumber);
    }
}
