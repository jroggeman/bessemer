package com.ast.statements;

import com.Visitor;
import com.ast.expressions.Expression;

public class Output implements Statement {
    public Expression expression;

    public Output(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "print " + expression + ";";
    }

    @Override
    public void accept(Visitor visitor) {
        expression.accept(visitor);
    }
}
