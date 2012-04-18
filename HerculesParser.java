//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "Hercules.byaccj"
	import java.io.*;
	import java.util.*;
//#line 20 "HerculesParser.java"




public class HerculesParser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class HerculesParserVal is defined in HerculesParserVal.java


String   yytext;//user variable to return contextual strings
HerculesParserVal yyval; //used to return semantic vals from action routines
HerculesParserVal yylval;//the 'lval' (result) I got from yylex()
HerculesParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new HerculesParserVal[YYSTACKSIZE];
  yyval=new HerculesParserVal();
  yylval=new HerculesParserVal();
  valptr=-1;
}
void val_push(HerculesParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
HerculesParserVal val_pop()
{
  if (valptr<0)
    return new HerculesParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
HerculesParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new HerculesParserVal();
  return valstk[ptr];
}
final HerculesParserVal dup_yyval(HerculesParserVal val)
{
  HerculesParserVal dup = new HerculesParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short INT=257;
public final static short STRING=258;
public final static short CHAR=259;
public final static short OP=260;
public final static short DOUBLE=261;
public final static short NAME=262;
public final static short DIGIT=263;
public final static short FUN=264;
public final static short BODY=265;
public final static short IF=266;
public final static short ELSE=267;
public final static short ELSEIF=268;
public final static short WHILE=269;
public final static short VAR=270;
public final static short RETURN=271;
public final static short AND=272;
public final static short OR=273;
public final static short NOT=274;
public final static short TRUE=275;
public final static short FALSE=276;
public final static short NULL=277;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    2,    0,    1,    1,    4,    4,    5,    6,    5,    7,
    8,    3,    9,    9,   11,   10,   10,   12,   12,   12,
   13,   13,   14,   14,   15,   15,   18,   16,   16,   17,
   20,   17,   17,   17,   17,   22,   24,   17,   17,   21,
   25,   26,   27,   23,   28,   23,   23,   19,   19,   29,
   29,   30,
};
final static short yylen[] = {                            2,
    0,    2,    2,    1,    1,    0,    1,    0,    4,    0,
    0,   10,    2,    0,    3,    3,    2,    3,    2,    1,
    3,    1,    3,    1,    2,    1,    0,    4,    1,    1,
    0,    5,    2,    1,    3,    0,    0,    6,    3,    3,
    0,    0,    0,    7,    0,    3,    0,    1,    0,    3,
    1,    1,
};
final static short yydefred[] = {                         1,
    0,    0,    0,    0,    4,   10,    3,    0,    0,    0,
    5,    0,   11,    0,    0,    9,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   24,    0,   29,   13,   15,    0,   33,   31,
    0,   36,    0,   19,   25,    0,   12,    0,    0,    0,
   27,    0,   18,    0,    0,   39,   35,   16,    0,   23,
    0,   52,    0,   48,    0,   37,    0,   28,   32,    0,
    0,   40,   50,   45,   41,   38,    0,    0,   46,   42,
    0,   43,    0,   44,
};
final static short yydgoto[] = {                          1,
    4,    2,    5,   10,   11,   12,    8,   15,   19,   29,
   20,   30,   31,   32,   33,   34,   35,   61,   63,   52,
   56,   54,   76,   71,   78,   81,   83,   77,   64,   65,
};
final static short yysindex[] = {                         0,
    0, -254,  -23, -254,    0,    0,    0, -252,    0,  -22,
    0,  -13,    0, -252,  -86,    0, -222, -252,  -40, -222,
   -8,   -6,  -12,  -40,  -40,  -40,  -24,  -40,  -73,   -5,
 -220, -217,    0, -204,    0,    0,    0,   19,    0,    0,
  -40,    0,  -66,    0,    0,   20,    0,  -40,  -24,  -24,
    0,  -40,    0,  -66,  -40,    0,    0,    0, -217,    0,
   -6,    0,   21,    0,   16,    0,  -62,    0,    0,  -40,
 -221,    0,    0,    0,    0,    0,  -66,  -40,    0,    0,
  -66,    0, -221,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,   64,    0,    0,    0,   24,   -9,    0,
    0,    0,    0,    0,    0,    0,  -39,    0,    0,  -39,
    0,    0,  -32,    0,    0,    0,    0,    0,    0,    0,
  -37,  -30,    0,  -26,    0,    0,    0,  -32,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -59,    0,    0,
    0,   26,    0,    0,    0,    0,    0,    0,  -20,    0,
    0,    0,    0,    0,   27,    0,    0,    0,    0,    0,
  -21,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -21,    0,
};
final static short yygindex[] = {                         0,
    0,    0,   66,    0,   22,    0,    0,    0,   51,  -42,
    0,   17,    0,   23,  -25,    0,  -17,    0,    0,    0,
  -51,    0,  -10,    0,    0,    0,    0,    0,    4,    0,
};
final static int YYTABLESIZE=263;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         28,
   14,   45,   66,   20,   39,   58,   20,    3,   30,    9,
   22,   30,   67,   22,   26,   28,    6,   26,   13,   47,
   21,   20,   47,   21,   60,   79,   30,   40,   22,   82,
   14,    7,   26,   28,    8,   16,   17,   47,   21,   21,
   42,   43,   44,   68,   46,   74,   75,   18,   41,    7,
   37,   47,   49,   48,   50,   51,   55,   53,   40,   70,
   57,   69,   72,    2,    6,   17,   49,   51,   62,    7,
   36,   59,   84,   73,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   20,   62,    0,    0,    0,
   30,    0,   22,    0,   80,    0,   26,    0,    0,    0,
    0,   47,   21,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   22,
   14,   23,   14,    0,    0,   24,   14,   30,   25,   14,
   26,   14,    0,   27,   14,   22,    0,   38,   47,   30,
   30,   24,   22,    0,   25,   26,   26,    0,    0,   27,
   47,   47,   21,   22,    0,   38,    0,    0,    0,   24,
    0,    0,   25,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   40,   27,   54,   41,   22,   48,   44,  262,   41,  262,
   41,   44,   55,   44,   41,   40,   40,   44,   41,   41,
   41,   59,   44,   44,   50,   77,   59,   40,   59,   81,
   44,   41,   59,   40,   44,   14,  123,   59,   59,   18,
   24,   25,   26,   61,   28,  267,  268,  270,   61,   59,
   59,  125,  273,   59,  272,  260,  123,   41,   40,   44,
   41,   41,  125,    0,   41,  125,   41,   41,   52,    4,
   20,   49,   83,   70,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  123,   70,   -1,   -1,   -1,
  123,   -1,  123,   -1,   78,   -1,  123,   -1,   -1,   -1,
   -1,  123,  123,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  260,
  260,  262,  262,   -1,   -1,  266,  266,  260,  269,  269,
  271,  271,   -1,  274,  274,  260,   -1,  262,  260,  272,
  273,  266,  273,   -1,  269,  272,  273,   -1,   -1,  274,
  272,  273,  273,  260,   -1,  262,   -1,   -1,   -1,  266,
   -1,   -1,  269,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=277;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'",null,null,"','",
null,null,null,null,null,null,null,null,null,null,null,null,null,null,"';'",
null,"'='",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"INT","STRING","CHAR","OP","DOUBLE","NAME",
"DIGIT","FUN","BODY","IF","ELSE","ELSEIF","WHILE","VAR","RETURN","AND","OR",
"NOT","TRUE","FALSE","NULL",
};
final static String yyrule[] = {
"$accept : start",
"$$1 :",
"start : $$1 program",
"program : program function",
"program : function",
"names : non_empty_names",
"names :",
"non_empty_names : NAME",
"$$2 :",
"non_empty_names : NAME $$2 ',' non_empty_names",
"$$3 :",
"$$4 :",
"function : NAME '(' $$3 names ')' $$4 '{' decls exprs '}'",
"decls : decl decls",
"decls :",
"decl : VAR non_empty_names ';'",
"exprs : expr ';' exprs",
"exprs : expr ';'",
"expr : NAME '=' expr",
"expr : RETURN expr",
"expr : or_expr",
"or_expr : or_expr OR and_expr",
"or_expr : and_expr",
"and_expr : and_expr AND not_expr",
"and_expr : not_expr",
"not_expr : NOT not_expr",
"not_expr : op_expr",
"$$5 :",
"op_expr : op_expr OP $$5 small_expr",
"op_expr : small_expr",
"small_expr : NAME",
"$$6 :",
"small_expr : NAME '(' $$6 args ')'",
"small_expr : OP small_expr",
"small_expr : NAME",
"small_expr : '(' expr ')'",
"$$7 :",
"$$8 :",
"small_expr : IF expr $$7 body $$8 ifrest",
"small_expr : WHILE expr body",
"body : '{' exprs '}'",
"$$9 :",
"$$10 :",
"$$11 :",
"ifrest : ELSEIF $$9 expr $$10 body $$11 ifrest",
"$$12 :",
"ifrest : ELSE $$12 body",
"ifrest :",
"args : non_empty_args",
"args :",
"non_empty_args : arg ',' non_empty_args",
"non_empty_args : arg",
"arg : expr",
};

//#line 120 "Hercules.byaccj"

	static private String name;
	private HerculesLexer lexer;
	private int varCount;
	private HashMap<String,Integer> varTable;
	private int tmpPos = -1;
	private int nextLabel = 1;
	private int argCount = 0;
	private int argPos = 0;
	private int elseLab = 0;
	private int endifLab = 0;
	
	static class Link
	{
		int val;
		Link next;
		public Link( int n, Link l )
		{
			val = n;
			next = l;
		}
	}
	Link stack = null;

	private int pushTemp()
	{
		return tmpPos--;
	}

	private void popTemp()
	{
		tmpPos++;
	}

	private int newLabel()
	{
		return nextLabel++;
	}

	private void push( int n )
	{
		stack = new Link(n,stack);
	}

	private int pop()
	{
		int res = stack.val;
		stack = stack.next;
		return res;
	}

	private void addVar( String name )
	{
		if( varTable.get(name) != null )
			yyerror("Variable already exists");
		varTable.put(name,varCount++);
	}

	private int findVar( String name )
	{
		Integer res = varTable.get(name);
		if( res == null )
			yyerror("Variable does not exist");
		return res;
	}

	private int yylex()
	{
		int yyl_return = -1;
		try
		{
			yylval = null;
			yyl_return = lexer.yylex();
			if( yylval==null )
				yylval = new HerculesParserVal(HerculesParser.yyname[yyl_return]);
			//System.out.println("Token: "+HerculesParser.yyname[yyl_return]+" Lexeme: "+yylval.sval);
		}
		catch (IOException e)
		{
			System.err.println("IO error: "+e);
		}
		return yyl_return;
	}

	public void yyerror( String error )
	{
		System.err.println("Error:  "+error);
		System.err.println("Line:   "+lexer.getLine());
		System.err.println("Column: "+lexer.getColumn());
		System.exit(1);
	}

	public HerculesParser( Reader r )
	{
		lexer = new HerculesLexer(r,this);
	}

	public static void main( String args[] )
	  	throws IOException
	{
		HerculesParser yyparser = new HerculesParser(new FileReader(args[0]));
		name = args[0].substring(0,args[0].lastIndexOf('.'));
		yyparser.yyparse();
	}

	public void emit( String s )
	{
		System.out.println(s);
	}
//#line 426 "HerculesParser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 14 "Hercules.byaccj"
{ emit("\""+name+".mexe\" = main in\n!{{"); }
break;
case 2:
//#line 16 "Hercules.byaccj"
{ emit("}}*BASIS;"); }
break;
case 7:
//#line 30 "Hercules.byaccj"
{addVar(val_peek(0).sval);}
break;
case 8:
//#line 31 "Hercules.byaccj"
{addVar(val_peek(0).sval);}
break;
case 10:
//#line 35 "Hercules.byaccj"
{varCount = 0;varTable=new HashMap<String,Integer>();}
break;
case 11:
//#line 36 "Hercules.byaccj"
{emit("#\"" + val_peek(4).sval + "[f" + varCount + "]\" = "); 
	emit("["); }
break;
case 12:
//#line 42 "Hercules.byaccj"
{emit("(Return)"); emit("];");}
break;
case 18:
//#line 60 "Hercules.byaccj"
{emit("(Store " + findVar(val_peek(2).sval) + ")");}
break;
case 27:
//#line 82 "Hercules.byaccj"
{}
break;
case 28:
//#line 82 "Hercules.byaccj"
{}
break;
case 30:
//#line 87 "Hercules.byaccj"
{emit("(Fetch " + findVar(val_peek(0).sval) + ")");}
break;
case 31:
//#line 88 "Hercules.byaccj"
{ push(argCount); argCount = 0;tmpPos--;  }
break;
case 32:
//#line 88 "Hercules.byaccj"
{ tmpPos++; emit("(Call #\"" + val_peek(4).sval + "[f" + argCount + "]\" " + tmpPos + ")"); argCount = pop(); }
break;
case 34:
//#line 90 "Hercules.byaccj"
{emit("(MakeVal " + val_peek(0).sval + ")");}
break;
case 36:
//#line 92 "Hercules.byaccj"
{push(elseLab);push(endifLab); elseLab = newLabel(); endifLab = newLabel(); emit("(GoFalse _" + elseLab + ")");}
break;
case 37:
//#line 93 "Hercules.byaccj"
{emit("(Go _" + endifLab + ")" );}
break;
case 38:
//#line 93 "Hercules.byaccj"
{emit("_" + endifLab +":"); endifLab = pop(); elseLab = pop(); }
break;
case 41:
//#line 102 "Hercules.byaccj"
{emit("_" + elseLab + ":" );}
break;
case 42:
//#line 102 "Hercules.byaccj"
{elseLab = newLabel(); emit("(GoFalse _" + elseLab + ")");}
break;
case 43:
//#line 102 "Hercules.byaccj"
{emit("(Go _" + endifLab + ")" );}
break;
case 45:
//#line 103 "Hercules.byaccj"
{emit("_" + elseLab + ":" );}
break;
case 47:
//#line 104 "Hercules.byaccj"
{emit("_" + elseLab + ":" );}
break;
case 52:
//#line 115 "Hercules.byaccj"
{emit("(StoreArgAcc " + (tmpPos+1) + " " + argCount + ")"); argCount++; }
break;
//#line 668 "HerculesParser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public HerculesParser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public HerculesParser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
