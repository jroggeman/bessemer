package com.visitors;

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
import com.symbol_table.EntryFactory;
import com.symbol_table.SymbolTable;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BuildSymbolTableVisitor implements Visitor {
    private static final Logger logger = Logger.getLogger(BuildSymbolTableVisitor.class.getCanonicalName());

    private SymbolTable table;
    private Program program;

    public BuildSymbolTableVisitor(Program program) {
        logger.setLevel(Level.FINER);
        this.table = new SymbolTable();
        this.program = program;
    }

    public SymbolTable getSymbolTable() {
        logger.log(Level.FINE, "Beginning symbol table construction");
        visit(program);
        return table;
    }

    @Override
    public void visit(Block element) throws TypeCheckException {
        logger.log(Level.FINER, "Visiting {0} {1}", new Object[] {element.getClass().getName(), element.hashCode()});
        for (Statement statement : element) {
            statement.accept(this);
        }
    }

    @Override
    public void visit(BinaryExpression element) throws TypeCheckException {
        logger.log(Level.FINER, "Visiting {0} {1}", new Object[] {element.getClass().getName(), element.hashCode()});
        element.leftHandSide.accept(this);
        element.rightHandSide.accept(this);
    }

    @Override
    public void visit(Call element) throws TypeCheckException {
        logger.log(Level.FINER, "Visiting {0} {1}", new Object[] {element.getClass().getName(), element.hashCode()});
        element.functionName.accept(this);
        for (Expression expression : element.parameterList) {
            expression.accept(this);
        }
    }

    @Override
    public void visit(Literal element) {
        logger.log(Level.FINER, "Visiting {0} {1}", new Object[] {element.getClass().getName(), element.hashCode()});
        /* Do nothing */
    }

    @Override
    public void visit(Subexpression element) throws TypeCheckException {
        logger.log(Level.FINER, "Visiting {0} {1}", new Object[] {element.getClass().getName(), element.hashCode()});
        element.expression.accept(this);
    }

    @Override
    public void visit(UnaryExpression element) throws TypeCheckException {
        logger.log(Level.FINER, "Visiting {0} {1}", new Object[] {element.getClass().getName(), element.hashCode()});
        element.expression.accept(this);
    }

    @Override
    public void visit(Function element) throws TypeCheckException {
        logger.log(Level.FINER, "Visiting {0} {1}", new Object[] {element.getClass().getName(), element.hashCode()});
        table.put(element.name, EntryFactory.createEntry(element));
        table.enterScope(element);
        element.paramList.accept(this);
        element.block.accept(this);
        table.leaveScope();
    }

    @Override
    public void visit(ParamDeclaration element) {
        logger.log(Level.FINER, "Visiting {0} {1}", new Object[] {element.getClass().getName(), element.hashCode()});
        table.put(element.id, EntryFactory.createEntry(element));
    }

    @Override
    public void visit(ParamDeclarationList element) throws TypeCheckException {
        logger.log(Level.FINER, "Visiting {0} {1}", new Object[] {element.getClass().getName(), element.hashCode()});
        for (ParamDeclaration param : element.params) {
            param.accept(this);
        }
    }

    @Override
    public void visit(Identifier element) {
        logger.log(Level.FINER, "Visiting {0} {1}", new Object[] {element.getClass().getName(), element.hashCode()});
        /* Do nothing */
    }

    @Override
    public void visit(Program element) {
        try {
            logger.log(Level.FINER, "Visiting {0} {1}", new Object[]{element.getClass().getName(), element.hashCode()});
            for (Function function : element.functionList) {
                function.accept(this);
            }
        } catch(TypeCheckException exception) {
            throw new RuntimeException("Somehow got TypeCheckException in SymbolTableVisitor");
        }
    }

    @Override
    public void visit(Assign element) throws TypeCheckException {
        logger.log(Level.FINER, "Visiting {0} {1}", new Object[] {element.getClass().getName(), element.hashCode()});
        element.leftHandSide.accept(this);
        element.rightHandSide.accept(this);
    }

    @Override
    public void visit(Mutable element) {
        /* Do nothing */
    }

    @Override
    public void visit(If element) throws TypeCheckException {
        logger.log(Level.FINER, "Visiting {0} {1}", new Object[] {element.getClass().getName(), element.hashCode()});
        element.condition.accept(this);
        table.enterScope(element);
        element.block.accept(this);
        table.leaveScope();
    }

    @Override
    public void visit(Input element) throws TypeCheckException {
        logger.log(Level.FINER, "Visiting {0} {1}", new Object[] {element.getClass().getName(), element.hashCode()});
        element.variable.accept(this);
    }

    @Override
    public void visit(Output element) throws TypeCheckException {
        logger.log(Level.FINER, "Visiting {0} {1}", new Object[] {element.getClass().getName(), element.hashCode()});
        element.expression.accept(this);
    }

    @Override
    public void visit(Return element) throws TypeCheckException {
        logger.log(Level.FINER, "Visiting {0} {1}", new Object[] {element.getClass().getName(), element.hashCode()});
        element.expression.accept(this);
    }

    @Override
    public void visit(VariableDeclaration element) {
        logger.log(Level.FINER, "Visiting {0} {1}", new Object[] {element.getClass().getName(), element.hashCode()});
        table.put(element.name, EntryFactory.createEntry(element));
    }

    @Override
    public void visit(While element) throws TypeCheckException {
        logger.log(Level.FINER, "Visiting {0} {1}", new Object[] {element.getClass().getName(), element.hashCode()});
        element.condition.accept(this);
        table.enterScope(element);
        element.block.accept(this);
        table.leaveScope();
    }

    @Override
    public void visit(TypeDeclaration element) {
        logger.log(Level.FINER, "Visiting {0} {1}", new Object[] {element.getClass().getName(), element.hashCode()});
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
