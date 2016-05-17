package com.ast.function;

import com.visitors.Visitor;
import com.ast.Token;
import com.ast.mutable.Identifier;
import com.ast.types.TypeDeclaration;

public class ParamDeclaration implements Token {
    public TypeDeclaration type;
    public Identifier id;

    public ParamDeclaration(TypeDeclaration type, Identifier id) {
        this.type = type;
        this.id = id;
    }

    @Override
    public String toString() {
        return type + " " + id;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
