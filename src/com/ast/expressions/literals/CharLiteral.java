package com.ast.expressions.literals;

import com.ast.types.Type;
import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;

public class CharLiteral extends Literal {
    public char value;

    public CharLiteral(int lineNumber, int columnNumber, char value) {
        super(lineNumber, columnNumber);
        this.value = value;
    }

    @Override
    public String toString() {
        return "'" + Character.toString(value) + "'";
    }

    @Override
    public Type getType() {
        return Type.CHARACTER;
    }

    @Override
    public void checkTypes(SymbolTable table) throws TypeCheckException {

    }
}
