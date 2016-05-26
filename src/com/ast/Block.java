package com.ast;

import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;
import com.visitors.Visitor;
import com.ast.statements.Statement;

import java.util.Iterator;
import java.util.List;

public class Block extends Token implements Iterable<Statement> {
    public List<Statement> statementList;

    public Block(int lineNumber, int columnNumber, List<Statement> statementList) {
        super(lineNumber, columnNumber);
        this.statementList = statementList;
    }

    @Override
    public Iterator<Statement> iterator() {
        return statementList.iterator();
    }

    public String toString() {
        throw new UnsupportedOperationException("Printing should be implemented in methods that contain blocks");
    }

    @Override
    public void accept(Visitor visitor) throws TypeCheckException {
        visitor.visit(this);
    }

    @Override
    public void checkTypes(SymbolTable table) throws TypeCheckException {

    }
}
