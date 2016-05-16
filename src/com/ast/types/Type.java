package com.ast.types;

import com.ast.Token;
import com.ast.mutable.Identifier;
import com.symbol_table.entries.Entry;

public abstract class Type implements Token {
    public abstract boolean isNumeric();
    public abstract boolean isBoolean();
    public abstract boolean isDouble();
    public abstract Entry getEntry(Identifier identifier);
}
