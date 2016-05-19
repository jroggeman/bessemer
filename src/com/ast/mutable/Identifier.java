package com.ast.mutable;

import com.ast.types.Type;
import com.exceptions.TypeCheckException;
import com.sun.istack.internal.NotNull;
import com.symbol_table.SymbolTable;
import com.visitors.Visitor;
import com.ast.expressions.Expression;

public class Identifier extends Expression implements Mutable {
    public String value;
    public Type type;

    public Identifier(int lineNumber, int columnNumber, String value) {
        super(lineNumber, columnNumber);
        if(value == null || value.isEmpty()) {
            throw new RuntimeException("Cannot create identifier without name.");
        }

        this.value = value;
    }

    public String toString() {
        return value;
    }

    public void accept(Visitor visitor) throws TypeCheckException {
        visitor.visit(this);
    }

    @Override
    public void checkTypes(SymbolTable table) throws TypeCheckException {

    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object id) {
        if(!(id instanceof Identifier))
            return false;

        return value.equals(((Identifier)id).value);
    }

    @Override
    public Type getType() {
        if(type == null)
            throw new RuntimeException("Found identifier with no type.  Should've caught all missing declaration errors.");

        return type;
    }
}
