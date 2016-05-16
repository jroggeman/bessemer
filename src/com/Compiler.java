package com;

import com.ast.Program;
import com.symbol_table.SymbolTable;
import com.visitors.BuildSymbolTableVisitor;
import com.visitors.PropagateSymbolInformationVisitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Compiler {
    public static final Level logLevel = Level.FINE;
    private static final Logger logger = Logger.getLogger(Compiler.class.getCanonicalName());

    public static void main(String args[]) throws Exception {
        Logger rootLogger = Logger.getLogger("com");
        rootLogger.setLevel(logLevel);
        ConsoleHandler console = new ConsoleHandler();
        console.setLevel(logLevel);
        rootLogger.addHandler(console);

        logger.log(Level.FINE, "Beginning compilation");
        String inputFilename = args[0];

        BufferedReader b = new BufferedReader(new FileReader(inputFilename));

        Parser parser = new Parser(new Lexer(b));

        Program ast = (Program) parser.parse().value;

        System.out.println(ast);

        BuildSymbolTableVisitor bstv = new BuildSymbolTableVisitor(ast);

        SymbolTable t = bstv.getSymbolTable();

        System.out.println(t);

        PropagateSymbolInformationVisitor psiv = new PropagateSymbolInformationVisitor(ast, t);
        psiv.propagate();
    }
}
