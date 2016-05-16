package com.ast.types;

import com.ast.Token;

public interface Type extends Token {
    boolean isNumeric();
    boolean isBoolean();
}
