import static org.junit.Assert.*;

import org.junit.Test;

public class HelloTest extends OutputTest {

    @Test
    public void testMain() throws Exception {
        Hello.main(new String[0]);

        assertEquals("Hello, world.\n", output.toString());
    }
}