package com.ast.types;

import com.visitors.Visitor;
import com.ast.Token;
import com.ast.mutable.Identifier;
import com.symbol_table.entries.*;

public class TypeDeclaration extends Token {
    private Type type;

    public Type getType() {
        return type;
    }

    public TypeDeclaration(int lineNumber, int columnNumber, Type type) {
        super(lineNumber, columnNumber);
        this.type = type;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public boolean isBoolean() {
        return type.isBoolean();
    }

    public boolean isNumeric() {
        return type.isNumeric();
    }

    public Entry getEntry(Identifier identifier) {
        return type.getEntry(identifier);
    }

    @Override
    public String toString() {
        return type.toString();
    }

}
