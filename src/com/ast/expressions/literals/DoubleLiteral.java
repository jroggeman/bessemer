package com.ast.expressions.literals;

public class DoubleLiteral extends Literal {
    public double value;

    public DoubleLiteral(double value) {
        this.value = value;
    }

    public String toString() {
        return Double.toString(value);
    }
}
