package com.ast.expressions.binary;

import com.ast.expressions.Expression;
import com.ast.types.Type;
import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;
import com.visitors.Visitor;

public class And extends BinaryExpression {
    public And(int lineNumber, int columnNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, columnNumber, leftHandSide, rightHandSide);
    }

    @Override
    public String getOperator() {
        return "&&";
    }

    @Override
    public void accept(Visitor visitor) throws TypeCheckException {
        visitor.visit(this);
    }

    @Override
    public Type getType() {
        Type lhs = leftHandSide.getType();
        Type rhs = rightHandSide.getType();

        if(!lhs.isBoolean() || !rhs.isBoolean()) {
            throw new RuntimeException("Non-boolean arguments to boolean operator, not caught in type checking");
        }

        return Type.BOOLEAN;
    }

    @Override
    public void checkTypes(SymbolTable table) throws TypeCheckException {

    }
}
