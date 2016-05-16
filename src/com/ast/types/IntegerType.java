package com.ast.types;

import com.Visitor;
import com.ast.mutable.Identifier;
import com.symbol_table.entries.Entry;
import com.symbol_table.entries.IntEntry;

public class IntegerType implements Type {
    public IntegerType() {}

    public String toString() {
        return "int";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
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
    public Entry getEntry(Identifier identifier) {
        return new IntEntry(identifier);
    }
}
