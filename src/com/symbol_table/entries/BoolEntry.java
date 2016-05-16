package com.symbol_table.entries;

public class BoolEntry extends Entry {
    @Override
    public boolean isNumeric() {
        return false;
    }

    @Override
    public boolean isBoolean() {
        return true;
    }
}
