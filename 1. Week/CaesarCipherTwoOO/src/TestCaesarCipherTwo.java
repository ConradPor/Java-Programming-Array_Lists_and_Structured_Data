import edu.duke.FileResource;

public class TestCaesarCipherTwo {

    //This method count letters in the String.
    private int[] countLetters(String message) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i = 0; i < message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int inx = alphabet.indexOf(ch);
            if (inx != -1) {
                counts[inx] += 1;
            }
        }
        return counts;
    }

    //This method calculate the index of the largest letter frequency.
    private int maxIndex(int[] values){
        int max = 0;
        for(int i = 0; i < values.length; i++){
            if(values[i] > values[max]){
                max = i;
            }
        }
        return max;
    }

    /*The method halfOfString that has two parameters, a String parameter named message
    and an int parameter named start. This method should return a new String that is every other character
    from message starting with the start position.*/
    private String halfOfString(String message, int start) {
        StringBuilder half = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            char a = message.charAt(i);
            half.append(a);
        }
        return half.toString();
    }
    /*Method get key call countLetters to get an array of the letter frequencies in String s and then use
    maxIndex to calculate the index of the largest letter frequency, which is the location of the encrypted letter ‘e’,
    which leads to the key, which is returned.*/
    private int getKey(String input) {
        int[] freqs = countLetters(input);
        int maxIn = maxIndex(freqs);
        int dkey = maxIn - 4;
        if (maxIn < 4) {
            dkey = 26 - (4 - maxIn);
        }
        return dkey;
    }

    /*This method call halfOfString, getKey and decrypt method from CaesarCipherTwo class
    and print two keys and decrypted message.*/
    private String breakCaesarCipher(String input) {

        String s1 = halfOfString(input, 0);
        String s2 = halfOfString(input, 1);
        int key1 = getKey(s1);
        int key2 = getKey(s2);
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        String message = cc.decrypt(input);
        System.out.println(key1 + "\t" + key2);
        System.out.println(message);
        return message;
    }

    public static void main(String [] args ) {
        TestCaesarCipherTwo cc = new TestCaesarCipherTwo();
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherTwo c = new CaesarCipherTwo(17,3);
        String encrypted = c.encrypt(message);
        System.out.println(encrypted);
        //System.out.println(c.decrypt(encrypted));
        //cc.breakCaesarCipher(message);

    }
}
