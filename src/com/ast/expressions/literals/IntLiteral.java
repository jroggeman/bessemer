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
    public boolean areInputsNumeric() {
        return false;
    }

    @Override
    public boolean areInputsBoolean() {
        return false;
    }

    @Override
    public boolean isOutputNumeric() {
        return true;
    }

    @Override
    public boolean isOutputBoolean() {
        return false;
    }

    @Override
    public Type getType() {
        return Type.INTEGER;
    }
}
