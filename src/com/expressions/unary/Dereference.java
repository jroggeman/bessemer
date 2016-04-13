package com.expressions.unary;

import com.mutable.Mutable;
import com.expressions.Expression;

public class Dereference extends UnaryExpression implements Mutable {
    public Dereference(Expression expression) {
        super(expression);
    }

    @Override
    public String getOperator() {
        return "*";
    }
}
