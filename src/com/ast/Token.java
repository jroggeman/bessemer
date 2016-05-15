package com.ast;

import com.Visitor;

public interface Token {
    void accept(Visitor visitor);
}
