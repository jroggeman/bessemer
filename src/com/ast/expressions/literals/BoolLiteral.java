package com.ast.expressions.literals;

import com.ast.types.Type;

public class BoolLiteral extends Literal {
    public boolean value;

    public BoolLiteral(int lineNumber, int columnNumber, boolean value) {
        super(lineNumber, columnNumber);
        this.value = value;
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }
}
