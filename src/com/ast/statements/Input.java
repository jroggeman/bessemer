package com.ast.statements;

import com.visitors.Visitor;
import com.ast.mutable.Identifier;

public class Input extends Statement {
    public Identifier variable;

    public Input(int lineNumber, int columnNumber, Identifier variable) {
        super(lineNumber, columnNumber);
        this.variable = variable;
    }

    @Override
    public String toString() {
        return "readInt => " + variable + ";";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
