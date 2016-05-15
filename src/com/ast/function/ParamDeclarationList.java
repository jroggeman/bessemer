package com.ast.function;

import com.Visitor;
import com.ast.Token;

import java.util.List;

public class ParamDeclarationList implements Token {
    public List<ParamDeclaration> params;

    public ParamDeclarationList(List<ParamDeclaration> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        StringBuffer toReturn = new StringBuffer();
        if(params.size() > 0) {
            for(int i = 0; i < params.size() - 1; i++) {
                toReturn.append(params.get(i) + ",");
            }
            toReturn.append(params.get(params.size() - 1));
        }

        return toReturn.toString();
    }

    public void accept(Visitor visitor) {
        for (ParamDeclaration param : params) {
            param.accept(visitor);
        }
    }
}
