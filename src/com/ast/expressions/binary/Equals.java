package com.ast.expressions.binary;

import com.ast.expressions.Expression;

public class Equals extends BinaryExpression {
    public Equals(Expression leftHandSide, Expression rightHandSide) {
        super(leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "==";
    }
}
