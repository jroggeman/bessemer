package com.ast.expressions.binary;

import com.ast.expressions.Expression;

public class Modulo extends BinaryExpression {
    public Modulo(Expression leftHandSide, Expression rightHandSide) {
        super(leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "%";
    }
}
