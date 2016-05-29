package com.iloc;

import com.iloc.registers.VirtualRegister;

public class LoadImmediate implements Instruction {
    IntLiteral literal;
    VirtualRegister register;

    public LoadImmediate(IntLiteral literal, VirtualRegister register) {
        this.register = register;
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal + " => " + register;
    }
}
