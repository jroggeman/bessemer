package com.ast.statements;

import com.ast.expressions.Expression;

public class While extends Statement {
    public Expression condition;
    public Block block;

    public While(Expression condition, Block block) {
        this.condition = condition;
        this.block = block;
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("while(");
        s.append(condition);
        s.append(") ");
        s.append(block);

        return s.toString();
    }
}
