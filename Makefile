parser:
	java -jar lib/java-cup-11a.jar -interface -parser Parser parser.cup
	mv Parser.java src/com/Parser.java
	mv sym.java src/com/sym.java
	jflex lexer.flex
	mv Lexer.java src/com/Lexer.java
