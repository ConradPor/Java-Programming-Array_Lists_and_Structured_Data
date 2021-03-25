//This program creates a story by replacing placeholder words such as <noun> by
//looking for a random word of that type.

import edu.duke.*;
import java.util.ArrayList;
import java.util.Random;

public class GladLib {

    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    private ArrayList<String> wordList;
    private int wordListCount = 0;
    private Random myRandom;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    //This method create constructor the initialize the instance variable
    //Method initializeFromSource load content from the text file stored in source directory

    public GladLib(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLib(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        adjectiveList= readIt(source+"/adjective.txt");
        nounList = readIt(source+"/noun.txt");
        colorList = readIt(source+"/color.txt");
        countryList = readIt(source+"/country.txt");
        nameList = readIt(source+"/name.txt");
        animalList = readIt(source+"/animal.txt");
        timeList = readIt(source+"/timeframe.txt");
        verbList = readIt(source+"/verb.txt");
        fruitList = readIt(source+"/fruit.txt");
        wordList = new ArrayList<>();

    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    //This method paste words from ArrayList to tags use in text file.
    //For example tag country is replace by word from file country.txt

    private String getSubstitute(String label) {
        if (label.equals("country")) {
            return randomFrom(countryList);
        }
        if (label.equals("color")){
            return randomFrom(colorList);
        }
        if (label.equals("noun")){
            return randomFrom(nounList);
        }
        if (label.equals("name")){
            return randomFrom(nameList);
        }
        if (label.equals("adjective")){
            return randomFrom(adjectiveList);
        }
        if (label.equals("animal")){
            return randomFrom(animalList);
        }
        if (label.equals("timeframe")){
            return randomFrom(timeList);
        }
        if (label.equals("verb")){
            return randomFrom(verbList);
        }
        if (label.equals("fruit")){
            return randomFrom(fruitList);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
    }

    //This method find index of "<" and ">" and tags uses in text file.
    //Later call getSubstitute method to find a substitute for the tag inside the brackets.

    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub;

        while(true) {
            sub = getSubstitute(w.substring(first+1,last));
            int findIndex = wordList.indexOf(sub);
            if (findIndex == -1) {
                wordList.add(sub);
                wordListCount +=1;
                break;
            }
        }

        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w +" ");
            charsWritten += w.length() + 1;
        }
    }

    //This method replace tags to new labels  from Url or text file

    private String fromTemplate(String source){
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

    //Method read data from text file or URL and store in ArrayLis

    private ArrayList<String> readIt(String source){
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

    public void makeStory() {
        wordList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        //String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 90);
        System.out.println("\nNumber of replace words: " + wordListCount);
    }

    public static void main(String[] args) {
        GladLib gl = new GladLib();
        gl.makeStory();
    }
}