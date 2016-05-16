package com.ast.types;

import com.Visitor;
import com.ast.Token;
import com.ast.mutable.Identifier;
import com.symbol_table.entries.*;

public enum Type implements Token {
    INTEGER ("int"),
    DOUBLE ("double"),
    CHARACTER ("char"),
    BOOLEAN ("bool");

    private String printableName;

    Type(String printableName) {
        this.printableName = printableName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public boolean isNumeric() {
        return (this == INTEGER || this == DOUBLE);
    }

    public boolean isBoolean() {
        return this == BOOLEAN;
    }

    public boolean isDouble() {
        return this == DOUBLE;
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
