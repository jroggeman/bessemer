package com.expressions.unary;

import com.expressions.Expression;

public abstract class UnaryExpression implements Expression {
    public Expression expression;

    public UnaryExpression(Expression expression) {
        this.expression = expression;
    }

    public abstract String getOperator();

    @Override
    public String toString() {
        return getOperator() + " " + expression;
    }
}
