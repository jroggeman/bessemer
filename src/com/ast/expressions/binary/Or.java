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
}
