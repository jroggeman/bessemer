package com.expressions;

import com.Expression;

public abstract class BinaryExpression extends Expression {
    public Expression leftHandSide;
    public Expression rightHandSide;

    public BinaryExpression(Expression leftHandSide, Expression rightHandSide) {
        this.leftHandSide = leftHandSide;
        this.rightHandSide = rightHandSide;
    }

    public abstract String getOperator();

    public String toString() {
        return leftHandSide + " " + getOperator() + " " + rightHandSide;
    }
}
