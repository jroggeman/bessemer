package com.ast.expressions.literals;

public class IntLiteral extends Literal {
    public int value;

    public IntLiteral(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
