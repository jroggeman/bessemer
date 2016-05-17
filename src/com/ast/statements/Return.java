package com.ast.statements;

import com.visitors.Visitor;
import com.ast.expressions.Expression;

public class Return extends Statement {
    public Expression expression;

    public Return(Expression expression) {
        this.expression = expression;
    }

    public String toString() {
        return "return " + expression + ";";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
