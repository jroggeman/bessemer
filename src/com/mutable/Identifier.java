package com.mutable;

import com.expressions.Expression;

public class Identifier implements Expression, Mutable {
    public String value;

    public Identifier(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
