parser:
	java -jar lib/java-cup-11a.jar -interface -parser Parser parser.cup
	mv Parser.java src/Parser.java
	mv sym.java src/sym.java
