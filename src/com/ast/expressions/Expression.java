package com.ast.expressions;

import com.ast.statements.Statement;
import com.ast.types.Type;
import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;

public abstract class Expression extends Statement {
    public Expression(int lineNumber, int columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public void checkTypes(SymbolTable table) throws TypeCheckException {

    }

    public abstract Type getType();
}
