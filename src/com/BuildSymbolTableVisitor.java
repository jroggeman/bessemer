package com;

import com.ast.Block;
import com.ast.Program;
import com.ast.expressions.Call;
import com.ast.expressions.Expression;
import com.ast.expressions.Subexpression;
import com.ast.expressions.binary.*;
import com.ast.expressions.literals.*;
import com.ast.expressions.unary.UnaryExpression;
import com.ast.function.Function;
import com.ast.function.ParamDeclaration;
import com.ast.function.ParamDeclarationList;
import com.ast.mutable.Identifier;
import com.ast.statements.*;
import com.ast.types.Type;
import com.symbol_table.EntryFactory;
import com.symbol_table.SymbolTable;
import com.symbol_table.entries.FuncEntry;
import sun.jvm.hotspot.debugger.cdbg.Sym;

public class BuildSymbolTableVisitor implements Visitor {
    private SymbolTable table;
    private Program program;

    public BuildSymbolTableVisitor(Program program) {
        this.table = new SymbolTable();
        this.program = program;
    }

    public SymbolTable getSymbolTable() {
        visit(program);
        return table;
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
        table.put(element.name, EntryFactory.createEntry(element));
        table.enterScope(element);
        element.paramList.accept(this);
        element.block.accept(this);
        table.leaveScope();
    }

    @Override
    public void visit(ParamDeclaration element) {
        table.put(element.id, EntryFactory.createEntry(element));
    }

    @Override
    public void visit(ParamDeclarationList element) {
        for (ParamDeclaration param : element.params) {
            param.accept(this);
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
        table.put(element.name, EntryFactory.createEntry(element));
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
