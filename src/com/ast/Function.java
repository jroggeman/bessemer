package com.ast;

import com.ast.mutable.Identifier;
import com.ast.statements.Statement;
import com.ast.types.Type;

import java.util.List;

public class Function {
    Type type;
    Identifier name;
    ParamDeclarationList paramList;
    List<Statement> statementList;

    public Function(Type type, Identifier name, ParamDeclarationList paramList, List<Statement> statementList) {
        this.type = type;
        this.name = name;
        this.paramList = paramList;
        this.statementList = statementList;
    }
}
