import java.util.*;
import edu.duke.*;

// This class implements a Vigenère Breaker.

public class VigenereBreaker {


    //Method check input parameters and return slicing string
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slicing = new StringBuilder(message);
        String result = new String();
        for(int i = whichSlice; i < slicing.length(); i += totalSlices) {
            result += slicing.charAt(i);
        }
        return result;
    }

    //This method take most common appearing character from breakForLanguage method, put in Caesar Cracker method, and
    //return key of breaker message.
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        CaesarCracker CaesarCracker = new CaesarCracker(mostCommon);
        int countKey;
        int[] key = new int[klength];
        for(int i =0; i<klength; i++) {
            countKey = CaesarCracker.getKey(sliceString(encrypted, i, klength));
            key[i] = countKey;
        }
        return key;

    }

    //This method read dictionaries and add to hashset.
    public HashSet<String> readDictionary (FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();
        for (String word : fr.words())
            dictionary.add(word.toLowerCase());
        return dictionary;
    }

    //This method count words contain in dictionaries
    public int countWords(String message, HashSet<String> dict) {
        int counts = 0;
        String[] wordList = message.split("\\W+");
        for (String word : wordList) {
            if (dict.contains(word.toLowerCase())) {
                counts+=1;
            }
        }
        return counts;
    }

    //This method try to find the most common character in the words.
    public String breakForLanguage (String encrypted, HashSet<String> dictionary) {
        int maxKeyLength = 100;
        String largDecrypt = "";
        String message = "";
        int bestCount = 0;
        char mostCommon = mostCommonCharIn(dictionary);
        for (int klength = 1; klength < maxKeyLength; klength++) {
            int[] key = tryKeyLength(encrypted, klength, mostCommon);
            VigenereCipher vc  = new VigenereCipher(key) ;
            message = vc.decrypt(encrypted);
            int count = countWords(message, dictionary);
            if (count > bestCount) {
                bestCount = count;
                largDecrypt = message;
            }
        }
        return largDecrypt;
    }

    private char maxValueKey (HashMap<Character, Integer> map) {
        char maxKey = ' ';
        int maxValue = -1;
        for (char c : map.keySet()) {
            if (map.get(c) > map.getOrDefault(maxKey, 0)) {
                maxKey = c;
                maxValue = map.get(c);
            }
        }
        return maxKey;
    }

    // This method count frequency of Letters in a dictionary
    public char mostCommonCharIn (HashSet<String> dictionary) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        HashMap<Character, Integer> counts = new HashMap<Character, Integer>();
        for (String word : dictionary) {
            for (char c : word.toCharArray()) {
                if (alphabet.indexOf(c) != -1) {
                    counts.put(c, counts.getOrDefault(c, 0) + 1);
                }
            }
        }
        return maxValueKey(counts);
    }

    public String breakForAllLangs (String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxCount = 0;
        String useLanguage = "";
        String textDecrypt = "";
        for (String lang : languages.keySet()) {
            HashSet<String> dictionary = languages.get(lang);
            String decrypted = breakForLanguage(encrypted, dictionary);
            int count = countWords(decrypted, dictionary);
            if (count > maxCount) {
                maxCount = count;
                useLanguage = lang;
                textDecrypt = decrypted;
            }


        }
        System.out.println(maxCount);
       // System.out.println("Decryption message: " + textDecrypt);
        System.out.println("To encryption this message used '" + useLanguage + "' language.");
        return useLanguage;
    }

    private HashMap<String, HashSet<String>> loadDictionaries() {
        String[] langs = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        HashMap<String, HashSet<String>> dictionaries = new HashMap<String, HashSet<String>>();
        for (String lang : langs) {
            dictionaries.put(lang, readDictionary(new FileResource("dictionaries/"+lang)));
        }
        return dictionaries;
    }

    public void breakVigenere () {
        String encrypted = new FileResource().asString();
        HashMap<String, HashSet<String>> dictionaries = loadDictionaries();
        breakForAllLangs(encrypted, dictionaries);
    }

    public static void main(String[] args) {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
}

