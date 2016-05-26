package com.iloc.registers;

import com.symbol_table.entries.Entry;

public class SymbolRegister extends VirtualRegister {
    private Entry entry;

    public SymbolRegister(Entry entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return "$" + entry.getIdentifier();
    }
}
