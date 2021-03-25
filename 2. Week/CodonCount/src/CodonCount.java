import edu.duke.*;
import java.util.*;

public class CodonCount {

    private HashMap<String,Integer> map;

    public CodonCount() {
        map = new HashMap<String,Integer>();
    }

    //This method build a new map of codons mapped to their counts from the string dna
    // with the reading frame with the position start (a value of 0, 1, or 2).

    private void buildCodonMap(int start, String dna) {
        for (int i= start; i < dna.length(); i+=3){
            if (i+3 <= dna.length()) {
                String codon = dna.substring(i, i+3);
                if (!map.containsKey(codon)) {
                    map.put(codon, 1);
                }
                else {
                    map.put(codon,map.get(codon) + 1);
                }
            }
        }
    }

    // This method returns a String, the codon in a reading frame that has the largest count.
    // If there are several such codons, return any one of them.

    private String getMostCommonCodon() {
        String mostCommonCodon = " ";
        if (map.size() == 0 )
            return "EMPTY MAP!";
        else{
            int max = 0;
            for(String s : map.keySet()) {
                int value = map.get(s);
                if (value > max) {
                    max = value;
                    mostCommonCodon = s;
                }
            }
        }
        return mostCommonCodon;
    }

    //This method prints all the codons in the HashMap along with their counts
    // if their count is between start and end, inclusive.

    private void printCodonCounts(int start, int end) {
        System.out.println("Counts of codons between " + start + " and " + end  + " " +  "inclusive are: ");
        for(String codon : map.keySet()) {
            int value = map.get(codon);
            if (value >= start && value <= end) {
                System.out.println(codon + " " + value);
            }
        }
    }

    /*Tester method reed a text file with DNA. If file contains a lower case, convert them to upper.
    *Then for each of the three possible reading frames, this method builds a HashMap of codons to their number
    * of occurrences in the DNA strand, prints the total number of unique codons in the reading frame,
    * prints the most common codon and its count, and prints the codons and their number of occurrences
    * for those codons whose number of occurrences in this reading frame are between two numbers inclusive.
     */

    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.toUpperCase().trim();
        for (int i=0; i < 3; i++) {
            map.clear();
            buildCodonMap(i, dna);
            System.out.println("Reading frame starting with " + i + " result in " + map.size()+" unique codons");
            String commonCodon = getMostCommonCodon();
            System.out.println("The most common codon is :" + commonCodon + " with count " + map.get(commonCodon));
            printCodonCounts(1, 5);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CodonCount cc = new CodonCount();
        cc.tester();
    }

}
