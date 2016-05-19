package com.ast.expressions.unary;

import com.ast.expressions.Expression;
import com.ast.types.Type;
import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;

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

    @Override
    public void checkTypes(SymbolTable table) throws TypeCheckException {

    }
}
