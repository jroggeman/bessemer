parser:
	java -jar lib/java-cup-11a.jar -interface -symbols Symbols -parser Parser parser.cup
	mv Parser.java src/com/Parser.java
	mv Symbols.java src/com/Symbols.java
	jflex lexer.flex
	mv Lexer.java src/com/Lexer.java
