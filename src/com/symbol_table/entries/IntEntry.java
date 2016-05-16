package com.symbol_table.entries;

public class IntEntry extends Entry {
    @Override
    public boolean isNumeric() {
        return true;
    }

    @Override
    public boolean isBoolean() {
        return false;
    }
}
