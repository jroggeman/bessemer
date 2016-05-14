package com.ast;

import java.util.List;

public class Program {
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
}

