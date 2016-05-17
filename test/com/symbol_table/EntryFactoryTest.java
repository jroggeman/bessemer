package com.symbol_table;

import com.ast.Block;
import com.ast.function.Function;
import com.ast.function.ParamDeclarationList;
import com.ast.mutable.Identifier;
import com.ast.statements.VariableDeclaration;
import com.ast.types.Type;
import com.ast.types.TypeDeclaration;
import com.symbol_table.entries.CharEntry;
import com.symbol_table.entries.Entry;
import com.symbol_table.entries.FuncEntry;
import com.symbol_table.entries.IntEntry;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EntryFactoryTest {
    @Test
    public void testCreateIntEntry() {
        TypeDeclaration type = new TypeDeclaration(0, 0, Type.INTEGER);
        Identifier id = new Identifier(0, 0, "variable");
        VariableDeclaration vd = new VariableDeclaration(0, 0, type, id);

        Entry entry = EntryFactory.createEntry(vd);

        assertTrue("Didn't return correct entry type for integer", entry instanceof IntEntry);
    }

    @Test
    public void testCreateCharEntry() {
        TypeDeclaration type = new TypeDeclaration(0, 0, Type.CHARACTER);
        Identifier id = new Identifier(0, 0, "variable");
        VariableDeclaration vd = new VariableDeclaration(0, 0, type, id);

        Entry entry = EntryFactory.createEntry(vd);

        assertTrue("Didn't return correct entry type for character", entry instanceof CharEntry);
    }

    @Test
    public void testCreateFuncEntry() {
        TypeDeclaration type = new TypeDeclaration(0, 0, Type.INTEGER);
        Identifier name = new Identifier(0, 0, "function");
        ParamDeclarationList pdl = new ParamDeclarationList(0, 0, new ArrayList<>());
        Block block = new Block(0, 0, new ArrayList<>());

        Function function = new Function(0, 0, type, name, pdl, block);

        Entry entry = EntryFactory.createEntry(function);

        assertTrue("Didn't return correct entry type for function", entry instanceof FuncEntry);
    }
}