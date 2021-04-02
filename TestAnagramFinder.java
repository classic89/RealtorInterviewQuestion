import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Paths;

class TestAnagramFinder{
  @Test
  public void testReadDictionary() {
      //Given  
    String data = "exit";
    InputStream stdin = System.in; //Backup
    System.setIn(new ByteArrayInputStream(data.getBytes()));

    // If
    String filename = "mockdict.txt";
    /* Content of mockdict.txt
    cinema
    least
    alerts
    dog
    cat
    act
    god
    SKATE
    */

    String[] arguments = new String[1];
    arguments[0] = filename;
    AnagramFinder finder = new AnagramFinder(arguments);
    HashMap<String, Integer> actual = finder.readDictionary(filename);

    // Then
    HashMap<String, Integer> expected = new HashMap<String, Integer>();
    expected.put("alerts", 6);
    expected.put("act", 3);
    expected.put("cat", 3);
    expected.put("least", 5);
    expected.put("cinema", 6);
    expected.put("dog", 3);
    expected.put("god", 3);
    expected.put("skate", 5); // testing capital letters
    assertEquals(expected, actual);

    // Cleanup
    System.setIn(stdin);
  }
  @Test
  public void testAlphabetizeUpperCase() {
    //Given  
    String data = "exit";
    InputStream stdin = System.in; //Backup
    System.setIn(new ByteArrayInputStream(data.getBytes()));

    String filename = "mockdict.txt";
    String[] arguments = new String[1];
    arguments[0] = filename;
    AnagramFinder finder = new AnagramFinder(arguments);


    // If
    String input = "WORD";
    String actual = finder.alphabetize(input);

    // Then
    String expected = "DORW";
    assertEquals(expected, actual);

    // Cleanup
    System.setIn(stdin);
  }
  @Test
  public void testAlphabatizeWord() {
    //Given  
    String data = "exit";
    InputStream stdin = System.in; //Backup
    System.setIn(new ByteArrayInputStream(data.getBytes()));

    String filename = "mockdict.txt";
    String[] arguments = new String[1];
    arguments[0] = filename;
    AnagramFinder finder = new AnagramFinder(arguments);


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
