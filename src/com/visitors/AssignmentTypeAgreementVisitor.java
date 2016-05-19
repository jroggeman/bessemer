package com.visitors;

import com.Errors;
import com.ast.Program;
import com.ast.statements.Assign;
import com.exceptions.AssignmentTypeDisagreementException;
import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;

import java.util.logging.Logger;

public class AssignmentTypeAgreementVisitor extends GenericVisitor {
    private static final Logger logger = Logger.getLogger(AssignmentTypeAgreementVisitor.class.getCanonicalName());

    public AssignmentTypeAgreementVisitor(Program program, SymbolTable table) {
        super(program, table);
    }

    @Override
    public void afterAssign(Assign assign) throws TypeCheckException {
        try {
            assign.checkTypes(table);
        } catch(AssignmentTypeDisagreementException exception) {
            Errors.ASSIGNMENT_TYPE_DISAGREEMENT.log(logger, assign);
            hasErrors = true;
        }
    }
}
