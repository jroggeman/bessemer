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
import com.ast.types.TypeDeclaration;
import com.symbol_table.SymbolTable;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Ensures type agreement across:
 * - Operators
 */
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
    public void visit(Block element) {
        for (Statement statement : element) {
            statement.accept(this);
        }
    }

    @Override
    public void visit(BinaryExpression element) {
        if(element.areInputsNumeric()) {
            if(!element.leftHandSide.isOutputNumeric()) {
                logger.log(Level.SEVERE, "Left hand side of numeric operator is not numeric");
                hasErrors = true;
            }

            if(!element.rightHandSide.isOutputNumeric()) {
                logger.log(Level.SEVERE, "Right hand side of numeric operator is not numeric");
                hasErrors = true;
            }

            if(hasErrors) {
                return;
            }
        } else if(element.areInputsBoolean()) {
            if(!element.leftHandSide.isOutputBoolean()) {
                logger.log(Level.SEVERE, "Left hand side of boolean operator is not boolean");
                hasErrors = true;
            }

            if(!element.rightHandSide.isOutputBoolean()) {
                logger.log(Level.SEVERE, "Right hand side of boolean operator is not boolean");
                hasErrors = true;
            }

            if(hasErrors) {
                return;
            }
        }

        element.leftHandSide.accept(this);
        element.rightHandSide.accept(this);
    }

    @Override
    public void visit(Call element) {
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
        if(element.areInputsNumeric() && !element.expression.isOutputNumeric()) {
            logger.log(Level.SEVERE, "Numeric unary expression with non-numeric argument");
            hasErrors = true;
            return;
        }

        if(element.areInputsBoolean() && !element.expression.isOutputBoolean()) {
            logger.log(Level.SEVERE, "Boolean unary expression with non-boolean argument");
            hasErrors = true;
            return;
        }

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
        /* Do nothing */
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
    public void visit(TypeDeclaration element) {
        /* Do nothing */
    }
}
