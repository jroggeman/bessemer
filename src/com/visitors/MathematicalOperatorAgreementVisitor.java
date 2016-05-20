package com.visitors;

import com.ast.Program;
import com.ast.expressions.binary.Add;
import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;

public class MathematicalOperatorAgreementVisitor extends GenericVisitor {
    public MathematicalOperatorAgreementVisitor(Program program, SymbolTable table) {
        super(program, table);
    }

    @Override
    public void afterAdd(Add add) throws TypeCheckException {
        add.checkTypes(table);
    }
}
