package com.symbol_table;

import com.ast.mutable.Identifier;
import com.symbol_table.entries.Entry;

public class SymbolTable {
    private Scope rootScope;
    private Scope currentScope;

    public SymbolTable() {
        currentScope = new Scope();
        rootScope = currentScope;
    }

    /**
     * Adds a new entry to the symbol table in the current scope
     * @param key The Identifier of the entry to add
     * @param value The actual Entry object to add
     */
    public void put(Identifier key, Entry value) {
        if(key == null || value == null) {
            throw new RuntimeException("Cannot insert null values into symbol table: [" + key + ", " + value + "]");
        }
        currentScope.put(key, value);
    }

    /**
     * Retrieves an entry from the symbol table.  We start by looking in the current scope,
     * and then cascade up the parent scopes.
     * @param key The Identifier that we're looking for.
     * @return The associated Entry object.
     */
    public Entry get(Identifier key) {
        return currentScope.get(key);
    }

    /**
     * Enter's a new scope identified by scopeIdentifier.
     * @param scopeIdentifier The object representing the parent of the entire scope.  For example, for a function,
     *                        this would be the Function object, but for an If block, this would be the If object.
     */
    public void enterScope(Object scopeIdentifier) {
        if(!currentScope.childScopes.containsKey(scopeIdentifier)) {
            Scope newScope = new Scope(currentScope);
            currentScope.childScopes.put(scopeIdentifier, newScope);
        }

        currentScope = currentScope.childScopes.get(scopeIdentifier);
    }

    /**
     * Leave the current scope, going up one level.
     * @throws RuntimeException if we're already in the topmost scope.
     */
    public void leaveScope() {
        if(currentScope.parent == null) {
            throw new RuntimeException("Can't leave the parent scope");
        }

        currentScope = currentScope.parent;
    }

    public String toString() {
        return "Root scope:\n-------------------------\n\n" + rootScope.toString();
    }
}
