package com.symbol_table;

import com.ast.function.Function;
import com.ast.function.ParamDeclaration;
import com.ast.mutable.Identifier;
import com.ast.statements.VariableDeclaration;
import com.ast.types.TypeDeclaration;
import com.symbol_table.entries.Entry;
import com.symbol_table.entries.FuncEntry;

public class EntryFactory {
    public static Entry createEntry(ParamDeclaration pdl) {
        return createEntryType(pdl.id, pdl.type);
    }

    public static Entry createEntry(VariableDeclaration vdl) {
        return createEntryType(vdl.name, vdl.type);
    }

    public static Entry createEntry(Function function) {
        return new FuncEntry(function.name, function);
    }

    private static Entry createEntryType(Identifier identifier, TypeDeclaration type) {
        return type.getEntry(identifier);
    }
}
