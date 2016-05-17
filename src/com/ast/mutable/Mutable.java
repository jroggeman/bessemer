package com.ast.mutable;

import com.visitors.Visitor;

public interface Mutable {
    void accept(Visitor visitor);
}
