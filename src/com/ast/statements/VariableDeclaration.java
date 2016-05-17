package com.ast.statements;

import com.visitors.Visitor;
import com.ast.mutable.Identifier;
import com.ast.types.TypeDeclaration;

public class VariableDeclaration extends Statement {
    public TypeDeclaration type;
    public Identifier name;

    public VariableDeclaration(int lineNumber, int columnNumber, TypeDeclaration type, Identifier name) {
        super(lineNumber, columnNumber);
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
