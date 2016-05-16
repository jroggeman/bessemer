package com.symbol_table;

import com.ast.mutable.Identifier;
import com.symbol_table.entries.Entry;
import com.symbol_table.entries.IntEntry;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.OBJ_ADAPTER;

import static org.junit.Assert.*;

public class SymbolTableTest {
    private SymbolTable table;

    @Before
    public void setup() {
        table = new SymbolTable();
    }

    @Test
    public void testInsertAndRetrieveFromRoot() {
        Identifier id = new Identifier("variable");
        Entry entry = new IntEntry(id);

        table.put(id, entry);

        Entry retrieved = table.get(id);

        assertEquals("Did not retrieve correct entry from table with associated ID", entry, retrieved);
    }

    @Test
    public void testInsertAndRetrieveAfterScopeMovement() {
        Identifier id = new Identifier("variable");
        Entry entry = new IntEntry(id);

        Object scopeId = new Object();

        table.enterScope(scopeId);
        table.put(id, entry);
        table.leaveScope();

        assertNull("Shouldn't be able to access identifier from parent scope", table.get(id));

        table.enterScope(scopeId);

        assertEquals("Did not retrieve correct entry from table with associated ID", entry, table.get(id));
    }

    @Test
    public void testCanRetrieveFromParentScope() {
        Identifier id = new Identifier("variable");
        Entry entry = new IntEntry(id);

        table.put(id, entry);

        Object scopeId = new Object();
        table.enterScope(scopeId);

        assertEquals("Did not retrieve correct entry from parent scope", entry, table.get(id));
    }

    @Test(expected=RuntimeException.class)
    public void testCannotLeaveParentScope() {
        table.leaveScope();
    }

    @Test(expected = RuntimeException.class)
    public void testCannotInsertEntryWithNullOrEmptyKey() {
        table.put(null, new IntEntry(null));
    }
}