package com.ast.statements;

import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;
import com.visitors.Visitor;
import com.ast.Block;
import com.ast.expressions.Expression;

public class If extends Statement {
    public Expression condition;
    public Block block;

    public If(int lineNumber, int columnNumber, Expression condition, Block block) {
        super(lineNumber, columnNumber);
        this.condition = condition;
        this.block = block;
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("if(").append(condition).append(") {\n");
        for(Statement statement : block) {
            for(String line : statement.toString().split("\n")) {
                s.append("\t" + line + "\n");
            }
        }
        s.append("}");
        return s.toString();
    }

    @Override
    public void accept(Visitor visitor) throws TypeCheckException {
        visitor.visit(this);
    }

    @Override
    public void checkTypes(SymbolTable table) throws TypeCheckException {

    }
}
