package com.expressions.binary;

import com.expressions.Expression;

public class Modulo extends BinaryExpression {
    public Modulo(Expression leftHandSide, Expression rightHandSide) {
        super(leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "%";
    }
}
