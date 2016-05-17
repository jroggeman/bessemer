package com.ast.expressions.unary;

import com.ast.expressions.Expression;

public class Not extends UnaryExpression {
    public Not(Expression expression) {
        super(expression);
    }

    @Override
    public String getOperator() {
        return "!";
    }

    @Override
    public boolean areInputsNumeric() {
        return false;
    }

    @Override
    public boolean areInputsBoolean() {
        return true;
    }

    @Override
    public boolean isOutputNumeric() {
        return false;
    }

    @Override
    public boolean isOutputBoolean() {
        return true;
    }
}
