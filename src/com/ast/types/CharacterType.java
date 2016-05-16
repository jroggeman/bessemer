package com.ast.types;

import com.Visitor;
import com.ast.mutable.Identifier;
import com.symbol_table.entries.CharEntry;
import com.symbol_table.entries.Entry;

public class CharacterType extends Type {
    public CharacterType() {
        super(Types.CHARACTER);
    }
}
