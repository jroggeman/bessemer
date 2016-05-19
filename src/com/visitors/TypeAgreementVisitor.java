package com.visitors;

import com.Errors;
import com.ast.Block;
import com.ast.Program;
import com.ast.expressions.Call;
import com.ast.expressions.Expression;
import com.ast.expressions.Subexpression;
import com.ast.expressions.binary.BinaryExpression;
import com.ast.expressions.literals.Literal;
import com.ast.expressions.unary.UnaryExpression;
import com.ast.function.Function;
import com.ast.function.ParamDeclaration;
import com.ast.function.ParamDeclarationList;
import com.ast.mutable.Identifier;
import com.ast.mutable.Mutable;
import com.ast.statements.*;
import com.ast.types.TypeDeclaration;
import com.exceptions.AssignmentTypeDisagreementException;
import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TypeAgreementVisitor implements Visitor {
    private static final Logger logger = Logger.getLogger(TypeAgreementVisitor.class.getCanonicalName());

    private Program program;
    private SymbolTable table;
    private boolean hasErrors = false;

    public TypeAgreementVisitor(Program program, SymbolTable table) {
        this.program = program;
        this.table = table;
    }

    public void checkTypeAgreement() {
        visit(program);
    }

    public boolean foundErrors() {
        return hasErrors;
    }

    @Override
    public void visit(Block element) throws TypeCheckException {
        for (Statement statement : element) {
            statement.accept(this);
        }
    }

    @Override
    public void visit(BinaryExpression element) throws TypeCheckException {
        element.leftHandSide.accept(this);
        element.rightHandSide.accept(this);
    }

    @Override
    public void visit(Call element) throws TypeCheckException {
        element.functionName.accept(this);
        for (Expression expression : element.parameterList) {
            expression.accept(this);
        }
    }

    @Override
    public void visit(Literal element) {
        /* Do nothing */
    }

    @Override
    public void visit(Subexpression element) throws TypeCheckException {
        element.expression.accept(this);
    }

    @Override
    public void visit(UnaryExpression element) throws TypeCheckException {
        element.expression.accept(this);
    }

    @Override
    public void visit(Function element) throws TypeCheckException {
        element.name.accept(this);
        element.type.accept(this);
        table.enterScope(element);

        element.paramList.accept(this);
        element.block.accept(this);

        table.leaveScope();
    }

    @Override
    public void visit(ParamDeclaration element) throws TypeCheckException {
        element.id.accept(this);
        element.type.accept(this);
    }

    @Override
    public void visit(ParamDeclarationList element) throws TypeCheckException {
        for(ParamDeclaration paramDeclaration : element) {
            paramDeclaration.accept(this);
        }
    }

    @Override
    public void visit(Identifier element) {
        /* Do nothing */
    }

    @Override
    public void visit(Program element) {
        for (Function function : element.functionList) {
            try {
                function.accept(this);
            } catch (TypeCheckException e) {
                logger.log(Level.SEVERE, e.toString());
            }
        }
    }

    @Override
    public void visit(Assign element) throws TypeCheckException {
        element.leftHandSide.accept(this);
        element.rightHandSide.accept(this);

        try {
            element.checkTypes(table);
        } catch(AssignmentTypeDisagreementException exception) {
            Errors.ASSIGNMENT_TYPE_DISAGREEMENT.log(logger, element);
            hasErrors = true;
        }
    }

    @Override
    public void visit(Mutable element) {
        /* Do nothing */
    }

    @Override
    public void visit(If element) throws TypeCheckException {
        element.condition.accept(this);
        table.enterScope(element);
        element.block.accept(this);
        table.leaveScope();
    }

    @Override
    public void visit(Input element) throws TypeCheckException {
        element.variable.accept(this);
    }

    @Override
    public void visit(Output element) throws TypeCheckException {
        element.expression.accept(this);
    }

    @Override
    public void visit(Return element) throws TypeCheckException {
        element.expression.accept(this);
    }

    @Override
    public void visit(VariableDeclaration element) throws TypeCheckException {
        element.name.accept(this);
        element.type.accept(this);
    }

    @Override
    public void visit(While element) throws TypeCheckException {
        element.condition.accept(this);
        table.enterScope(element);
        element.block.accept(this);
        table.leaveScope();
    }

    @Override
    public void visit(TypeDeclaration element) {
        /* Do nothing */
    }
}
