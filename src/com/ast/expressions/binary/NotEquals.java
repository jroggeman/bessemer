package com.ast.expressions.binary;

import com.ast.expressions.Expression;

public class NotEquals extends BinaryExpression {
    public NotEquals(int lineNumber, int columnNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, columnNumber, leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "!=";
    }

    @Override
    public boolean areInputsNumeric() {
        return false;
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
