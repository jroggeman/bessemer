package com.ast.types;

import com.Visitor;

public class CharacterType implements Type {
    public CharacterType() {}

    public String toString() {
        return "char";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
