package com.ast.expressions.unary;

import com.ast.mutable.Mutable;
import com.ast.expressions.Expression;

public class Dereference extends UnaryExpression implements Mutable {
    public Dereference(Expression expression) {
        super(expression);
    }

    @Override
    public String getOperator() {
        return "*";
    }
}
