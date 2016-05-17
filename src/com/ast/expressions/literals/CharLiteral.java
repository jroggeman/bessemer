package com.ast.expressions.literals;

public class CharLiteral extends Literal {
    public char value;

    public CharLiteral(char value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "'" + Character.toString(value) + "'";
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
        return false;
    }
}
