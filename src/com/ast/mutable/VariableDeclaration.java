package com.ast.mutable;

import com.ast.statements.Statement;
import com.ast.types.Type;

public class VariableDeclaration extends Statement implements Mutable {
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
