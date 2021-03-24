import java.util.*;
import edu.duke.*;

public class WordFrequencies {

    private ArrayList<String>myWords;
    private ArrayList<Integer>myFreqs;

    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    //This method calculate how many words occur in the file.

    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        for (String s : resource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index, freq+1);
            }
        }
    }

    //This method returns an int that is the index location of the largest value in myFreqs.
    // If there is a tie, then return the first such value.
    
    public int findIndexOfMax() {
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for(int k = 0; k < myFreqs.size(); k++) {
            if (myFreqs.get(k) > max) {
                max = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }

    public void tester() {
        findUnique();
        System.out.println("This file has: "+myWords.size()+ " of unique words.");
        int index = findIndexOfMax();
        System.out.println("The most popular word is '"+myWords.get(index) +"' which occurs "+myFreqs.get(index) + " times.");
    }

    public static void main (String [] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }
}
