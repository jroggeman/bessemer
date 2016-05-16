package com.ast.types;

import com.Visitor;
import com.ast.mutable.Identifier;
import com.symbol_table.entries.CharEntry;
import com.symbol_table.entries.Entry;

public class CharacterType extends Type {
    public CharacterType() {
        super(Types.CHARACTER);
    }

    public String toString() {
        return "char";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Entry getEntry(Identifier identifier) {
        return new CharEntry(identifier);
    }
}
