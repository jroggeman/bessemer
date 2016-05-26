package com.iloc;

import com.iloc.registers.VirtualRegister;

public abstract class ArithmeticInstruction implements Instruction {
    VirtualRegister leftHandSide;
    VirtualRegister rightHandSide;
    VirtualRegister destination;

    public ArithmeticInstruction(VirtualRegister leftHandSide, VirtualRegister rightHandSide, VirtualRegister destination) {
        this.leftHandSide = leftHandSide;
        this.rightHandSide = rightHandSide;
        this.destination = destination;
    }

    public abstract String getOperator();

    public String toString() {
        return leftHandSide + " " + getOperator() + " " + rightHandSide + " => " + destination;
    }
}
