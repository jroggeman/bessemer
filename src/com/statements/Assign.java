package com.statements;

import com.mutable.Mutable;
import com.expressions.Expression;

public class Assign extends Statement {
    public Mutable leftHandSide;
    public Expression rightHandSide;

    public Assign(Mutable leftHandSide, Expression rightHandSide) {
        this.leftHandSide = leftHandSide;
        this.rightHandSide = rightHandSide;
    }

    public String toString() {
        return leftHandSide + " = " + rightHandSide;
    }
}
