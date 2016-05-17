package com.ast.statements;

import com.visitors.Visitor;
import com.ast.expressions.Expression;

public class Output extends Statement {
    public Expression expression;

    public Output(int lineNumber, int columnNumber, Expression expression) {
        super(lineNumber, columnNumber);
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
