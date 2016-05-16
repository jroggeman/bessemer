package com.ast.types;

import com.Visitor;
import com.ast.mutable.Identifier;
import com.symbol_table.entries.Entry;
import com.symbol_table.entries.IntEntry;

public class IntegerType extends Type {
    public IntegerType() {
        super(Types.INTEGER);
    }
}
