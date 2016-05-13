package com;

import com.ast.Program;
import java_cup.parser;

import java.io.BufferedReader;
import java.io.FileReader;

public class Compiler {

    public static void main(String args[]) throws Exception {
        String inputFilename = args[0];

        BufferedReader b = new BufferedReader(new FileReader(inputFilename));

        parser parser = new parser(new Lexer(b));

        Program ast = (Program) parser.parse().value;

        System.out.println(ast);
    }
}
