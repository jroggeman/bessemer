package com.ast.expressions.unary;

import com.visitors.Visitor;
import com.ast.expressions.Expression;

public abstract class UnaryExpression extends Expression {
    public Expression expression;

    public UnaryExpression(int lineNumber, int columnNumber, Expression expression) {
        super(lineNumber, columnNumber);
        this.expression = expression;
    }

    public abstract String getOperator();

    @Override
    public String toString() {
        return getOperator() + " " + expression;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
