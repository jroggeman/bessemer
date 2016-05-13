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

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(type + " " + name + "(");
        if(paramList.params.size() > 0) {
            for(int i = 0; i < paramList.params.size() - 1; i++) {
                s.append(paramList.params.get(i) + ",");
            }
            s.append(paramList.params.get(paramList.params.size() - 1));
        }
        s.append(") ");
        s.append(statementList);

        return s.toString();
    }
}
