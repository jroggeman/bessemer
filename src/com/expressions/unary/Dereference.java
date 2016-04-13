package com.expressions.unary;

import com.expressions.Expression;

public class Dereference extends UnaryExpression {
    public Dereference(Expression expression) {
        super(expression);
    }

    @Override
    public String getOperator() {
        return "*";
    }
}
