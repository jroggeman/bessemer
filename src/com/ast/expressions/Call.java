package com.ast.expressions;

import com.ast.types.Type;
import com.exceptions.TypeCheckException;
import com.symbol_table.SymbolTable;
import com.visitors.Visitor;
import com.ast.function.Function;
import com.ast.mutable.Identifier;

import java.util.List;

public class Call extends Expression {
    public Identifier functionName;
    public List<Expression> parameterList;
    public Function associatedFunction;

    public Call(int lineNumber, int columnNumber, Identifier functionName, List<Expression> parameterList) {
        super(lineNumber, columnNumber);
        this.functionName = functionName;
        this.parameterList = parameterList;
    }

    @Override
    public String toString() {
        StringBuffer toReturn = new StringBuffer();
        toReturn.append(functionName);
        toReturn.append("(");
        if(parameterList.size() > 0) {
            for(int i = 0; i < parameterList.size() - 1; i++) {
                toReturn.append(parameterList.get(i) + ", ");
            }
            toReturn.append(parameterList.get(parameterList.size() - 1) + ")");
        } else {
            toReturn.append(");");
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
    public Type getType() {
        return associatedFunction.type.getType();
    }
}
