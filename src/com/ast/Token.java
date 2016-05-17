package com.ast;

import com.visitors.Visitor;

public abstract class Token {
    public int lineNumber;
    public int columnNumber;

    public Token(int lineNumber, int columnNumber) {
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    public abstract void accept(Visitor visitor);
}
