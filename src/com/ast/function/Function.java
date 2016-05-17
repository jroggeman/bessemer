package com.ast.function;

import com.visitors.Visitor;
import com.ast.Block;
import com.ast.Token;
import com.ast.mutable.Identifier;
import com.ast.statements.Statement;
import com.ast.types.TypeDeclaration;

public class Function extends Token {
    public TypeDeclaration type;
    public Identifier name;
    public ParamDeclarationList paramList;
    public Block block;

    public Function(int lineNumber, int columnNumber, TypeDeclaration type, Identifier name, ParamDeclarationList paramList, Block block) {
        super(lineNumber, columnNumber);
        this.type = type;
        this.name = name;
        this.paramList = paramList;
        this.block = block;
    }

    @Override
    public String toString() {
        StringBuffer toReturn = new StringBuffer();
        toReturn.append(type + " " + name + "(");
        toReturn.append(paramList);
        toReturn.append(") {\n");
        for(Statement statement : block) {
            for(String line : statement.toString().split("\n")) {
                toReturn.append("\t" + line + "\n");
            }
        }

        toReturn.append("}\n\n");

        return toReturn.toString();
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
