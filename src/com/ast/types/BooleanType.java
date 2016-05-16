package com.ast.types;

import com.Visitor;
import com.ast.mutable.Identifier;
import com.symbol_table.entries.BoolEntry;
import com.symbol_table.entries.Entry;

public class BooleanType implements Type {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
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
    public Entry getEntry(Identifier identifier) {
        return new BoolEntry(identifier);
    }
}
