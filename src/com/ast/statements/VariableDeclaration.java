package com.ast.statements;

import com.Visitor;
import com.ast.mutable.Identifier;
import com.ast.types.Type;

public class VariableDeclaration implements Statement {
    public Type type;
    public Identifier name;

    public VariableDeclaration(Type type, Identifier name) {
        this.type = type;
        this.name = name;
    }

    public String toString() {
        return type + " " + name + ";";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
