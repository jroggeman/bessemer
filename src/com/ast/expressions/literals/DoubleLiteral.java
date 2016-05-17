package com.ast.expressions.literals;

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

    public double value;

    public DoubleLiteral(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
