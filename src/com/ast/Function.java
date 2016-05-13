package com.ast;

import com.ast.mutable.Identifier;
import com.ast.types.Type;

public class Function {
    Type type;
    Identifier name;
    ParamDeclarationList paramList;
    StatementList statementList;

    public Function(Type type, Identifier name, ParamDeclarationList paramList, StatementList statementList) {
        this.type = type;
        this.name = name;
        this.paramList = paramList;
        this.statementList = statementList;
    }
}
