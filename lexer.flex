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
DoubleLiteral = (0|[1-9][0-9]*)\.[0-9]+
CharLiteral = "'" [a-zA-Z0-9] "'"

//%state STRING

%%
<YYINITIAL> {
    {WhiteSpace}          { /* Ignore */ }
    {Comment}             { /* Ignore */ }

    "*"                   { return symbol(sym.TIMES); }
    "double"              { return symbol(sym.DOUBLE_TYPE); }
    "char"                { return symbol(sym.CHARACTER_TYPE); }
    "if"                  { return symbol(sym.IF); }
    "while"               { return symbol(sym.WHILE); }
    "return"              { return symbol(sym.RETURN); }
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
    "="                   { return symbol(sym.EQUALS); }
    "&&"                  { return symbol(sym.LOG_AND); }
    "||"                  { return symbol(sym.LOG_OR); }
    "!"                   { return symbol(sym.LOG_NOT); }
    "=="                  { return symbol(sym.EQUALS); }
    "<"                   { return symbol(sym.LESS_THAN); }
    "<="                  { return symbol(sym.LESS_THAN_EQUALS); }
    "!="                  { return symbol(sym.NOT_EQUALS); }
    {Identifier}          { return symbol(sym.IDENTIFIER, new Identifier(yytext())); }
    {DecIntegerLiteral}   { return symbol(sym.INTEGER_LITERAL, Integer.parseInt(yytext())); }
    {DoubleLiteral}       { return symbol(sym.DOUBLE_LITERAL, Double.parseDouble(yytext())); }
    {CharLiteral}         { return symbol(sym.CHARACTER_LITERAL, yytext().charAt(0)); }
}

[^] { throw new Error("Illegal character <" + yytext() + ">"); }
