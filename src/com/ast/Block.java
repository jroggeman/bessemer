package com.ast;

import com.visitors.Visitor;
import com.ast.statements.Statement;

import java.util.Iterator;
import java.util.List;

public class Block implements Iterable<Statement>, Token {
    public List<Statement> statementList;

    public Block(List<Statement> statementList) {
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
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
