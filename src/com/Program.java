package com;

import com.constants.IntConstant;
import com.expressions.Expression;
import com.expressions.binary.Add;
import com.expressions.unary.Not;
import com.statements.ArrayAssign;
import com.statements.Assign;
import com.statements.Statement;

import java.util.ArrayList;
import java.util.List;

public class Program {
    public List<Statement> statementList;

    public Program(List<Statement> statementList) {
        this.statementList = statementList;
    }

    public String toString() {
        StringBuffer s = new StringBuffer();

        for(Statement statement : statementList) {
            s.append(statement + ";\n");
        }

        return s.toString();
    }

    public static void main(String args[]) {
        IntConstant five = new IntConstant(5);
        IntConstant three = new IntConstant(3);
        IntConstant twenty = new IntConstant(20);

        Expression first = new Add(five, three);
        Expression second = new Not(twenty);

        Identifier x = new Identifier("x");
        Identifier y = new Identifier("y");

        List<Statement> l = new ArrayList<Statement>();
        l.add(new Assign(x, first));
        l.add(new ArrayAssign(y, first, second));

        Program p = new Program(l);

        System.out.println(p);
    }
}

