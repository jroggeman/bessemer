package com.ast;

import com.visitors.Visitor;
import com.ast.function.Function;

import java.util.List;

public class Program implements Token {
    public List<Function> functionList;

    public Program(List<Function> functionList) {
        this.functionList = functionList;
    }

    public String toString() {
        StringBuffer s = new StringBuffer();

        // Whoa, maps in Java!
        functionList.forEach(s::append);

        return s.toString();
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

