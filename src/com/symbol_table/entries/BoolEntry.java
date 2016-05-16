package com.symbol_table.entries;

import com.ast.mutable.Identifier;

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
    public String toString() {
        return "boolean";
    }
}
