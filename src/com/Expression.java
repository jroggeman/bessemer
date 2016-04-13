package com;

import com.constants.DoubleConstant;
import com.constants.IntConstant;
import com.expressions.Add;
import com.expressions.Multiply;

public abstract class Expression {

    public static void main(String args[]) {
        Expression e = new Add(new IntConstant(4), new Multiply(new IntConstant(5), new DoubleConstant(4.3)));
        System.out.println(e);
    }
}
