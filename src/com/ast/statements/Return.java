package com.ast.statements;

import com.ast.expressions.Expression;

public class Return implements Statement {
    public Expression expression;

    public Return(Expression expression) {
        this.expression = expression;
    }

    public String toString() {
        return "return " + expression + ";";
    }
}
