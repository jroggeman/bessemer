package com.iloc;

import java.util.Iterator;
import java.util.List;

public class Program implements Iterable<Instruction> {
    private List<Instruction> instructions;

    @Override
    public Iterator<Instruction> iterator() {
        return instructions.iterator();
    }
}
