package com;

import com.ast.Program;
import com.symbol_table.SymbolTable;
import com.visitors.*;
import org.apache.commons.cli.*;

import java.io.*;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Compiler {
    public static final Level logLevel = Level.FINE;
    private static final Logger logger = Logger.getLogger(Compiler.class.getCanonicalName());

    public static void main(String args[]) throws Exception {
        setupLogger();

        Options options = new Options();

        options.addOption(new Option("astOnly", "Only generate and save the AST (into ast.ser)"));
        options.addOption(Option.builder("astFile").hasArg().desc("Dump AST into specified file").argName("file").build());
        options.addOption(new Option("debug", "Print debug information"));

        logger.log(Level.FINE, "Beginning compilation");

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine line = commandLineParser.parse(options, args);

        if(!line.hasOption("debug")) {
            logger.setLevel(Level.SEVERE);
        }

        List<String> remainingOptions = line.getArgList();

        if(remainingOptions.size() == 0) {
            logger.log(Level.SEVERE, "Please specify a file to compile.");
            System.exit(1);
        }

        String inputFilename = remainingOptions.get(0);

        BufferedReader b = new BufferedReader(new FileReader(inputFilename));

        Parser parser = new Parser(new Lexer(b));

        Program ast = (Program) parser.parse().value;

        if(line.hasOption("astOnly")) {
            try {
                String outputFilename = line.getOptionValue("astFile");
                FileOutputStream out = new FileOutputStream(outputFilename);
                ObjectOutputStream objOut = new ObjectOutputStream(out);
                objOut.writeObject(ast);
                objOut.close();
                out.close();
                logger.log(Level.FINE, "Successfully saved AST to the disk");
            } catch(IOException exception) {
                logger.log(Level.SEVERE, "Error writing AST to disk");
            }
        } else {

            VisitorPipeline pipeline = new VisitorPipeline(ast);

            if(pipeline.foundErrors()) {
                logger.log(Level.SEVERE, "Found errors, aborting...");
                System.exit(1);
            }
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
