package com.ast.expressions.unary;

import com.ast.expressions.Expression;
import com.ast.types.Type;
import com.exceptions.InvalidBooleanOperandsException;
import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;
import com.visitors.Visitor;

public class Not extends UnaryExpression {
    public Not(int lineNumber, int columnNumber, Expression expression) {
        super(lineNumber, columnNumber, expression);
    }

    @Override
    public String getOperator() {
        return "!";
    }

    @Override
    public void accept(Visitor visitor) throws TypeCheckException {
        visitor.visit(this);
    }

    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }

    @Override
    public void checkTypes(SymbolTable table) throws TypeCheckException {
        if(!expression.getType().isBoolean()) {
            throw new InvalidBooleanOperandsException();
        }
    }
}
