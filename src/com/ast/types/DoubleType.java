package com.ast.types;

import com.Visitor;

public class DoubleType implements Type {
    public DoubleType() {}

    public String toString() {
        return "double";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
