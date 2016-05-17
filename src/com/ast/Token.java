package com.ast;

import com.visitors.Visitor;

/*



T    O    D    O

Convert all interfaces into abstract classes
Include row and column in token
Enforce row and column constructor through Token
Update parser



 */
public abstract class Token {
    public int lineNumber;
    public int columnNumber;

    public Token(int lineNumber, int columnNumber) {
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    public abstract void accept(Visitor visitor);
}
