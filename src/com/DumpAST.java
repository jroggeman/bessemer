package com;

import com.ast.Program;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DumpAST {
    private static final Logger logger = Logger.getLogger(DumpAST.class.getCanonicalName());

    public static void main(String args[]) {
        Program ast = null;

        try {
            FileInputStream fileIn = new FileInputStream(args[0]);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            ast = (Program) in.readObject();
            in.close();
            fileIn.close();
        } catch(IOException exception) {
            logger.log(Level.SEVERE, "Couldn't read AST");
        } catch(ClassNotFoundException exception) {
            logger.log(Level.SEVERE, "Couldn't identify class");
        }

        System.out.println(ast);
    }
}
