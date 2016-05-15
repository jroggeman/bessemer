package com.ast.function;

import java.util.List;

public class ParamDeclarationList {
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
}
