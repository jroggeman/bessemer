package com.symbol_table.entries;

import com.ast.mutable.Identifier;

public abstract class Entry {
    private Identifier identifier;

    public abstract boolean isNumeric();
    public abstract boolean isBoolean();
}
