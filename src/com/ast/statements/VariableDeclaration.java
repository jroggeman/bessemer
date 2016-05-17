package com.ast.statements;

import com.visitors.Visitor;
import com.ast.mutable.Identifier;
import com.ast.types.TypeDeclaration;

public class VariableDeclaration implements Statement {
    public TypeDeclaration type;
    public Identifier name;

    public VariableDeclaration(TypeDeclaration type, Identifier name) {
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
