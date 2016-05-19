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
import com.ast.mutable.Mutable;
import com.ast.statements.*;
import com.ast.types.TypeDeclaration;
import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;

public abstract class GenericVisitor implements Visitor {
    private SymbolTable table;

    public GenericVisitor(SymbolTable table) {
        this.table = table;
    }

    @Override
    public void visit(Block block) throws TypeCheckException {
        beforeBlock(block);

        beforeAllStatementsInBlock(block);
        for (Statement statement : block) {
            beforeEachStatementInBlock(block, statement);
            statement.accept(this);
            afterEachStatementInBlock(block, statement);
        }
        afterAllStatementsInBlock(block);

        afterBlock(block);
    }

    public void beforeBlock(Block block) throws TypeCheckException { }
    public void afterBlock(Block block) throws TypeCheckException { }
    public void beforeAllStatementsInBlock(Block block) { }
    public void afterAllStatementsInBlock(Block block) { }
    public void beforeEachStatementInBlock(Block block, Statement statement) throws TypeCheckException { }
    public void afterEachStatementInBlock(Block block, Statement statement) throws TypeCheckException { }

    @Override
    public void visit(BinaryExpression expression) throws TypeCheckException {
        beforeBinaryExpression(expression);

        beforeBinaryExpressionLeftHandSide(expression, expression.leftHandSide);
        expression.leftHandSide.accept(this);
        afterBinaryExpressionLeftHandSide(expression, expression.leftHandSide);

        beforeBinaryExpressionRightHandSide(expression, expression.rightHandSide);
        expression.rightHandSide.accept(this);
        afterBinaryExpressionRightHandSide(expression, expression.rightHandSide);

        afterBinaryExpression(expression);
    }

    public void beforeBinaryExpression(BinaryExpression expression) { }
    public void afterBinaryExpression(BinaryExpression expression) { }
    public void beforeBinaryExpressionLeftHandSide(BinaryExpression expression, Expression leftHandSide) { }
    public void afterBinaryExpressionLeftHandSide(BinaryExpression expression, Expression leftHandSide) { }
    public void beforeBinaryExpressionRightHandSide(BinaryExpression expression, Expression rightHandSide) { }
    public void afterBinaryExpressionRightHandSide(BinaryExpression expression, Expression rightHandSide) { }

    @Override
    public void visit(Call call) throws TypeCheckException {
        beforeCall(call);

        beforeFunctionNameForCall(call, call.functionName);
        call.functionName.accept(this);
        afterFunctionNameForCall(call, call.functionName);

        beforeAllExpressionInCallParameterList(call);
        for (Expression expression : call.parameterList) {
            beforeEachExpressionInCallParameterList(call, expression);
            expression.accept(this);
            afterEachExpressionInCallParameterList(call, expression);
        }
        afterAllExpressionInCallParameterList(call);

        afterCall(call);
    }

    public void beforeCall(Call call) { }
    public void afterCall(Call call) { }
    public void beforeFunctionNameForCall(Call call, Identifier functionName) { }
    public void afterFunctionNameForCall(Call call, Identifier functionName) { }
    public void beforeAllExpressionInCallParameterList(Call call) { }
    public void afterAllExpressionInCallParameterList(Call call) { }
    public void beforeEachExpressionInCallParameterList(Call call, Expression expression) { }
    public void afterEachExpressionInCallParameterList(Call call, Expression expression) { }

    @Override
    public void visit(Literal literal) {
        beforeLiteral(literal);
        afterLiteral(literal);
    }

    public void beforeLiteral(Literal literal) { }
    public void afterLiteral(Literal literal) { }

    @Override
    public void visit(Subexpression subexpression) throws TypeCheckException {
        beforeSubexpression(subexpression);
        subexpression.expression.accept(this);
        afterSubexpression(subexpression);
    }

    public void beforeSubexpression(Subexpression subexpression) { }
    public void afterSubexpression(Subexpression subexpression) { }

    @Override
    public void visit(UnaryExpression unaryExpression) throws TypeCheckException {
        beforeUnaryExpression(unaryExpression);
        unaryExpression.expression.accept(this);
        afterUnaryExpression(unaryExpression);
    }

    public void beforeUnaryExpression(UnaryExpression unaryExpression) { }
    public void afterUnaryExpression(UnaryExpression unaryExpression) { }

    @Override
    public void visit(Function function) throws TypeCheckException {
        beforeFunction(function);

        beforeNameOfFunction(function, function.name);
        function.name.accept(this);
        afterNameOfFunction(function, function.name);

        beforeTypeOfFunction(function, function.type);
        function.type.accept(this);
        afterTypeOfFunction(function, function.type);

        table.enterScope(function);

        beforeParamListOfFunction(function, function.paramList);
        function.paramList.accept(this);
        afterParamListOfFunction(function, function.paramList);

        beforeBlockOfFunction(function, function.block);
        function.block.accept(this);
        afterBlockOfFunction(function, function.block);

        table.leaveScope();

        afterFunction(function);
    }

    public void beforeFunction(Function function) { }
    public void afterFunction(Function function) { }
    public void beforeNameOfFunction(Function function, Identifier name) { }
    public void afterNameOfFunction(Function function, Identifier name) { }
    public void beforeTypeOfFunction(Function function, TypeDeclaration type) { }
    public void afterTypeOfFunction(Function function, TypeDeclaration type) { }
    public void beforeParamListOfFunction(Function function, ParamDeclarationList parameterList) { }
    public void afterParamListOfFunction(Function function, ParamDeclarationList parameterList) { }
    public void beforeBlockOfFunction(Function function, Block block) { }
    public void afterBlockOfFunction(Function function, Block block) { }

    @Override
    public void visit(ParamDeclaration paramDeclaration) throws TypeCheckException {
        beforeParamDeclaration(paramDeclaration);

        beforeNameOfParamDeclaration(paramDeclaration, paramDeclaration.id);
        paramDeclaration.id.accept(this);
        afterNameOfParamDeclaration(paramDeclaration, paramDeclaration.id);

        beforeTypeOfParamDeclaration(paramDeclaration, paramDeclaration.type);
        paramDeclaration.type.accept(this);
        afterTypeOfParamDeclaration(paramDeclaration, paramDeclaration.type);

        afterParamDeclaration(paramDeclaration);
    }

    public void beforeParamDeclaration(ParamDeclaration paramDeclaration) { }
    public void afterParamDeclaration(ParamDeclaration paramDeclaration) { }
    public void beforeNameOfParamDeclaration(ParamDeclaration paramDeclaration, Identifier name) { }
    public void afterNameOfParamDeclaration(ParamDeclaration paramDeclaration, Identifier name) { }
    public void beforeTypeOfParamDeclaration(ParamDeclaration paramDeclaration, TypeDeclaration type) { }
    public void afterTypeOfParamDeclaration(ParamDeclaration paramDeclaration, TypeDeclaration type) { }

    @Override
    public void visit(ParamDeclarationList paramDeclarationList) throws TypeCheckException {
        beforeParamDeclarationList(paramDeclarationList);

        beforeAllParamDeclarationInList(paramDeclarationList);
        for(ParamDeclaration paramDeclaration : paramDeclarationList) {
            beforeEachParamDeclarationInList(paramDeclarationList, paramDeclaration);
            paramDeclaration.accept(this);
            afterEachParamDeclarationInList(paramDeclarationList, paramDeclaration);
        }
        afterAllParamDeclarationInList(paramDeclarationList);

        afterParamDeclarationList(paramDeclarationList);
    }

    public void beforeParamDeclarationList(ParamDeclarationList paramDeclarationList) { }
    public void afterParamDeclarationList(ParamDeclarationList paramDeclarationList) { }
    public void beforeAllParamDeclarationInList(ParamDeclarationList paramDeclarationList) { }
    public void afterAllParamDeclarationInList(ParamDeclarationList paramDeclarationList) { }
    public void beforeEachParamDeclarationInList(ParamDeclarationList paramDeclarationList, ParamDeclaration paramDeclaration) { }
    public void afterEachParamDeclarationInList(ParamDeclarationList paramDeclarationList, ParamDeclaration paramDeclaration) { }

    @Override
    public void visit(Identifier identifier) {
        beforeIdentifier(identifier);
        /* Do nothing */
        afterIdentifier(identifier);
    }

    public void beforeIdentifier(Identifier identifier) { }
    public void afterIdentifier(Identifier identifier) { }

    @Override
    public void visit(Program program) {
        beforeProgram(program);

        try {
            beforeAllFunctionsInProgram(program);
            for (Function function : program.functionList) {
                beforeEachFunctionInProgram(program, function);
                function.accept(this);
                afterEachFunctionInProgram(program, function);
            }
            afterAllFunctionsInProgram(program);
        } catch(TypeCheckException exception) {
            throw new RuntimeException("Override this, log errors and quit");
        }

        afterProgram(program);
    }

    public void beforeProgram(Program program) { }
    public void afterProgram(Program program) { }
    public void beforeAllFunctionsInProgram(Program program) { }
    public void afterAllFunctionsInProgram(Program program) { }
    public void beforeEachFunctionInProgram(Program program, Function function) { }
    public void afterEachFunctionInProgram(Program program, Function function) { }

    @Override
    public void visit(Assign assign) throws TypeCheckException {
        beforeAssign(assign);

        beforeLeftOfAssign(assign, assign.leftHandSide);
        assign.leftHandSide.accept(this);
        afterLeftOfAssign(assign, assign.leftHandSide);

        beforeRightOfAssign(assign, assign.rightHandSide);
        assign.rightHandSide.accept(this);
        afterRightOfAssign(assign, assign.rightHandSide);

        afterAssign(assign);
    }

    public void beforeAssign(Assign assign) { }
    public void afterAssign(Assign assign) { }
    public void beforeLeftOfAssign(Assign assign, Mutable mutable) { }
    public void afterLeftOfAssign(Assign assign, Mutable mutable) { }
    public void beforeRightOfAssign(Assign assign, Expression expression) { }
    public void afterRightOfAssign(Assign assign, Expression expression) { }

    @Override
    public void visit(Mutable mutable) {
        beforeMutable(mutable);
        /* Do nothing */
        afterMutable(mutable);
    }

    public void beforeMutable(Mutable mutable) { }
    public void afterMutable(Mutable mutable) { }

    @Override
    public void visit(If ifElement) throws TypeCheckException {
        beforeIf(ifElement);

        beforeConditionOfIf(ifElement, ifElement.condition);
        ifElement.condition.accept(this);
        afterConditionOfIf(ifElement, ifElement.condition);

        table.enterScope(ifElement);

        beforeBlockOfIf(ifElement, ifElement.block);
        ifElement.block.accept(this);
        afterBlockOfIf(ifElement, ifElement.block);

        table.leaveScope();

        afterIf(ifElement);
    }

    public void beforeIf(If ifElement) { }
    public void afterIf(If ifElement) { }
    public void beforeConditionOfIf(If ifElement, Expression condition) { }
    public void afterConditionOfIf(If ifElement, Expression condition) { }
    public void beforeBlockOfIf(If ifElement, Block block) { }
    public void afterBlockOfIf(If ifElement, Block block) { }

    @Override
    public void visit(Input input) throws TypeCheckException {
        beforeInput(input);
        input.variable.accept(this);
        afterInput(input);
    }

    public void beforeInput(Input input) { }
    public void afterInput(Input input) { }

    @Override
    public void visit(Output output) throws TypeCheckException {
        beforeOutput(output);
        output.expression.accept(this);
        afterOutput(output);
    }

    public void beforeOutput(Output output) { }
    public void afterOutput(Output output) { }

    @Override
    public void visit(Return returnElement) throws TypeCheckException {
        beforeReturn(returnElement);
        returnElement.expression.accept(this);
        afterReturn(returnElement);
    }

    public void beforeReturn(Return returnElement) { }
    public void afterReturn(Return returnElement) { }

    @Override
    public void visit(VariableDeclaration variableDeclaration) throws TypeCheckException {
        beforeVariableDeclaration(variableDeclaration);

        beforeTypeOfVariableDeclaration(variableDeclaration, variableDeclaration.type);
        variableDeclaration.type.accept(this);
        afterTypeOfVariableDeclaration(variableDeclaration, variableDeclaration.type);

        beforeNameOfVariableDeclaration(variableDeclaration, variableDeclaration.name);
        variableDeclaration.name.accept(this);
        afterNameOfVariableDeclaration(variableDeclaration, variableDeclaration.name);

        afterVariableDeclaration(variableDeclaration);
    }

    public void beforeVariableDeclaration(VariableDeclaration variableDeclaration) { }
    public void afterVariableDeclaration(VariableDeclaration variableDeclaration) { }
    public void beforeNameOfVariableDeclaration(VariableDeclaration variableDeclaration, Identifier name) { }
    public void afterNameOfVariableDeclaration(VariableDeclaration variableDeclaration, Identifier name) { }
    public void beforeTypeOfVariableDeclaration(VariableDeclaration variableDeclaration, TypeDeclaration typeDeclaration) { }
    public void afterTypeOfVariableDeclaration(VariableDeclaration variableDeclaration, TypeDeclaration typeDeclaration) { }

    @Override
    public void visit(While whileElement) throws TypeCheckException {
        beforeWhile(whileElement);

        beforeConditionOfWhile(whileElement, whileElement.condition);
        whileElement.condition.accept(this);
        afterConditionOfWhile(whileElement, whileElement.condition);

        table.enterScope(whileElement);

        beforeBlockOfWhile(whileElement, whileElement.block);
        whileElement.block.accept(this);
        afterBlockOfWhile(whileElement, whileElement.block);

        table.leaveScope();

        afterWhile(whileElement);
    }

    public void beforeWhile(While whileElement) { }
    public void afterWhile(While whileElement) { }
    public void beforeConditionOfWhile(While whileElement, Expression condition) { }
    public void afterConditionOfWhile(While whileElement, Expression condition) { }
    public void beforeBlockOfWhile(While whileElement, Block block) { }
    public void afterBlockOfWhile(While whileElement, Block block) { }

    @Override
    public void visit(TypeDeclaration typeDeclaration) {
        beforeTypeDeclaration(typeDeclaration);
        /* Do nothing */
        afterTypeDeclaration(typeDeclaration);
    }

    public void beforeTypeDeclaration(TypeDeclaration typeDeclaration) { }
    public void afterTypeDeclaration(TypeDeclaration typeDeclaration) { }
}
