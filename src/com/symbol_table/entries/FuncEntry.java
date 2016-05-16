package com.symbol_table.entries;

import com.ast.function.Function;

public class FuncEntry extends Entry {
    private Function function;

    public FuncEntry(Function function) {
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
}
