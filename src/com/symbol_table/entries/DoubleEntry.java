package com.symbol_table.entries;

import com.ast.mutable.Identifier;

public class DoubleEntry extends Entry {
    public DoubleEntry(Identifier identifier) {
        super(identifier);
    }

    @Override
    public boolean isNumeric() {
        return true;
    }

    @Override
    public boolean isBoolean() {
        return false;
    }

    @Override
    public String toString() {
        return "double";
    }
}
