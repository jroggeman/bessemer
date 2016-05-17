package com.ast.expressions.binary;

import com.ast.expressions.Expression;
import com.ast.types.Type;

public class Subtract extends BinaryExpression{

    public Subtract(int lineNumber, int columnNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, columnNumber, leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "-";
    }

    @Override
    public Type getType() {
        Type lhs = leftHandSide.getType();
        Type rhs = rightHandSide.getType();

        if(!lhs.isNumeric() || !rhs.isNumeric()) {
            throw new RuntimeException("Found non-numeric arguments to numeric operator, weren't caught in type checking");
        }

        return Type.getResultType(lhs, rhs);
    }
}
