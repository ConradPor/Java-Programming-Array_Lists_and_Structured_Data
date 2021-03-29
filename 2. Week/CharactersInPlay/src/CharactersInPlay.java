import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {

    private ArrayList<String> characters;
    private ArrayList<Integer> parts;

    public CharactersInPlay() {
        this.characters = new ArrayList<String>();
        this.parts = new ArrayList<Integer>();
    }
    
    //This method should update the two ArrayLists, adding the character’s name if it is not already there,
    //and counting this line as one speaking part for this person.

    public void update(String name) {
        String subString = "";
        int endLine = name.indexOf(".");
        subString = name.substring(0, endLine);
        subString =subString.trim();
        int index = characters.indexOf(subString);
        if (index == -1) {
            characters.add(subString);
            parts.add(1);
        } else {
            int value = parts.get(index);
            parts.set(index, value + 1);
        }

    }

    // This method clear the appropriate instance variables before each new file, opens a file, and reads the file line-by-line.
    // For each line, if there is a period on the line, extract the possible name of the speaking part,
    // and call update to count it as an occurrence for this person.

    public void findAllCharacters() {
        this.characters.clear();
        this.parts.clear();
        FileResource resource = new FileResource();

        for (String line : resource.lines()) {
            if (line.contains(".")) {
                update(line);
                }
            }
        }

    //This method print out the names of all those characters that have exactly number speaking parts,
    // where number is greater than or equal to num1 and less than or equal to num2.

    public void charactersWithNumParts(int num1, int num2) {
        System.out.println("Characters who speaks between " + num1 + " and " + num2 + " are: ");
        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i) >= num1 && parts.get(i) <= num2)
                System.out.println (characters.get(i) + " who speaks in " + parts.get(i) + " parts.");
        }
    }

    public void tester() {
        findAllCharacters();
        charactersWithNumParts(8, 18);
        int indOfCharacter = 0;
        for (int i = 0; i < characters.size(); i++) {
            if (parts.get(i) > parts.get(indOfCharacter)) {
                indOfCharacter = i;
            }
        }
        System.out.println("The most speaking character is: " + characters.get(indOfCharacter) + " who speak in " + parts.get(indOfCharacter) + " parts.");
    }

    public static void main (String [] args) {
        CharactersInPlay cip = new CharactersInPlay();
        cip.tester();
    }
}
