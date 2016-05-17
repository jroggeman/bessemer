package com.ast.expressions.binary;

import com.ast.expressions.Expression;
import com.ast.types.Type;

public class Or extends BinaryExpression {
    public Or(int lineNumber, int columnNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, columnNumber, leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "||";
    }

    @Override
    public Type getType() {
        Type lhs = leftHandSide.getType();
        Type rhs = rightHandSide.getType();

        if(!lhs.isBoolean() || !rhs.isBoolean()) {
            throw new RuntimeException("Found non-boolean arguments to boolean operator, weren't caught in type checking");
        }

        return Type.BOOLEAN;
    }
}
