package com.expressions.binary;

import com.expressions.Expression;

public class Subtract extends BinaryExpression{

    public Subtract(Expression leftHandSide, Expression rightHandSide) {
        super(leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "-";
    }
}
