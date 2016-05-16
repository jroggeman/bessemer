package com.ast.mutable;

import com.Visitor;
import com.ast.expressions.Expression;

public class Identifier implements Expression, Mutable {
    public String value;

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
        return;
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
}
