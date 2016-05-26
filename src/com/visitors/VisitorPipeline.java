package com.visitors;

import com.ast.Program;
import com.symbol_table.SymbolTable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class VisitorPipeline {
    private Program program;
    private SymbolTable symbolTable;

    private static final Class[] VISITOR_LIST = {
            OperatorTypeAgreementVisitor.class
    };

    public VisitorPipeline(Program program) {
        this.program = program;
    }

    public boolean foundErrors() {
        BuildSymbolTableVisitor bstv = new BuildSymbolTableVisitor(program);
        symbolTable = bstv.getSymbolTable();

        PropagateSymbolInformationVisitor psiv = new PropagateSymbolInformationVisitor(program, symbolTable);
        psiv.propagate();

        if(psiv.foundErrors()) {
            return false;
        }

        boolean foundErrors = false;

        for(Class visitorClass : VISITOR_LIST) {
            try {
                Constructor<Visitor> constructor  = visitorClass.getConstructor(Program.class, SymbolTable.class);
                Visitor visitor = constructor.newInstance(new Object[] { program, symbolTable });

                if(visitor.foundErrors()) {
                    foundErrors = true;
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                throw new RuntimeException("Error instantiating visitors, exiting...");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("Error instantiating visitors, exiting...");
            } catch (InstantiationException e) {
                e.printStackTrace();
                throw new RuntimeException("Error instantiating visitors, exiting...");
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                throw new RuntimeException("Error instantiating visitors, exiting...");
            }
        }

        return foundErrors;
    }
}
