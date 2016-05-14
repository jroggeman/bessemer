package com.ast.expressions.literals;

public class BoolLiteral extends Literal {
    public boolean value;

    public BoolLiteral(boolean value) {
        this.value = value;
    }

    public String toString() {
        return Boolean.toString(value);
    }
}
