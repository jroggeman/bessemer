package com;

import java_cup.runtime.*;
import com.ast.mutable.Identifier;

%%

%class Lexer
%unicode
%cup
%line
%column

%{
    StringBuffer string = new StringBuffer();

    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
    }
%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace = {LineTerminator} | [ \t\f]

Comment = {EndOfLineComment}

EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?

Identifier = [a-zA-Z][a-zA-Z0-9]*

DecIntegerLiteral = 0 | [1-9][0-9]*
CharLiteral = "'" [a-zA-Z0-9] "'"

//%state STRING

%%
<YYINITIAL> {
    {WhiteSpace}          { /* Ignore */ }
    {Comment}             { /* Ignore */ }

    "*"                   { return symbol(sym.TIMES); }
    "double"              { return symbol(sym.DOUBLE_TYPE); }
    "char"                { return symbol(sym.CHARACTER_TYPE); }
    ";"                   { return symbol(sym.SEMICOLON); }
    "+"                   { return symbol(sym.PLUS); }
    ")"                   { return symbol(sym.RPAREN); }
    "%"                   { return symbol(sym.MODULO); }
    "("                   { return symbol(sym.LPAREN); }
    ","                   { return symbol(sym.COMMA); }
    "int"                 { return symbol(sym.INTEGER_TYPE); }
    "/"                   { return symbol(sym.DIVIDE); }
    "-"                   { return symbol(sym.MINUS); }
    "{"                   { return symbol(sym.LBRACE); }
    "}"                   { return symbol(sym.RBRACE); }
    {Identifier}          { return symbol(sym.IDENTIFIER, new Identifier(yytext())); }
    {DecIntegerLiteral}   { return symbol(sym.INTEGER_LITERAL, Integer.parseInt(yytext())); }
    {CharLiteral}         { return symbol(sym.CHARACTER_LITERAL, yytext().charAt(0)); }
}

[^] { throw new Error("Illegal character <" + yytext() + ">"); }
