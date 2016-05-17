package com.visitors;

import com.ast.Block;
import com.ast.Program;
import com.ast.expressions.Call;
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

public interface Visitor {
    void visit(Block element);
    void visit(BinaryExpression element);
    void visit(Call element);
    void visit(Literal element);
    void visit(Subexpression element);
    void visit(UnaryExpression element);
    void visit(Function element);
    void visit(ParamDeclaration element);
    void visit(ParamDeclarationList element);
    void visit(Identifier element);
    void visit(Program element);
    void visit(Assign element);
    void visit(Mutable element);
    void visit(If element);
    void visit(Input element);
    void visit(Output element);
    void visit(Return element);
    void visit(VariableDeclaration element);
    void visit(While element);
    void visit(TypeDeclaration element);
}
