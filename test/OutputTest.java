import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Inherit tests from this class if you need to test what is printed to standard out.
 */
public class OutputTest {
    OutputStream output;

    @Before
    public void setup() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @After
    public void teardown() {
        System.setOut(null);
    }
}
