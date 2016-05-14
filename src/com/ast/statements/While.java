package com.ast.statements;

import com.ast.expressions.Expression;

import java.util.List;

public class While implements Statement {
    public Expression condition;
    public List<Statement> statementList;

    public While(Expression condition, List<Statement> statementList) {
        this.condition = condition;
        this.statementList = statementList;
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("while(");
        s.append(condition);
        s.append(") {\n");
        for(Statement statement : statementList) {
            s.append("\t" + statement + "\n");
        }

        return s.toString();
    }
}
