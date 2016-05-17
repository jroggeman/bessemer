package com.ast.expressions;

import com.ast.types.Type;
import com.visitors.Visitor;

public class Subexpression extends Expression {
    public Expression expression;

    public Subexpression(int lineNumber, int columnNumber, Expression expression) {
        super(lineNumber, columnNumber);
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "(" + expression + ")";
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean areInputsNumeric() {
        return expression.areInputsNumeric();
    }

    @Override
    public boolean areInputsBoolean() {
        return expression.areInputsBoolean();
    }

    @Override
    public boolean isOutputNumeric() {
        return expression.isOutputNumeric();
    }

    @Override
    public boolean isOutputBoolean() {
        return expression.isOutputBoolean();
    }

    @Override
    public Type getType() {
        return expression.getType();
    }
}
