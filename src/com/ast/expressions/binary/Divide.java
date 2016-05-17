package com.ast.expressions.binary;

import com.ast.expressions.Expression;
import com.ast.types.Type;

public class Divide extends BinaryExpression {
    public Divide(int lineNumber, int columnNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, columnNumber, leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "/";
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
        return true;
    }

    @Override
    public boolean isOutputBoolean() {
        return false;
    }

    @Override
    public Type getType() {
        Type lhs = leftHandSide.getType();
        Type rhs = rightHandSide.getType();

        if(!lhs.isNumeric() || !rhs.isNumeric()) {
            throw new RuntimeException("Non-numeric arguments to numeric operator, not caught in type checking");
        }

        return Type.getResultType(lhs, rhs);
    }
}
