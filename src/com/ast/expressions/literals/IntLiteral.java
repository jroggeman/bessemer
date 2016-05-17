package com.ast.expressions.literals;

import com.ast.types.Type;

public class IntLiteral extends Literal {
    public int value;

    public IntLiteral(int lineNumber, int columnNumber, int value) {
        super(lineNumber, columnNumber);
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public Type getType() {
        return Type.INTEGER;
    }
}
