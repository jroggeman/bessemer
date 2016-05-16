package com.ast.mutable;

import com.visitors.Visitor;
import com.ast.expressions.Expression;
import com.ast.types.Type;

public class Identifier implements Expression, Mutable {
    public String value;
    public Type type;

    public Identifier(String value) {
        if(value == null || value.isEmpty()) {
            throw new RuntimeException("Cannot create identifier without name.");
        }

        this.value = value;
    }

    public String toString() {
        return value;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
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
    public boolean isNumeric() {
        return false;
    }

    @Override
    public boolean isBoolean() {
        return false;
    }
}
