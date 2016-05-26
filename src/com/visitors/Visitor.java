package com.visitors;

import com.ast.Block;
import com.ast.Program;
import com.ast.expressions.Call;
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

public interface Visitor {
    boolean foundErrors();

    void visit(Block element) throws TypeCheckException;
    void visit(BinaryExpression element) throws TypeCheckException;
    void visit(Call element) throws TypeCheckException;
    void visit(Literal element) throws TypeCheckException;
    void visit(Subexpression element) throws TypeCheckException;
    void visit(UnaryExpression element) throws TypeCheckException;
    void visit(Function element) throws TypeCheckException;
    void visit(ParamDeclaration element) throws TypeCheckException;
    void visit(ParamDeclarationList element) throws TypeCheckException;
    void visit(Identifier element) throws TypeCheckException;
    void visit(Program element);
    void visit(Assign element) throws TypeCheckException;
    void visit(Mutable element) throws TypeCheckException;
    void visit(If element) throws TypeCheckException;
    void visit(Input element) throws TypeCheckException;
    void visit(Output element) throws TypeCheckException;
    void visit(Return element) throws TypeCheckException;
    void visit(VariableDeclaration element) throws TypeCheckException;
    void visit(While element) throws TypeCheckException;
    void visit(TypeDeclaration element) throws TypeCheckException;

    /* Binary elements */
    void visit(Add element) throws TypeCheckException;
    void visit(And element) throws TypeCheckException;
    void visit(Divide element) throws TypeCheckException;
    void visit(Equals element) throws TypeCheckException;
    void visit(LessThan element) throws TypeCheckException;
    void visit(LessThanOrEquals element) throws TypeCheckException;
    void visit(Modulo element) throws TypeCheckException;
    void visit(Multiply element) throws TypeCheckException;
    void visit(NotEquals element) throws TypeCheckException;
    void visit(Or element) throws TypeCheckException;
    void visit(Subtract element) throws TypeCheckException;

    /* Literal Expressions */
    void visit(BoolLiteral element) throws TypeCheckException;
    void visit(CharLiteral element) throws TypeCheckException;
    void visit(DoubleLiteral element) throws TypeCheckException;
    void visit(IntLiteral element) throws TypeCheckException;

    /* Unary Expressions */
    void visit(Not element) throws TypeCheckException;
}
