package com.statements;

import com.statements.Statement;

import java.util.List;

public class Block {
    List<Statement> statements;

    public Block(List<Statement> statements) {
        this.statements = statements;
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("{\n");
        for(Statement statement : statements) {
            s.append("\t" + statement + ";\n");
        }
        s.append("}");

        return s.toString();
    }
}
