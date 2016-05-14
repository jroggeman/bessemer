package com.ast.expressions.literals;

public class StringLiteral extends Literal {
    public String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    public String toString() {
        return "\"" + value + "\"";
    }
}
