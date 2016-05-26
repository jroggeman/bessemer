package com.ast.statements;

import com.ast.Token;
import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;

public abstract class Statement extends Token {
    public Statement(int lineNumber, int columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public void checkTypes(SymbolTable table) throws TypeCheckException {

    }
}
