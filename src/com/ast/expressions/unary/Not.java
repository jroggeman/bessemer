package com.ast.expressions.unary;

import com.ast.expressions.Expression;

public class Not extends UnaryExpression {
    public Not(Expression expression) {
        super(expression);
    }

    @Override
    public String getOperator() {
        return "!";
    }
}
