package com;

public class Identifier extends Expression {
    public String value;

    public Identifier(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
