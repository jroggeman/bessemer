package com.expressions.binary;

import com.expressions.Expression;

public class Divide extends BinaryExpression {
    public Divide(Expression leftHandSide, Expression rightHandSide) {
        super(leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "/";
    }
}
