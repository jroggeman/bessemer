package com.ast.types;

import com.ast.Token;
import com.ast.mutable.Identifier;
import com.symbol_table.entries.Entry;

public interface Type extends Token {
    boolean isNumeric();
    boolean isBoolean();
    Entry getEntry(Identifier identifier);
}
