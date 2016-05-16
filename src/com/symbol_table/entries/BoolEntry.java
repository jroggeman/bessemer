package com.symbol_table.entries;

import com.ast.mutable.Identifier;
import com.ast.types.Type;

public class BoolEntry extends Entry {
    public BoolEntry(Identifier identifier) {
        super(identifier);
    }

    @Override
    public boolean isNumeric() {
        return false;
    }

    @Override
    public boolean isBoolean() {
        return true;
    }

    @Override
    public boolean isFunction() {
        return false;
    }

    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }

    @Override
    public String toString() {
        return "boolean";
    }
}
