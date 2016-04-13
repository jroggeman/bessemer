package com.ast.mutable;

import com.ast.expressions.Expression;

public class ArrayIndex implements Mutable {
    public Identifier array;
    public Expression index;

    public ArrayIndex(Identifier array, Expression index) {
        this.array = array;
        this.index = index;
    }

    public String toString() {
        return array + "[" + index + "]";
    }
}
