package com.symbol_table.entries;

public class CharEntry extends Entry {
    @Override
    public boolean isNumeric() {
        return false;
    }

    @Override
    public boolean isBoolean() {
        return false;
    }
}
