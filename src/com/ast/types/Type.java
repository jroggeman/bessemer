package com.ast.types;

import com.ast.mutable.Identifier;
import com.symbol_table.entries.*;

public enum Type {
    INTEGER ("int"),
    DOUBLE ("double"),
    CHARACTER ("char"),
    BOOLEAN ("bool");

    private String printableName;

    Type(String printableName) {
        this.printableName = printableName;
    }

    public boolean isNumeric() {
        return (this == INTEGER || this == DOUBLE);
    }

    public boolean isBoolean() {
        return this == BOOLEAN;
    }

    public Entry getEntry(Identifier identifier) {
        switch(this) {
            case INTEGER:
                return new IntEntry(identifier);
            case DOUBLE:
                return new DoubleEntry(identifier);
            case CHARACTER:
                return new CharEntry(identifier);
            case BOOLEAN:
                return new BoolEntry(identifier);
            default:
                throw new UnsupportedOperationException("Unexpected type " + this);
        }
    }

    @Override
    public String toString() {
        return printableName;
    }
}
