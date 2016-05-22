package com.visitors;

import com.Errors;
import com.ast.Program;
import com.ast.expressions.binary.Add;
import com.exceptions.InvalidMathematicalOperandsException;
import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;

import java.util.logging.Logger;

public class MathematicalOperatorAgreementVisitor extends GenericVisitor {
    private static final Logger logger = Logger.getLogger(MathematicalOperatorAgreementVisitor.class.getCanonicalName());

    public MathematicalOperatorAgreementVisitor(Program program, SymbolTable table) {
        super(program, table);
    }

    @Override
    public void afterAdd(Add add) throws TypeCheckException {
        try {
            add.checkTypes(table);
        } catch(InvalidMathematicalOperandsException exception) {
            Errors.INVALID_MATHEMATICAL_OPERANDS.log(logger, add);
        }
    }
}
