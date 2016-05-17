package com.ast.expressions.literals;

import com.visitors.Visitor;
import com.ast.expressions.Expression;

public abstract class Literal extends Expression {
    public Literal(int lineNumber, int columnNumber) {
        super(lineNumber, columnNumber);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
