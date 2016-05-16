package com.ast.expressions.binary;

import com.ast.expressions.Expression;

public class LessThanOrEquals extends BinaryExpression {
    public LessThanOrEquals(Expression leftHandSide, Expression rightHandSide) {
        super(leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "<=";
    }

    @Override
    public boolean isNumeric() {
        return false;
    }

    @Override
    public boolean isBoolean() {
        return true;
    }
}
