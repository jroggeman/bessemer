package com.symbol_table;

import com.ast.mutable.Identifier;
import com.symbol_table.entries.Entry;

public class SymbolTable {
    private Scope rootScope;
    private Scope currentScope;

    public SymbolTable() {
        currentScope = new Scope();
    }

    public void put(Identifier key, Entry value) {
        currentScope.put(key, value);
    }

    public Entry get(Identifier key) {
        return currentScope.get(key);
    }

    public void enterScope(Object scopeIdentifier) {
        if(!currentScope.childScopes.containsKey(scopeIdentifier)) {
            Scope newScope = new Scope(currentScope);
            currentScope.childScopes.put(scopeIdentifier, newScope);
        }

        currentScope = currentScope.childScopes.get(scopeIdentifier);
    }

    public void leaveScope() {
        if(currentScope.parent == null) {
            throw new RuntimeException("Can't leave the parent scope");
        }

        currentScope = currentScope.parent;
    }
}
