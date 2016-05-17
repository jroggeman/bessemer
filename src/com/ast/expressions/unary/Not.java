package com.ast.expressions.unary;

import com.ast.expressions.Expression;

public class Not extends UnaryExpression {
    public Not(int lineNumber, int columnNumber, Expression expression) {
        super(lineNumber, columnNumber, expression);
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
