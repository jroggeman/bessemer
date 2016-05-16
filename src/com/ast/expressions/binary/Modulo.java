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

    @Override
    public boolean isNumeric() {
        return true;
    }

    @Override
    public boolean isBoolean() {
        return false;
    }
}
