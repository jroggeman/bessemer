package com.ast;

import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;
import com.visitors.Visitor;

import java.io.Serializable;

public abstract class Token implements Serializable {
    public int lineNumber;
    public int columnNumber;

    public Token(int lineNumber, int columnNumber) {
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    public abstract void accept(Visitor visitor) throws TypeCheckException;
    public abstract void checkTypes(SymbolTable table) throws TypeCheckException;
}
