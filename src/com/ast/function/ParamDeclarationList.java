package com.ast.function;

import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;
import com.visitors.Visitor;
import com.ast.Token;

import java.util.Iterator;
import java.util.List;

public class ParamDeclarationList extends Token implements Iterable<ParamDeclaration> {
    public List<ParamDeclaration> params;

    public ParamDeclarationList(int lineNumber, int columnNumber, List<ParamDeclaration> params) {
        super(lineNumber, columnNumber);
        this.params = params;
    }

    @Override
    public String toString() {
        StringBuffer toReturn = new StringBuffer();
        if(params.size() > 0) {
            for(int i = 0; i < params.size() - 1; i++) {
                toReturn.append(params.get(i) + ", ");
            }
            toReturn.append(params.get(params.size() - 1));
        }

        return toReturn.toString();
    }

    public void accept(Visitor visitor) throws TypeCheckException {
        visitor.visit(this);
    }

    @Override
    public void checkTypes(SymbolTable table) throws TypeCheckException {

    }

    @Override
    public Iterator<ParamDeclaration> iterator() {
        return params.iterator();
    }
}
