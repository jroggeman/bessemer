package com;

import com.constants.IntConstant;
import com.expressions.Expression;
import com.expressions.binary.Add;
import com.expressions.unary.Not;
import com.mutable.Identifier;
import com.mutable.VariableDeclaration;
import com.statements.ArrayAssign;
import com.statements.Assign;
import com.statements.Statement;
import com.types.Type;

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
        IntConstant twenty = new IntConstant(20);

        Identifier x = new Identifier("x");
        Identifier y = new Identifier("y");
        Identifier z = new Identifier("z");

        Expression first = new Add(five, z);
        Expression second = new Not(twenty);

        VariableDeclaration v = new VariableDeclaration(Type.INT, x);

        List<Statement> l = new ArrayList<Statement>();
        l.add(new Assign(v, first));
        l.add(new ArrayAssign(y, first, second));

        Program p = new Program(l);

        System.out.println(p);
    }
}

