package com.ast.expressions.literals;

import com.ast.types.Type;

public class DoubleLiteral extends Literal {
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
        return Type.DOUBLE;
    }

    public double value;

    public DoubleLiteral(int lineNumber, int columnNumber, double value) {
        super(lineNumber, columnNumber);
        this.value = value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
