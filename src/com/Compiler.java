package com;

import com.ast.Program;
import com.symbol_table.SymbolTable;

import java.io.BufferedReader;
import java.io.FileReader;

public class Compiler {

    public static void main(String args[]) throws Exception {
        String inputFilename = args[0];

        BufferedReader b = new BufferedReader(new FileReader(inputFilename));

        Parser parser = new Parser(new Lexer(b));

        Program ast = (Program) parser.parse().value;

        System.out.println(ast);

        BuildSymbolTableVisitor bstv = new BuildSymbolTableVisitor(ast);

        SymbolTable t = bstv.getSymbolTable();

        System.out.println(t);
    }
}
