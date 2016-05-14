package com.ast.function;

import com.ast.mutable.Identifier;
import com.ast.types.Type;

public class ParamDeclaration {
    Type type;
    Identifier id;

    public ParamDeclaration(Type type, Identifier id) {
        this.type = type;
        this.id = id;
    }
}
