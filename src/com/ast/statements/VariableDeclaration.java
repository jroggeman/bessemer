package com.ast.statements;

import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;
import com.visitors.Visitor;
import com.ast.mutable.Identifier;
import com.ast.types.TypeDeclaration;

public class VariableDeclaration extends Statement {
    public TypeDeclaration type;
    public Identifier name;

    public VariableDeclaration(int lineNumber, int columnNumber, TypeDeclaration type, Identifier name) {
        super(lineNumber, columnNumber);
        this.type = type;
        this.name = name;
    }

    public String toString() {
        return type + " " + name + ";";
    }

    @Override
    public void accept(Visitor visitor) throws TypeCheckException {
        visitor.visit(this);
    }

    @Override
    public void checkTypes(SymbolTable table) throws TypeCheckException {

    }
}
