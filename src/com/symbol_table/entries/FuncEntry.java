package com.symbol_table.entries;

import com.ast.function.Function;
import com.ast.mutable.Identifier;

public class FuncEntry extends Entry {
    private Function function;

    public FuncEntry(Identifier identifier, Function function) {
        super(identifier);
        this.function = function;
    }

    @Override
    public boolean isNumeric() {
        return function.type.isNumeric();
    }

    @Override
    public boolean isBoolean() {
        return function.type.isBoolean();
    }

    @Override
    public String toString() {
        return function.type + " " + function.name + "(" + function.paramList + ");";
    }
}
