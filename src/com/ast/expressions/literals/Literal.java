package com.ast.expressions.literals;

import com.visitors.Visitor;
import com.ast.expressions.Expression;

public abstract class Literal extends Expression {
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
