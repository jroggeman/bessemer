package com.ast.expressions.binary;

import com.ast.expressions.Expression;

public class And extends BinaryExpression {
    public And(Expression leftHandSide, Expression rightHandSide) {
        super(leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "&&";
    }
}
