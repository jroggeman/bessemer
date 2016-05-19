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
    protected Program program;
    protected SymbolTable table;

    protected boolean hasErrors = false;

    public GenericVisitor(Program program, SymbolTable table) {
        this.program = program;
        this.table = table;
    }

    public boolean hasErrors() {
        visit(program);
        return hasErrors;
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
    public void beforeAllStatementsInBlock(Block block) throws TypeCheckException { }
    public void afterAllStatementsInBlock(Block block) throws TypeCheckException { }
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

    public void beforeBinaryExpression(BinaryExpression expression) throws TypeCheckException { }
    public void afterBinaryExpression(BinaryExpression expression) throws TypeCheckException { }
    public void beforeBinaryExpressionLeftHandSide(BinaryExpression expression, Expression leftHandSide) throws TypeCheckException { }
    public void afterBinaryExpressionLeftHandSide(BinaryExpression expression, Expression leftHandSide) throws TypeCheckException { }
    public void beforeBinaryExpressionRightHandSide(BinaryExpression expression, Expression rightHandSide) throws TypeCheckException { }
    public void afterBinaryExpressionRightHandSide(BinaryExpression expression, Expression rightHandSide) throws TypeCheckException { }

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

    public void beforeCall(Call call) throws TypeCheckException { }
    public void afterCall(Call call) throws TypeCheckException { }
    public void beforeFunctionNameForCall(Call call, Identifier functionName) throws TypeCheckException { }
    public void afterFunctionNameForCall(Call call, Identifier functionName) throws TypeCheckException { }
    public void beforeAllExpressionInCallParameterList(Call call) throws TypeCheckException { }
    public void afterAllExpressionInCallParameterList(Call call) throws TypeCheckException { }
    public void beforeEachExpressionInCallParameterList(Call call, Expression expression) throws TypeCheckException { }
    public void afterEachExpressionInCallParameterList(Call call, Expression expression) throws TypeCheckException { }

    @Override
    public void visit(Literal literal) throws TypeCheckException {
        beforeLiteral(literal);
        afterLiteral(literal);
    }

    public void beforeLiteral(Literal literal) throws TypeCheckException { }
    public void afterLiteral(Literal literal) throws TypeCheckException { }

    @Override
    public void visit(Subexpression subexpression) throws TypeCheckException {
        beforeSubexpression(subexpression);
        subexpression.expression.accept(this);
        afterSubexpression(subexpression);
    }

    public void beforeSubexpression(Subexpression subexpression) throws TypeCheckException { }
    public void afterSubexpression(Subexpression subexpression) throws TypeCheckException { }

    @Override
    public void visit(UnaryExpression unaryExpression) throws TypeCheckException {
        beforeUnaryExpression(unaryExpression);
        unaryExpression.expression.accept(this);
        afterUnaryExpression(unaryExpression);
    }

    public void beforeUnaryExpression(UnaryExpression unaryExpression) throws TypeCheckException { }
    public void afterUnaryExpression(UnaryExpression unaryExpression) throws TypeCheckException { }

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

    public void beforeFunction(Function function) throws TypeCheckException { }
    public void afterFunction(Function function) throws TypeCheckException { }
    public void beforeNameOfFunction(Function function, Identifier name) throws TypeCheckException { }
    public void afterNameOfFunction(Function function, Identifier name) throws TypeCheckException { }
    public void beforeTypeOfFunction(Function function, TypeDeclaration type) throws TypeCheckException { }
    public void afterTypeOfFunction(Function function, TypeDeclaration type) throws TypeCheckException { }
    public void beforeParamListOfFunction(Function function, ParamDeclarationList parameterList) throws TypeCheckException { }
    public void afterParamListOfFunction(Function function, ParamDeclarationList parameterList) throws TypeCheckException { }
    public void beforeBlockOfFunction(Function function, Block block) throws TypeCheckException { }
    public void afterBlockOfFunction(Function function, Block block) throws TypeCheckException { }

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

    public void beforeParamDeclaration(ParamDeclaration paramDeclaration) throws TypeCheckException { }
    public void afterParamDeclaration(ParamDeclaration paramDeclaration) throws TypeCheckException { }
    public void beforeNameOfParamDeclaration(ParamDeclaration paramDeclaration, Identifier name) throws TypeCheckException { }
    public void afterNameOfParamDeclaration(ParamDeclaration paramDeclaration, Identifier name) throws TypeCheckException { }
    public void beforeTypeOfParamDeclaration(ParamDeclaration paramDeclaration, TypeDeclaration type) throws TypeCheckException { }
    public void afterTypeOfParamDeclaration(ParamDeclaration paramDeclaration, TypeDeclaration type) throws TypeCheckException { }

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

    public void beforeParamDeclarationList(ParamDeclarationList paramDeclarationList) throws TypeCheckException { }
    public void afterParamDeclarationList(ParamDeclarationList paramDeclarationList) throws TypeCheckException { }
    public void beforeAllParamDeclarationInList(ParamDeclarationList paramDeclarationList) throws TypeCheckException { }
    public void afterAllParamDeclarationInList(ParamDeclarationList paramDeclarationList) throws TypeCheckException { }
    public void beforeEachParamDeclarationInList(ParamDeclarationList paramDeclarationList, ParamDeclaration paramDeclaration) throws TypeCheckException { }
    public void afterEachParamDeclarationInList(ParamDeclarationList paramDeclarationList, ParamDeclaration paramDeclaration) throws TypeCheckException { }

    @Override
    public void visit(Identifier identifier) throws TypeCheckException {
        beforeIdentifier(identifier);
        /* Do nothing */
        afterIdentifier(identifier);
    }

    public void beforeIdentifier(Identifier identifier) throws TypeCheckException { }
    public void afterIdentifier(Identifier identifier) throws TypeCheckException { }

    @Override
    public void visit(Program program) {
        try {
            beforeProgram(program);

            beforeAllFunctionsInProgram(program);
            for (Function function : program.functionList) {
                beforeEachFunctionInProgram(program, function);
                function.accept(this);
                afterEachFunctionInProgram(program, function);
            }
            afterAllFunctionsInProgram(program);

            afterProgram(program);
        } catch(TypeCheckException exception) {
            throw new RuntimeException("Override this, log errors and quit");
        }
    }

    public void beforeProgram(Program program) throws TypeCheckException { }
    public void afterProgram(Program program) throws TypeCheckException { }
    public void beforeAllFunctionsInProgram(Program program) throws TypeCheckException { }
    public void afterAllFunctionsInProgram(Program program) throws TypeCheckException { }
    public void beforeEachFunctionInProgram(Program program, Function function) throws TypeCheckException { }
    public void afterEachFunctionInProgram(Program program, Function function) throws TypeCheckException { }

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

    public void beforeAssign(Assign assign) throws TypeCheckException { }
    public void afterAssign(Assign assign) throws TypeCheckException { }
    public void beforeLeftOfAssign(Assign assign, Mutable mutable) throws TypeCheckException { }
    public void afterLeftOfAssign(Assign assign, Mutable mutable) throws TypeCheckException { }
    public void beforeRightOfAssign(Assign assign, Expression expression) throws TypeCheckException { }
    public void afterRightOfAssign(Assign assign, Expression expression) throws TypeCheckException { }

    @Override
    public void visit(Mutable mutable) throws TypeCheckException {
        beforeMutable(mutable);
        /* Do nothing */
        afterMutable(mutable);
    }

    public void beforeMutable(Mutable mutable) throws TypeCheckException { }
    public void afterMutable(Mutable mutable) throws TypeCheckException { }

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

    public void beforeIf(If ifElement) throws TypeCheckException { }
    public void afterIf(If ifElement) throws TypeCheckException { }
    public void beforeConditionOfIf(If ifElement, Expression condition) throws TypeCheckException { }
    public void afterConditionOfIf(If ifElement, Expression condition) throws TypeCheckException { }
    public void beforeBlockOfIf(If ifElement, Block block) throws TypeCheckException { }
    public void afterBlockOfIf(If ifElement, Block block) throws TypeCheckException { }

    @Override
    public void visit(Input input) throws TypeCheckException {
        beforeInput(input);
        input.variable.accept(this);
        afterInput(input);
    }

    public void beforeInput(Input input) throws TypeCheckException { }
    public void afterInput(Input input) throws TypeCheckException { }

    @Override
    public void visit(Output output) throws TypeCheckException {
        beforeOutput(output);
        output.expression.accept(this);
        afterOutput(output);
    }

    public void beforeOutput(Output output) throws TypeCheckException { }
    public void afterOutput(Output output) throws TypeCheckException { }

    @Override
    public void visit(Return returnElement) throws TypeCheckException {
        beforeReturn(returnElement);
        returnElement.expression.accept(this);
        afterReturn(returnElement);
    }

    public void beforeReturn(Return returnElement) throws TypeCheckException { }
    public void afterReturn(Return returnElement) throws TypeCheckException { }

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

    public void beforeVariableDeclaration(VariableDeclaration variableDeclaration) throws TypeCheckException { }
    public void afterVariableDeclaration(VariableDeclaration variableDeclaration) throws TypeCheckException { }
    public void beforeNameOfVariableDeclaration(VariableDeclaration variableDeclaration, Identifier name) throws TypeCheckException { }
    public void afterNameOfVariableDeclaration(VariableDeclaration variableDeclaration, Identifier name) throws TypeCheckException { }
    public void beforeTypeOfVariableDeclaration(VariableDeclaration variableDeclaration, TypeDeclaration typeDeclaration) throws TypeCheckException { }
    public void afterTypeOfVariableDeclaration(VariableDeclaration variableDeclaration, TypeDeclaration typeDeclaration) throws TypeCheckException { }

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

    public void beforeWhile(While whileElement) throws TypeCheckException { }
    public void afterWhile(While whileElement) throws TypeCheckException { }
    public void beforeConditionOfWhile(While whileElement, Expression condition) throws TypeCheckException { }
    public void afterConditionOfWhile(While whileElement, Expression condition) throws TypeCheckException { }
    public void beforeBlockOfWhile(While whileElement, Block block) throws TypeCheckException { }
    public void afterBlockOfWhile(While whileElement, Block block) throws TypeCheckException { }

    @Override
    public void visit(TypeDeclaration typeDeclaration) throws TypeCheckException {
        beforeTypeDeclaration(typeDeclaration);
        /* Do nothing */
        afterTypeDeclaration(typeDeclaration);
    }

    public void beforeTypeDeclaration(TypeDeclaration typeDeclaration) throws TypeCheckException { }
    public void afterTypeDeclaration(TypeDeclaration typeDeclaration) throws TypeCheckException { }
}
