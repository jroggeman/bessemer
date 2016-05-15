package com.ast.function;

import com.ast.mutable.Identifier;
import com.ast.types.Type;

public class ParamDeclaration {
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
