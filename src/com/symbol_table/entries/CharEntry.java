package com.symbol_table.entries;

import com.ast.mutable.Identifier;
import com.ast.types.Type;
import com.ast.types.TypeDeclaration;

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
    public boolean isFunction() {
        return false;
    }

    @Override
    public Type getType() {
        return Type.CHARACTER;
    }

    @Override
    public String toString() {
        return "char";
    }
}
