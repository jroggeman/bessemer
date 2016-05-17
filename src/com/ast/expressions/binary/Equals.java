package com.ast.expressions.binary;

import com.ast.expressions.Expression;
import com.ast.types.Type;

public class Equals extends BinaryExpression {
    public Equals(int lineNumber, int columnNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, columnNumber, leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "==";
    }

    @Override
    public Type getType() {
        if(!Type.areComparisonCompatible(leftHandSide.getType(), rightHandSide.getType())) {
            throw new RuntimeException("Invalid != comparison not caught in type checking");
        }

        return Type.BOOLEAN;
    }
}
