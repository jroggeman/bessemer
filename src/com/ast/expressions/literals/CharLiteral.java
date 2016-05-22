package com.ast.expressions.literals;

import com.ast.types.Type;
import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;
import com.visitors.Visitor;

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
    public void accept(Visitor visitor) throws TypeCheckException {
        visitor.visit(this);
    }

    @Override
    public void checkTypes(SymbolTable table) throws TypeCheckException {

    }
}
