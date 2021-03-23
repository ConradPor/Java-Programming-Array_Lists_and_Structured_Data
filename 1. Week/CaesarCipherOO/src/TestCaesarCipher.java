import edu.duke.FileResource;

public class TestCaesarCipher {

    //This method count letters in the String.
    public static int[] countLetters(String message) {
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
    private int maxIndex(int[] values) {
        int max = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[max]) {
                max = i;
            }
        }
        return max;
    }

    //This method call maxIndex and decrypt method from caesar cipher class and print decrypted key and massage.

    public void breakCaesarCipher(String input) {
        int [] freqs = countLetters(input);
        int maxIn = maxIndex(freqs);
        int dkey = maxIn - 4;
            if (maxIn < 4) {
                dkey = 26 - (4 - maxIn);
            }
            CaesarCipher cc = new CaesarCipher(dkey);
            String message = cc.decrypt(input);
            System.out.println(dkey);
            System.out.println(message);
            //return message;
    }

    public static void main (String [] args) {

        /*CaesarCipher cc = new CaesarCipher(14);
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = cc.encrypt(message);
        System.out.println("The encryption result is "+ encrypted);*/
        TestCaesarCipher tc = new TestCaesarCipher();
        FileResource fr = new FileResource();
        String message = fr.asString();
        tc.breakCaesarCipher(message);
    }
}
