package com.ast.expressions.unary;

import com.ast.expressions.Expression;
import com.ast.types.Type;

public class Not extends UnaryExpression {
    public Not(int lineNumber, int columnNumber, Expression expression) {
        super(lineNumber, columnNumber, expression);
    }

    @Override
    public String getOperator() {
        return "!";
    }

    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }
}
