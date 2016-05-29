package com.iloc;

public class IntLiteral {
    private int number;

    public IntLiteral(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "" + number;
    }
}
