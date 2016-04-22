package com.ast.mutable;

import com.ast.Token;
import com.ast.expressions.Expression;

public class Identifier extends Token implements Expression, Mutable {
    public String value;

    public Identifier(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
