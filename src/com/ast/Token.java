package com.ast;

import com.visitors.Visitor;

/*



T    O    D    O

Convert all interfaces into abstract classes
Include row and column in token
Enforce row and column constructor through Token
Update parser



 */
public abstract class Token {
    public abstract void accept(Visitor visitor);
}
