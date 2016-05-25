package com;

import com.ast.Program;
import com.symbol_table.SymbolTable;
import com.visitors.*;

import java.io.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Compiler {
    public static final Level logLevel = Level.FINE;
    private static final Logger logger = Logger.getLogger(Compiler.class.getCanonicalName());

    public static void main(String args[]) throws Exception {
        setupLogger();

        logger.log(Level.FINE, "Beginning compilation");
        String inputFilename = args[0];

        BufferedReader b = new BufferedReader(new FileReader(inputFilename));

        Parser parser = new Parser(new Lexer(b));

        Program ast = (Program) parser.parse().value;

        BuildSymbolTableVisitor bstv = new BuildSymbolTableVisitor(ast);

        SymbolTable t = bstv.getSymbolTable();

        PropagateSymbolInformationVisitor psiv = new PropagateSymbolInformationVisitor(ast, t);
        psiv.propagate();

        if(psiv.foundErrors()) {
            logger.log(Level.SEVERE, "Found errors, aborting...");
            System.exit(1);
        }

        OperatorTypeAgreementVisitor otav = new OperatorTypeAgreementVisitor(ast, t);
        if(otav.hasErrors()) {
            logger.log(Level.SEVERE, "Found type errors, aborting...");
            System.exit(1);
        }

        try {
            FileOutputStream out = new FileOutputStream("ast.ser");
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(ast);
            objOut.close();
            out.close();
        } catch(IOException exception) {
            logger.log(Level.SEVERE, "Error writing AST to disk");
        }
    }

    private static void setupLogger() {
        Logger rootLogger = Logger.getLogger("com");
        rootLogger.setUseParentHandlers(false);

        Handler[] handlers = rootLogger.getHandlers();
        for(Handler handler : handlers) {
            if(handler.getClass() == ConsoleHandler.class) {
                rootLogger.removeHandler(handler);
            }
        }

        ConsoleHandler console = new ConsoleHandler();
        console.setLevel(logLevel);
        rootLogger.addHandler(console);

        rootLogger.setLevel(logLevel);
    }
}
