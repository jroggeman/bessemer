package com.expressions;

import com.constants.DoubleConstant;
import com.constants.IntConstant;
import com.expressions.binary.Add;
import com.expressions.binary.Multiply;

public abstract class Expression {

    public static void main(String args[]) {
        Expression e = new Add(new IntConstant(4), new Multiply(new IntConstant(5), new DoubleConstant(4.3)));
        System.out.println(e);
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Didn't implement toString for the referenced object.");
    }
}
