package com.ast.expressions.literals;

public class CharLiteral extends Literal {
    public char value;

    public CharLiteral(char value) {
        this.value = value;
    }

    public String toString() {
        return "'" + Character.toString(value) + "'";
    }
}
