package com.ast.expressions.binary;

import com.ast.expressions.Expression;

public class Add extends BinaryExpression {
    public Add(Expression leftHandSide, Expression rightHandSide) {
        super(leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "+";
    }
}
