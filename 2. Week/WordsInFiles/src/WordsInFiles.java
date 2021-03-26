
//Words in Files program determine which words occur in the greatest number of files,
// and for each word, which files they occur in.

import edu.duke.*;
import java.util.*;
import java.lang.*;
import java.io.*;


public class WordsInFiles {

    private HashMap<String,ArrayList<String>> mapWord;


    public WordsInFiles(){
        mapWord = new HashMap<String, ArrayList<String>>();
    }

    /*This method should add all the words from
    file into the map.If a word is not in the map, method create
    a new ArrayList of type String with this word, and have the word map
    to this ArrayList.*/

    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        String name = f.getName();
        for (String word : fr.words())
            if (mapWord.containsKey(word)) {
                ArrayList<String> fileName = mapWord.get(word);
                if (!fileName.contains(name)) {
                    fileName.add(name);
                    mapWord.put(word, fileName);
                }
            } else {
                ArrayList<String> fileName = new ArrayList<String>();
                fileName.add(f.getName());
                mapWord.put(word, fileName);
            }
    }

    /*This method first clears the map, and then uses a DirectoryResource to select a group of files.
    For each file, it puts all of its words into the map by calling the method addWordsFromFile.*/

    public void buildWordFileMap() {
        mapWord.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }

    //This method returns the maximum number of files any word appears in, considering all words from a group of files.

    public int maxNumber() {
        int max = 0;
        for (String word : mapWord.keySet()){
            ArrayList<String> currentFiles = mapWord.get(word);
            int currentNum = currentFiles.size();
            if (currentNum > max) {
                max = currentNum;
            }
        }
        return max;
    }


    //This method returns an ArrayList of words that appear in exactly number files.

    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> WordList = new ArrayList<String>();
        for (String word : mapWord.keySet()) {
            ArrayList<String> currentFiles = mapWord.get(word);
            int currentNum = currentFiles.size();
            if (currentNum == number) {
                WordList.add(word);
            }
        }
        return WordList;
    }

    //This method prints the names of the files this word appears in, one filename per line.

    private void printFilesIn(String word){
        ArrayList<String> fileNames = mapWord.get(word);
        for (String name : fileNames)
            System.out.println(name);

    }



    public void test(){
        buildWordFileMap();
        int maximum = maxNumber();
        System.out.println("Max number of files where occur the same word = " + maximum);
        ArrayList<String> listWord = wordsInNumFiles(maximum);
        for (String word : listWord) {
            System.out.println("The most common word in many files is: '"+ word + "' which occur in files:");
            printFilesIn(word);
            System.out.println();
        }
        // Add method to write number of chose files!!!!
        int winf = 2; //Write number of words which occur in files
        System.out.println("Total words in  files = " + wordsInNumFiles(winf).size());
        System.out.println();
        String wf = "funny"; //Write what word you are looking for
        System.out.println("The file or files where occur the word: '"+ wf+"' is/are: ");
        printFilesIn(wf);
    }




    public static void main(String[] args) {
        WordsInFiles wif = new WordsInFiles();
        wif.test();

    }
}
