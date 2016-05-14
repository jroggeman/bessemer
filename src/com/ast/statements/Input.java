package com.ast.statements;

import com.ast.mutable.Identifier;

public class Input implements Statement {
    Identifier variable;

    public Input(Identifier variable) {
        this.variable = variable;
    }

    @Override
    public String toString() {
        return "readInt => " + variable + ";";
    }
}
