package com;

import com.expressions.Expression;

public class Assign extends Statement {
    public Identifier leftHandSide;
    public Expression rightHandSide;

    public Assign(Identifier leftHandSide, Expression rightHandSide) {
        this.leftHandSide = leftHandSide;
        this.rightHandSide = rightHandSide;
    }

    public String toString() {
        return leftHandSide + " = " + rightHandSide;
    }
}
