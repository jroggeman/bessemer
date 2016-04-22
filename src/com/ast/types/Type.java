package com.ast.types;

import com.ast.Token;

public abstract class Type extends Token {
    public static Type INT = new IntegerType();
}
