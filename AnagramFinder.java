import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.logging.Level;

class AnagramFinder {

  private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  public AnagramFinder(String[] args) {
    if (args.length > 0) {
      AnagramFinder.play(args[0]);
    } else {
      logger.log(Level.INFO, "Error: argument not included. Please provide a text file. \n");
      System.exit(1);
    }
  }

  public static HashMap<String, String> mapByAnagram(Set<String> mySet) {
    HashMap<String, String> hashByLetters = new HashMap<>();
    for (String word : mySet) {
      hashByLetters.put(word, alphabetize(word));
    }
    return hashByLetters;
  }

  public static String alphabetize(String word) {
    char[] charArray = word.toCharArray();
    Arrays.sort(charArray);
    return new String(charArray);
  }

  public static HashMap<String, Integer> readDictionary(String fromPath) { // Fuck String, String
    HashMap<String, Integer> myDictionary = new HashMap<String, Integer>();

    try {
      File myFile = new File(fromPath);
      Scanner myReader = new Scanner(myFile);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        data = data.toLowerCase();
        myDictionary.put(data, data.length());
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      logger.log(Level.INFO, "Error: File not found.");
      e.printStackTrace();
    }
    return myDictionary;
  }
  public static HashMap<String, Integer> DELETE_THIS_FUNCTION (String fromPath) { // Fuck String, String
    HashMap<String, Integer> myDictionary = new HashMap<String, Integer>();

    try {
      File myFile = new File(fromPath);
      Scanner myReader = new Scanner(myFile);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        data = data.toLowerCase();
        myDictionary.put(data, data.length());
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      logger.log(Level.INFO, "Error: File not found.");
      e.printStackTrace();
    }
    return myDictionary;
  }

  public static <K, V> Set<K> getKeys(Map<K, V> map, V value) {
    Set<K> keys = new HashSet<>();
    for (Map.Entry<K, V> entry : map.entrySet()) {
      if (value.equals(entry.getValue())) {
        keys.add(entry.getKey());
      }
    }
    return keys;
  }

  public static Set<String> lookup(String word, HashMap<String, Integer> mydict) {
    Set<String> subDictionary = getKeys(mydict, word.length());
    HashMap<String, String> subHash = mapByAnagram(subDictionary);
    Set<String> result = lookupAnagram(word, subHash);
    return result;
  }

  public static Set<String> lookupAnagram(String word, HashMap<String, String> mydict) {
    Set<String> setOfAnagrams = getKeys(mydict, alphabetize(word));
    return setOfAnagrams;
  }

  public static void play(String txtPath) {

    System.out.println("\nWelcome to Anagram Finder");
    System.out.println("---------------------------");

    long start = System.nanoTime();
    HashMap<String, Integer> myDictionary = readDictionary(txtPath);
    long finish = System.nanoTime();
    long timeElapsed = (finish - start) / 1000000;
    System.out.println("Dictionary loaded in " + timeElapsed + " ms\n");

    Scanner console = new Scanner(System.in);
    boolean running = true;

    while (running) {
      System.out.print("AnagramFinder>");
      String myWord = console.nextLine();
      myWord = myWord.toLowerCase();
      if (myWord.equals("exit")) {
        running = false;
        console.close();
        break;
      } else {
        System.out.println(lookup(myWord, myDictionary));
      }
    }
  }

  public static void main(String[] args) {
    if (args.length > 0) {
      System.out.println("Shell Detected->");
      AnagramFinder af = new AnagramFinder(args);
    } else {
      System.out.println("Console Detected->");
      String[] systemArgugments = new String[1];
      systemArgugments[0] = "dictionary.txt";
      AnagramFinder af = new AnagramFinder(systemArgugments);
    }
  }
}