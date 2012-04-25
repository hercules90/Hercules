all: HerculesLexer.class HerculesParser.class HerculesParserVal.class

test: all test.test
	java HerculesParser test.test > test.masm
	morpho -c test.masm
	morpho test

HerculesLexer.class HerculesParser.class HerculesParserVal.class: HerculesLexer.java HerculesParser.java HerculesParserVal.java
	javac HerculesLexer.java HerculesParser.java HerculesParserVal.java

HerculesLexer.java: Hercules.jflex
	java -jar JFlex.jar Hercules.jflex

HerculesParser.java HerculesParserVal.java: Hercules.byaccj
	./byacc -Jclass=HerculesParser Hercules.byaccj

clean:
	rm -rf *.class *~ *.java *.bak yacc.*
