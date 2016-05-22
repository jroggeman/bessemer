package com.ast.expressions.literals;

import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;
import com.visitors.Visitor;
import com.ast.expressions.Expression;

public abstract class Literal extends Expression {
    public Literal(int lineNumber, int columnNumber) {
        super(lineNumber, columnNumber);
    }

    public abstract void accept(Visitor visitor) throws TypeCheckException;

    @Override
    public void checkTypes(SymbolTable table) throws TypeCheckException {

    }
}
