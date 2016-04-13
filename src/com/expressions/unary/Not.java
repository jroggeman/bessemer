package com.expressions.unary;

import com.expressions.Expression;

public class Not extends UnaryExpression {
    public Not(Expression expression) {
        super(expression);
    }

    @Override
    public String getOperator() {
        return "!";
    }
}
