package com.ast;

import com.iloc.Instruction;
import com.iloc.registers.VirtualRegister;

public class Output implements Instruction {
    private VirtualRegister register;

    @Override
    public String toString() {
        return "print " + register;
    }
}
