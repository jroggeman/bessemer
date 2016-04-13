package com.constants;

public class DoubleConstant extends Constant {
    public double value;

    public DoubleConstant(double value) {
        this.value = value;
    }

    public String toString() {
        return Double.toString(value);
    }
}
