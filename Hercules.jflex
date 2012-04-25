/*
 *  Beinagrind fyrir lesgreini.
 *
 *  Höfundur: Snorri Agnarsson
 * 			  Ívar Haukur Sævarsson
 *
 *  Athugið að hér er verið að nota Java en ekki C eða C++.
 *  Þessa skrá má þýða og keyra með eftirfarandi skipunum:
 *     java -jar JFlex.jar morpho.jflex
 *     javac MyLexer.java
 *     java MyLexer < inntak
 */

%%

%public
%class HerculesLexer
%unicode
%byaccj
%line
%column
%x COMMENT

%{
int nesting = 0;

public HerculesParser yyHerculesParser;

public HerculesLexer( java.io.Reader r, HerculesParser yyHerculesParser )
{
	this(r);
	this.yyHerculesParser = yyHerculesParser;
}

public int getLine() { return yyline; }
public int getColumn() { return yycolumn; }
%}

  /* Reglulegar skilgreiningar */
  /* Regular definitions */

DIGIT =		[0-9]
FLOAT =		{DIGIT}+\.{DIGIT}+([eE][+-]?{DIGIT}+)?
INT = 		{DIGIT}+
OPCHAR =	[\+\-*/!%&=><\:\^\~&|?]
/* OP = 		{OPCHAR}+ */

BINOP7  =  [*/%]{OPCHAR}*
BINOP6  =  [+-]{OPCHAR}*
BINOP5  =  [<>!=]{OPCHAR}*
BINOP4  =  &{OPCHAR}*
BINOP3  =  \|{OPCHAR}*
BINOP2  =  :{OPCHAR}*
BINOP1  =  [~\^\?]{OPCHAR}*

DELIM =		[()\[\]{},;.$#\!=]
ALPHA =		[:letter:]
NAME = 		(_|{ALPHA})(_|{ALPHA}|{DIGIT})*

STRING=\"([^\"\\]|\\b|\\t|\\n|\\f|\\r|\\\"|\\\'|\\\\|(\\[0-3][0-7][0-7])|\\[0-7][0-7]|\\[0-7])*\"
CHAR=\'([^\'\\]|\\b|\\t|\\n|\\f|\\r|\\\"|\\\'|\\\\|(\\[0-3][0-7][0-7])|(\\[0-7][0-7])|(\\[0-7]))\'

NEWLINE	=	\r|\n|\r\n
WHITESPACE = [\n\r\ \t\b\012]

%%

{FLOAT} {
	yyHerculesParser.yylval = new HerculesParserVal(yytext());
	return HerculesParser.DOUBLE;
}

"==" {
	return HerculesParser.EQUALS;
}

"||" {
	return HerculesParser.OR;
}

"&&" {
	return HerculesParser.AND;
}

"!" {
	return HerculesParser.NOT;
}

{DELIM} {
	yyHerculesParser.yylval = new HerculesParserVal(yytext());
	return yycharat(0);
}

{BINOP7} {
	yyHerculesParser.yylval = new HerculesParserVal(yytext());
	return HerculesParser.BINOP7;
}

{BINOP6} {
	yyHerculesParser.yylval = new HerculesParserVal(yytext());
	return HerculesParser.BINOP6;
}

{BINOP5} {
	yyHerculesParser.yylval = new HerculesParserVal(yytext());
	return HerculesParser.BINOP5;
}

{BINOP4} {
	yyHerculesParser.yylval = new HerculesParserVal(yytext());
	return HerculesParser.BINOP4;
}

{BINOP3} {
	yyHerculesParser.yylval = new HerculesParserVal(yytext());
	return HerculesParser.BINOP3;
}

{BINOP2} {
	yyHerculesParser.yylval = new HerculesParserVal(yytext());
	return HerculesParser.BINOP2;
}

{BINOP1} {
	yyHerculesParser.yylval = new HerculesParserVal(yytext());
	return HerculesParser.BINOP1;
}

{STRING} {
	yyHerculesParser.yylval = new HerculesParserVal(yytext());
	return HerculesParser.LITERAL;
}

{CHAR} {
	yyHerculesParser.yylval = new HerculesParserVal(yytext());
	return HerculesParser.LITERAL;
}

{INT} {
	yyHerculesParser.yylval = new HerculesParserVal(yytext());
	return HerculesParser.LITERAL;
}

"null" {
	return HerculesParser.LITERAL;
}

"true" {
	return HerculesParser.LITERAL;
}

"false" {
	return HerculesParser.LITERAL;
}

"if" {
	return HerculesParser.IF;
}

"else" {
	return HerculesParser.ELSE;
}

"elseif" {
	return HerculesParser.ELSEIF;
}

"while" {
	return HerculesParser.WHILE;
}

"var" {
	return HerculesParser.VAR;
}

"return" {
	return HerculesParser.RETURN;
}

"fun" {
	return HerculesParser.FUN;
}

{NAME} {
	yyHerculesParser.yylval = new HerculesParserVal(yytext());
	return HerculesParser.NAME;
}

";;;".*$ {
}

"{;;;" {
	yybegin(COMMENT); nesting++;
}

<COMMENT>"{;;;" {
	nesting++;
}

<COMMENT>";;;}" {
	if( --nesting==0 )
	{
		yybegin(YYINITIAL);
	}
}

<COMMENT>.|\n {
	/* ignore */
}
[ \t\r\n\f] {
}

. {
	return HerculesParser.YYERRCODE;
}
