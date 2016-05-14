package com.ast.statements;

import com.ast.Block;
import com.ast.expressions.Expression;

public class If implements Statement {
    public Expression condition;
    public Block block;

    public If(Expression condition, Block block) {
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
}
