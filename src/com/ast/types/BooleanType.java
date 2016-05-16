package com.ast.types;

import com.Visitor;

public class BooleanType implements Type {
    @Override
    public void accept(Visitor visitor) {
        return;
    }

    @Override
    public boolean isNumeric() {
        return false;
    }

    @Override
    public boolean isBoolean() {
        return true;
    }
}
