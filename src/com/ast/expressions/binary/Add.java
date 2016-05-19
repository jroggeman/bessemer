package com.ast.expressions.binary;

import com.ast.expressions.Expression;
import com.ast.types.Type;
import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;

public class Add extends BinaryExpression {
    public Add(int lineNumber, int columnNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, columnNumber, leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "+";
    }

    @Override
    public Type getType() {
        Type lhs = leftHandSide.getType();
        Type rhs = rightHandSide.getType();

        if(!lhs.isNumeric() || !rhs.isNumeric()) {
            throw new RuntimeException("Non-numeric arguments to numeric operator, not caught in type checking");
        }

        return Type.getResultType(lhs, rhs);
    }

    @Override
    public void checkTypes(SymbolTable table) throws TypeCheckException {

    }
}
