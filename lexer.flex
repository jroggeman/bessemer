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

Identifier = [:jletter:] [:jletterdigit:]*

DecIntegerLiteral = 0 | [1-9][0-9]*
CharLiteral = "'" [a-zA-Z0-9] "'"

//%state STRING

%%

{WhiteSpace}          { /* Ignore */ }
{Comment}             { /* Ignore */ }

"*"                   { return symbol(com.sym.TIMES); }
"double"              { return symbol(com.sym.DOUBLE_TYPE); }
"char"                { return symbol(com.sym.CHARACTER_TYPE); }
";"                   { return symbol(com.sym.SEMICOLON); }
"+"                   { return symbol(com.sym.PLUS); }
")"                   { return symbol(com.sym.RPAREN); }
"%"                   { return symbol(com.sym.MODULO); }
"("                   { return symbol(com.sym.LPAREN); }
","                   { return symbol(com.sym.COMMA); }
"int"                 { return symbol(com.sym.INTEGER_TYPE); }
"/"                   { return symbol(com.sym.DIVIDE); }
"-"                   { return symbol(com.sym.MINUS); }
{Identifier}          { return symbol(com.sym.IDENTIFIER, new Identifier(yytext())); }
{DecIntegerLiteral}   { return symbol(com.sym.INTEGER_LITERAL, Integer.parseInt(yytext())); }
{CharLiteral}         { return symbol(com.sym.CHARACTER_LITERAL, yytext().charAt(0)); }

[^] { throw new Error("Illegal character <" + yytext() + ">"); }
