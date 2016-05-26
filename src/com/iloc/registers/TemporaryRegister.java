package com.iloc.registers;

import java.util.HashMap;
import java.util.Map;

public class TemporaryRegister extends VirtualRegister {
    private static int currentRegisterNumber = 0;
    private static Map<Integer, TemporaryRegister> registers = new HashMap<>();

    private int number;

    private TemporaryRegister(int number) {
        this.number = number;
    }

    public static TemporaryRegister getNextTemporaryRegister() {
        TemporaryRegister register = new TemporaryRegister(currentRegisterNumber);
        registers.put(currentRegisterNumber, register);
        currentRegisterNumber++;

        return register;
    }

    public static TemporaryRegister getExistingTemporaryRegisterByNumber(int number) {
        return registers.get(number);
    }

    @Override
    public String toString() {
        return "$t" + number;
    }
}
