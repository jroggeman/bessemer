package com.ast.types;

import com.Visitor;
import com.ast.mutable.Identifier;
import com.symbol_table.entries.DoubleEntry;
import com.symbol_table.entries.Entry;

public class DoubleType extends Type {
    public DoubleType() {
        super(Types.DOUBLE);
    }

    public String toString() {
        return "double";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Entry getEntry(Identifier identifier) {
        return new DoubleEntry(identifier);
    }
}
