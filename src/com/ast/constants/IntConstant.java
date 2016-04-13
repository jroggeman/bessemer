package com.ast.constants;

public class IntConstant extends Constant {
    public int value;

    public IntConstant(int value) {
        this.value = value;
    }

    public String toString() {
        return Integer.toString(value);
    }
}
