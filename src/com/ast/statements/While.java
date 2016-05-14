package com.ast.statements;

import com.ast.Block;
import com.ast.expressions.Expression;

public class While implements Statement {
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
        s.append(") {\n");
        for(Statement statement : block) {
            for(String line : statement.toString().split("\n")) {
                s.append("\t" + line + "\n");
            }
        }

        s.append("}");

        return s.toString();
    }
}
