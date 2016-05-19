package com.ast.expressions.literals;

import com.ast.types.Type;
import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;

public class IntLiteral extends Literal {
    public int value;

    public IntLiteral(int lineNumber, int columnNumber, int value) {
        super(lineNumber, columnNumber);
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public Type getType() {
        return Type.INTEGER;
    }

    @Override
    public void checkTypes(SymbolTable table) throws TypeCheckException {

    }
}
