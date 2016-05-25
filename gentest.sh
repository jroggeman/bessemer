PASS_DIR=test/resources/pass
FAIL_DIR=test/resources/fail

CLASSPATH=.:lib/commons-cli-1.3.1.jar:lib/java-cup-11a.jar:build/classes/main

#!/bin/bash
for testfile in $(ls $PASS_DIR/*.c); do
    testfile=$(basename "$testfile")
    outputName="${testfile%.*}"
    echo "Saving $testfile to $outputName.ser..."
    java -cp $CLASSPATH com.Compiler -astOnly -astFile $PASS_DIR/${outputName}.ser $PASS_DIR/${testfile}
done

for testfile in $(ls $FAIL_DIR/*.c); do
    testfile=$(basename "$testfile")
    outputName="${testfile%.*}"
    echo "Saving $testfile to $outputName.ser..."
    java -cp $CLASSPATH com.Compiler -astOnly -astFile $FAIL_DIR/${outputName}.ser $FAIL_DIR/${testfile}
done
