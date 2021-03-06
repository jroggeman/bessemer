package com.visitors;

import com.Errors;
import com.ast.Block;
import com.ast.Program;
import com.ast.expressions.Call;
import com.ast.expressions.Expression;
import com.ast.expressions.Subexpression;
import com.ast.expressions.binary.*;
import com.ast.expressions.literals.*;
import com.ast.expressions.unary.Not;
import com.ast.expressions.unary.UnaryExpression;
import com.ast.function.Function;
import com.ast.function.ParamDeclaration;
import com.ast.function.ParamDeclarationList;
import com.ast.mutable.Identifier;
import com.ast.mutable.Mutable;
import com.ast.statements.*;
import com.ast.types.TypeDeclaration;
import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;
import com.symbol_table.entries.Entry;
import com.symbol_table.entries.FuncEntry;

import java.util.logging.Logger;

/**
 * This is run to associate all identifiers and method calls with their associated
 * types.
 */
public class PropagateSymbolInformationVisitor implements Visitor {
    private static final Logger logger = Logger.getLogger(PropagateSymbolInformationVisitor.class.getCanonicalName());

    private Program program;
    private SymbolTable table;
    private boolean hasErrors = false;

    public PropagateSymbolInformationVisitor(Program program, SymbolTable table) {
        this.program = program;
        this.table = table;
    }

    public void propagate() {
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
        Entry associatedEntry = table.get(element.functionName);
        if(associatedEntry == null) {
            Errors.CALL_NON_EXISTENT_FUNCTION.log(logger, element);
            hasErrors = true;
                    return;
        }

        if(!associatedEntry.isFunction()) {
            Errors.CALL_NON_FUNCTION.log(logger, element);
            hasErrors = true;
            return;
        }

        FuncEntry functionEntry = (FuncEntry) associatedEntry;
        element.associatedFunction = functionEntry.getFunction();

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
        Entry entry = table.get(element);

        if(entry == null) {
            Errors.VARIABLE_USED_BEFORE_DECLARATION.log(logger, element);
            hasErrors = true;
            return;
        }

        element.type = entry.getType();
    }

    @Override
    public void visit(Program element) {
        for (Function function : element.functionList) {
            try {
                function.accept(this);
            } catch (TypeCheckException e) {
                throw new RuntimeException("Somehow got TypeCheckException in PropagateSymbolInformationVisitor");
            }
        }
    }

    @Override
    public void visit(Assign element) throws TypeCheckException {
        element.leftHandSide.accept(this);
        element.rightHandSide.accept(this);
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

    @Override
    public void visit(Add element) throws TypeCheckException {
        visit((BinaryExpression) element);
    }

    @Override
    public void visit(And element) throws TypeCheckException {
        visit((BinaryExpression) element);
    }

    @Override
    public void visit(Divide element) throws TypeCheckException {
        visit((BinaryExpression) element);
    }

    @Override
    public void visit(Equals element) throws TypeCheckException {
        visit((BinaryExpression) element);
    }

    @Override
    public void visit(LessThan element) throws TypeCheckException {
        visit((BinaryExpression) element);
    }

    @Override
    public void visit(LessThanOrEquals element) throws TypeCheckException {
        visit((BinaryExpression) element);
    }

    @Override
    public void visit(Modulo element) throws TypeCheckException {
        visit((BinaryExpression) element);
    }

    @Override
    public void visit(Multiply element) throws TypeCheckException {
        visit((BinaryExpression) element);
    }

    @Override
    public void visit(NotEquals element) throws TypeCheckException {
        visit((BinaryExpression) element);
    }

    @Override
    public void visit(Or element) throws TypeCheckException {
        visit((BinaryExpression) element);
    }

    @Override
    public void visit(Subtract element) throws TypeCheckException {
        visit((BinaryExpression) element);
    }

    @Override
    public void visit(BoolLiteral element) throws TypeCheckException {
        visit((Literal) element);
    }

    @Override
    public void visit(CharLiteral element) throws TypeCheckException {
        visit((Literal) element);
    }

    @Override
    public void visit(DoubleLiteral element) throws TypeCheckException {
        visit((Literal) element);
    }

    @Override
    public void visit(IntLiteral element) throws TypeCheckException {
        visit((Literal) element);
    }

    @Override
    public void visit(Not element) throws TypeCheckException {
        visit((UnaryExpression) element);
    }
}
