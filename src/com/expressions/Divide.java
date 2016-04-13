package com.expressions;

import com.Expression;

public class Divide extends BinaryExpression {
    public Divide(Expression leftHandSide, Expression rightHandSide) {
        super(leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "/";
    }
}
