package com.ast;

import com.ast.mutable.Identifier;
import com.ast.statements.Statement;
import com.ast.types.Type;

public class Function {
    Type type;
    Identifier name;
    ParamDeclarationList paramList;
    Block block;

    public Function(Type type, Identifier name, ParamDeclarationList paramList, Block block) {
        this.type = type;
        this.name = name;
        this.paramList = paramList;
        this.block = block;
    }

    @Override
    public String toString() {
        StringBuffer toReturn = new StringBuffer();
        toReturn.append(type + " " + name + "(");
        if(paramList.params.size() > 0) {
            for(int i = 0; i < paramList.params.size() - 1; i++) {
                toReturn.append(paramList.params.get(i) + ",");
            }
            toReturn.append(paramList.params.get(paramList.params.size() - 1));
        }

        toReturn.append(") {\n");
        for(Statement statement : block) {
            for(String line : statement.toString().split("\n")) {
                toReturn.append("\t" + line + "\n");
            }
        }

        toReturn.append("}\n\n");

        return toReturn.toString();
    }
}
