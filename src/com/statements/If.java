package com.statements;

import com.Block;
import com.expressions.Expression;
import com.statements.Statement;

public class If extends Statement {
    public Expression condition;
    public Block block;

    public If(Expression condition, Block block) {
        this.condition = condition;
        this.block = block;
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("if(").append(condition).append(") ");
        s.append(block);
        return s.toString();
    }
}
