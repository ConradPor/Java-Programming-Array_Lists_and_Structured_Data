import edu.duke.FileResource;

public class CaesarBreaker {



    //This method count letters in the String
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


    //This method calculate the index of the largest letter frequency
    public static int maxIndex(int[] vals) {
        int max = 0;
        for (int i = 0; i < vals.length; i++) {
            if (vals[i] > vals[max]) {
                max = i;
            }
        }
        return max;
    }

    /*The method decryptTwoKeys has one parameter, a String parameter named encrypted that represents
    a String that was encrypted with the two key. This method attempts to determine the two keys used
    to encrypt the message, prints the two keys, and then returns the decrypted
    String with those two keys.*/

    public String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int dkey1 = getKey(halfOfString(encrypted, 0));
        int dkey2 = getKey(halfOfString(encrypted, 1));
        System.out.println(dkey1 + "\t" + dkey2);
        String message = cc.encryptTwoKeys(encrypted, 26 - dkey1, 26 - dkey2);
        return message;
    }

    public static String decrypt(String encrypted, int key) {

        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt(encrypted, 26-key);
        return message;
    }

    /*The method halfOfString that has two parameters, a String parameter named message
    and an int parameter named start. This method should return a new String that is every other character
    from message starting with the start position.*/

    public String halfOfString(String message, int start) {
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

    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxIn = maxIndex(freqs);
        int dkey = maxIn - 4;
        if (maxIn < 4) {
            dkey = 26 - (4 - maxIn);
        }
        return dkey;
    }

    //Method checking code to decryption message.

    public void testDecrypt(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        int dke = getKey(message);
        String dec = decrypt(message, dke);
        System.out.println("Key is: "+ dke+ "\nDekrypted massage: "+ dec);
    }

    //Method checking code to decryption message with two keys.

    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        String dec = decryptTwoKeys(message);
        System.out.println("Dekrypted massage: "+ dec);

    }

    /*public static void main(String[] args) {
        CaesarBreaker cc = new CaesarBreaker();
        //cc.testDecrypt();
        cc.testDecryptTwoKeys();
    }*/
}
