package com.symbol_table.entries;

import com.ast.mutable.Identifier;
import com.ast.types.Type;
import com.ast.types.TypeDeclaration;

public abstract class Entry {
    private Identifier identifier;

    public Entry(Identifier identifier) {
        this.identifier = identifier;
    }

    public abstract boolean isNumeric();
    public abstract boolean isBoolean();
    public abstract boolean isFunction();

    public abstract Type getType();
}
