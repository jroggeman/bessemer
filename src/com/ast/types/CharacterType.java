package com.ast.types;

import com.Visitor;
import com.ast.mutable.Identifier;
import com.symbol_table.entries.CharEntry;
import com.symbol_table.entries.Entry;

public class CharacterType implements Type {
    public CharacterType() {}

    public String toString() {
        return "char";
    }

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
        return false;
    }

    @Override
    public boolean isDouble() {
        return false;
    }

    @Override
    public Entry getEntry(Identifier identifier) {
        return new CharEntry(identifier);
    }
}
