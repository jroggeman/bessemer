package com.ast.statements;

import com.visitors.Visitor;
import com.ast.mutable.Identifier;

public class Input extends Statement {
    public Identifier variable;

    public Input(Identifier variable) {
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
