package com.ast.expressions.binary;

import com.ast.expressions.Expression;
import com.ast.types.Type;

public class NotEquals extends BinaryExpression {
    public NotEquals(int lineNumber, int columnNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, columnNumber, leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "!=";
    }

    @Override
    public Type getType() {
        if(!Type.areComparisonCompatible(leftHandSide.getType(), rightHandSide.getType())) {
            throw new RuntimeException("Invalid != comparison not caught in type checking");
        }

        return Type.BOOLEAN;
    }
}
