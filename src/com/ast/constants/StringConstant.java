package com.ast.constants;

public class StringConstant extends Constant {
    public String value;

    public StringConstant(String value) {
        this.value = value;
    }

    public String toString() {
        return "\"" + value + "\"";
    }
}
