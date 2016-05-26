package com.iloc;

import com.iloc.registers.VirtualRegister;

public class Add extends ArithmeticInstruction {
    public Add(VirtualRegister leftHandSide, VirtualRegister rightHandSide, VirtualRegister destination) {
        super(leftHandSide, rightHandSide, destination);
    }

    @Override
    public String getOperator() {
        return "+";
    }
}
