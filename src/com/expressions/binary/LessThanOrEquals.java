package com.expressions.binary;

import com.expressions.Expression;

public class LessThanOrEquals extends BinaryExpression {
    public LessThanOrEquals(Expression leftHandSide, Expression rightHandSide) {
        super(leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "<=";
    }
}
