import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;

class Anagram{
  
  public Anagram(String[] args){
    if(args.length > 0) {
      //System.out.print("Dictionary: "+args[0]);
      this.play(args[0]);
    } else{
      System.out.println("Error: dictionary not included. Please provide a text file. \n");
      System.exit(1);
    }
    
  } //constructor
  
  public static HashMap<String,Integer> sortByLength(String data, HashMap<String, Integer> sorted){
    HashMap<String, Integer> hm = sorted;
    alphabetize(data);
    // System.out.println("data: "+data+" length: "+data.length());
    hm.put(data, data.length());
    return hm;
  }
  
  public static HashMap<String,String> sortByAnagram(String data, HashMap<String, String> sorted){
    HashMap<String, String> hm = sorted;
    hm.put(data, alphabetize(data));
    return hm;
  }
  
  public static String alphabetize(String word){
    char[] charArray = word.toCharArray();
    Arrays.sort(charArray);
    String sortedString = new String(charArray);
    // System.out.println(sortedString);  
    return sortedString;
  }
  
  public static HashMap<String, Integer> getDictionary(String fromPath){
    HashMap<String, Integer> dictionaryMappedByLength = new HashMap<>();
    int count = 0; 
    
    try {
        File myFile = new File(fromPath);
        Scanner myReader = new Scanner(myFile);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          dictionaryMappedByLength = sortByLength(data, dictionaryMappedByLength);
          count++;
        }
        myReader.close();
      } catch (FileNotFoundException e) {
        System.out.println("An error occurred: File not found.");
        e.printStackTrace();
      } 
      System.out.println("File has "+count+" lines");
      return dictionaryMappedByLength;
  }
  
  public static String ask(String question, Scanner fromConsole){
    System.out.print(question);
    String answer = fromConsole.nextLine();
    return answer;
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
  public static Set<String> lookup(String input, HashMap<String, Integer> mydict){
    Set<String> subDictionary = getKeys(mydict, input.length());
    HashMap<String,String> mapByLettersOfAnagram = new HashMap<>();
    
    for (String word : subDictionary) {
      mapByLettersOfAnagram = sortByAnagram(word, mapByLettersOfAnagram);  
    }
    
    Set<String> setOfAnagrams = getKeys(mapByLettersOfAnagram, alphabetize(input));
    return setOfAnagrams;
  }
  
  public static void play(String txtPath){
    System.out.println("\nWelcome to Anagram Finder");
    System.out.println("---------------------------");
    
    long start = System.nanoTime();
    HashMap<String, Integer> myDictionary = getDictionary(txtPath);
    long finish = System.nanoTime();
    long timeElapsed = (finish - start) / 1000000;
    System.out.println("Dictionary loaded in "+timeElapsed+" ms\n");
    
    Scanner console = new Scanner(System.in);
    boolean running = true;
    
    while(running){
      String myWord = ask("AnagramFinder>", console);
      if(myWord.equals("exit")){
        running = false; 
        console.close();
        break;
      }else{
        long begin = System.nanoTime();
        System.out.println(lookup(myWord, myDictionary));
        long end = System.nanoTime();
        long timed = (end - begin) / 1000000;
        System.out.println("Anagrams loaded in "+timed+" ms");
      }
    } 
  }
  public static void main(String[] args){
    Anagram a = new Anagram(args);
  }
}