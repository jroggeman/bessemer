package com.ast.expressions.binary;

import com.ast.expressions.Expression;

public class LessThan extends BinaryExpression{
    public LessThan(Expression leftHandSide, Expression rightHandSide) {
        super(leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "<";
    }

    @Override
    public boolean areInputsNumeric() {
        return true;
    }

    @Override
    public boolean areInputsBoolean() {
        return false;
    }

    @Override
    public boolean isOutputNumeric() {
        return false;
    }

    @Override
    public boolean isOutputBoolean() {
        return true;
    }
}
