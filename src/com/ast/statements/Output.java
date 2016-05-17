package com.ast.statements;

import com.visitors.Visitor;
import com.ast.expressions.Expression;

public class Output extends Statement {
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
        visitor.visit(this);
    }
}
