package com.constants;

public class CharConstant extends Constant {
    public char value;

    public CharConstant(char value) {
        this.value = value;
    }

    public String toString() {
        return "'" + Character.toString(value) + "'";
    }
}
