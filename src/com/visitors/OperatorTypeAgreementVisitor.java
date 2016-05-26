package com.visitors;

import com.Errors;
import com.ast.Block;
import com.ast.Program;
import com.ast.Token;
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
import com.exceptions.ComparisonOperandDisagreementException;
import com.exceptions.InvalidBooleanOperandsException;
import com.exceptions.InvalidMathematicalOperandsException;
import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;

import java.util.logging.Logger;

/**
 * Checks for:
 * - Numeric operands for numeric operators
 * - Boolean operands for boolean operators
 * - Agreement between types for comparison operators
 */
public class OperatorTypeAgreementVisitor implements Visitor {
    private static final Logger logger = Logger.getLogger(OperatorTypeAgreementVisitor.class.getCanonicalName());

    private Program program;
    private SymbolTable table;

    private boolean foundErrors = false;

    public OperatorTypeAgreementVisitor(Program program, SymbolTable table) {
        this.program = program;
        this.table = table;
    }

    public boolean foundErrors() {
        visit(program);
        return foundErrors;
    }

    @Override
    public void visit(Block element) throws TypeCheckException {
        for(Statement statement : element) {
            statement.accept(this);
        }
    }

    @Override
    public void visit(BinaryExpression element) throws TypeCheckException {
        throw new UnsupportedOperationException("Parsing a binary operator that isn't explicitly handled");
    }

    @Override
    public void visit(Call element) throws TypeCheckException {
        element.functionName.accept(this);

        for (Expression expression : element.parameterList) {
            expression.accept(this);
        }
    }

    @Override
    public void visit(Literal element) throws TypeCheckException {
        /* Do nothing */
    }

    @Override
    public void visit(Subexpression element) throws TypeCheckException {
        element.expression.accept(this);
    }

    @Override
    public void visit(UnaryExpression element) throws TypeCheckException {
        throw new UnsupportedOperationException("Parsing unary operator that isn't explicitly stated");
    }

    @Override
    public void visit(Function element) throws TypeCheckException {
        element.type.accept(this);
        element.name.accept(this);
        element.paramList.accept(this);
        element.block.accept(this);
    }

    @Override
    public void visit(ParamDeclaration element) throws TypeCheckException {
        element.type.accept(this);
        element.id.accept(this);
    }

    @Override
    public void visit(ParamDeclarationList element) throws TypeCheckException {
        for (ParamDeclaration paramDeclaration : element) {
            paramDeclaration.accept(this);
        }
    }

    @Override
    public void visit(Identifier element) throws TypeCheckException {
        /* Do nothing */
    }

    @Override
    public void visit(Program element) {
        try {
            for (Function function : element.functionList) {
                function.accept(this);
            }
        } catch (TypeCheckException exception) {
            throw new RuntimeException("Unhandled exception at program level");
        }
    }

    @Override
    public void visit(Assign element) throws TypeCheckException {
        try {
            element.leftHandSide.accept(this);
            element.rightHandSide.accept(this);
        } catch(InvalidMathematicalOperandsException exception) {
            Errors.INVALID_MATHEMATICAL_OPERANDS.log(logger, element);
            foundErrors = true;
        }
    }

    @Override
    public void visit(Mutable element) throws TypeCheckException {
        /* Do nothing */
    }

    @Override
    public void visit(If element) throws TypeCheckException {
        try {
            element.condition.accept(this);
            element.block.accept(this);
        } catch (InvalidMathematicalOperandsException exception) {
            Errors.INVALID_MATHEMATICAL_OPERANDS.log(logger, element);
            foundErrors = true;
        }
    }

    @Override
    public void visit(Input element) throws TypeCheckException {
        try {
            element.variable.accept(this);
        } catch (InvalidMathematicalOperandsException exception) {
            Errors.INVALID_MATHEMATICAL_OPERANDS.log(logger, element);
            foundErrors = true;
        }
    }

    @Override
    public void visit(Output element) throws TypeCheckException {
        try {
            element.expression.accept(this);
        } catch(InvalidMathematicalOperandsException exception) {
            Errors.INVALID_MATHEMATICAL_OPERANDS.log(logger, element);
            foundErrors = true;
        }
    }

    @Override
    public void visit(Return element) throws TypeCheckException {
        try {
            element.expression.accept(this);
        } catch (InvalidMathematicalOperandsException exception) {
            Errors.INVALID_MATHEMATICAL_OPERANDS.log(logger, element);
            foundErrors = true;
        }
    }

    @Override
    public void visit(VariableDeclaration element) throws TypeCheckException {
        element.type.accept(this);
        element.name.accept(this);
    }

    @Override
    public void visit(While element) throws TypeCheckException {
        try {
            element.condition.accept(this);
            element.block.accept(this);
        } catch (InvalidMathematicalOperandsException exception) {
            Errors.INVALID_MATHEMATICAL_OPERANDS.log(logger, element);
            foundErrors = true;
        }
    }

    @Override
    public void visit(TypeDeclaration element) throws TypeCheckException {
        /* Do nothing */
    }

    @Override
    public void visit(Add element) throws TypeCheckException {
        element.leftHandSide.accept(this);
        element.rightHandSide.accept(this);
        checkMathOperators(element);
    }

    @Override
    public void visit(And element) throws TypeCheckException {
        element.leftHandSide.accept(this);
        element.rightHandSide.accept(this);
        checkBooleanOperators(element);
    }

    @Override
    public void visit(Divide element) throws TypeCheckException {
        element.leftHandSide.accept(this);
        element.rightHandSide.accept(this);
        checkMathOperators(element);
    }

    @Override
    public void visit(Equals element) throws TypeCheckException {
        element.leftHandSide.accept(this);
        element.rightHandSide.accept(this);
        checkComparisonAgreement(element);
    }

    @Override
    public void visit(LessThan element) throws TypeCheckException {
        element.leftHandSide.accept(this);
        element.rightHandSide.accept(this);
        checkMathOperators(element);
    }

    @Override
    public void visit(LessThanOrEquals element) throws TypeCheckException {
        element.leftHandSide.accept(this);
        element.rightHandSide.accept(this);
        checkMathOperators(element);
    }

    @Override
    public void visit(Modulo element) throws TypeCheckException {
        element.leftHandSide.accept(this);
        element.rightHandSide.accept(this);
        checkMathOperators(element);
    }

    @Override
    public void visit(Multiply element) throws TypeCheckException {
        element.leftHandSide.accept(this);
        element.rightHandSide.accept(this);
        checkMathOperators(element);
    }

    @Override
    public void visit(NotEquals element) throws TypeCheckException {
        element.leftHandSide.accept(this);
        element.rightHandSide.accept(this);
        checkComparisonAgreement(element);
    }

    @Override
    public void visit(Or element) throws TypeCheckException {
        element.leftHandSide.accept(this);
        element.rightHandSide.accept(this);
        checkBooleanOperators(element);
    }

    @Override
    public void visit(Subtract element) throws TypeCheckException {
        element.leftHandSide.accept(this);
        element.rightHandSide.accept(this);
        checkMathOperators(element);
    }

    @Override
    public void visit(BoolLiteral element) throws TypeCheckException {
        /* Do nothing */
    }

    @Override
    public void visit(CharLiteral element) throws TypeCheckException {
        /* Do nothing */
    }

    @Override
    public void visit(DoubleLiteral element) throws TypeCheckException {
        /* Do nothing */
    }

    @Override
    public void visit(IntLiteral element) throws TypeCheckException {
        /* Do nothing */
    }

    @Override
    public void visit(Not element) throws TypeCheckException {
        element.expression.accept(this);
        checkBooleanOperators(element);
    }

    public void checkMathOperators(Token element) throws TypeCheckException {
        try {
            element.checkTypes(table);
        } catch(InvalidMathematicalOperandsException exception) {
            Errors.INVALID_MATHEMATICAL_OPERANDS.log(logger, element);
            foundErrors = true;
            throw exception;
        }
    }

    public void checkBooleanOperators(Token element) throws TypeCheckException {
        try {
            element.checkTypes(table);
        } catch (InvalidBooleanOperandsException exception) {
            Errors.INVALID_BOOLEAN_OPERANDS.log(logger, element);
        }
    }

    public void checkComparisonAgreement(Token element) throws TypeCheckException {
        try {
            element.checkTypes(table);
        } catch (ComparisonOperandDisagreementException exception) {
            Errors.COMPARISON_OPERAND_DISAGREEMENT.log(logger, element);
        }
    }
}
