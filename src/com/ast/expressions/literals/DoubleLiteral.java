package com.ast.expressions.literals;

import com.ast.types.Type;
import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;

public class DoubleLiteral extends Literal {
    @Override
    public Type getType() {
        return Type.DOUBLE;
    }

    public double value;

    public DoubleLiteral(int lineNumber, int columnNumber, double value) {
        super(lineNumber, columnNumber);
        this.value = value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public void checkTypes(SymbolTable table) throws TypeCheckException {

    }
}
