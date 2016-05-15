package com.ast.expressions;

public class Subexpression implements Expression {
    public Expression expression;

    public Subexpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "(" + expression + ")";
    }
}
