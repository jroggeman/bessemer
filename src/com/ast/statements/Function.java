package com.ast.statements;

import com.ast.mutable.Identifier;
import com.ast.mutable.VariableDeclaration;
import com.ast.types.Type;

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

}
