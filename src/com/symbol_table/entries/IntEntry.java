package com.symbol_table.entries;

import com.ast.mutable.Identifier;
import com.ast.types.Type;

public class IntEntry extends Entry {
    public IntEntry(Identifier identifier) {
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
    public boolean isFunction() {
        return false;
    }

    @Override
    public Type getType() {
        return Type.INTEGER;
    }

    @Override
    public String toString() {
        return "integer";
    }
}
