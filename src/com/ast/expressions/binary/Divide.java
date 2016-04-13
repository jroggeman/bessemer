package com.ast.expressions.binary;

import com.ast.expressions.Expression;

public class Divide extends BinaryExpression {
    public Divide(Expression leftHandSide, Expression rightHandSide) {
        super(leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "/";
    }
}