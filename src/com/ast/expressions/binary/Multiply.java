package com.ast.expressions.binary;


import com.ast.expressions.Expression;

public class Multiply extends BinaryExpression {
    public Multiply(Expression leftHandSide, Expression rightHandSide) {
        super(leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "*";
    }
}
