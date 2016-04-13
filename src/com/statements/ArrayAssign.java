package com.statements;

import com.mutable.Identifier;
import com.expressions.Expression;

public class ArrayAssign extends Statement {
    public Identifier array;
    public Expression index;
    public Expression rightHandSide;

    public ArrayAssign(Identifier array, Expression index, Expression rightHandSide) {
        this.array = array;
        this.index = index;
        this.rightHandSide = rightHandSide;
    }

    @Override
    public String toString() {
        return array + "[" + index + "] = " + rightHandSide;
    }
}
