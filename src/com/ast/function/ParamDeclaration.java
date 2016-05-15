package com.ast.function;

import com.ast.Token;
import com.ast.mutable.Identifier;
import com.ast.types.Type;

public class ParamDeclaration implements Token {
    public Type type;
    public Identifier id;

    public ParamDeclaration(Type type, Identifier id) {
        this.type = type;
        this.id = id;
    }

    @Override
    public String toString() {
        return type + " " + id;
    }
}
