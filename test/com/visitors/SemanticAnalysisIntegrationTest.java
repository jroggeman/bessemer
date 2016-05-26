package com.visitors;

import static junit.framework.Assert.*;

import com.ast.Program;
import com.symbol_table.SymbolTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

@RunWith(Parameterized.class)
public class SemanticAnalysisIntegrationTest {
    private static List<String> passingFiles;
    private static List<String> failingFiles;

    private String filename;
    private boolean passed;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        if(passingFiles == null) {
            passingFiles = new ArrayList<>();

            File passFolder = new File("test/resources/pass");
            File[] listOfPassingFiles = passFolder.listFiles();

            for(File passFile : listOfPassingFiles) {
                if(passFile.isFile()) {
                    String filename = passFile.getName();
                    String extension = filename.substring(filename.lastIndexOf('.') + 1);
                    if(extension.equals("ser")) {
                        passingFiles.add(passFile.getAbsolutePath());
                    }
                }
            }

            failingFiles = new ArrayList<>();

            File failFolder = new File("test/resources/fail");
            File[] listOfFailingFiles = failFolder.listFiles();

            for(File failFile : listOfFailingFiles) {
                if(failFile.isFile()) {
                    String filename = failFile.getName();
                    String extension = filename.substring(filename.lastIndexOf('.') + 1);
                    if(extension.equals("ser")) {
                        failingFiles.add(failFile.getAbsolutePath());
                    }
                }
            }
        }

        List finalList = new ArrayList<>();

        for(String passFile : passingFiles) {
            finalList.add(new Object[] { passFile, true });
        }

        for(String failFile : failingFiles) {
            finalList.add(new Object[] { failFile, false });
        }

        return finalList;
    }

    public SemanticAnalysisIntegrationTest(String filename, boolean passed) {
        this.filename = filename;
        this.passed = passed;
    }

    @Test
    public void testSemanticAnalysis() {
        Program ast = null;
        try {
            boolean foundErrors = false;

            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            ast = (Program) in.readObject();

            BuildSymbolTableVisitor bstv = new BuildSymbolTableVisitor(ast);

            SymbolTable t = bstv.getSymbolTable();

            PropagateSymbolInformationVisitor psiv = new PropagateSymbolInformationVisitor(ast, t);
            psiv.propagate();

            foundErrors |= psiv.foundErrors();

            OperatorTypeAgreementVisitor otav = new OperatorTypeAgreementVisitor(ast, t);

            foundErrors  |= otav.hasErrors();

            assertEquals(passed, !foundErrors);
        } catch(IOException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}
