package com.ast.types;

import com.ast.Token;
import com.ast.mutable.Identifier;
import com.symbol_table.entries.Entry;

public abstract class Type implements Token {
    private Types type;

    public Type(Types type) {
        this.type = type;
    }

    public boolean isNumeric() {
        return type.isNumeric();
    }

    public boolean isBoolean() {
        return type.isBoolean();
    }

    public boolean isDouble() {
        return type.isDouble();

    }

    public Entry getEntry(Identifier identifier) {
        throw new UnsupportedOperationException();
    }

    protected enum Types {
        INTEGER ("int"),
        DOUBLE ("double"),
        CHARACTER ("char"),
        BOOLEAN ("bool");

        private String printableName;

        Types(String printableName) {
            this.printableName = printableName;
        }

        @Override
        public String toString() {
            return printableName;
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
    };
}
