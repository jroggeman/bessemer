package com.symbol_table.entries;

import com.ast.mutable.Identifier;
import com.ast.types.Type;
import com.ast.types.TypeDeclaration;

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
    public boolean isFunction() {
        return false;
    }

    @Override
    public Type getType() {
        return Type.DOUBLE;
    }

    @Override
    public String toString() {
        return "double";
    }
}
