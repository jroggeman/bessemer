package com.ast;

import java.util.List;

public class Program {
    public List<Function> functionList;

    public Program(List<Function> functionList) {
        this.functionList = functionList;
    }

    public String toString() {
        StringBuffer s = new StringBuffer();

        for(Function function : functionList) {
            s.append(function + ";\n");
        }

        return s.toString();
    }
}

