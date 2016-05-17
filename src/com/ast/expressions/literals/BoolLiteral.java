package com.ast.expressions.literals;

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
    public boolean areInputsNumeric() {
        return false;
    }

    @Override
    public boolean areInputsBoolean() {
        return false;
    }

    @Override
    public boolean isOutputNumeric() {
        return false;
    }

    @Override
    public boolean isOutputBoolean() {
        return true;
    }
}
