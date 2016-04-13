package com.expressions.binary;

import com.expressions.Expression;

public class NotEquals extends BinaryExpression {
    public NotEquals(Expression leftHandSide, Expression rightHandSide) {
        super(leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "!=";
    }
}
