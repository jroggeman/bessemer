package com.symbol_table.entries;

import com.ast.mutable.Identifier;

public class CharEntry extends Entry {
    public CharEntry(Identifier identifier) {
        super(identifier);
    }

    @Override
    public boolean isNumeric() {
        return false;
    }

    @Override
    public boolean isBoolean() {
        return false;
    }

    @Override
    public String toString() {
        return "char";
    }
}
