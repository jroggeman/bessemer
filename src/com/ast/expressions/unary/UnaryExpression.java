package com.ast.expressions.unary;

import com.ast.expressions.Expression;

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
