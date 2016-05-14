package com.ast.expressions;

import com.ast.Token;

public class Subexpression extends Token implements Expression {
    public Expression expression;

    public Subexpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "(" + expression + ")";
    }
}
