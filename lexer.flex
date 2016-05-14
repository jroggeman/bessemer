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

    "*"                   { return symbol(Symbols.TIMES); }
    "double"              { return symbol(Symbols.DOUBLE_TYPE); }
    "char"                { return symbol(Symbols.CHARACTER_TYPE); }
    "if"                  { return symbol(Symbols.IF); }
    "while"               { return symbol(Symbols.WHILE); }
    "return"              { return symbol(Symbols.RETURN); }
    "input"               { return symbol(Symbols.INPUT); }
    "output"              { return symbol(Symbols.OUTPUT); }
    ";"                   { return symbol(Symbols.SEMICOLON); }
    "+"                   { return symbol(Symbols.PLUS); }
    ")"                   { return symbol(Symbols.RPAREN); }
    "%"                   { return symbol(Symbols.MODULO); }
    "("                   { return symbol(Symbols.LPAREN); }
    ","                   { return symbol(Symbols.COMMA); }
    "int"                 { return symbol(Symbols.INTEGER_TYPE); }
    "/"                   { return symbol(Symbols.DIVIDE); }
    "-"                   { return symbol(Symbols.MINUS); }
    "{"                   { return symbol(Symbols.LBRACE); }
    "}"                   { return symbol(Symbols.RBRACE); }
    "="                   { return symbol(Symbols.EQUALS); }
    "&&"                  { return symbol(Symbols.LOG_AND); }
    "||"                  { return symbol(Symbols.LOG_OR); }
    "!"                   { return symbol(Symbols.LOG_NOT); }
    "=="                  { return symbol(Symbols.EQUALS); }
    "<"                   { return symbol(Symbols.LESS_THAN); }
    "<="                  { return symbol(Symbols.LESS_THAN_EQUALS); }
    "!="                  { return symbol(Symbols.NOT_EQUALS); }
    {Identifier}          { return symbol(Symbols.IDENTIFIER, new Identifier(yytext())); }
    {DecIntegerLiteral}   { return symbol(Symbols.INTEGER_LITERAL, Integer.parseInt(yytext())); }
    {DoubleLiteral}       { return symbol(Symbols.DOUBLE_LITERAL, Double.parseDouble(yytext())); }
    {CharLiteral}         { return symbol(Symbols.CHARACTER_LITERAL, yytext().charAt(0)); }
}

[^] { throw new Error("Illegal character <" + yytext() + ">"); }
