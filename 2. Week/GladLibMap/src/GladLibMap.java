//This program modifies GladLib to use one HashMap that maps word types to ArrayList of possible words to select.

import edu.duke.*;
import java.util.*;



public class GladLibMap {
    private HashMap<String,ArrayList<String>> myMap;
    private ArrayList<String> usedList;
    private ArrayList<String> usedCategories;
    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMap() {
        myMap = new HashMap<String,ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    //This method create constructor the initialize the instance variable
    //Method initializeFromSource load content from the text file stored in source directory

    public GladLibMap(String source) {
        myMap = new HashMap<String,ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color", "country",
                "name", "animal","verb", "timeframe","fruit"};

        for (String category : categories) {
            String fileName = source +"/"+category+".txt";
            ArrayList<String> list = readIt(fileName);
            myMap.put(category, list);
        }
        usedList = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
    }

    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    //This method paste words from HashMap to tags use in text file.
    //For example tag country is replace by word from file country.txt

    private String getSubstitute(String label) {
        if (!usedCategories.contains(label))
            usedCategories.add(label);
        if (label.equals("number")){
            return "" + myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
    }

    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = " ";
        do {
            sub = getSubstitute(w.substring(first+1,last));
        }
        while(usedList.contains(sub));
        usedList.add(sub);
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print( w + " ");
            charsWritten += w.length() + 1;
        }
    }

    //This method replace tags to new labels  from Url or text file

    private String fromTemplate(String source) {
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    //Method read data from text file or URL and store in ArrayList

    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    //This method returns the total number of words in all the ArrayLists in the HashMap.

    private void totalWordsInMap() {
        int total = 0;
        for(String s : myMap.keySet()){
            int count = myMap.get(s).size();
            total+=count;
        }
        System.out.println("Total numbers of words in map: " + total);
    }

    //This method returns the total number of words in the ArrayLists of the categories that
    // were used for a particular GladLib.

    private void totalWordsConsidered() {
        int totalConsidered = 0;
        System.out.println(myMap.size()+ " Map");
        System.out.println(usedCategories.size()+ " List");

        for(String label : usedCategories) {
            if (myMap.containsKey(label)){
                int count = myMap.get(label).size();
                totalConsidered += count;
                System.out.print( label + " , ");
            }
        }
        System.out.println("Total numbers of words considered in map: " + totalConsidered);

    }
    public void makeStory() {
        String story = fromTemplate("data/madtemplate3.txt");
        printOut(story, 60);
        System.out.println("\nNumbers of replaced words: " + (usedList.size()));
        totalWordsInMap();
        totalWordsConsidered();
    }

    public static void main(String[] args) {
        GladLibMap glm = new GladLibMap();
        glm.makeStory();
    }


}