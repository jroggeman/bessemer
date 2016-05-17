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

    public boolean isCharacter() {
        return this == CHARACTER;
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

    public static boolean areComparisonCompatible(Type t1, Type t2) {
        if(t1.isNumeric() && t2.isNumeric())
            return true;

        if(t1.isBoolean() && t2.isBoolean())
            return true;

        if(t1.isCharacter() && t2.isCharacter())
            return true;

        return false;

    }

    public static Type getResultType(Type t1, Type t2) {
        switch(t1) {
            case INTEGER:
                if(t2.isNumeric())
                    return t2;
            case DOUBLE:
                if(t2.isNumeric())
                    return DOUBLE;
                break;
            case CHARACTER:
                if(t2 == CHARACTER)
                    return CHARACTER;
                break;
            case BOOLEAN:
                if(t2 == BOOLEAN)
                    return BOOLEAN;
                break;
        }

        throw new RuntimeException("Type mismatch not caught in type checking");
    }

    @Override
    public String toString() {
        return printableName;
    }
}
