package com.ast;

import com.visitors.Visitor;

public interface Token {
    void accept(Visitor visitor);
}
