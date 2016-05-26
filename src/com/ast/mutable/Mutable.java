package com.ast.mutable;

import com.ast.types.Type;
import com.exceptions.TypeCheckException;
import com.visitors.Visitor;

public interface Mutable {
    void accept(Visitor visitor) throws TypeCheckException;
    Type getType();
}
