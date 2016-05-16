package com.ast.statements;

import com.visitors.Visitor;
import com.ast.expressions.Expression;
import com.ast.mutable.Mutable;

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

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
