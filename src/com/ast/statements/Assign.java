package com.ast.statements;

import com.ast.mutable.Mutable;
import com.ast.expressions.Expression;

public class Assign implements Statement {
    public Mutable leftHandSide;
    public Expression rightHandSide;

    public Assign(Mutable leftHandSide, Expression rightHandSide) {
        this.leftHandSide = leftHandSide;
        this.rightHandSide = rightHandSide;
    }

    public String toString() {
        return leftHandSide + " = " + rightHandSide + ";";
    }
}
