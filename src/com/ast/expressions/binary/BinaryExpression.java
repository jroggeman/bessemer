package com.ast.expressions.binary;

import com.visitors.Visitor;
import com.ast.expressions.Expression;

public abstract class BinaryExpression implements Expression {
    public Expression leftHandSide;
    public Expression rightHandSide;

    public BinaryExpression(Expression leftHandSide, Expression rightHandSide) {
        this.leftHandSide = leftHandSide;
        this.rightHandSide = rightHandSide;
    }

    public abstract String getOperator();

    @Override
    public String toString() {
        return leftHandSide + " " + getOperator() + " " + rightHandSide;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
