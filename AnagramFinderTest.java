// package default;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

// import org.junit.jupiter.api.BeforeAll;

public class AnagramFinderTest {

    // @BeforeAll
    public void setUp() {
        // setup
    }

    @Test
    public void testAlphabatizeWord() {
        // Given
        String data = "exit";
        InputStream stdin = System.in; // Backup
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        String filename = "mockdict.txt";
        String[] arguments = new String[1];
        arguments[0] = filename;
        AnagramFinder finder = new AnagramFinder(arguments);
        Scanner scanner = new Scanner(System.in);

        // If
        String input = "word";
        String actual = finder.alphabetize(input);

        // Then
        String expected = "dorw";
        assertEquals(expected, actual);

        // Cleanup
        System.setIn(stdin);
    }
}
