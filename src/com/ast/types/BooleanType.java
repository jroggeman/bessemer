package com.ast.types;

import com.Visitor;
import com.ast.mutable.Identifier;
import com.symbol_table.entries.BoolEntry;
import com.symbol_table.entries.Entry;

public class BooleanType extends Type {
    public BooleanType() {
        super(Types.BOOLEAN);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Entry getEntry(Identifier identifier) {
        return new BoolEntry(identifier);
    }
}
