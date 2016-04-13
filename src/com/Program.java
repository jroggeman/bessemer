package com;

import com.constants.BoolConstant;
import com.constants.IntConstant;
import com.expressions.Expression;
import com.expressions.binary.Add;
import com.expressions.unary.Not;
import com.mutable.ArrayIndex;
import com.mutable.Identifier;
import com.mutable.VariableDeclaration;
import com.statements.Assign;
import com.statements.If;
import com.statements.Statement;
import com.statements.While;
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

        List<Statement> s = new ArrayList<Statement>();
        s.add(new Assign(v, first));

        Block b = new Block(s);
        While i = new While(new BoolConstant(true), b);

        List<Statement> l = new ArrayList<Statement>();
        l.add(new Assign(v, first));
        l.add(new Assign(new ArrayIndex(y, first), second));
        l.add(i);

        Program p = new Program(l);

        System.out.println(p);
    }
}

