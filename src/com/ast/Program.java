package com.ast;

import com.ast.constants.IntConstant;
import com.ast.expressions.Expression;
import com.ast.expressions.binary.Add;
import com.ast.expressions.binary.LessThan;
import com.ast.expressions.unary.Not;
import com.ast.mutable.ArrayIndex;
import com.ast.mutable.Identifier;
import com.ast.mutable.VariableDeclaration;
import com.ast.statements.*;
import com.ast.types.Type;

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
        If i = new If(new LessThan(y, five), b);

        List<Statement> l = new ArrayList<Statement>();
        l.add(new Assign(v, first));
        l.add(new Assign(new ArrayIndex(y, first), second));
        l.add(i);

        List<VariableDeclaration> params = new ArrayList<VariableDeclaration>();
        Identifier fname = new Identifier("printf");
        Type ftype = Type.INT;

        // TODO: Update smoke tests with new types
        //Function func = new Function(ftype, fname, params, b);

        //l.add(func);

        Program p = new Program(l);

        System.out.println(p);
    }
}

