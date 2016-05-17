package com.ast.expressions.binary;

import com.ast.expressions.Expression;

public class Or extends BinaryExpression {
    public Or(Expression leftHandSide, Expression rightHandSide) {
        super(leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "||";
    }

    @Override
    public boolean areInputsNumeric() {
        return false;
    }

    @Override
    public boolean areInputsBoolean() {
        return true;
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
