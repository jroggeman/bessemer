package com;

import com.mutable.Identifier;
import com.mutable.VariableDeclaration;
import com.statements.Statement;
import com.types.Type;

import java.util.List;

public class Function extends Statement {
    public Type type;
    public Identifier name;
    public List<VariableDeclaration> parameters;
    public Block block;

    public Function(Type type, Identifier name, List<VariableDeclaration> parameters, Block block) {
        this.type = type;
        this.name = name;
        this.parameters = parameters;
        this.block = block;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(type + " " + name + "(");
        if(parameters.size() > 0) {
            for(int i = 0; i < parameters.size() - 1; i++) {
                s.append(parameters.get(i) + ",");
            }
            s.append(parameters.get(parameters.size() - 1));
        }
        s.append(") ");
        s.append(block);

        return s.toString();
    }
}
