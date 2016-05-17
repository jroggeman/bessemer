package com.ast.expressions.literals;

import com.ast.types.Type;

public class CharLiteral extends Literal {
    public char value;

    public CharLiteral(int lineNumber, int columnNumber, char value) {
        super(lineNumber, columnNumber);
        this.value = value;
    }

    @Override
    public String toString() {
        return "'" + Character.toString(value) + "'";
    }

    @Override
    public Type getType() {
        return Type.CHARACTER;
    }
}
