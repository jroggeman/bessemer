package com.ast.types;

import com.Visitor;

public class IntegerType implements Type {
    public IntegerType() {}

    public String toString() {
        return "int";
    }

    @Override
    public void accept(Visitor visitor) {

    }

    @Override
    public boolean isNumeric() {
        return true;
    }

    @Override
    public boolean isBoolean() {
        return false;
    }
}
