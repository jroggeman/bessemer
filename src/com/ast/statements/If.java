package com.ast.statements;

import com.ast.expressions.Expression;

import java.util.List;

public class If extends Statement {
    public Expression condition;
    public List<Statement> statementList;

    public If(Expression condition, List<Statement> statementList) {
        this.condition = condition;
        this.statementList = statementList;
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("if(").append(condition).append(") {\n");
        for(Statement statement : statementList) {
            s.append("\t" + statement + "\n");
        }
        s.append("}");
        return s.toString();
    }
}
