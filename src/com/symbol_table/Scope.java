package com.symbol_table;

import com.ast.mutable.Identifier;
import com.symbol_table.entries.Entry;

import java.util.HashMap;
import java.util.Map;

public class Scope {
    protected Scope parent;
    protected Map<Object, Scope> childScopes;
    private Map<Identifier, Entry> entries;

    public Scope() {
        this.entries = new HashMap<>();
        this.childScopes = new HashMap<>();
    }

    public Scope(Scope parent) {
        this();
        this.parent = parent;
    }

    /**
     * Attempts to get a particular entry of the symbol table.  Looks up the
     * scope tree if necessary.  Returns null if key isn't found anywhere.
     * @param Entry to search for
     * @return The Entry associated with the Key, or null if key isn't found.
     */
    protected Entry get(Identifier key) {
        if(entries.containsKey(key)) {
            return entries.get(key);
        }

        if(parent != null) {
            return parent.get(key);
        }

        return null;
    }

    /**
     * Inserts a new entry into the symbol table
     * @param key The Identifier of the new entry
     * @param value The full Entry of the new entry (yeah, sorry about wording here)
     * @return The value from above
     */
    protected Entry put(Identifier key, Entry value) {
        entries.put(key, value);
        return value;
    }

    public String toString() {
        StringBuffer toReturn = new StringBuffer();
        for (Identifier identifier : entries.keySet()) {
            toReturn.append(" - " + identifier + " --> " + entries.get(identifier) + "\n");
        }

        for (Object o : childScopes.keySet()) {
            toReturn.append("\n\n\n" + o.getClass() + " scope:\n-------------------\n\n" + childScopes.get(o));
        }

        return toReturn.toString();
    }
}
