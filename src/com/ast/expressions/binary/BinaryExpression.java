package com.ast.expressions.binary;

import com.visitors.Visitor;
import com.ast.expressions.Expression;

public abstract class BinaryExpression extends Expression {
    public Expression leftHandSide;
    public Expression rightHandSide;

    public BinaryExpression(int lineNumber, int columnNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, columnNumber);
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
