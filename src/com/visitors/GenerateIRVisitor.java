package com.visitors;

import com.ast.Block;
import com.ast.Program;
import com.ast.expressions.Call;
import com.ast.expressions.Subexpression;
import com.ast.expressions.binary.*;
import com.ast.expressions.binary.Add;
import com.ast.expressions.literals.*;
import com.ast.expressions.literals.IntLiteral;
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
import com.iloc.*;
import com.iloc.registers.SymbolRegister;
import com.symbol_table.SymbolTable;

import java.util.List;

public class GenerateIRVisitor implements Visitor {
    private Program program;
    private SymbolTable symbolTable;

    private List<Instruction> instructions;

    public GenerateIRVisitor(Program program, SymbolTable symbolTable) {
        this.program = program;
        this.symbolTable = symbolTable;
    }

    @Override
    public boolean foundErrors() {
        return false;
    }

    @Override
    public void visit(Block element) throws TypeCheckException {
        for(Statement statement : element) {
            element.accept(this);
        }
    }

    @Override
    public void visit(BinaryExpression element) throws TypeCheckException {
        throw new UnsupportedOperationException("Parsing a binary operator that isn't explicitly handled");
    }

    @Override
    public void visit(Call element) throws TypeCheckException {

    }

    @Override
    public void visit(Literal element) throws TypeCheckException {

    }

    @Override
    public void visit(Subexpression element) throws TypeCheckException {

    }

    @Override
    public void visit(UnaryExpression element) throws TypeCheckException {

    }

    @Override
    public void visit(Function element) throws TypeCheckException {
//        element.type.accept(this);
//        element.name.accept(this);
//        element.paramList.accept(this);
        element.block.accept(this);
    }

    @Override
    public void visit(ParamDeclaration element) throws TypeCheckException {

    }

    @Override
    public void visit(ParamDeclarationList element) throws TypeCheckException {

    }

    @Override
    public void visit(Identifier element) throws TypeCheckException {

    }

    @Override
    public void visit(Program element) {
        try {
            for (Function function : element.functionList) {
                function.accept(this);
            }
        } catch (TypeCheckException e) {
            e.printStackTrace();
            throw new RuntimeException("Unhandled exception at program level");
        }
    }

    @Override
    public void visit(Assign element) throws TypeCheckException {

    }

    @Override
    public void visit(Mutable element) throws TypeCheckException {

    }

    @Override
    public void visit(If element) throws TypeCheckException {

    }

    @Override
    public void visit(Input element) throws TypeCheckException {

    }

    @Override
    public void visit(Output element) throws TypeCheckException {

    }

    @Override
    public void visit(Return element) throws TypeCheckException {

    }

    @Override
    public void visit(VariableDeclaration element) throws TypeCheckException {
        com.iloc.IntLiteral lit = new com.iloc.IntLiteral(0);
        Instruction instruction = new LoadImmediate(lit, new SymbolRegister(symbolTable.get(element.name)));
        instructions.add(instruction);
    }

    @Override
    public void visit(While element) throws TypeCheckException {

    }

    @Override
    public void visit(TypeDeclaration element) throws TypeCheckException {

    }

    @Override
    public void visit(Add element) throws TypeCheckException {

    }

    @Override
    public void visit(And element) throws TypeCheckException {

    }

    @Override
    public void visit(Divide element) throws TypeCheckException {

    }

    @Override
    public void visit(Equals element) throws TypeCheckException {

    }

    @Override
    public void visit(LessThan element) throws TypeCheckException {

    }

    @Override
    public void visit(LessThanOrEquals element) throws TypeCheckException {

    }

    @Override
    public void visit(Modulo element) throws TypeCheckException {

    }

    @Override
    public void visit(Multiply element) throws TypeCheckException {

    }

    @Override
    public void visit(NotEquals element) throws TypeCheckException {

    }

    @Override
    public void visit(Or element) throws TypeCheckException {

    }

    @Override
    public void visit(Subtract element) throws TypeCheckException {

    }

    @Override
    public void visit(BoolLiteral element) throws TypeCheckException {

    }

    @Override
    public void visit(CharLiteral element) throws TypeCheckException {

    }

    @Override
    public void visit(DoubleLiteral element) throws TypeCheckException {

    }

    @Override
    public void visit(IntLiteral element) throws TypeCheckException {

    }

    @Override
    public void visit(Not element) throws TypeCheckException {

    }
}
