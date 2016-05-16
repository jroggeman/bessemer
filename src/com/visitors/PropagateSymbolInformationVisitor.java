package com.visitors;

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
import com.ast.statements.*;
import com.ast.types.Type;
import com.symbol_table.SymbolTable;
import com.symbol_table.entries.Entry;
import com.symbol_table.entries.FuncEntry;

import java.util.logging.Level;
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
    public void visit(Block element) {
        for (Statement statement : element) {
            statement.accept(this);
        }
    }

    @Override
    public void visit(BinaryExpression element) {
        element.leftHandSide.accept(this);
        element.rightHandSide.accept(this);
    }

    @Override
    public void visit(Call element) {
        Entry associatedEntry = table.get(element.functionName);
        if(associatedEntry == null) {
            logger.log(Level.SEVERE, "Attempting to call a non-existent function.");
            hasErrors = true;
                    return;
        }

        if(!associatedEntry.isFunction()) {
            logger.log(Level.SEVERE, "Attempting to call a non-function identifier");
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
    public void visit(Subexpression element) {
        element.expression.accept(this);
    }

    @Override
    public void visit(UnaryExpression element) {
        element.expression.accept(this);
    }

    @Override
    public void visit(Function element) {
        element.name.accept(this);
        element.type.accept(this);
        table.enterScope(element);

        element.paramList.accept(this);
        element.block.accept(this);

        table.leaveScope();
    }

    @Override
    public void visit(ParamDeclaration element) {
        element.id.accept(this);
        element.type.accept(this);
    }

    @Override
    public void visit(ParamDeclarationList element) {
        for(ParamDeclaration paramDeclaration : element) {
            paramDeclaration.accept(this);
        }
    }

    @Override
    public void visit(Identifier element) {
        Entry entry = table.get(element);

        if(entry == null) {
            logger.log(Level.SEVERE, "Variable not declared");
            hasErrors = true;
            return;
        }

        element.type = entry.getType();
    }

    @Override
    public void visit(Program element) {
        for (Function function : element.functionList) {
            function.accept(this);
        }
    }

    @Override
    public void visit(Assign element) {
        element.leftHandSide.accept(this);
        element.rightHandSide.accept(this);
    }

    @Override
    public void visit(If element) {
        element.condition.accept(this);
        table.enterScope(element);
        element.block.accept(this);
        table.leaveScope();
    }

    @Override
    public void visit(Input element) {
        element.variable.accept(this);
    }

    @Override
    public void visit(Output element) {
        element.expression.accept(this);
    }

    @Override
    public void visit(Return element) {
        element.expression.accept(this);
    }

    @Override
    public void visit(VariableDeclaration element) {
        element.name.accept(this);
        element.type.accept(this);
    }

    @Override
    public void visit(While element) {
        element.condition.accept(this);
        table.enterScope(element);
        element.block.accept(this);
        table.leaveScope();
    }

    @Override
    public void visit(Type element) {
        /* Do nothing */
    }
}
