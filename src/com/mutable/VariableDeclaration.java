package com.mutable;

import com.types.Type;

public class VariableDeclaration implements Mutable {
    public Type type;
    public Identifier name;

    public VariableDeclaration(Type type, Identifier name) {
        this.type = type;
        this.name = name;
    }

    public String toString() {
        return type + " " + name;
    }
}
