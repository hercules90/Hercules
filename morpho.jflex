/*
 *  Beinagrind fyrir lesgreini.
 *
 *  H�fundur: Snorri Agnarsson
 *
 *  Athugi� a� h�r er veri� a� nota Java en ekki C e�a C++.
 *  �essa skr� m� ���a og keyra me� eftirfarandi skipunum:
 *     java -jar JFlex.jar morpho.jflex
 *     javac MyLexer.java
 *     java MyLexer < inntak
 */

%%

%public
%class MyLexer
%byaccj

%unicode

%{
public static final int
	OP = 1,
	NAME = 2,
	INT = 3,
	ERROR = -1;

public static String theString;

public static void main( String[] args ) throws Exception
{
	MyLexer l = new MyLexer(new java.io.InputStreamReader(System.in));
	while(true)
	{
		int tok = l.yylex();
		System.out.println(""+tok+": "+theString);
		if( tok==0 ) break;
	}
}

%}

  /* Reglulegar skilgreiningar */

OPCHAR = [\-!%&/=?~\^+*:<>|]
ALPHA = [a-zA-Z��������������������]
DIGIT = [0-9]
NAME = {ALPHA}({ALPHA}|{DIGIT}|_)*
INT = {DIGIT}+
OP = {OPCHAR}+

NEWLINE	=	\r|\n|\r\n
WHITESPACE = [\n\r\ \t\b\012]

%%

  /* JFlex/Flex/Lex reglur -- athugi� a� h�r vantar miki� � til
   * a� �etta n�lgist a� vera lexgreinir fyrir Morpho
   */

{OP} {
	theString = yytext();
	return(OP);
}

{NAME} {
	theString = yytext();
	return(NAME);
}

{INT} {
	theString = yytext();
	return(INT);
}

{WHITESPACE} {
	// Ignore whitespace
}

. {
	theString = yytext();
	return(ERROR);
}
