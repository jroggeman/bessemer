package com.ast.expressions;

import com.Visitor;

public class Subexpression implements Expression {
    public Expression expression;

    public Subexpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "(" + expression + ")";
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
